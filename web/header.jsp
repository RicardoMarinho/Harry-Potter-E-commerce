<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ##### Header Area Start ##### -->
<div id="cookie-bar" class="fixed bottom"><p>Este website utiliza cookies de acordo com a política em vigor. Ao continuar a navegação está a aceitar a sua utilização.<a href="" class="cb-enable">Concordo</a></p></div>
<header class="header_area">
    <div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
        <!-- Classy Menu -->
        <nav class="classy-navbar" id="essenceNav">
            <!-- Logo -->
            <a class="nav-brand" href="HomeControllerServlet"><img src="app/img/core-img/logo.png" width="100" alt=""></a>
            <!-- Navbar Toggler -->
            <div class="classy-navbar-toggler">
                <span class="navbarToggler"><span></span><span></span><span></span></span>
            </div>
            <!-- Menu -->
            <div class="classy-menu">
                <!-- close btn -->
                <div class="classycloseIcon">
                    <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                </div>
                <!-- Nav Start -->
                <div class="classynav">
                    <ul>
                        <li><a href="HomeControllerServlet">Home</a></li>
                        <li><a href="#">Shop</a>
                            <div class="megamenu">
                                <ul class="single-mega cn-col-4">
                                    <li class="title">Clothing</li>
                                    <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=costumes">Costume</a></li>
                                    <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=knitwear">Knitwear</a></li>
                                    <li><a href="ProductControllerServlet?command=VIEWCAT&categoryId=clothing">View All</a></li>
                                </ul>
                                <ul class="single-mega cn-col-4">
                                    <li class="title">Departments</li>
                                    <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=books">Books</a></li>
                                    <li><a href="ProductControllerServlet?command=VIEWCAT&categoryId=departments">View All</a></li>
                                </ul>
                                <ul class="single-mega cn-col-4">
                                    <li class="title">Souvenirs</li>
                                    <li><a href="ProductControllerServlet?command=VIEWSUBCAT&subcategoryId=bookmarks">Bookmarks</a></li>
                                    <li><a href="ProductControllerServlet?command=VIEWCAT&categoryId=souvenirs">View All</a></li>
                                </ul>
                                <div class="single-mega cn-col-4">
                                    <img src="app/img/bg-img/bg-5.png" alt="">
                                </div>
                            </div>
                        </li>
                        <c:url var="contactLink" value="HomeControllerServlet">
                            <c:param name="command" value="CONTACT" />
                        </c:url>
                        <li><a href="${contactLink}">Contact</a></li>
                            <c:if test="${LOGGED_USER == true}">                           
                                <li><a href="HomeControllerServlet?command=LOGOUT">Logout</a></li>
                            </c:if>
                    </ul>
                </div>
                <!-- Nav End -->
            </div>
        </nav>

        <!-- Header Meta Data -->
        <div class="header-meta d-flex clearfix justify-content-end">
            <!-- Search Area -->
            <div class="search-area">
                <form action="ProductControllerServlet" method="get" autocomplete="off">
                    <input type="hidden" name="command" value="SEARCH"/>
                    <input type="search" name="keywords" id="headerSearch" placeholder="Type for search">
                    <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                </form>
            </div>

            <!-- User Login Info -->
                <div class="user-login-info">
                    <c:if test="${LOGGED_USER == true}">
                        <c:url var="accountLink" value="AccountControllerServlet">
                            <c:param name="command" value="MYACCOUNT" />
                            <c:param name="userId" value="${THE_ACCOUNT.id}" />
                        </c:url>
                        <a href="${accountLink}"><img src="app/img/core-img/user.svg" alt="My Account" title="My Account"></a>
                    </c:if>
                    <c:if test="${LOGGED_USER == null}">
                        <a href="AccountControllerServlet?command=LOGIN"><img src="app/img/core-img/user.svg" alt="Login" title="Login"></a>
                    </c:if>
                </div>
            <!-- Cart Area -->
            <div class="cart-area">
                <a href="#" id="essenceCartBtn"><img src="app/img/core-img/bag.svg" alt="Cart" title="Cart"> <span></span></a>
            </div>
            
        </div>
    </div>
</header>
<!-- ##### Header Area End ##### -->