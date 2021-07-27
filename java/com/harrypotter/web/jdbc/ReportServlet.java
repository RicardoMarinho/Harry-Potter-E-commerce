/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
/**
 *
 * @author ricardo.braz
 */

public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static OrderDetailsDbUtil orderDetailsDbUtil;
    private static OrderDbUtil orderDbUtil;
    private static AccountDbUtil accountDbUtil;

    @Resource(name = "jdbc/store")
    private DataSource dataSource;

    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        // create product in db util and pass in the conn pool
        try {
            orderDbUtil = new OrderDbUtil(dataSource);
            orderDetailsDbUtil = new OrderDetailsDbUtil(dataSource);
            accountDbUtil = new AccountDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, 
     * HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    final ServletContext servletContext = request.getSession()
            .getServletContext();
    final File tempDirectory = (File) servletContext
            .getAttribute("javax.servlet.context.tempdir");
    final String temperotyFilePath = tempDirectory.getAbsolutePath();
    
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    String fileName = "Order_Details_"+System.currentTimeMillis()+".pdf";
    response.setContentType("application/pdf");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "max-age=0");
    response.setHeader("Content-disposition", "attachment; " +
            "filename="+ fileName);

    try {
        Order theOrder = orderDbUtil.viewOrder(orderId);
        String accountId = Integer.toString(theOrder.getAccount());
        Account theAccount = accountDbUtil.getAccount(accountId);
        List<OrderDetails> theOrderDetailsList = orderDetailsDbUtil.viewOrderDetail(orderId);

        GeneratePDF.createPDF(temperotyFilePath+"\\"+fileName, theOrder, theAccount, theOrderDetailsList);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = convertPDFToByteArrayOutputStream(
                temperotyFilePath+"\\"+fileName);
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
    } catch (Exception e1) {
        e1.printStackTrace();
    }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, 
     * HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,HttpServletResponse 
            response) throws ServletException, IOException {        
    }

    private static ByteArrayOutputStream 
        convertPDFToByteArrayOutputStream(String fileName) {

        InputStream inputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            inputStream = new FileInputStream(fileName);

            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos;
    }
}
