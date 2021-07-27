/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;


import com.sun.java.accessibility.util.EventID;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author ricardo.braz
 */
@WebServlet(name = "AccountControllerServlet", urlPatterns = {"/AccountControllerServlet"})
public class AccountControllerServlet extends HttpServlet {
    
    private AccountDbUtil accountDbUtil;
    private PaymentDbUtil paymentDbUtil;
    private CartDbUtil cartDbUtil;
    private ItemsCartDbUtil itemsCartDbUtil;
    private ProductDbUtil productDbUtil;
    private OrderDbUtil orderDbUtil;
    private OrderDetailsDbUtil orderDetailsDbUtil;
    
    @Resource(name = "jdbc/store")
    private DataSource dataSource;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        // create account in db util and pass in the conn pool
        try {
            accountDbUtil = new AccountDbUtil(dataSource);
            paymentDbUtil = new PaymentDbUtil(dataSource);
            cartDbUtil = new CartDbUtil(dataSource);
            itemsCartDbUtil = new ItemsCartDbUtil(dataSource);
            productDbUtil = new ProductDbUtil(dataSource);
            orderDbUtil = new OrderDbUtil(dataSource);
            orderDetailsDbUtil = new OrderDetailsDbUtil(dataSource);
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
            out.println("<title>Servlet AccountControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountControllerServlet at " + request.getContextPath() + "</h1>");
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
        
        String command = request.getParameter("command");
        
        if(command == null){
            command = "LOGIN";
        }
        switch(command){
            case "LOGIN":
                try 
                {
                    response.sendRedirect("login.jsp");
                    break;
                } 
                catch (IOException e) 
                {
                    throw new ServletException(e);
                }
            case "MYACCOUNT":
                try 
                {
                    myAccount(request, response);
                    break;
                } 
                catch (Exception e) 
                    {
                        throw new ServletException(e);
                    }

            default:
                try 
                {
                    response.sendRedirect("login.jsp");
                    break;
                } 
                catch (IOException e) 
                {
                    throw new ServletException(e);
                }
        }
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
           
            // if the command is missing, then default to listing students
            if(theCommand == null){
                theCommand = "LOGIN";
            }
            
            // route to the appropriate method
            switch (theCommand){
                case "LOGIN":
                    try 
                    {
                        loginAccount(request, response);
                        break; 
                    }
                    
                    catch (Exception e) 
                    {
                        throw new ServletException(e);
                    }
                case "REGISTER":
                    try 
                    {
                        createAccount(request, response);
                        break; 
                    }
                    
                    catch (Exception e) 
                    {
                        throw new ServletException(e);
                    }
                case "UPDATE":
                    try 
                    {
                        updateAccount(request, response);
                        break; 
                    }
                    
                    catch (Exception e) 
                    {
                        throw new ServletException(e);
                    }
                case "MYACCOUNT":
                    try 
                    {
                        myAccount(request, response);
                        break; 
                    }
                    
                    catch (Exception e) 
                    {
                        throw new ServletException(e);
                    }
                default:
                    try 
                    {
                        loginAccount(request, response);
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

    private void createAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // read account info from form data
        String name = request.getParameter("fullname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        password = toHexString(password, email);

        // create a new account object
        Account theAccount = new Account(name, password, email, false);
        
        // add the account to the database
        accountDbUtil.addAccount(theAccount);
        
        // send back to login page
        response.sendRedirect("login.jsp?success=1");
        
    }

    private void loginAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        HttpSession session = request.getSession();
        
        // read account info from form data
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        password = toHexString(password, email);
        
        Account theAccount = accountDbUtil.loginAccount(email, password);
        
        if(theAccount == null){
            session.setAttribute("errorMessage", "Email ou Password inválidos");
            // send to login jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        else{
 
        // set attribute session
        session.setAttribute("THE_ACCOUNT", theAccount);
        session.setAttribute("LOGGED_USER", true);
        
        // get list of payments from db util
        try {
            List<Payment> payments = paymentDbUtil.getAllPaymentMethods(theAccount.getId());
            Cart userCart = cartDbUtil.getCart(theAccount.getId());
            if(userCart == null)
            {
                cartDbUtil.createCart(theAccount.getId());
                userCart = cartDbUtil.getCart(theAccount.getId());
            }
                
            //set attribute session
            session.setAttribute("PAYMENT_LIST", payments);
            session.setAttribute("CART", userCart);
            
            List<Order> theOrderList = orderDbUtil.getOrders(theAccount.getId());
            request.setAttribute("ORDER_LIST", theOrderList);
            List<OrderDetails> theOrderDetailsList = orderDetailsDbUtil.getOrdersDetails(theAccount.getId());
            request.setAttribute("ORDER_DETAILS_LIST", theOrderDetailsList);
            
  
            List<ItemsCart> itemsCart = itemsCartDbUtil.getItemsCart(userCart.getId());
            
            List<Product> InfoProductsCarList = new ArrayList<>();
            
            if(itemsCart != null){
                InfoProductsCarList = productDbUtil.getInfoProductsCart(userCart.getId());
            }
            
            session.setAttribute("ITEMSCART_LIST", itemsCart);
            session.setAttribute("MYCART", InfoProductsCarList);
            
            // Set inative time session
            session.setMaxInactiveInterval(10*60);
            
        } catch (Exception e) {
            throw new ServletException(e);
        }

        session.setAttribute("userId", theAccount.getId());

        
        // send to myaccount jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("myaccount.jsp");
        dispatcher.forward(request, response);
        }
    }
    
    public static String toHexString(String password, String email) throws Exception
    { 
        String newPassword = password + email;
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
  
        return sb.toString();  
    } 

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        HttpSession session = request.getSession();

        // read account info from form data
        int id = Integer.parseInt(request.getParameter("accountId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        // create a new account object
        Account theAccount = new Account(id, address, name, email, phone);
        
        // perform update on database (model)
        accountDbUtil.updateAccount(theAccount);
        
        // get new data from select on database
        theAccount = accountDbUtil.getAccount(request.getParameter("accountId"));
        
        // set attribute session
        session.setAttribute("THE_ACCOUNT", theAccount);
        
        List<Order> theOrderList = orderDbUtil.getOrders(theAccount.getId());
        session.setAttribute("ORDER_LIST", theOrderList);
        List<OrderDetails> theOrderDetailsList = orderDetailsDbUtil.getOrdersDetails(theAccount.getId());
        session.setAttribute("ORDER_DETAILS_LIST", theOrderDetailsList);
        
        // send back to account detail page
        response.sendRedirect("myaccount.jsp?success=1");
    }


    private void myAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        HttpSession session = request.getSession();
        
        // read account id from request
        String id = request.getParameter("userId");
        
        // create a new account object from db
        Account theAccount = accountDbUtil.getAccount(id);
        
        // set attribute session
        session.setAttribute("THE_ACCOUNT", theAccount);
        
        List<Order> theOrderList = orderDbUtil.getOrders(theAccount.getId());
        session.setAttribute("ORDER_LIST", theOrderList);
        List<OrderDetails> theOrderDetailsList = orderDetailsDbUtil.getOrdersDetails(theAccount.getId());
        session.setAttribute("ORDER_DETAILS_LIST", theOrderDetailsList);
    
        // send to jsp page : myaccount  
        RequestDispatcher dispatcher = request.getRequestDispatcher("/myaccount.jsp");
        dispatcher.forward(request, response);
        
    }

}
