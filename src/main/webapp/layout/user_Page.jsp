<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 2/3/2020
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    UserRolePrivilegeDTO userBean = null;
    if (session.getAttribute("UserRolePrivilege") != null) {
        userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String LocationId = "";
        for (int n = 0; n < userBean.getJurisdictions().length; n++) {
            LocationId = userBean.getJurisdictions()[n].getLocationID();
            //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID());
        }
        for (int m = 0; m < userBean.getRoles().length; m++) {
            for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) {
                Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                    //  System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                }
            }
        }
        userId = userBean.getCurrentRole().getRoleName();

        System.out.println("=== current user is : " + userId);
    }%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>DoFPS</title>
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
</head>
<body>
<div class="page">
    <div class="page-main">
        <div class="header pb-lg-0 pt-lg-0 py-4" style="background: #120f65;">
            <div class="container">
                <div class="d-flex">
                    <div class="nav-item d-md-flex">
                        <img src="<c:url value="/resources/images/logo.png"/>" class="header-brand-img" alt="tabler logo" style="height:80px;width:80px">
                        <h3 class="text-white" style="margin-top: -10px"><br>Government to citizen service delivery initiative
                            <span style="font-size:smaller;">Ministry of Agriculture and Forests</span></h3>
                    </div>
                    <div class="d-flex order-lg-2 ml-auto">

                        <div class="dropdown">
                            <%if(userBean.getCurrentRole().getRoleName()!=null && !userBean.getFullName().equalsIgnoreCase("null")){%>
                            <a href="#" class="nav-link pr-0 leading-none pt-4" data-toggle="dropdown">
                                <span class="avatar" style="background-image: url(https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=<%=userdet.getCid()%>)"></span>
                            <span class="ml-2 d-none d-lg-block">
                              <span class="text-white"><%=userBean.getFullName().replaceAll("null","")%></span>
                              <small class="text-muted d-block mt-1"><%=userBean.getCurrentRole().getRoleName()%> <%=userBean.getUserType()%></small>
                            </span>
                            </a>
                            <% }%>
                            <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                                <a class="dropdown-item" href="#">
                                    <i class="dropdown-icon fe fe-user"></i> Profile
                                </a>
                                <a class="dropdown-item" href="<c:url value="/common/logout"/>">
                                    <i class="dropdown-icon fe fe-log-out"></i> Sign out
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div id="content_main_div">
                            <%if(userBean.getFullName()!=null){%>
                            <sitemesh:write property='body'/>
                            <% }else{%>
                            <div class="container">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="alert alert-danger text-center">
                                            This user is not added. Please contact with ICT and add your user.
                                            <a class="dropdown-item" href="<c:url value="/common/logout"/>">
                                                <i class="dropdown-icon fe fe-log-out"></i> Sign out
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% }%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
