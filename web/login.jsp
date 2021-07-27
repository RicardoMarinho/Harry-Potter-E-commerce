<%-- 
    Document   : login
    Created on : 15/05/2020, 15:12:56
    Author     : ricardo.braz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${LOGGED_USER == true}">
    <c:redirect url="HomeControllerServlet" />
</c:if>
<!DOCTYPE html>
<html lang="en">

    <%@include file="head.jsp" %>

    <body>
        <%@include file="header.jsp" %>
        <%@include file="cart.jsp" %>
        <!-- ##### Blog Wrapper Area Start ##### -->
        <div class="single-blog-wrapper">
            <div class="account__image">
                <img src="app/img/bg-img/bg-account.png"/>
            </div>
            <div class="container">
                <div class="row justify-content-center login-form">

                    <div class="col-md-6 col-md-offset-3">
                        <div class="panel panel-login">
                            <div class="panel-heading">
                                <div class="row p-3 m-0">
                                    <div class="col-sm-6">
                                        <a href="#" class="active" id="login-form-link">welcome back, log in</a>
                                    </div>
                                    <div class="col-sm-6">
                                        <a href="#" id="register-form-link">Create new account</a>
                                    </div>
                                </div>
                                <hr>
                            </div>
                            <div class="panel-body">
                                <div class="row p-3 m-0">
                                    <div class="col-lg-12">

                                        <form id="login-form" action="AccountControllerServlet" method="post" role="form" style="display: block;" autocomplete="off">
                                            <c:if test="${param.success eq 1}">
                                                <div class="alert alert-success text-center alert-dismissible fade show" role="alert">
                                                    You have registered successfully.
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty errorMessage}">
                                                <div class="alert alert-danger text-center alert-dismissible fade show" role="alert">
                                                    ${errorMessage}
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <c:remove var="errorMessage" />
                                            </c:if>
                                            <h4 class="text-center hp_color">Login to</h4>
                                            <h1 class="text-center hp_color">Your account</h1>
                                            <div class="form-group">
                                                <input type="hidden" name="command" value="LOGIN" />
                                                <input type="text" name="email" id="email" tabindex="1" class="form-control" placeholder="Email" value="">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                                            </div>
                                            <div class="form-group text-center">
                                                <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                                <label for="remember" class="hp_color"> Remember Me</label>
                                            </div>
                                            <div class="form-group">
                                                <div class="row justify-content-center align-items-center">
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="text-center">
                                                            <a href="#" tabindex="5" class="forgot-password">Forgot Password?</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <form id="register-form" action="AccountControllerServlet" method="post" role="form" style="display: none;" autocomplete="off">
                                            <div class="form-group">
                                                <input type="hidden" name="command" value="REGISTER" />
                                                <input type="text" name="fullname" id="username" tabindex="1" class="form-control" placeholder="Full Name" value="" required="">
                                            </div>
                                            <div class="form-group">
                                                <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="" required="">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
                                            </div>
                                            <div class="form-group">
                                                <div class="row justify-content-center align-items-center">
                                                    <p class="text-center">By creating an account, I agree to the <a href="" class="hp_color">Terms & Conditions</a> and I acknowledge that I have read and understand the <a href="#" class="hp_color">Privacy Policy</a>.</p>
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Blog Wrapper Area End ##### -->

        <%@include file="footer.jsp" %>

    </body>

</html>