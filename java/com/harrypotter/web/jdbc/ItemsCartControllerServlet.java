/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
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
@WebServlet(name = "ItemsCartControllerServlet", urlPatterns = {"/ItemsCartControllerServlet"})
public class ItemsCartControllerServlet extends HttpServlet {
    
    private ItemsCartDbUtil itemsCartDbUtil;
    private ProductDbUtil productDbUtil;
    
    @Resource(name = "jdbc/store")
    private DataSource dataSource;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        // create account in db util and pass in the conn pool
        try {
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
            out.println("<title>Servlet ItemsCartControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemsCartControllerServlet at " + request.getContextPath() + "</h1>");
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
            command = "CHECKOUT";
        }
        switch(command){
            case "ADDTOCART":
                try 
                {
                    addItemToCart(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            case "REMOVEFROMCART":
                try 
                {
                    removeItemFromCart(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            case "CHECKOUT":
                try 
                {
                    response.sendRedirect("checkout.jsp");
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            default:
                try 
                {
                    addItemToCart(request, response);
                    break;
                } 
                catch (Exception e) 
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
        processRequest(request, response);
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

    private void addItemToCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        
        // read cart id && product id from query string or from single product form 
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int qty = 1;
        if(request.getParameter("qty") != null){
            qty = Integer.parseInt(request.getParameter("qty"));
        }
        
        if (itemsCartDbUtil.checkDuplicateItem(cartId, productId) > 0)
        {
            // set quantity update message case already exists the item
            session.setAttribute("Successmsg", "Your product quantity has been updated");
        }
        else
        {
            // instance object of newItem with properties that came from shop
            ItemsCart newItem = new ItemsCart(cartId, productId, qty);
        
            // call method from model to add product to cart
            itemsCartDbUtil.addToCart(newItem);
            
            // set success message added item
            session.setAttribute("Successmsg", "Item added to your cart");
        }    
            // Update Cart
            List<ItemsCart> itemsCart = itemsCartDbUtil.getItemsCart(cartId);
            List<Product> InfoProductsCarList = new ArrayList<>();

            if(itemsCart != null){
                    InfoProductsCarList = productDbUtil.getInfoProductsCart(cartId);
                }
            session.setAttribute("ITEMSCART_LIST", itemsCart);
            session.setAttribute("MYCART", InfoProductsCarList);
            
            

         // return to same page
         response.sendRedirect(request.getHeader("referer"));
    }

    private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        
        int productId = Integer.parseInt(request.getParameter("productId"));
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        
        itemsCartDbUtil.deleteItemCart(productId, cartId);
        
        List<ItemsCart> itemsCart = itemsCartDbUtil.getItemsCart(cartId);
        List<Product> InfoProductsCarList = new ArrayList<>();
        
        if(itemsCart != null){
                InfoProductsCarList = productDbUtil.getInfoProductsCart(cartId);
            }
        session.setAttribute("ITEMSCART_LIST", itemsCart);
        session.setAttribute("MYCART", InfoProductsCarList);
        
        // return to same page
         response.sendRedirect(request.getHeader("referer"));
    }

}
