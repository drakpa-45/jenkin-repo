<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 1/15/2020
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    if (session.getAttribute("UserRolePrivilege") != null) {
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
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
        System.out.println("=== current user is : " + userId);
    }%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <% if (userId.equalsIgnoreCase("CC Operator")) { %>
    <jsp:include page="inc/header.jsp"></jsp:include>
    <%}else if(userId.equalsIgnoreCase("Dealing Officer")) {%>
    <%}else if(userId.equalsIgnoreCase("CFO/PM") || userId.equalsIgnoreCase("Officiating CFO/PM")) {%>
    <%}else if(userId.equalsIgnoreCase("Beat Officer") || userId.equalsIgnoreCase("Range Officer")) {%>
    <%}else if(userId.equalsIgnoreCase("Gewog"))  {%>
    <jsp:include page="inc/headerCitizen.jsp"></jsp:include>
    <%}%>
</head>
<body>
    <sitemesh:write property='body'/>
</body>
<%--<jsp:include page="inc/footer.jsp"></jsp:include>--%>
</html>