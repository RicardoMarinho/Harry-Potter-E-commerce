<%-- 
    Document   : cart
    Created on : 28/05/2020, 20:40:54
    Author     : ricardo.braz
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- ##### Right Side Cart Area ##### -->
<div class="cart-bg-overlay"></div>

<div class="right-side-cart-area">

    <!-- Cart Button -->
    <div class="cart-button">
        <a href="#" id="rightSideCart"><img src="app/img/core-img/bag.svg" alt=""> <span>${fn:length(ITEMSCART_LIST)}</span></a>
    </div>

    <div class="cart-content d-flex">

        <!-- Cart List Area -->
        <div class="cart-list">
            <!-- Single Cart Item -->
            <c:choose>
                <c:when test="${fn:length(ITEMSCART_LIST) > 0}">
                    <c:set var="total" value="0" scope="page" />
                    <c:forEach var="tempItem" items="${MYCART}">
                        <div class="single-cart-item">
                            <c:set var="imgUrl" value="${tempItem.imgUrl}" />
                            <c:set var="src" value="${fn:split(imgUrl, ',')}" />
                            <div class="product-image">
                                <img src="${src[0]}" class="cart-thumb" alt="">
                                <div class="cart-item-desc">
                                    <!-- Set up a link for each product -->
                                    <c:url var="removecartLink" value="ItemsCartControllerServlet">
                                        <c:param name="command" value="REMOVEFROMCART" />
                                        <c:param name="cartId" value="${CART.id}" />
                                        <c:param name="productId" value="${tempItem.id}" />
                                    </c:url>

                                    <!-- Cart Item Desc -->
                                    <span class="product-remove"><a href="${removecartLink}"><i class="far fa-times-circle" ></i></a></span>
                                    <span class="badge">${tempItem.subcategory}</span>
                                    <h6>${tempItem.name}</h6>
                                    <p class="price">${tempItem.price}&euro;</p>
                                    <c:set var="total" value="${total + tempItem.price}" scope="page"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- Cart Summary -->
                <div class="cart-amount-summary">

                    <h2>Summary</h2>
                    <ul class="summary-table">
                        <li><span>total:</span> <span><fmt:formatNumber value="${total}" maxFractionDigits="2"/>&euro;</span></li>
                    </ul>
                    <div class="checkout-btn mt-100">
                        <a href="checkout.jsp" class="btn essence-btn">check out</a>
                    </div>
                </div>
            </c:when>

            <c:otherwise>
            </div>
            <!-- Cart Summary -->
            <div class="cart-amount-summary">

                <h2>Your cart is currently empty</h2>

                <div class="checkout-btn mt-100">
                    <a href="ProductControllerServlet" class="btn essence-btn">Continue shopping</a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</div>
</div> <!-- fecho de div para funcionar em todas as páginas -->
<!-- ##### Right Side Cart End ##### -->