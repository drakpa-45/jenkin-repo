<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deepak
  Date: 5/16/19
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript" src="<c:url value="/resources/jquery/jQuery-3.4.1.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/dist/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/dist/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/dist/dataTables.bootstrap4.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/dist/css/component-custom-switch.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/chosen.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/fontAwosome/css/fontawesome.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/fontAwosome/css/fontawesome.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/fontAwosome/css/all.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/fontAwosome/css/all.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/fonts/fontawesome-webfont.woff2"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/fonts/fontawesome-webfont.woff"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/fonts/fontawesome-webfont.ttf"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/assets/css/nprogress.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/assets/css/sweetalert/sweetalert.css"/>"/>

<style>
    img {
        vertical-align: middle;
        border-style: none;
        padding-top: 9px;
    }
    .pb-4, .py-4 {
        padding-bottom: 0.5rem !important;
    }
    .pt-4, .py-4 {
        padding-top: 0.7rem !important;
    }
    .chosen-container-single .chosen-single {
        height: 35px;
        border-radius: 3px;
        border: 1px solid #CCCCCC;
    }
    .dropdown-submenu {
        position: relative;
    }
    .dropdown-item {
        color: navajowhite;
    }
    .header {
        background-color: #484848;
    }
    .d-flex {
        margin-left: 25px;
    }
    .dropdown {
        margin-right: 25px;
        margin-top: 22px;
    }
    .ml-2 {
        font-size: 17px;
    }
    .col-12 {
        margin-top: -9px;
    }
    .dropdown-submenu a::after {
        transform: rotate(-90deg);
        position: absolute;
        right: 6px;
        top: 0.8em;
    }
    .dropdown-submenu .dropdown-menu {
        top: 0;
        left: 100%;
        margin-left: 0.1rem;
        margin-right: 0.1rem;
    }
    * {
        margin: 0px;
        padding: 0px;
    }
    .dropdown-menu {
        margin: 0px;
        padding: 0px;
        background-color: #343a40;
    }
    .dropdown-sub-menu {
        background-color: #343a40;
        margin-left: 100%!important;
    }
    .has-dropdown {
        width: 100%;
    }
    .dropdown-sub-menu li {
        width: 100%;
    }
    #navbarSupportedContent ul {
        list-style: none;
    }
    #navbarSupportedContent ul li {
        height: 40px;
        float: left;
        position: relative;
    }
    #navbarSupportedContent ul li a {
        text-decoration: none;
        display: block;
    }
    #navbarSupportedContent ul li a:hover {
        color: #13ab8f;
        /* font-weight: bold;*/
    }
    #navbarSupportedContent ul ul {
        position: absolute;
        display: none;
    }
    #navbarSupportedContent ul li:hover > ul {
        display: block;
    }
    #navbarSupportedContent ul ul ul {
        margin-left: 177px;
        top: 0px;
    }
    .banner-heading {
        font-family: 'Libre Baskerville', serif;
        font-size: 30px;
        line-height: 25px;
    }
    .banner-sub-heading {
        font-family: 'Libre Baskerville', serif;
        font-size: 25px;
        line-height: 25px;
    }
    .footer {
        position: fixed;
        bottom: 0;
        width: 100%;
        background: #1b1d29;
        color: #ffffff;
        clear: both;
        padding: 15px 0;
    }
    .card {
        min-height: 168px;
    }
</style>