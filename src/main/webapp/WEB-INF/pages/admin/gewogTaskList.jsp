<%@ page import="bt.gov.ditt.dofps.dto.NewTimberDto" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 1/28/2020
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div id="loadMainPage">
<div class="my-3 my-md-5 col-lg-12 col-md-10 col-sm-12 col-xs-12">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                <h5>Department of Forest and Park Services
                </h5>
            </div>
        </div>
       <%-- <div class="card">
            <div class="card-header badge-secondary">
                <b>Task list > </b>Group Task
            </div>
            &lt;%&ndash;<div class="card-status bg-blue"></div>&ndash;%&gt;
            &lt;%&ndash;<div class="card-header">&ndash;%&gt;
                &lt;%&ndash;<h6 class="card-title">Task list > Group Task</h6><span class="pl-9">(Click on home button to go back)</span>&ndash;%&gt;
            &lt;%&ndash;</div>&ndash;%&gt;
            <div class="card-body">
                <div class="row">
                    <% List<NewTimberDto> group_dto=(List<NewTimberDto>) request.getAttribute("Group_Task_List"); %>
                    <% if(group_dto.size()>=0) {%>
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 form-control-sm">
                        <div class="table-responsive">
                            <table class="table table-striped border" id="group_task">
                                <thead class="bg-gray-light form-control-sm">
                                <tr>
                                    <td># </td>
                                    <td>Application Number </td>
                                    <td>Service Name </td>
                                    <td>Current Status</td>
                                    <td>Construction Type</td>
                                    <td>Last Action Date</td>
                                </tr>
                                </thead>
                                <tbody>
                              <%for(int i=0;i<group_dto.size();i++){%>
                                 <tr class="form-control-sm">
                                     <td><%=i + 1%>
                                     </td>
                                     <td><a href="#" onclick="openAndClaimApplication('<%=group_dto.get(i).getApplication_Number()%>','open')"
                                            data-toggle="tooltip"data-original-title="">
                                         <%=group_dto.get(i).getApplication_Number()%>
                                     </a></td>
                                     <td><span class="fa fa-save pr-2"></span><%=group_dto.get(i).getService_Name()%></td>
                                     <td>
                                         <% if(group_dto.get(i).getCurrent_Status().equalsIgnoreCase("SUBMITTED")){%>
                                         <span class="badge-success btn-block text-center"><%=group_dto.get(i).getCurrent_Status()%></span>
                                         <% }else if(group_dto.get(i).getCurrent_Status().equalsIgnoreCase("VERIFIED")){%>
                                         <span class="badge-primary btn-block text-center"><%=group_dto.get(i).getCurrent_Status()%></span>
                                         <% }else if(group_dto.get(i).getCurrent_Status().equalsIgnoreCase("APPROVED")){%>
                                         <span class="badge-info btn-block text-center"><%=group_dto.get(i).getCurrent_Status()%></span>
                                         <% }else{%>
                                         <span class="badge-warning btn-block text-center"><%=group_dto.get(i).getCurrent_Status()%></span>
                                         <% }%>
                                     </td>
                                     <td><span class="fa fa-university pr-2"></span><%=group_dto.get(i).getCons_Type()%></td>
                                     <td><span class="fa fa-clock pr-2"></span><%=group_dto.get(i).getAction_Date()%></td>
                                 </tr>
                                 <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>--%>
        <div class="card">
            <div class="card-status bg-azure-darker"></div>
            <%--<div class="card-header">--%>
                <%--<h5 class="card-title">Task list > My Task</h5>--%>
            <%--</div>--%>
            <div class="card-header badge-secondary">
                <b>Task list > </b>My Task
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 form-control-sm">
                        <% List<NewTimberDto> mydto=(List<NewTimberDto>) request.getAttribute("My_Task_List"); %>
                        <% if(mydto.size()>=0){%>
                        <div class="table-responsive">
                            <table class="table table-striped border" id="mytask">
                                <thead class="bg-gray-lighter form-control-sm">
                                <tr class="form-control-sm">
                                    <td>#</td>
                                    <td>Application Number </td>
                                    <td>Service Name </td>
                                    <td>Current Status  </td>
                                    <td>Construction Type</td>
                                    <td>Last Action Date</td>
                                </tr>
                                </thead>
                                <tbody>
                                <%for(int i=0;i<mydto.size();i++){%>
                                <tr class="form-control-sm">
                                    <td><%=i+1%>
                                        <a href="#" onclick="openAndClaimApplication('<%=mydto.get(i).getApplication_Number()%>','release')"
                                           data-toggle="tooltip" data-original-title="Release to group task">
                                            <span class="text-danger badge-pill">
                                                <i class="fa fa-times"></i>
                                            </span>
                                        </a></td>
                                    <td>
                                        <a href="#" onclick="openAndClaimApplication('<%=mydto.get(i).getApplication_Number()%>','claim','<%=mydto.get(i).getCons_Type()%>')"
                                           data-toggle="tooltip">
                                            <%=mydto.get(i).getApplication_Number()%></a></td>
                                    <td><span class="fa fa-save pr-2"></span><%=mydto.get(i).getService_Name()%></td>
                                    <td><span class="fa fa-tags pr-2"></span><%=mydto.get(i).getCurrent_Status()%></td>
                                    <td><span class="fa fa-university pr-2"></span><%=mydto.get(i).getCons_Type()%></td>
                                    <td><span class="fa fa-clock pr-2"></span><%=mydto.get(i).getAction_Date()%></td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
   // $('[data-toggle="tooltip"]').tooltip();
   $('[data-toggle="tooltip"]').tooltip({
       trigger : 'hover'
   })
    $(document).ready(function() {
        $('#group_task').DataTable({
            responsive: true
        });
        $('#mytask').DataTable({
            responsive: true
        });
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
            cons_type="r";
        }
        var url='${pageContext.request.contextPath}/common/loadpagetoemptylayout/openAndClaimApplication?Application_Number='+appNo+'&action_type='+actiontype+'&cons_type='+cons_type;
        $('#loadMainPage').load(url);
    }
</script>
</div>
</body>
</html>
