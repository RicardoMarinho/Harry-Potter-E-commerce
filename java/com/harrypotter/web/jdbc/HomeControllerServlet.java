/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author ricardo.braz
 */
@WebServlet(name = "HomeControllerServlet", urlPatterns = {"/HomeControllerServlet"})
public class HomeControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDbUtil productDbUtil;
    private AccountDbUtil accountDbUtil;
    private ItemsCartDbUtil itemsCartDbUtil;
    private PaymentDbUtil paymentDbUtil;
    private CartDbUtil cartDbUtil;
    private OrderDbUtil orderDbUtil;
    private OrderDetailsDbUtil orderDetailsDbUtil;

    @Resource(name = "jdbc/store")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        // create account and product in db util and pass in the conn pool
        try {
            productDbUtil = new ProductDbUtil(dataSource);
            accountDbUtil = new AccountDbUtil(dataSource);
            itemsCartDbUtil = new ItemsCartDbUtil(dataSource);
            paymentDbUtil = new PaymentDbUtil(dataSource);
            cartDbUtil = new CartDbUtil(dataSource);
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
            out.println("<title>Servlet HomeControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeControllerServlet at " + request.getContextPath() + "</h1>");
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

        // read the command parameter
        String theCommand = request.getParameter("command");

        // if the command is missing, then default to view product
        if (theCommand == null) {
            theCommand = "INDEX";
        }
        // route to the appropriate method
        switch (theCommand) {
            case "INDEX":
                try {
                viewPopularProducts(request, response);
                break;
            } catch (Exception e) {
                throw new ServletException(e);
            }
            case "CONTACT":
                // send to contact page (view)
                RequestDispatcher dispatcher = request.getRequestDispatcher("/contact.jsp");
                dispatcher.forward(request, response);
                break;
            case "LOGOUT":
                try {
                logout(request, response);
                break;
            } catch (Exception e) {
                throw new ServletException(e);
            }
            default: {
                try {
                    viewPopularProducts(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
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

        // if the command is missing, then default to view product
        if (theCommand == null) {
            theCommand = "INDEX";
        }
        // route to the appropriate method
        switch (theCommand) {
            case "INDEX":
                try {
                    viewPopularProducts(request, response);
                    break;
                } catch (Exception e) {
                    throw new ServletException(e);
                }
            case "CONTACT":
                try {
                    sendEmail(request, response);
                    break;
                } catch (Exception e) {
                    throw new ServletException(e);
                }
            default: {
                try {
                    viewPopularProducts(request, response);
                } catch (Exception e) {
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

    private void viewPopularProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get popular products from db util
        HttpSession session = request.getSession();
        List<Product> products = productDbUtil.getPopularProducts();
        Account tempAccount = null;
        Cookie theCookies[] = request.getCookies();

        if (session.getAttribute("THE_ACCOUNT") == null) {
            String tempUser = "";

            if (theCookies != null) {
                for (Cookie tempCookie : theCookies) {
                    if ("JSESSIONID".equals(tempCookie.getName())) {
                        if(!"sessionID".equals(tempCookie.getName())){
                            Cookie sessionCookie = new Cookie("sessionID", tempCookie.getValue());
                            sessionCookie.setMaxAge(60 * 60 * 24 * 365); // one year lifetime
                            response.addCookie(sessionCookie);
                            tempUser = sessionCookie.getValue();
                        } 
                        break;
                    }
                }
                if (!"".equals(tempUser)) {
                    tempAccount = new Account(tempUser, tempUser, tempUser, true);
                    Account verifyAccount = null;
                    verifyAccount = accountDbUtil.verifyTemporaryAccount(tempAccount);
                    if (verifyAccount == null) {
                        tempAccount = accountDbUtil.addTemporaryAccount(tempAccount);
                    } else {
                        tempAccount = accountDbUtil.getAccount(Integer.toString(verifyAccount.getId()));
                    }

                    session.setAttribute("THE_ACCOUNT", tempAccount);
                }
            }
        }

        // add products to the request
        request.setAttribute("PRODUCT_LIST", products);

        if (tempAccount != null) {
            // get list of payments from db util
            try {
                List<Payment> payments = paymentDbUtil.getAllPaymentMethods(tempAccount.getId());
                Cart userCart = cartDbUtil.getCart(tempAccount.getId());
                if (userCart == null) {
                    cartDbUtil.createCart(tempAccount.getId());
                    userCart = cartDbUtil.getCart(tempAccount.getId());
                }

                //set attribute session
                session.setAttribute("PAYMENT_LIST", payments);
                session.setAttribute("CART", userCart);

                List<ItemsCart> itemsCart = itemsCartDbUtil.getItemsCart(userCart.getId());

                List<Product> InfoProductsCarList = new ArrayList<>();

                if (itemsCart != null) {
                    InfoProductsCarList = productDbUtil.getInfoProductsCart(userCart.getId());
                }

                session.setAttribute("ITEMSCART_LIST", itemsCart);
                session.setAttribute("MYCART", InfoProductsCarList);

                // Set inative time session
                session.setMaxInactiveInterval(10 * 60);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        
        session.removeAttribute("THE_ACCOUNT");
        session.removeAttribute("LOGGED_USER");
        session.invalidate();
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("HomeControllerServlet?command=INDEX");
        dispatcher.forward(request, response);
    }

    private void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Recipient's email ID needs to be mentioned.
      String to = "ricardo.braz.winprovit@gmail.com";
      String pass = "Kokoro1991!!";
      String subject = "Recebeu um contacto de: " + request.getParameter("name") + " - " + request.getParameter("subject");
 
      // Sender's email ID needs to be mentioned
      String from = "ricardo.braz@my.istec.pt";
 
      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com";
 
      // Get system properties
      Properties props = System.getProperties();
 
      // Setup mail server
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

 
      // Get the default Session object.
      Session session = Session.getDefaultInstance(props);
      
      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
         
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));
         
         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         
         // Set Subject: header field
         message.setSubject(subject);
         
         // Now set the actual message
         message.setText(request.getParameter("message") + "\n Email do contacto: " + request.getParameter("email"));
         
         // Send message
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        request.setAttribute("successMessage", "Contacto efetuado com sucesso!");
        
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/contact.jsp");
        dispatcher.forward(request, response);
        
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
    }

}
