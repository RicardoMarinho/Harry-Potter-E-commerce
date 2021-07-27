/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import static com.harrypotter.web.jdbc.AccountControllerServlet.toHexString;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
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
@WebServlet(name = "OrderControllerServlet", urlPatterns = {"/OrderControllerServlet"})
public class OrderControllerServlet extends HttpServlet {
    
    private OrderDbUtil orderDbUtil;
    private OrderDetailsDbUtil orderDetailsDbUtil;
    private CartDbUtil cartDbUtil;
    private ItemsCartDbUtil itemsCartDbUtil;
    private ProductDbUtil productDbUtil;
    private AccountDbUtil accountDbUtil;

    @Resource(name = "jdbc/store")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        // create product in db util and pass in the conn pool
        try {
            orderDbUtil = new OrderDbUtil(dataSource);
            orderDetailsDbUtil = new OrderDetailsDbUtil(dataSource);
            accountDbUtil = new AccountDbUtil(dataSource);
            cartDbUtil = new CartDbUtil(dataSource);
            itemsCartDbUtil = new ItemsCartDbUtil(dataSource);
            productDbUtil = new ProductDbUtil(dataSource);
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
            out.println("<title>Servlet OrderControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderControllerServlet at " + request.getContextPath() + "</h1>");
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
        // read the command parameter
        String theCommand = request.getParameter("command");

        // if the command is missing, then default to create order
        if (theCommand == null) {
            theCommand = "NEWORDER";
        }
        // route to the appropriate method
        switch (theCommand) {
            case "NEWORDER":
                try 
                {
                    createOrder(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            default: {
                try 
                {
                    createOrder(request, response);
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
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

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // read data from checkout's form
        
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        float total = Float.parseFloat(request.getParameter("total"));
        String address = request.getParameter("address");
        String createAccount = request.getParameter("createAccount");
        
        if(createAccount != null){
            String name = request.getParameter("fullName");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            
            password = toHexString(password, email);
                        
            Account theAccount = new Account(accountId, address, name, password, email, phone, false);
            accountDbUtil.updateTemporaryAccount(theAccount);
        }
        Order newOrder = new Order(accountId, total, address);
        int orderId = orderDbUtil.createOrder(newOrder);
        if(orderId != 0){
            Cart theCart = cartDbUtil.getCart(accountId);
            List<ItemsCart> itemsCarts = itemsCartDbUtil.getItemsCart(theCart.getId());
            Product theProduct = null;
            
            for (ItemsCart item : itemsCarts) {
                String productId = Integer.toString(item.getProductId());
                theProduct = productDbUtil.getProduct(productId);
                OrderDetails theOrderDetails = new OrderDetails(orderId, theProduct.getId(), theProduct.getName(), item.getQty(), theProduct.getPrice());
                orderDetailsDbUtil.createOrderDetails(theOrderDetails);
            }
            itemsCartDbUtil.emptyItemsCart(theCart.getId());
            List<Order> theOrderList = orderDbUtil.getOrders(accountId);
            request.setAttribute("ORDER_LIST", theOrderList);
            List<OrderDetails> theOrderDetailsList = orderDetailsDbUtil.getOrdersDetails(accountId);
            request.setAttribute("ORDER_DETAILS_LIST", theOrderDetailsList);
        }
        
    }
}
