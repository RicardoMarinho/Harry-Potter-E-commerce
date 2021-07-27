<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">

    <%@include file="head.jsp" %>
    <body>
        <%@ include file="header.jsp" %>

        <%@include file="cart.jsp" %>

        <!-- ##### Welcome Area Start ##### -->
        <section class="welcome_area bg-img background-overlay" style="background-image: url(app/img/bg-img/bg-1.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="hero-content">
                            <h2>New Collection</h2>
                            <a href="#" class="btn essence-btn">view collection</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ##### Welcome Area End ##### -->

        <!-- ##### Top Catagory Area Start ##### -->
        <div class="top_catagory_area section-padding-80 clearfix">
            <div class="container">
                <div class="row justify-content-center">
                    <!-- Single Catagory -->
                    <div class="col-12 col-sm-6 col-md-4">
                        <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(app/img/bg-img/bg-2.jpg);">
                            <div class="catagory-content">
                                <a href="ProductControllerServlet?command=VIEWCAT&categoryId=clothing">Clothing</a>
                            </div>
                        </div>
                    </div>
                    <!-- Single Catagory -->
                    <div class="col-12 col-sm-6 col-md-4">
                        <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(app/img/bg-img/bg-3.jpg);">
                            <div class="catagory-content">
                                <a href="ProductControllerServlet?command=VIEWCAT&categoryId=departments">Departments</a>
                            </div>
                        </div>
                    </div>
                    <!-- Single Catagory -->
                    <div class="col-12 col-sm-6 col-md-4">
                        <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(app/img/bg-img/bg-4.jpg);">
                            <div class="catagory-content">
                                <a href="ProductControllerServlet?command=VIEWCAT&categoryId=souvenirs">Souvenirs</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Top Catagory Area End ##### -->

        <!-- ##### CTA Area Start ##### -->
        <div class="cta-area">
            <div class="container">
                <div class="row">
                    <div class="col-8">
                        <div class="cta-area__image">
                            <img class="lazyload active" src="app/img/bg-img/bg-5.png" />
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="cta-content bg-img background-overlay" >
                            <div class="h-100 d-flex align-items-center justify-content-end">
                                <div class="cta--text">
                                    <a href="#" class="btn essence-btn">Buy Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### CTA Area End ##### -->

        <!-- ##### New Arrivals Area Start ##### -->
        <section class="new_arrivals_area section-padding-80 clearfix">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section-heading text-center">
                            <h2>Popular Products</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="popular-products-slides owl-carousel">
                            <c:forEach var="tempProduct" items="${PRODUCT_LIST}" >
                                <!-- Set up a link for each student -->
                                <c:url var="tempLink" value="ProductControllerServlet">
                                    <c:param name="command" value="VIEW" />
                                    <c:param name="productId" value="${tempProduct.id}" />
                                </c:url>
                                <!-- Set up a link for delete each student -->
                                <c:url var="addtocartLink" value="ItemsCartControllerServlet">
                                    <c:param name="command" value="ADDTOCART" />
                                    <c:param name="cartId" value="${CART.id}" />
                                    <c:param name="productId" value="${tempProduct.id}" />
                                </c:url>
                                <c:set var="imgUrl" value="${tempProduct.imgUrl}" />
                                <c:set var="src" value="${fn:split(imgUrl, ',')}" />
                                <!-- Single Product -->
                                <div class="single-product-wrapper">
                                    <!-- Product Image -->
                                    <div class="product-img">
                                        <img src="${src[0]}" alt="">
                                        <!-- Favourite -->
                                        <div class="product-favourite">
                                            <a href="#" class="favme fa fa-heart"></a>
                                        </div>
                                    </div>
                                    <!-- Product Description -->
                                    <div class="product-description">
                                        <span>${tempProduct.subcategory}</span>
                                        <a href="${tempLink}">
                                            <h6>${tempProduct.name}</h6>
                                        </a>
                                        <p class="product-price">&euro;${tempProduct.price}</p>

                                        <!-- Hover Content -->
                                        <div class="hover-content">
                                            <!-- Add to Cart -->
                                            <div class="add-to-cart-btn">
                                                <a href="${addtocartLink}" class="btn essence-btn">Add to Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>   
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ##### New Arrivals Area End ##### -->

        <%@include file="footer.jsp" %>

    </body>

</html>
