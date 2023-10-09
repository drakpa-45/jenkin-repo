<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Deepak
  Date: 9/13/2019
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    System.out.println("############################ INSIDE THE cf_home.jsp %%%%%%%%%%%");
    String roleName = "";
    String userId = "";

    if (session.getAttribute("UserRolePrivilege") != null) {
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String LocationId = "";
        for (int n = 0; n < userBean.getJurisdictions().length; n++) {
            LocationId = userBean.getJurisdictions()[n].getLocationID();
            System.out.println("Jurisdiction Id: " + userBean.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + userBean.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + userBean.getJurisdictions()[n].getLocationID());
        }
        /*for (int m = 0; m < userBean.getRoles().length; m++) {
            for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) {
                Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                    System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                }
            }
        }*/
        userId = userBean.getUserType();
        System.out.println("=== THE USERID IS : " + userId);
    }%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="card">
    <div class="card-body">
        <h2>${acknowledgement_message}</h2>
    </div>
</div>
</body>
</html>
<script>


</script>