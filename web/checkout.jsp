<%-- 
    Document   : checkout
    Created on : 04/06/2020, 22:54:40
    Author     : ricardo.braz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

    <%@include file="head.jsp" %>
    <%@include file="cart.jsp" %>
    <body>
        <%@include file="header.jsp" %>

        <!-- ##### Breadcumb Area Start ##### -->
        <div class="breadcumb_area breadcumb-style-two bg-img" style="background-image: url(app/img/bg-img/checkout.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="page-title text-center">
                            <h2>Checkout</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Breadcumb Area End ##### -->

        <!-- ##### Checkout Area Start ##### -->
        <div class="checkout_area section-padding-80">
            <div class="container">
                <div class="row">

                    <div class="col-12 col-md-6">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-page-heading mb-30">
                                <h5>Billing Address</h5>
                            </div>

                            <form action="OrderControllerServlet" method="post" id="orderFom" autocomplete="off">
                                <input type="hidden" name="command" value="NEWORDER"/>
                                <input type="hidden" name="accountId" value="${THE_ACCOUNT.id}"/>
                                <div class="row">
                                    <div class="col-md-12 mb-3">
                                        <div class="md-form mb-0">
                                            <input type="text" name="fullName" class="form-control" id="first_name" value="${THE_ACCOUNT.isTemporary == false ? THE_ACCOUNT.name : ''}" required>
                                            <label for="first_name">Full Name <span>*</span></label>
                                        </div>

                                    </div>
                                    <div class="col-12 mb-3">
                                        <input type="text" name="address" class="form-control mb-3" id="street_address" value="${THE_ACCOUNT.isTemporary == false ? THE_ACCOUNT.address : ''}">
                                        <label for="street_address">Address <span>*</span></label>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <input type="number" name="phone" class="form-control" id="phone_number" min="0" value="${THE_ACCOUNT.isTemporary == false ? THE_ACCOUNT.phone : ''}">
                                        <label for="phone_number">Phone No <span>*</span></label>
                                    </div>
                                    <div class="col-12 mb-4">
                                        <input type="email" name="email" class="form-control" id="email_address" value="${THE_ACCOUNT.isTemporary == false ? THE_ACCOUNT.email : ''}">
                                        <label for="email_address">Email Address <span>*</span></label>
                                    </div>
                                    <c:if test="${THE_ACCOUNT.isTemporary == true}">
                                        <div class="col-12 mb-4">
                                            <input type="password" name="password" class="form-control" id="password">
                                            <label for="password">Password <span>*</span></label>
                                        </div>
                                    </c:if>
                                    <c:set var="total" value="0" scope="page" />
                                    <c:forEach var="productsOrder" items="${MYCART}">
                                        <c:set var="total" value="${total + productsOrder.price}" scope="page"/>
                                    </c:forEach>
                                    <input type="hidden" name="total" value="${total}" />
                                    <div class="col-12">
                                        <div class="custom-control custom-checkbox d-block mb-2">
                                            <input type="checkbox" class="custom-control-input" id="customCheck1">
                                            <label class="custom-control-label" for="customCheck1">Terms and conditions</label>
                                        </div>
                                        <c:if test="${THE_ACCOUNT.isTemporary == true}">

                                            <div class="custom-control custom-checkbox d-block mb-2">
                                                <input type="checkbox" name="createAccount" value="true" class="custom-control-input" id="customCheck2">
                                                <label class="custom-control-label" for="customCheck2">Create an account</label>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-12 col-md-6 col-lg-5 ml-lg-auto">
                        <div class="order-details-confirmation">

                            <div class="cart-page-heading">
                                <h5>Your Order</h5>
                                <p>The Details</p>
                            </div>

                            <ul class="order-details-form mb-4">

                                <li><span>Product</span> <span>Total</span></li>
                                    <c:forEach var="productsOrder" items="${MYCART}">
                                    <li><span>${productsOrder.name}</span> <span><fmt:formatNumber value="${productsOrder.price}" type="currency"/></span></li>
                                        </c:forEach>
                                <li><span>Shipping</span> <span>Free</span></li>
                                <li><span>Total</span> <span><fmt:formatNumber value="${total}" type="currency"/></span></li>
                            </ul>

                            <div id="accordion" role="tablist" class="mb-4">
                                <div class="card">
                                    <div class="card-header" role="tab" id="headingTwo">
                                        <h6 class="mb-0">
                                            <a class="collapsed" data-toggle="collapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"><i class="far fa-circle mr-3"></i>cash on delievery</a>
                                        </h6>
                                    </div>
                                    <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">
                                        <div class="card-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo quis in veritatis officia inventore, tempore provident dignissimos.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-header" role="tab" id="headingThree">
                                        <h6 class="mb-0">
                                            <a class="collapsed" data-toggle="collapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree"><i class="far fa-circle mr-3"></i>credit card</a>
                                        </h6>
                                    </div>
                                    <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree" data-parent="#accordion">
                                        <div class="card-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Esse quo sint repudiandae suscipit ab soluta delectus voluptate, vero vitae</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <input type="submit" class="btn essence-btn" form="orderFom" value="Place Order"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Checkout Area End ##### -->

        <%@include file="footer.jsp" %>

    </body>

</html>