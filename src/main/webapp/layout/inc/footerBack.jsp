<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
&lt;%&ndash; Created by IntelliJ IDEA. User: deepak Date: 5/16/19 Time: 6:40 PM To change this template use File | Settings | File Templates. &ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .footer {
    position: fixed;
    bottom: -30px;
    width: 100%;
    background: #374649;
    color: #ffffff;
    clear: both;
    padding: 15px 0;
} </style>
<footer class="footer">
    <div class="container col-lg-12 col-md-12 col-sm-12">
    &lt;%&ndash;<div class="row align-items-center flex-row-reverse"> <div class="col-auto ml-lg-auto">
    <div class="row align-items-center"> </div> </div>
     <div class="col-12 col-lg-auto mt-3 mt-lg-0 text-center"> Copyright © 2020 G2C Project Office | OFFICE OF THE PRIME MINISTER.
     </div> </div>&ndash;%&gt;
        <div class="column col-lg-3 col-md-3 col-sm-3"><h2 class="text-white">Useful Links</h2>
            <ul>
                <li><a href="http://www.btcirt.bt" title="">Bhutan Computer Incident Response Team</a></li>
                <li><a href="https://www.gov.bt/covid19/" title="">COVID-19 Information Link</a></li>
                <li><a href="http://scs.rbp.gov.bt/" title="">Citizen Security Clearance Portal</a></li>
                <li><a href="https://www.citizenservices.gov.bt/home" title="">Citizen Services Portal</a></li>
                <li><a href="http://www.lesspaper.gov.bt" title="">Learning site for Government Email</a></li>
                <li><a href="http://www.moh.gov.bt/" title="">Ministry of Health</a></li>
                <li><a href="http://www.moic.gov.bt" title="">Ministry of Information and Communication</a></li>
                <li><a href="https://egif.dit.gov.bt" title="">eGIF Portal</a></li>
            </ul>
            <!-- /.region -->
        </div>
        <div class="column col-lg-2 col-md-2 col-sm-2">
            <div id="rgob"><img src="${pageContext.request.contextPath}/resources/images/India-Bhutan.jpg" align="left" style="width:250px;"/></div>
        </div>
        <div class="column  col-lg-3 col-md-3 col-sm-3"><h2 class="text-white">Address</h2>
            <p id="address">Department of IT and Telecom,<br> Ministry of Information and Communications,<br> Thori
                Lam,Upper Chubachu,<br> Thimphu, Bhutan.<br> Tel: +975-02-323215<br> Fax: +975-02-328440<br> Post box :482 </p></div>
        <div class="column col-lg-3 col-md-3 col-sm-3">
            <p>© 2023 G2C Project Office | OFFICE OF THE PRIME MINISTER. All Rights Reserved.</p>
            <p>Developed by: <b><a href="https://yangkhor.com" title="YPL" target="_blank">Yangkhor Private Limited, @2023 </a></b></p>
        </div>
       &lt;%&ndash; <div class="container col-lg-12 col-md-12 col-sm-12" style="margin-left:185px;">
            <div class="footer_credit">
                <div id="copyright" class="full-wrap clearfix">
                    <p>© 2023 G2C Project Office | OFFICE OF THE PRIME MINISTER. All Rights Reserved.</p>
                    <p>Developed by: <b><a href="https://yangkhor.com" title="YPL" target="_blank">Yangkhor Private Limited, @2023 </a></b></p>
                </div>
                &lt;%&ndash; <div class="credits">
                     Design by  <b>Yangkhor Private Limited, @2023 </b>
                 </div>&ndash;%&gt;
            </div>
        </div>&ndash;%&gt;
    </div>
</footer>


&lt;%&ndash;
<footer class="footer hidden-xs-down" style="position: fixed;bottom: 0;width: 100%">
    <marquee>Copyright © 2023 G2C Project Office | OFFICE OF THE PRIME MINISTER.</marquee>
</footer>&ndash;%&gt;
--%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />

<style>
    body { color: #ccc }
    .footer-widget p {
        margin-bottom: 27px;
    }
    p {
        font-family: 'Nunito', sans-serif;
        font-size: 16px;
        line-height: 28px;
    }

    .animate-border {
        position: relative;
        display: block;
        width: 115px;
        height: 3px;
        background: #007bff; }

    .animate-border:after {
        position: absolute;
        content: "";
        width: 35px;
        height: 3px;
        left: 0;
        bottom: 0;
        border-left: 10px solid #fff;
        border-right: 10px solid #fff;
        -webkit-animation: animborder 2s linear infinite;
        animation: animborder 2s linear infinite; }

    @-webkit-keyframes animborder {
        0% {
            -webkit-transform: translateX(0px);
            transform: translateX(0px); }
        100% {
            -webkit-transform: translateX(113px);
            transform: translateX(113px); } }

    @keyframes animborder {
        0% {
            -webkit-transform: translateX(0px);
            transform: translateX(0px); }
        100% {
            -webkit-transform: translateX(113px);
            transform: translateX(113px); } }

    .animate-border.border-white:after {
        border-color: #fff; }

    .animate-border.border-yellow:after {
        border-color: #F5B02E; }

    .animate-border.border-orange:after {
        border-right-color: #007bff;
        border-left-color: #007bff; }

    .animate-border.border-ash:after {
        border-right-color: #EEF0EF;
        border-left-color: #EEF0EF; }

    .animate-border.border-offwhite:after {
        border-right-color: #F7F9F8;
        border-left-color: #F7F9F8; }

    /* Animated heading border */
    @keyframes primary-short {
        0% {
            width: 15%; }
        50% {
            width: 90%; }
        100% {
            width: 10%; } }

    @keyframes primary-long {
        0% {
            width: 80%; }
        50% {
            width: 0%; }
        100% {
            width: 80%; } }

    .dk-footer {
        padding: 75px 0 0;
        background-color: #151414;
        position: relative;
        z-index: 2; }
    .dk-footer .contact-us {
        margin-top: 0;
        margin-bottom: 30px;
        padding-left: 80px; }
    .dk-footer .contact-us .contact-info {
        margin-left: 50px; }
    .dk-footer .contact-us.contact-us-last {
        margin-left: -80px; }
    .dk-footer .contact-icon i {
        font-size: 24px;
        top: -15px;
        position: relative;
        color:#007bff; }

    .dk-footer-box-info {
       /* position: absolute;*/
        top: -122px;
        background: #202020;
        padding: 40px;
        z-index: 2; }
    .dk-footer-box-info .footer-social-link h3 {
        color: #fff;
        font-size: 24px;
        margin-bottom: 25px; }
    .dk-footer-box-info .footer-social-link ul {
        list-style-type: none;
        padding: 0;
        margin: 0; }
    .dk-footer-box-info .footer-social-link li {
        display: inline-block; }
    .dk-footer-box-info .footer-social-link a i {
        display: block;
        width: 40px;
        height: 40px;
        border-radius: 50%;
        text-align: center;
        line-height: 40px;
        background: #000;
        margin-right: 5px;
        color: #fff; }
    .dk-footer-box-info .footer-social-link a i.fa-facebook {
        background-color: #3B5998; }
    .dk-footer-box-info .footer-social-link a i.fa-twitter {
        background-color: #55ACEE; }
    .dk-footer-box-info .footer-social-link a i.fa-google-plus {
        background-color: #DD4B39; }
    .dk-footer-box-info .footer-social-link a i.fa-linkedin {
        background-color: #0976B4; }
    .dk-footer-box-info .footer-social-link a i.fa-instagram {
        background-color: #B7242A; }

    .footer-awarad {
        margin-top: 285px;
        display: -webkit-box;
        display: -webkit-flex;
        display: -moz-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-flex: 0;
        -webkit-flex: 0 0 100%;
        -moz-box-flex: 0;
        -ms-flex: 0 0 100%;
        flex: 0 0 100%;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -moz-box-align: center;
        -ms-flex-align: center;
        align-items: center; }
    .footer-awarad p {
        color: #fff;
        font-size: 12px;
        font-weight: 700;
        margin-left: 20px;
        padding-top: 10px; }

    .footer-info-text {
        margin: 26px 0 32px; }

    .footer-left-widget {
        padding-left: 80px; }

    .footer-widget .section-heading {
        margin-bottom: 35px; }

    .footer-widget h3 {
        font-size: 24px;
        color: #fff;
        position: relative;
        margin-bottom: 15px;
        max-width: -webkit-fit-content;
        max-width: -moz-fit-content;
        max-width: fit-content; }

    .footer-widget ul {
        width: 50%;
        float: left;
        list-style: none;
        margin: 0;
        padding: 0; }

    .footer-widget li {
        margin-bottom: 18px; }

    .footer-widget p {
        margin-bottom: 27px; }

    .footer-widget a {
        color: #878787;
        -webkit-transition: all 0.3s;
        -o-transition: all 0.3s;
        transition: all 0.3s; }
    .footer-widget a:hover {
        color: #007bff; }

    .footer-widget:after {
        content: "";
        display: block;
        clear: both; }

    .dk-footer-form {
        position: relative; }
    .dk-footer-form input[type=email] {
        padding: 14px 28px;
        border-radius: 50px;
        background: #2E2E2E;
        border: 1px solid #2E2E2E; }
    .dk-footer-form input::-webkit-input-placeholder, .dk-footer-form input::-moz-placeholder, .dk-footer-form input:-ms-input-placeholder, .dk-footer-form input::-ms-input-placeholder, .dk-footer-form input::-webkit-input-placeholder {
        color: #878787;
        font-size: 14px; }
    .dk-footer-form input::-webkit-input-placeholder, .dk-footer-form input::-moz-placeholder, .dk-footer-form input:-ms-input-placeholder, .dk-footer-form input::-ms-input-placeholder, .dk-footer-form input::placeholder {
        color: #878787;
        font-size: 14px; }
    .dk-footer-form button[type=submit] {
        position: absolute;
        top: 0;
        right: 0;
        padding: 12px 24px 12px 17px;
        border-top-right-radius: 25px;
        border-bottom-right-radius: 25px;
        border: 1px solid #007bff;
        background: #007bff;
        color: #fff; }
    .dk-footer-form button:hover {
        cursor: pointer; }

    /* ==========================

        Contact

    =============================*/
    .contact-us {
        position: relative;
        z-index: 2;
        margin-top: 65px;
        display: -webkit-box;
        display: -webkit-flex;
        display: -moz-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -moz-box-align: center;
        -ms-flex-align: center;
        align-items: center; }

    .contact-icon {
        position: absolute; }
    .contact-icon i {
        font-size: 36px;
        top: -5px;
        position: relative;
        color: #007bff; }

    .contact-info {
        margin-left: 75px;
        color: #fff; }
    .contact-info h3 {
        font-size: 20px;
        color: #fff;
        margin-bottom: 0; }

    .copyright {
        padding: 28px 0;
        margin-top: 55px;
        background-color: #202020; }
    .copyright span,
    .copyright a {
        color: #878787;
        -webkit-transition: all 0.3s linear;
        -o-transition: all 0.3s linear;
        transition: all 0.3s linear; }
    .copyright a:hover {
        color:#007bff; }

    .copyright-menu ul {
        text-align: right;
        margin: 0; }

    .copyright-menu li {
        display: inline-block;
        padding-left: 20px; }

    .back-to-top {
        position: relative;
        z-index: 2; }
    .back-to-top .btn-dark {
        width: 35px;
        height: 35px;
        border-radius: 50%;
        padding: 0;
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: #2e2e2e;
        border-color: #2e2e2e;
        display: none;
        z-index: 999;
        -webkit-transition: all 0.3s linear;
        -o-transition: all 0.3s linear;
        transition: all 0.3s linear; }
    .back-to-top .btn-dark:hover {
        cursor: pointer;
        background: #FA6742;
        border-color: #FA6742; }
</style>
<footer id="dk-footer" class="dk-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <div class="row">
                    <div class="col-md-3 col-lg-3">
                        <div class="contact-us">
                            <div class="contact-icon">
                                <i class="fa fa-map-o" aria-hidden="true"></i>
                            </div>
                            <!-- End contact Icon -->
                            <div class="contact-info">
                                <h4>Address</h4>
                                <p>Department of IT and Telecom, Ministry of Information and Communications,Thori Lam Chubachu,Thimphu, Bhutan.</p>
                            </div>
                            <!-- End Contact Info -->
                        </div>
                        <!-- End Contact Us -->
                    </div>
                    <div class="col-md-3 col-lg-3">
                        <div class="dk-footer-box-info">
                            <a href="index.html" class="footer-logo">
                                <img src="${pageContext.request.contextPath}/resources/images/India-Bhutan.jpg" alt="footer_logo" class="img-fluid">
                            </a>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="contact-us contact-us-last">
                            <div class="contact-icon">
                                <i class="fa fa-volume-control-phone" aria-hidden="true"></i>
                            </div>
                            <!-- End contact Icon -->
                            <div class="contact-info">
                                <h3> Tel: +975-02-323215</h3>
                                <p>Fax: +975-02-328440<br> Post box :482</p>
                            </div>
                            <!-- End Contact Info -->
                        </div>
                        <!-- End Contact Us -->
                    </div>
                    <div class="col-md-4 col-lg-4">
                        <div class="footer-widget footer-left-widget">
                            <div class="section-heading">
                                <h3>Useful Links</h3>
                                <span class="animate-border border-black"></span>
                            </div>
                            <ul>
                                <li><a href="http://www.btcirt.bt" title="">Bhutan Computer Incident Response Team</a></li>
                                <li><a href="https://www.gov.bt/covid19/" title="">COVID-19 Information Link</a></li>
                            </ul>
                            <ul>
                                <li><a href="http://scs.rbp.gov.bt/" title="">Citizen Security Clearance Portal</a></li>
                                <li><a href="https://www.citizenservices.gov.bt/home" title="">Citizen Services Portal</a></li>
                            </ul>
                        </div>
                        <!-- End Footer Widget -->
                    </div>
                    <!-- End Col -->
                </div>
                <!-- End Contact Row -->
                <%--<div class="row">
                    &lt;%&ndash;<div class="col-md-12 col-lg-6">
                        <div class="footer-widget footer-left-widget">
                            <div class="section-heading">
                                <h3>Useful Links</h3>
                                <span class="animate-border border-black"></span>
                            </div>
                            <ul>
                                <li><a href="http://www.btcirt.bt" title="">Bhutan Computer Incident Response Team</a></li>
                                <li><a href="https://www.gov.bt/covid19/" title="">COVID-19 Information Link</a></li>
                                <li><a href="http://scs.rbp.gov.bt/" title="">Citizen Security Clearance Portal</a></li>
                                <li><a href="https://www.citizenservices.gov.bt/home" title="">Citizen Services Portal</a></li>
                            </ul>
                            <ul>
                                <li><a href="http://www.lesspaper.gov.bt" title="">Learning site for Government Email</a></li>
                                <li><a href="http://www.moh.gov.bt/" title="">Ministry of Health</a></li>
                                <li><a href="http://www.moic.gov.bt" title="">Ministry of Information and Communication</a></li>
                                <li><a href="https://egif.dit.gov.bt" title="">eGIF Portal</a></li>
                            </ul>
                        </div>
                        <!-- End Footer Widget -->
                    </div>&ndash;%&gt;
                    <!-- End col -->
                    <div class="col-md-12 col-lg-6">
                        <div class="footer-widget">
                            <div class="section-heading">
                                <h3>Subscribe</h3>
                                <span class="animate-border border-black"></span>
                            </div>
                            <p><!-- Don’t miss to subscribe to our new feeds, kindly fill the form below. -->
                                Reference site about Lorem Ipsum, giving information on its origins, as well.</p>
                            <form action="#">
                                <div class="form-row">
                                    <div class="col dk-footer-form">
                                        <input type="email" class="form-control" placeholder="Email Address">
                                        <button type="submit">
                                            <i class="fa fa-send"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <!-- End form -->
                        </div>
                        <!-- End footer widget -->
                    </div>
                    <!-- End Col -->
                </div>--%>
                <!-- End Row -->
            </div>
            <!-- End Col -->
        </div>
        <!-- End Widget Row -->
    </div>
    <!-- End Contact Container -->


    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                   <%-- <span>Copyright © 2019, All Right Reserved Seobin</span>
                    <span>Developed By: Yangkhor Private Limited, 2023</span>--%>
                    <span>© 2023 G2C Project Office | OFFICE OF THE PRIME MINISTER. All Rights Reserved.</span>
                    <span>Developed by: <b><a href="https://yangkhor.com" title="YPL" target="_blank">Yangkhor Private Limited, @2023 </a></b></span>
                </div>
                <!-- End Col -->
                <div class="col-md-6">
                    <div class="copyright-menu">
                        <ul>
                            <li>
                                <a href="#">Home</a>
                            </li>
                            <li>
                                <a href="#">Terms</a>
                            </li>
                            <li>
                                <a href="#">Privacy Policy</a>
                            </li>
                            <li>
                                <a href="#">Contact</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- End col -->
            </div>
            <!-- End Row -->
        </div>
        <!-- End Copyright Container -->
    </div>
    <!-- End Copyright -->
    <!-- Back to top -->
    <div id="back-to-top" class="back-to-top">
        <button class="btn btn-dark" title="Back to Top" style="display: block;">
            <i class="fa fa-angle-up"></i>
        </button>
    </div>
    <!-- End Back to top -->
</footer>
