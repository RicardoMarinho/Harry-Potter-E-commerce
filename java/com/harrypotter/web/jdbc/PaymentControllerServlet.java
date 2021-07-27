/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author ricardo.braz
 */
public class PaymentControllerServlet extends HttpServlet {
    
    private PaymentDbUtil paymentDbUtil;
    
    @Resource(name = "jdbc/store")
    private DataSource dataSource;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        // create payment in db util and pass in the conn pool
        try {
            paymentDbUtil = new PaymentDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        
        if(command == null){
            command = "CREATE";
        }
        switch(command){
            case "CREATE":
                try 
                {
                    createPaymentMethod(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            case "DELETE":
                try 
                {
                    deletePaymentMethod(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            default:
                try 
                {
                    createPaymentMethod(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void createPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // read account info from form data
        int userId = Integer.parseInt(request.getParameter("userId"));
        String fullName = request.getParameter("fullName");
        String cardNumber = request.getParameter("cardNumber");
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        int cvv = Integer.parseInt(request.getParameter("cvv"));
        
        if (!checkLuhn(cardNumber)) 
            // send back to myaccount page
            response.sendRedirect("myaccount.jsp?error=1");
        else
        {
            // create a new payment object
            Payment thePayment = new Payment(userId, fullName, cardNumber, month, year, cvv);
        
            // add the payment to the database
            paymentDbUtil.addPayment(thePayment);
        
            // send back to myaccount page
            response.sendRedirect("myaccount.jsp?success=1");
        }  
    }

    private void deletePaymentMethod(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Luhn algorithm Returns true if given card number is valid 
    static boolean checkLuhn(String cardNo)
    { 
        int nDigits = cardNo.length(); 

        int nSum = 0; 
        boolean isSecond = false; 
        for (int i = nDigits - 1; i >= 0; i--)  
        { 

            int d = cardNo.charAt(i) - '0'; 

            if (isSecond == true) 
                d = d * 2; 

            // We add two digits to handle cases that make two digits after doubling 
            nSum += d / 10; 
            nSum += d % 10; 

            isSecond = !isSecond; 
        } 
        return (nSum % 10 == 0); 
    }
}
