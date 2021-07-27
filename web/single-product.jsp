<%-- 
    Document   : single-product
    Created on : 20/05/2020, 22:04:04
    Author     : ricardo.braz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

    <%@include file="head.jsp" %>

    <body>
        <%@include file="header.jsp" %>
        <%@include file="cart.jsp" %>

        <!-- ##### Single Product Details Area Start ##### -->
        <section class="single_product_details_area d-flex align-items-center">
            <c:set var="imgUrl" value="${THE_PRODUCT.imgUrl}" />
            <c:set var="src" value="${fn:split(imgUrl, ',')}" />
            <!-- Single Product Thumb -->
            <div class="single_product_thumb clearfix">
                <div class="product_thumbnail_slides owl-carousel">
                    <img class="owl-carousel-single" src="${src[0]}" alt="">
                    <img class="owl-carousel-single" src="${src[1]}" alt="">
                </div>
            </div>

            <!-- Single Product Description -->
            <div class="single_product_desc clearfix">
                <h2>${THE_PRODUCT.name}</h2>
                <c:choose>
                    <c:when test="${THE_PRODUCT.stock >= 6}">
                        <p>${THE_PRODUCT.stock} units in stock</p>
                    </c:when>
                    <c:when test="${THE_PRODUCT.stock <= 5 && THE_PRODUCT.stock > 3}">
                        <p>Only ${THE_PRODUCT.stock} units available</p>
                    </c:when>
                    <c:when test="${THE_PRODUCT.stock <=3 && THE_PRODUCT.stock > 0}">
                        <p>Last ${THE_PRODUCT.stock} units available</p>
                    </c:when>
                    <c:otherwise>
                        <p>Without stock</p>
                    </c:otherwise>
                </c:choose>
                <p></p>
                <p class="product-price">&euro;${THE_PRODUCT.price}</p>
                <p class="product-desc">${THE_PRODUCT.description}</p>

                <!-- Form -->
                <form class="cart-form clearfix" method="post">
                    <div class="select-box d-flex mt-50 mb-30 align-items-center ">
                        <label for="qty" class="pr-3">QTY:</label>
                        <select name="qty" id="productQty" class="mr-5">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>     
                    </div>
                    <!-- Cart & Favourite Box -->
                    <div class="cart-fav-box d-flex align-items-center">
                        <!-- Cart -->
                        <button type="submit" name="addtocart" value="5" ${THE_PRODUCT.stock == 0 ? 'disabled' : ''} class="btn essence-btn">Add to cart</button>
                        <!-- Favourite -->
                        <div class="product-favourite ml-4">
                            <a href="#" class="favme fa fa-heart"></a>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- ##### Single Product Details Area End ##### -->

        <%@include file="footer.jsp" %>
    </body>

</html>