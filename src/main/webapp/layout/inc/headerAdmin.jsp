<%@ page import="org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj" %>
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
    <link rel="stylesheet" href="<c:url value="/resources/menu/css/dofps.css"/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/nprogress.css' />"/>
    <link href="https://code.jquery.com/ui/1.12.1/themes/ui-lightness/jquery-ui.css" rel="stylesheet"/>
</head>
<% UserRolePrivilegeHierarchyObj user = (UserRolePrivilegeHierarchyObj) request.getSession().getAttribute("userdetail");
    Integer roleId=user.getUserRoles().getUserRole().get(0).getRoleId();
    String userName = user.getFullName();
%>

<body data-sa-theme="3">
<main class="main">
    <header class="header">
        <div class="navigation-trigger hidden-xl-up" data-sa-action="aside-open" data-sa-target=".sidebar">
            <i class="zmdi zmdi-menu"></i>
        </div>
        <div class="logo <%--hidden-sm-down--%> col-lg-10 col-md-10 col-sm-12 col-xs-12">
            <img src="<c:url value="/resources/images/DOFLogo2.png"/>" style="width: 105px; height: 108%">
            <h1><a href="#">Department of Forests and Park Services</a></h1>
        </div>
         <%--<form class="search">
                div class="search__inner">
                    <input type="text" class="search__text" placeholder="Search for people, files, documents...">
                    <i class="zmdi zmdi-search search__helper" data-sa-action="search-close"></i>
                </div>
            </form> --%>
           <%-- <div style="padding-left:350px;">
                <input type="text" id="appNo" style="width:250px;">
                <a class="searchAppDtls" data-sa-action="search-open"><i class="zmdi zmdi-search"></i></a>
            </div>--%>
            <ul class="top-nav">
                <%--<li class="hidden-xl-up"><a href="#" data-sa-action="search-open"><i class="zmdi zmdi-search"></i></a></li>--%>
                <li class="dropdown <%--hidden-xs-down--%>">
                    <a href="#" data-toggle="dropdown"><i class="zmdi zmdi-account-circle" style="font-size:xx-large"></i></a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu--block-sm" role="menu">
                        <div class="listview listview--hover">
                            <a href="#" class="view-more text-white"> <i class="zmdi zmdi-account"></i> &nbsp;<%=user.getFullName()%> (<%=user.getUserRoles().getUserRole().get(0).getRoleName()%>)</a>
                        </div>
                        <div class="listview listview--hover">
                            <a href="<c:url value="/common/logout"/>" class="view-more text-white"><i class="zmdi zmdi-lock-outline"></i> &nbsp; Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
    </header>
    <aside class="sidebar">
        <div class="scrollbar-inner">
                <input type="text" placeholder="Enter CID/App No..." id="app_No">
                <button type="button" class="btn text-white"  style="background-color: #d0802b" onclick="searchHistory()"><i class="zmdi zmdi-search"></i></button>
            <ul class="navigation">
                <li class="navigation__active"><a href="${pageContext.request.contextPath}/loginMain/"><i class="zmdi zmdi-home"></i> Home</a></li>
                <%if(roleId.equals(26086)){%>
                    <li class="navigation__sub @@variantsactive">
                        <a href="#">
                            <i class="zmdi zmdi-plus-circle-o"></i> &nbsp; Create Applications <i class="ml-2 zmdi zmdi-caret-down"></i></a>
                            <ul>
                                <li class="@@sidebaractive"><a href="${pageContext.request.contextPath}/gewog/loginMain/citizenDashboard?cons=n" target="_self"><i class="zmdi zmdi-caret-right"></i> &nbsp; New Construction</a>
                                    <ul>
                                        <li class="@@sidebaractive ml-5"><a href="${pageContext.request.contextPath}/gewog/loginMain/permitExtension">Permit Extension</a></li>
                                    </ul>
                                </li>
                                <li class="@@boxedactive"><a href="${pageContext.request.contextPath}/gewog/loginMain/citizenDashboard?cons=r"><i class="zmdi zmdi-caret-right"></i> &nbsp; Renovation</a></li>
                                <li class="@@hiddensidebarboxedactive"><a href="${pageContext.request.contextPath}//gewog/loginMain/citizenDashboard?cons=o"><i class="zmdi zmdi-caret-right"></i> &nbsp; Other Construction</a></li>
                                <li class="@@hiddensidebarboxedactive"><a href="${pageContext.request.contextPath}/public/initiate/privateLandPermit"><i class="zmdi zmdi-caret-right"></i> &nbsp; Removal of Forest Produce From Registered Land</a></li>
                                <li class="@@hiddensidebarboxedactive"><a href="${pageContext.request.contextPath}/public/initiate/firewoodAndFencingPoles?cons_desc=WP"><i class="zmdi zmdi-caret-right"></i> &nbsp; Firewood,Fencing Posts & Flag Poles</a></li>
                             </ul>
                        </li>
                    <li class="navigation__active"><a href="${pageContext.request.contextPath}/gewog/loginMain/timberReplacement"><i class="zmdi zmdi-device-hub"></i> &nbsp; Timber Replacement</a></li>
                    <li class="navigation__sub @@tableactive">
                        <a href="#"><i class="zmdi zmdi-plus-circle-o"></i> &nbsp; Scheduling Services <i class="ml-2 zmdi zmdi-caret-down"></i></a>
                        <ul>
                            <li class="@@normaltableactive"><a href="${pageContext.request.contextPath}/public/initiate/markingDate"><i class="zmdi zmdi-caret-right"></i> &nbsp; Marking Date</a></li>
                            <li class="@@datatableactive"><a href="${pageContext.request.contextPath}/public/initiate/sowingPermit"><i class="zmdi zmdi-caret-right"></i> &nbsp; Sawing Permit</a></li>
                        </ul>
                    </li>
                <li class="navigation__sub @@tableactive">
                    <a href="#"><i class="zmdi zmdi-plus-circle-o"></i> &nbsp; Certifications<i class="ml-2 zmdi zmdi-caret-down"></i></a>
                    <ul>
                        <li class="@@normaltableactive"><a href="${pageContext.request.contextPath}/public/initiate/allotmentOrder"><i class="zmdi zmdi-caret-right"></i> &nbsp; Allotment Order</a></li>
                        <li class="@@datatableactive"><a href="${pageContext.request.contextPath}/public/initiate/permit"><i class="zmdi zmdi-caret-right"></i> &nbsp; Permit</a></li>
                        <li class="@@datatableactive"><a href="${pageContext.request.contextPath}/public/initiate/printSowingPermit"><i class="zmdi zmdi-caret-right"></i> &nbsp; Sawing Permit</a></li>
                    </ul>
                </li>
                <li class="navigation__active"><a href="${pageContext.request.contextPath}/gewog/loginMain/updateConstructionCompletion"><i class="zmdi zmdi-steam"></i> &nbsp; Update Construction</a></li>
                <li class="navigation__active"><a href="${pageContext.request.contextPath}/gewog/loginMain/manualRecord"><i class="zmdi zmdi-widgets"></i> &nbsp; Manual Records</a></li>
                <%}else if(roleId.equals(26097)){%>
                <li class="navigation__sub @@variantsactive">
                    <a href="#"><i class="zmdi zmdi-plus-circle-o"></i> &nbsp; Reports <i class="ml-2 zmdi zmdi-caret-down"></i></a>
                    <ul>
                        <li class="@@sidebaractive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="by_construction_type"/></c:url>"><i class="zmdi zmdi-group-work"></i> &nbsp; By Construction Type & Product Category</a></li>
                        <li class="@@boxedactive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="by_construction_status"/></c:url>"><i class="zmdi zmdi-group-work"></i> &nbsp; By Construction Status</a></li>
                    </ul>
                </li>
                <%--<li class="navigation__active"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="rural_Timber"/></c:url>"><i class="zmdi zmdi-group-work"></i>Generate Report</a></li>--%>
                   <%-- <li class="navigation__sub @@tableactive">
                        <a href="#"><i class="zmdi zmdi-view-list"></i>Reports<i class="ml-2 zmdi zmdi-caret-down"></i></a>
                        <ul>
                            <li class="@@normaltableactive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="rural_Timber"/></c:url>">Report 1</a></li>
                            <li class="@@datatableactive"><a href="#">Report 2</a></li>
                        </ul>
                    </li>--%>
                <%}else if((user.getUserRoles().getUserRole().get(0).getRoleName().equalsIgnoreCase("CFO/PM")) || (user.getUserRoles().getUserRole().get(0).getRoleName().equalsIgnoreCase("Officiating CFO/PM"))){%>
                <li class="navigation__sub @@variantsactive">
                    <a href="#"><i class="zmdi zmdi-plus-circle-o"></i> &nbsp; Reports <i class="ml-2 zmdi zmdi-caret-down"></i></a>
                    <ul>
                        <li class="@@sidebaractive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="by_construction_type"/></c:url>"><i class="zmdi zmdi-group-work"></i> &nbsp;By Construction Type & Product Category</a></li>
                        <li class="@@boxedactive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="by_division"/></c:url>"><i class="zmdi zmdi-group-work"></i> &nbsp;By Division/Park</a></li>
                        <li class="@@boxedactive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="royaltyAmount"/></c:url>"><i class="zmdi zmdi-group-work"></i> &nbsp;By Royalty Collected</a></li>
                        <%--<li class="@@boxedactive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="by_range"/></c:url>"><i class="zmdi zmdi-check-circle"></i>By Range</a></li>--%>
                    </ul>
                </li>
               <%-- <li class="navigation__active"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="rural_Timber"/></c:url>"><i class="zmdi zmdi-group-work"></i>Generate Report</a></li>--%>
                <%-- <li class="navigation__sub @@tableactive">
                     <a href="#"><i class="zmdi zmdi-view-list"></i>Reports<i class="ml-2 zmdi zmdi-caret-down"></i></a>
                     <ul>
                         <li class="@@normaltableactive"><a href="<c:url value="/report/RuralTimberReport"><c:param name="header_Name" value="rural_Timber"/></c:url>">Report 1</a></li>
                         <li class="@@datatableactive"><a href="#">Report 2</a></li>
                     </ul>
                 </li>--%>
                <%}%>
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
<script src="<c:url value='/resources/sweetalert/sweetalert.min.js'/>"></script>

<!-- App functions and actions -->
<script src="<c:url value='/resources/jquery/jquery.validate.min.js'/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.70/jquery.blockUI.min.js"></script>
<script src="<c:url value="/resources/assets/js/nprogress.js"/>"></script>
<script src="<c:url value='/resources/assets/js/script.js' />"></script>
<script src="<c:url value='/resources/assets/js/dofps.js' />"></script>
<script src="<c:url value='/resources/bootstrap/dist/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/resources/framework/js/app.min.js' />"></script>

<script src="<c:url value='/resources/jquery/JqueryAjaxFormSubmit.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/jspdf.debug.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/html2canvas.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF-AutoTable/jspdf.plugin.autotable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF"/>"></script>
<%--script to disable inspection--%>

<%--<script src="https://dunggramer.github.io/disable-devtool/disable-devtool.min.js" defer></script>
<script src="https://cdn.staticaly.com/gh/DungGramer/disable-devtool/cbf447f/disable-devtool.min.js" defer></script>
<script src="https://gitcdn.xyz/repo/DungGramer/disable-devtool/master/disable-devtool.min.js" defer></script>--%>

<script>
     $('.searchAppDtls').on('click',function(){
         window.open('${pageContext.request.contextPath}/gewog/getApplicationDetails?applicationNo=' + $('#appNo').val());
     });
         // $('[data-toggle="tooltip"]').tooltip();
     $('[data-toggle="tooltip"]').tooltip({
         trigger : 'hover'
     });

     function searchHistory(){
         var appNo = $('#app_No').val();
         $.ajax({
             type : "GET",
             url : '${pageContext.request.contextPath}/gewog/appHistory',
             data: {applicationNo:appNo},
             cache : false,
             success: function (res) {
                 $("#mainLayout").html(res);
                 $('#app_No').val("");
             }
         });
     }
</script>

</body>
</html>