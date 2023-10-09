<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%@ page import="org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="logic" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%
    String roleName = "";
    String userId = "";
    UserRolePrivilegeDTO userBean = null;
    if (session.getAttribute("UserRolePrivilege") != null) {
    }%>
<body>
<style>
    .card{
        background-color: rgb(241,243,244);
    }
    form, input, select, textarea{
        color: black !important;
    }
</style>
<%
    UserRolePrivilegeHierarchyObj user = (UserRolePrivilegeHierarchyObj) request.getSession().getAttribute("userdetail");
    Integer roleId = user.getUserRoles().getUserRole().get(0).getRoleId();
%>
<div id="loadMainPage">
    <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Task List</strong>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="card-table table table-bordered table-vcenter" id="myTaskListGrid">
                                <thead style="background-color:#e3d5a1">
                                <tr>
                                    <th>#</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Current Status</th>
                                    <th>Construction Type</th>
                                    <th>Action Date</th>
                                    <%if(roleId.equals(1804)){%>
                                    <th>Payment Status</th>
                                    <th>Marking Date</th>
                                    <%}%>
                                </tr>
                                </thead>
                                <tbody>
                                    <logic:if test="${empty My_Task_List}">
                                        <tr>
                                            <td colspan="6" align="center">
                                                <p class="text-danger">No data available</p>
                                            </td>
                                        </tr>
                                    </logic:if>
                                    <logic:if test="${not empty My_Task_List}">
                                        <logic:forEach var="task" items="${My_Task_List}" varStatus="counter">
                                            <tr>
                                                <td>${counter.index+1}</td>
                                                <td><a href="#" onclick="openAndClaimApplication('${task.application_Number}','claim','${task.cons_Type}')"
                                                       data-toggle="tooltip">
                                                    ${task.application_Number}</a
                                                </td>
                                                <td>${task.service_Name}</td>
                                                <td>${task.current_Status}</td>
                                                <td>${task.cons_Type}</td>
                                                <td>${task.action_Date}</td>
                                                <%if(roleId.equals(1804)){%>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${task.payment_status == 'Paid'}">
                                                            <span class="zmdi zmdi-circle" style="color: limegreen;"></span>&nbsp; ${task.payment_status}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="zmdi zmdi-circle" style="color: lightslategray;"></span>&nbsp; ${task.payment_status}</td>
                                                    </c:otherwise>
                                                    </c:choose>
                                                <td>${task.marking_Date}</td>
                                                <%}%>
                                            </tr>
                                        </logic:forEach>
                                    </logic:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
    </section>
</div>
    <script>

        $('#myTaskListGrid').DataTable({
            responsive: true
        });

        function openAndClaimApplication(appNo,actiontype,cons_type){
            if(cons_type=="New Construction"){
                cons_type="n";
            }else if(cons_type=="Other Rural Construction"){
                cons_type="o";
            }else if(cons_type=="Renovation"){
                cons_type="r";
            }else if(cons_type=="Removal of Forest produce from Pvt Registered Land"){
                cons_type="PRL";
            }else{
                cons_type="WP";
            }
            $.ajax({
                type : "POST",
                url : '${pageContext.request.contextPath}/common/loadpagetoemptylayout/openAndClaimApplication?Application_Number='+appNo+'&action_type='+actiontype+'&cons_type='+cons_type,
                data: $('form').serialize(),
                cache : false,
                success : function(res) {
                    $('#loadMainPage').html(res);

                }
            });
        }
    </script>
</body>
</html>
