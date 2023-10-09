<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>DoFPS-RTP</title>
    <!-- Vendor styles -->
    <link rel="stylesheet" href="<c:url value="/resources/framework/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/framework/vendors/bower_components/animate.css/animate.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/framework/vendors/bower_components/jquery.scrollbar/jquery.scrollbar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/framework/vendors/bower_components/fullcalendar/dist/fullcalendar.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/sweetalert/sweetalert.css"/>"/>
    <!-- App styles -->
    <link rel="stylesheet" href="<c:url value="/resources/framework/css/app.min.css"/>"/>
</head>
<body data-sa-theme="3">
<main class="main">
    <header class="header">
        <div class="navigation-trigger hidden-xl-up" data-sa-action="aside-open" data-sa-target=".sidebar">
            <i class="zmdi zmdi-menu"></i>
        </div>
        <div class="logo hidden-sm-down">
            <img src="<c:url value="/resources/images/DOFLogo2.png"/>" style="width: 105px; height: 108%">
            <h1><a href="#">Department of Forest and Park Services</a></h1>
        </div>
         <%--   <form class="search">
                div class="search__inner">
                    <input type="text" class="search__text" placeholder="Search for people, files, documents...">
                    <i class="zmdi zmdi-search search__helper" data-sa-action="search-close"></i>
                </div>
            </form>--%>
            <div style="padding-left:350px;">
                <input type="text" id="appNo" style="width:250px;">
                <a class="searchAppDtls" data-sa-action="search-open"><i class="zmdi zmdi-search"></i></a>
            </div>
            <ul class="top-nav">
                <%--<li class="hidden-xl-up"><a href="#" data-sa-action="search-open"><i class="zmdi zmdi-search"></i></a></li>--%>
                <li class="dropdown hidden-xs-down">
                    <a href="#" data-toggle="dropdown"><i class="zmdi zmdi-check-circle"></i></a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu--block-sm" role="menu">
                        <div class="listview listview--hover">
                            <a href="#" class="view-more">User</a>
                        </div>
                        <div class="listview listview--hover">
                            <a href="<c:url value="/common/logout"/>" class="view-more">Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
    </header>
    <aside class="sidebar">
        <div class="scrollbar-inner">
            <ul class="navigation">
                <li class="navigation__active"><a href="${pageContext.request.contextPath}/public/"><i class="zmdi zmdi-home"></i> Home</a></li>
                <%--<li class="navigation__sub @@variantsactive">
                    <a href="#"><i class="zmdi zmdi-view-week"></i> Applications <i class="ml-2 zmdi zmdi-caret-down"></i></a>
                    <ul>
                        <li class="@@sidebaractive"><a href="${pageContext.request.contextPath}/public/initiate/citizenDashboard?cons=n" target="_self">New Construction</a>
                            <ul>
                                <li class="@@sidebaractive ml-5"><a href="hidden-sidebar.html">Permit Extension</a></li>
                            </ul>
                        </li>
                        <li class="@@boxedactive"><a href="${pageContext.request.contextPath}/public/initiate/citizenDashboard?cons=r">Renovation</a></li>
                        <li class="@@hiddensidebarboxedactive"><a href="${pageContext.request.contextPath}/public/initiate/citizenDashboard?cons=o">Other Construction</a></li>
                    </ul>
                </li>--%>
                <li class="navigation__sub @@variantsactive">
                    <a href="#"><i class="zmdi zmdi-view-week"></i> Report <i class="ml-2 zmdi zmdi-caret-down"></i></a>
                </li>
            </ul>
        </div>
    </aside>
</main>
<!-- Vendors -->
<script type="text/javascript" src="<c:url value="/resources/jquery/jQuery-3.4.1.min.js"/>"></script>
<script src="<c:url value='/resources/framework/vendors/bower_components/popper.js/dist/umd/popper.min.js' />"></script>
<script src="<c:url value='/resources/framework/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/resources/framework/vendors/bower_components/jquery.scrollbar/jquery.scrollbar.min.js' />"></script>
<script src="<c:url value='/resources/framework/vendors/bower_components/jquery-scrollLock/jquery-scrollLock.min.js' />"></script>
<script src="<c:url value='/resources/sweetalert/sweetalert.min.js' />"></script>

<!-- App functions and actions -->
<script src="<c:url value="/resources/dofps.js"/>"></script>
<script src="<c:url value='/resources/framework/js/app.min.js' />"></script>
<script src="<c:url value='/resources/jquery/jquery.validate.js'/>"></script>

<script>
     $('.searchAppDtls').on('click',function(){
         window.open('${pageContext.request.contextPath}/track/initiate/getApplicationDetails?applicationNo=' + $('#appNo').val());
     });
</script>
</body>
</html>
