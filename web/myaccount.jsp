<%-- 
    Document   : myaccount
    Created on : 18/05/2020, 17:42:38
    Author     : ricardo.braz
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${THE_ACCOUNT.isTemporary == true}">
    <c:redirect url="HomeControllerServlet" />
</c:if>
<!DOCTYPE html>
<html lang="en">

    <%@include file="head.jsp" %>

    <body>
        <%@include file="header.jsp" %>

        <%@include file="cart.jsp" %>

        <!-- ##### Breadcumb Area Start ##### -->
        <div class="breadcumb_area breadcumb-style-two bg-img" style="background-image: url(app/img/bg-img/breadcumb2.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="page-title text-center">
                            <h2>My Account</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Breadcumb Area End ##### -->

        <!-- ##### Blog Wrapper Area Start ##### -->
        <div class="blog-wrapper section-padding-80">
            <div class="container max-width-container">
                <div class="row">

                    <!-- Single Blog Area -->
                    <div class="col-md-12 col-md-2">
                        <div class="single-blog-area mb-50">
                            <section id="post-your-event-tabs">
                                <div class="container max-width-container">
                                    <blockquote class="blockquote text-center">
                                        <p class="mb-0  gold-text">Curiosity is not a sin?. But we should exercise caution with our curiosity? yes, indeed.</p>
                                        <footer class="blockquote-footer"><cite>Albus Dumbledore</cite></footer>
                                    </blockquote>
                                    <!-- Nav pills -->
                                    <ul class="nav nav-pills nav-justified" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active font uppercase" id="organizer-event-tab" data-toggle="tab" href="#organizer-event" role="tab" aria-controls="organizer-event" aria-selected="true">Account Details</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link font uppercase" id="event-details-tab" data-toggle="tab" href="#event-details" role="tab" aria-controls="event-details" aria-selected="false">Order History</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link font uppercase" id="confirm-details-tab" data-toggle="tab" href="#confirm-details" role="tab" aria-controls="confirm-details" aria-selected="false">Payment Method</a>
                                        </li>
                                    </ul>
                                    <!-- Tab panes -->
                                    <div class="tab-content py-3 px-3 px-sm-0 events-tab-content">
                                        <div class="tab-pane fade show active" id="organizer-event" role="tabpanel" aria-labelledby="organizer-event-tab">
                                            <form method="post" action="AccountControllerServlet" class="seminor-login-form ges-location-from">
                                                <input type="hidden" name="command" value="UPDATE" />
                                                <input type="hidden" name="accountId" value="${THE_ACCOUNT.id}" />
                                                <div class="form-group">
                                                    <label for="name">Full Name</label>
                                                    <input type="text" name="name" value="${THE_ACCOUNT.name}" placeholder="Enter you full name" class="form-control ges-form-control" required autocomplete="off">
                                                </div>

                                                <div class="form-group">
                                                    <label for="email">Email</label>
                                                    <input type="email" name="email" value="${THE_ACCOUNT.email}" readonly placeholder="Enter your email" class="form-control ges-form-control" required autocomplete="off">
                                                </div>

                                                <div class="form-group">
                                                    <label for="phone">Phone</label>
                                                    <input type="text" name="phone" value="${THE_ACCOUNT.phone}" placeholder="Enter you phone number" class="form-control ges-form-control" autocomplete="off">
                                                </div>

                                                <div class="form-group">
                                                    <label  for="address">Address</label>
                                                    <input type="text" name="address" value="${THE_ACCOUNT.address}" placeholder="Enter your billing address" class="form-control ges-form-control" autocomplete="off">
                                                </div>

                                                <div class="btn-check-log text-center">
                                                    <button type="submit" class="btn btn-login pr-4 pl-4">Save</button>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="tab-pane fade" id="event-details" role="tabpanel" aria-labelledby="event-details-tab">
                                            <ul class="post-final-submit">
                                                <c:choose>
                                                    <c:when test="${ORDER_LIST == null}">
                                                        <li><p>You haven't placed any orders yet.</p></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li><table class="table table-borderless text-center">
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col"><i class="fas fa-hashtag"></i>&nbsp;Order Number</th>
                                                                        <th scope="col"><i class="far fa-calendar-check"></i>&nbsp;Date Ordered</th>
                                                                        <th scope="col"><i class="fas fa-truck-loading"></i>&nbsp;Order Status</th>
                                                                        <th scope="col"><i class="fas fa-map-marker-alt"></i>&nbsp;Ship Address</th>
                                                                        <th scope="col"><i class="far fa-calendar-check"></i>&nbsp;Date Delivered</th>
                                                                        <th scope="col">View details</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var="tempOrder" items="${ORDER_LIST}" >
                                                                        <!-- Set up a link for each payment -->
                                                                        <c:url var="orderLink" value="ReportServlet">
                                                                            <c:param name="orderId" value="${tempOrder.id}" />
                                                                        </c:url>
                                                                        <tr>
                                                                            <td>${tempOrder.id}</td>
                                                                            <td>${tempOrder.ordered}</td>
                                                                            <td>${tempOrder.status}</td>
                                                                            <td>${tempOrder.shipAddress}</td>
                                                                            <td>
                                                                                <c:choose>
                                                                                    <c:when test="${tempOrder.status == 'Shipped'}">
                                                                                        ${tempOrder.shipped}
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        -
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </td>
                                                                            <td><a href="${orderLink}"><i class="far fa-eye"></i></a></td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table></li>
                                                    </c:otherwise>
                                                </c:choose>
                                                
                                            </ul>
                                        </div>
                                        <div class="tab-pane fade" id="confirm-details" role="tabpanel" aria-labelledby="confirm-details-tab">
                                            <ul class="post-final-submit">
                                                <c:choose>
                                                    <c:when test="${PAYMENT_LIST == null}">
                                                        <li><p>You haven't placed any credit card information yet.</p></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><table class="table table-borderless text-center">
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col"><i class="far fa-credit-card"></i>&nbsp;Card Number</th>
                                                                        <th scope="col"><i class="far fa-calendar-check"></i>&nbsp;Date Expire</th>
                                                                        <th scope="col">Define as main card</th>
                                                                        <th scope="col">Delete</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var="tempPayment" items="${PAYMENT_LIST}" >
                                                                        <!-- Set up a link for each payment -->
                                                                        <c:url var="tempLink" value="PaymentControllerServlet">
                                                                            <c:param name="command" value="SETMAIN" />
                                                                            <c:param name="paymentId" value="${tempPayment.id}" />
                                                                        </c:url>
                                                                        <c:url var="deleteLink" value="PaymentControllerServlet">
                                                                            <c:param name="command" value="DELETE" />
                                                                            <c:param name="paymentId" value="${tempPayment.id}" />
                                                                        </c:url>
                                                                        <tr>
                                                                            <td>${tempPayment.cardNumber}</td>
                                                                            <td>${tempPayment.monthExpire}/${tempPayment.yearExpire}</td>
                                                                            <td>
                                                                                <c:choose>
                                                                                    <c:when test="${tempPayment.active == true}">
                                                                                        <p class="alert alert-success">Active</p>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <a href="${tempLink}">
                                                                                            <i class="far fa-check-circle"></i>
                                                                                        </a>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </td>
                                                                            <td><a href="${deleteLink}"><i class="far fa-trash-alt"></i></a></td>
                                                                        </tr>
                                                                    </c:forEach>

                                                                </tbody>
                                                            </table></li>
                                                        </c:otherwise>
                                                    </c:choose>

                                            </ul>
                                            <div class="btn-check-log text-center">
                                                <button type="button" class="btn btn-login pr-4 pl-4" data-toggle="collapse" 
                                                        data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">Add new credit card</button>
                                            </div>
                                            <div class="collapse" id="collapseExample">
                                                <div class="card card-body">
                                                    <!-- credit card info-->
                                                    <div id="nav-tab-card" class="tab-pane fade show active">
                                                        <p class="alert alert-success">Some text success or error</p>
                                                        <form role="form" method="post" action="PaymentControllerServlet">
                                                            <input type="hidden" name="userId" value="${THE_ACCOUNT.id}" />
                                                            <input type="hidden" name="command" value="CREATE" />
                                                            <div class="form-group">
                                                                <label for="username">Full name</label>
                                                                <input type="text" name="fullName" placeholder="Full name on the card" required class="form-control">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="cardNumber">Card number</label>
                                                                <div class="input-group">
                                                                    <input type="text" name="cardNumber" placeholder="Your card number" class="form-control" required>
                                                                    <div class="input-group-append">
                                                                        <span class="input-group-text text-muted">
                                                                            <i class="fab fa-cc-visa mx-1"></i>
                                                                            <i class="fab fa-cc-amex mx-1"></i>
                                                                            <i class="fab fa-cc-mastercard mx-1"></i>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-8">
                                                                    <div class="form-group">
                                                                        <label><span class="hidden-xs">Expiration</span></label>
                                                                        <div class="input-group">
                                                                            <input type="number" placeholder="MM" name="month" min="1" max="12" maxlength="2"
                                                                                   class="form-control" required>
                                                                            <input type="number" placeholder="YY" name="year" min="2020" max="2030" masxlength="4"  
                                                                                   class="form-control" required>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <div class="form-group mb-4">
                                                                        <label data-toggle="tooltip" title="Three-digits code on the back of your card">CVV
                                                                            <i class="fa fa-question-circle"></i>
                                                                        </label>
                                                                        <input type="number" name="cvv" maxlength="3" required class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <button type="submit" class="subscribe btn btn-login btn-block rounded-pill shadow-sm">Confirm</button>
                                                        </form>
                                                    </div>
                                                    <!-- End -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Blog Wrapper Area End ##### -->

        <%@include file="footer.jsp" %>

    </body>

</html>