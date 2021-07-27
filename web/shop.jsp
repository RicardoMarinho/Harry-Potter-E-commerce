<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

    <%@include file="head.jsp" %>

    <body>
        <%@include file="header.jsp" %>

        <%@include file="cart.jsp" %>

        <!-- ##### Breadcumb Area Start ##### -->
        <div class="breadcumb_area breadcumb-style-two bg-img" style="background-image: url(app/img/bg-img/bg-1.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="page-title text-center">
                            <h2>${not empty param.subcategoryId ? param.subcategoryId : param.categoryId}</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Breadcumb Area End ##### -->
        <c:choose>
            <c:when test="${not empty Errormsg}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Error!</strong>${Errormsg}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        <c:remove var="Errormsg"/>
                        <c:remove var="Successmsg"/>
                    </button>
                </div>
            </c:when>
            <c:when test="${not empty Successmsg}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${Successmsg}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        <c:remove var="Errormsg"/>
                        <c:remove var="Successmsg"/>
                    </button>
                </div>
            </c:when>
        </c:choose>
        <!-- ##### Shop Grid Area Start ##### -->
        <section class="shop_grid_area section-padding-80">
            <div class="container">
                <div class="row">

                    <div class="col-12 col-md-4 col-lg-3">
                        <div class="shop_sidebar_area">

                            <!-- ##### Single Widget ##### -->
                            <div class="widget catagory mb-50">
                                <!-- Widget Title -->
                                <h6 class="widget-title mb-30">Catagories</h6>

                                <!--  Catagories  -->
                                <div class="catagories-menu">
                                    <ul id="menu-content2" class="menu-content collapse show">
                                        <!-- Single Item -->
                                        <li data-toggle="collapse" data-target="#clothing">
                                            <a href="#">clothing</a>
                                            <ul class="sub-menu collapse ${param.categoryId eq "clothing" ? 'show' : ''}" id="clothing">
                                                <li><a href="ProductControllerServlet?command=VIEWCAT&categoryId=clothing">All</a></li>
                                                <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=costumes">Costume</a></li>
                                                <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=knitwear">Knitwear</a></li>
                                            </ul>
                                        </li>
                                        <!-- Single Item -->
                                        <li data-toggle="collapse" data-target="#departments" class="collapsed">
                                            <a href="#">departments</a>
                                            <ul class="sub-menu collapse ${param.categoryId eq "departments" ? 'show' : ''}" id="departments">
                                                <li><a href="ProductControllerServlet?command=VIEWCAT&categoryId=departments">All</a></li>
                                                <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=books">Books</a></li>
                                            </ul>
                                        </li>
                                        <!-- Single Item -->
                                        <li data-toggle="collapse" data-target="#souvenirs" class="collapsed">
                                            <a href="#">souvenirs</a>
                                            <ul class="sub-menu collapse ${param.categoryId eq "souvenirs" ? 'show' : ''}" id="souvenirs">
                                                <li><a href="ProductControllerServlet?command=VIEWCAT&categoryId=souvenirs">All</a></li>
                                                <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=bookmarks">Bookmarks</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-8 col-lg-9">
                        <div class="shop_grid_product_area">
                            <div class="row">
                                <div class="col-12">
                                    <div class="product-topbar d-flex align-items-center justify-content-between">
                                        <!-- Total Products -->
                                        <div class="total-products">
                                            <p><span>${fn:length(ALLPRODUCTS_LIST)}</span> products found</p>
                                        </div>
                                        <!-- Sorting -->
                                        <div class="product-sorting d-flex">
                                            <p>Sort by:</p>
                                            <form action="ProductControllerServlet" method="get">
                                                <input type="hidden" name="command" value="ORDERBY" />
                                                <c:if test="${not empty param.categoryId}">
                                                    <input type="hidden" name="categoryId" value="${param.categoryId}" />
                                                </c:if>
                                                <c:if test="${not empty param.keywords}">
                                                    <input type="hidden" name="keywords" value="${param.keywords}" />
                                                </c:if>
                                                <c:if test="${not empty param.subcategoryId}">
                                                    <input type="hidden" name="subcategoryId" value="${param.subcategoryId}" />
                                                </c:if>
                                                <select name="order" id="sortByselect" onchange="this.form.submit()">
                                                    <option value="nameAsc" ${param.order eq "nameAsc" ? 'selected' : ''}>A-Z</option>
                                                    <option value="nameDesc" ${param.order eq "nameDesc" ? 'selected' : ''}>Z-A</option>                                                    
                                                    <option value="priceAsc" ${param.order eq "priceAsc" ? 'selected' : ''}>Price: Low to High</option>
                                                    <option value="priceDesc" ${param.order eq "priceDesc" ? 'selected' : ''}>Price: High to Low</option>
                                                </select>
                                                <input type="submit" class="d-none" value="">
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <c:forEach var="tempProduct" items="${ALLPRODUCTS_LIST}" >
                                    <!-- Set up a link for each product -->
                                    <c:url var="tempLink" value="ProductControllerServlet">
                                        <c:param name="command" value="VIEW" />
                                        <c:param name="productId" value="${tempProduct.id}" />
                                    </c:url>
                                    <c:url var="addtocartLink" value="ItemsCartControllerServlet">
                                        <c:param name="command" value="ADDTOCART" />
                                        <c:param name="cartId" value="${CART.id}" />
                                        <c:param name="productId" value="${tempProduct.id}" />
                                    </c:url>
                                    <c:set var="imgUrl" value="${tempProduct.imgUrl}" />
                                    <c:set var="src" value="${fn:split(imgUrl, ',')}" />
                                    <!-- Single Product -->
                                    <div class="col-12 col-sm-6 col-lg-4">
                                        <div class="single-product-wrapper">
                                            <!-- Product Image -->
                                            <div class="product-img">
                                                <img src="${src[0]}" alt="">
                                                <c:if test="${tempProduct.stock == 0}">
                                                    <!-- Product Badge -->
                                                    <div class="product-badge offer-badge">
                                                        <span>Out of stock</span>
                                                    </div>
                                                </c:if>  
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
                                                        <a href="${tempProduct.stock == 0 ? '#' : addtocartLink}" class="btn essence-btn">Add to Cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ##### Shop Grid Area End ##### -->

        <%@include file="footer.jsp" %>

    </body>

</html>