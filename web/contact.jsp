<%-- 
    Document   : contact
    Created on : 01/06/2020, 16:49:47
    Author     : ricardo.braz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

    <%@include file="head.jsp" %>

    <body>
        <%@include file="header.jsp" %>

        <%@include file="cart.jsp" %>

        <!-- ##### Breadcumb Area Start ##### -->
        <div class="breadcumb_area breadcumb-style-two bg-img" style="background-image: url(app/img/bg-img/contactus.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="page-title text-center">
                            <h2>Contact Us</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Breadcumb Area End ##### -->


        <div class="contact-area d-flex align-items-center">

            <div class="google-map">
                <div id="googleMap" style="width:100%;height:400px;"></div>
            </div>
            <script>
                function myMap() {
                    var myLatLng = {lat: 41.1494723, lng: -8.6031946};

                    var map = new google.maps.Map(document.getElementById('googleMap'), {
                        zoom: 18,
                        center: myLatLng
                    });

                    var marker = new google.maps.Marker({
                        position: myLatLng,
                        map: map,
                        title: 'ISTEC PORTO'
                    });
                }

            </script>

            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDgAbUAIn5vUx4PkR4MLyxztpZVqfKpbsc&callback=myMap"></script>

            <div class="contact-info">
                <c:if test="${not empty successMessage}">
                                <div class="alert alert-success text-center alert-dismissible fade show" role="alert">
                                    ${successMessage}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <c:remove var="successMessage" />
                            </c:if>
                <!--Section: Contact v.2-->
                <section class="mb-4">

                    <!--Section heading-->
                    <h2 class="h1-responsive font-weight-bold text-center my-4">Contact us</h2>
                    <!--Section description-->
                    <p class="text-center w-responsive mx-auto mb-5">Do you have any questions? Please do not hesitate to contact us directly. Our team will come back to you within
                        a matter of hours to help you.</p>

                    <div class="row">
                        

                        <!--Grid column-->
                        <div class="col-md-9 mb-md-0 mb-5">
                            
                            <form id="contact-form" name="contact-form" method="post" autocomplete="off" onsubmit="sendEmail()">
                                <input type="hidden" name="command" value="CONTACT"/>
                                <!--Grid row-->
                                <div class="row">

                                    <!--Grid column-->
                                    <div class="col-md-6">
                                        <div class="md-form mb-0">
                                            <input type="text" id="name" name="name" class="form-control" required>
                                            <label for="name" class="">Your name</label>
                                        </div>
                                    </div>
                                    <!--Grid column-->

                                    <!--Grid column-->
                                    <div class="col-md-6">
                                        <div class="md-form mb-0">
                                            <input type="email" id="email" name="email" class="form-control" required>
                                            <label for="email" class="">Your email</label>
                                        </div>
                                    </div>
                                    <!--Grid column-->

                                </div>
                                <!--Grid row-->

                                <!--Grid row-->
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="md-form mb-0">
                                            <input type="text" id="subject" name="subject" class="form-control" required>
                                            <label for="subject" class="">Subject</label>
                                        </div>
                                    </div>
                                </div>
                                <!--Grid row-->

                                <!--Grid row-->
                                <div class="row">

                                    <!--Grid column-->
                                    <div class="col-md-12">

                                        <div class="md-form">
                                            <textarea type="text" id="message" name="message" rows="2" class="form-control md-textarea" required></textarea>
                                            <label for="message">Your message</label>
                                        </div>

                                    </div>
                                </div>
                                <!--Grid row-->

                            </form>

                            <div class="text-center text-md-left">
                                <input type="submit" form="contact-form" class="form-control btn btn-login" value="Send"/>
                            </div>
                            <div class="status"></div>
                        </div>
                        <!--Grid column-->

                        <!--Grid column-->
                        <div class="col-md-3 text-center">
                            <ul class="list-unstyled mb-0">
                                <li><i class="fas fa-map-marker-alt fa-2x"></i>
                                    <p class="text-muted small">Rua Dr. Alves da Veiga nº142 4000-072 Porto</p>
                                </li>

                                <li><i class="fas fa-phone mt-4 fa-2x"></i>
                                    <p class="text-muted small">225 193 220</p>
                                </li>

                                <li><i class="fas fa-envelope mt-4 fa-2x"></i>
                                    <p class="text-muted small">hpstore@istec.pt</p>
                                </li>
                            </ul>
                        </div>
                        <!--Grid column-->

                    </div>

                </section>
                <!--Section: Contact v.2-->
            </div>

        </div>

        <%@include file="footer.jsp" %>
    </body>
    <script>
        function sendEmail(){formData={command:$("input[name=command]").val(),name:$("input[name=name]").val(),email:$("input[name=email]").val(),subject:$("input[name=subject]").val(),message:$("textarea[name=message]").val()},$.ajax({url:"HomeControllerServlet",type:"POST",data:formData,success:function(a,e,t){$("#status").text(a.message),a.code&&$("#contact-form").closest("form").find("input[type=text], textarea").val("")},error:function(a,e,t){$("#status").text(a)}})}
    </script>
</html>