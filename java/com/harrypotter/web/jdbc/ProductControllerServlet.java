/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ProductControllerServlet", urlPatterns = {"/ProductControllerServlet"})
public class ProductControllerServlet extends HttpServlet {

    private ProductDbUtil productDbUtil;

    @Resource(name = "jdbc/store")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        // create product in db util and pass in the conn pool
        try {
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
            out.println("<title>Servlet ProductControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductControllerServlet at " + request.getContextPath() + "</h1>");
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
            theCommand = "VIEWALL";
        }
        // route to the appropriate method
        switch (theCommand) {
            case "VIEW":
                try 
                {
                    viewProduct(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            case "VIEWALL":
                try 
                {
                    viewAllProduct(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            case "VIEWCAT":
                try 
                {
                    viewCategoryProduct(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            case "VIEWSUBCAT":
                try 
                {
                    viewSubCategoryProduct(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                } 
            case "ORDERBY":
                try 
                {
                    productOrderBy(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            case "SEARCH":
                try 
                {
                    searchProduct(request, response);
                    break;
                } 
                catch (Exception e) 
                {
                    throw new ServletException(e);
                }
            default: {
                try 
                {
                    viewProduct(request, response);
                } 
                catch (Exception e) 
                {
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

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read product id from form data
        String theproductId = request.getParameter("productId");

        // get product from db util
        Product theProduct = productDbUtil.getProduct(theproductId);

        // place product in the request attribute
        request.setAttribute("THE_PRODUCT", theProduct);

        // send to jsp page : single-product-details.jsp   
        RequestDispatcher dispatcher = request.getRequestDispatcher("/single-product.jsp");
        dispatcher.forward(request, response);
    }

    private void viewAllProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // get products from db util
        List<Product> products = productDbUtil.getAllProducts();

        // add students to the request
        request.setAttribute("ALLPRODUCTS_LIST", products);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
    }

    private void productOrderBy(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // inicialize list of all products
        List<Product> products = productDbUtil.getAllProducts();
        
        // read sort value & current category from form data
        String sortValue = request.getParameter("order");
        String categoryId = request.getParameter("categoryId");
        String subcategoryId = request.getParameter("subcategoryId");
        String keywords = request.getParameter("keywords");
        
        if(categoryId != null){
            // get sorted products by category from db util
            products = productDbUtil.getSortedProducts(sortValue, categoryId);
        }
        if(subcategoryId != null){
            // get sorted products by subcategory from db util
            products = productDbUtil.getSortedProducts(sortValue, subcategoryId);
        }
        if(keywords != null){
            // get sorted products by keywords from db util
            products = productDbUtil.getSortedProducts(sortValue, keywords);
        }
        
        // add students to the request
        request.setAttribute("ALLPRODUCTS_LIST", products);
        
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
        
    }

    private void viewCategoryProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // read category value from request
        String categoryId = request.getParameter("categoryId");
        
        // get products from db util
        List<Product> products = productDbUtil.getProductsByCategory(categoryId);

        // add students to the request
        request.setAttribute("ALLPRODUCTS_LIST", products);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read keyword value from request
        String keywords = request.getParameter("keywords");
        
        // get products from db util
        List<Product> products = productDbUtil.searchProduct(keywords);

        // add students to the request
        request.setAttribute("ALLPRODUCTS_LIST", products);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
    }

    private void viewSubCategoryProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // read category value from request
        String subcategoryId = request.getParameter("subcategoryId");
        
        // get products from db util
        List<Product> products = productDbUtil.getProductsBySubCategory(subcategoryId);

        // add students to the request
        request.setAttribute("ALLPRODUCTS_LIST", products);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/shop.jsp");
        dispatcher.forward(request, response);
    }

}
