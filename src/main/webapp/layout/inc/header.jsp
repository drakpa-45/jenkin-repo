<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 1/15/2020
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    String CurrentUser = "";
    UserRolePrivilegeDTO userBean = null;
    if (session.getAttribute("UserRolePrivilege") != null) {
        userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String LocationId = "";
        for (int n = 0; n < userBean.getJurisdictions().length; n++) {
            LocationId = userBean.getJurisdictions()[n].getLocationID();
            //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID());
        }
        /*for (int m = 0; m < userBean.getRoles().length; m++) {
            for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) {
                Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                    //  System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                }
            }
        }*/
        userId = userBean.getCurrentRole().getRoleName();
        CurrentUser = userBean.getUserID();
        //String location  = userBean.getJurisdictions()[0].getJurisdictionType();
        //System.out.print(location);
        System.out.println("=== current user is : " + userId);
    }%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Language" content="en"/>
    <meta name="msapplication-TileColor" content="#2d89ef">
    <meta name="theme-color" content="#4188c9">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <title>Home</title>
</head>
<body>
<div class="header py-4" style="background:#236F67; height:108px;">
    <div class="container">
        <div class="d-flex">
            <img src="<c:url value="/resources/images/logo.png"/>" class="header-brand-img" alt="tabler logo"
                 style="height:100px; width:90px;">

            <h3 class="text-white" style="margin-top:-2px;"><br/>
                Government to Citizen Service Delivery Initiative<br/>
                <span style="font-size:smaller;">Ministry of Agriculture and Forests</span>
            </h3>

            <div class="d-flex order-lg-2 ml-auto">
                <div class="dropdown">
                    <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown" style="margin-top:-2px;">
                        <span style="background-image: url(../../resources/images/profile.png)" class="avatar"></span>
                        <span class="ml-2 d-none d-lg-block">
                             <%if (userBean.getCurrentRole().getRoleName().equalsIgnoreCase("CC Operator")) {%>
                            <small class="text-white d-block mt-1"><%=userBean.getFullName().replaceAll("null", "")%></small>
                                 <%if (userBean.getCurrentRole().getRoleName().equalsIgnoreCase("CC Operator")){%>
                            <small class="text-white d-block mt-1">Community Center</small>
                                 <%}%>
                            <small class="text-white d-block mt-1"><%=userBean.getJurisdictions()[0].getJurisdiction()%>
                            </small><%}%>

                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item" style="color:#71dd8a;">
                            <i class="dropdown-icon fe fe-help-circle"></i>Need help?
                        </a>
                        <a class="dropdown-item" href="<c:url value="/common/logout"/>" style="color:#71dd8a;">
                            <i class="dropdown-icon fe fe-log-out"></i>Sign out
                        </a>
                    </div>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 d-lg-flex header p-0" id="headerMenuCollapse">
    <div class="container">
        <div class="row pull-left">
            <div class="col-lg order-lg-first">
                <nav class="navbar navbar-expand-lg navbar-dark">
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active pr-2">
                                <a class="nav-link" style="color:#ffffff;" href="<c:url value="/common/redirect"><c:param name="userId" value="<%=userBean.getCurrentRole().getRoleName()%>"/>
                                <c:param name="CurrentUser" value="<%=CurrentUser%>"/><c:param name="locationId" value="<%=userBean.getJurisdictions()[0].getLocationID()%>"/></c:url>">
                                <i class="fa fa-home"></i>&nbsp;Home <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item pr-2">
                                <a href="javascript:void(0)" class="nav-link" style="color:#ffffff;"
                                   data-toggle="dropdown"><i class="fa fa-tree"></i> &nbsp; Rural House Building Timber <i class="fa fa-chevron-down"></i></a>
                                <ul class="dropdown-menu dropdown-menu-arrow"
                                    style="margin-top:2px; color:#000000;">
                                    <li class="has-dropdown" class="dropdown-item" style="position:relative;"><a href="<c:url value="/ruralTimber/ruralTimberApplication"><c:param name="cons_type" value="n"/></c:url>"
                                            class="dropdown-item">New Construction of Rural Houses</a>
                                    </li>
                                    <li class="has-dropdown"><a href="<c:url value="/ruralTimber/ruralTimberApplication"><c:param name="cons_type" value="r"/></c:url>" class="dropdown-item">Repair OR Renovation OR Extension of Rural Houses</a>
                                    </li>
                                    <li class="has-dropdown" class="dropdown-item" style="position: relative;"><a href="<c:url value="/ruralTimber/ruralTimberApplication"><c:param name="cons_type" value="o"/></c:url>"
                                            class="dropdown-item">Other Rural Construction </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item pr-2">
                                <a href="<c:url value="/woodPole/wood_Pole"><c:param name="cons_desc" value="WP"/></c:url>" class="nav-link" style="color:#ffffff;"> <i class="fa fa-bacon"></i>&nbsp; Permit For Pole and Firewood</a>
                            </li>
                            <li class="nav-item pr-2">
                                <a href="<c:url value="/privateLand/private"></c:url>" class="nav-link" style="color:#ffffff;"> <i class="fas fa-seedling"></i>&nbsp; Removal of Forest Produce From Private</a>
                            </li>
                            <li class="nav-item pr-2">
                                <a href="<c:url value="/ruralTimber/claimAdditionalTimber"></c:url>" class="nav-link" style="color:#ffffff;"> <i class="fa-air-freshener fas"></i>&nbsp; Claim Balance Timber</a>
                            </li>
                            <li class="nav-item pr-2">
                                <a href="${pageContext.request.contextPath}/ruralTimber/viewAppStatus" class="nav-link" style="color:#ffffff;"> <i class="fa fa-eye"></i>&nbsp;View Status</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>
