
    <%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
    <%@ page import="bt.gov.ditt.dofps.dto.Service" %>
    <%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
    <%@ page import="org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj" %>
    <%@ page import="bt.gov.ditt.dofps.dto.CommonDto" %>
    <%@ page import="java.util.List" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <%
    System.out.println("###################### INSIDE THE cf_home.jsp %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
        String CurrentUser = "";
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
                    // System.out.println("role name : " + dto.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                }
            }
        }*/
        userId = userBean.getCurrentRole().getRoleName();
        CurrentUser = userBean.getUserType();
        System.out.println("=== THE USERID IS : " + userId);
    }%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
    <title>Department of Forest and Park Services</title>
    </head>
    <body>
    <div id="loadMainPage">
    <div class="my-3 my-md-5">
        <div class="container">
            <div class="row">
                <%--<% List<CommonDto> gloCount = (List<CommonDto>) request.getAttribute("GroupTaskDealing"); %>--%>
               <% if(userId.equalsIgnoreCase("CC Operator")){%>

                    <div class="card card-deck">
                        <%--<div class="card-status bg-blue"></div>--%>
                        <div class="card-header w-50">
                            <h5 class="card-title"><b><%=userId%> DashBoard</b></h5>
                            <hr/>
                            <img src="<c:url value="/resources/images/bhutan_map_for_DoFPS.png"/>" alt="Bhutan map" class="w-100"/>
                        </div>
                        <div class="card">
                            <h5>Schedule for allotment and extraction of rural subsidized timber:</h5>
                            <table class="table table-hover table-success">
                                <thead>
                                <tr>
                                    <th scope="col">Sl. No.</th>
                                    <th scope="col">Activity</th>
                                    <th scope="col">Time Frame</th>
                                    <th scope="col">Responsible Agency</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Submission of application and verification of construction sites.</td>
                                    <td>June to September</td>
                                    <td>Gewog administration</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Issuance of permit, marking and feeling of trees.</td>
                                    <td>October to December</td>
                                    <td>CC or Division and Park or applicant</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Extraction and transportation to construction site or sawmill.</td>
                                    <td>January to June</td>
                                    <td>Applicant (Obtain COSDTMO & ITMO)</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Monitoring and reporting</td>
                                    <td>July to December</td>
                                    <td>Division or Park</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Submission of construction or renovation completion report to CFO</td>
                                    <td>Within two years after construction approval.</td>
                                    <td>Gewog administration and applicant</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                <%--</div>--%>
                <%}else if(userId.equalsIgnoreCase("Dealing Officer")){%>
                <div class="col-lg-4 col-md-6">
                    <div class="card" style="height: 100%;">
                        <div class="card-status bg-blue"></div>
                        <div class="card-header">
                            <h5 class="card-title">
                                <b>Select Service to Take Action</b>
                            </h5>

                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div>
                                    <div class="" id="side_bar1" style="margin-top:-50px;">
                                        <ul class="navbar-nav mr-auto">
                                            <li class="dropdown nav-item pt-3">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah32" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Issuance of Rural Timber Permit
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/common/loadapplication"/> ">
                                                        New Construction and Renovation
                                                    </a>
                                                </div>
                                            </li>

                                            <li class="dropdown nav-item">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah37" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Issuance of Fencing Pole and FireWood
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="WP"/> </c:url>">
                                                        Fencing Pole and FireWood
                                                    </a>
                                                </div>
                                            </li>

                                            <li class="dropdown nav-item">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah7" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Removal of Product From Private Land
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="PRL"/></c:url>">
                                                        Removal of Forest Product from Pvt Registered Land
                                                    </a>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-6">
                    <div class="card">
                        <div class="card-status bg-blue"></div>
                        <div class="card-header">
                            <h5 class="card-title"><b><%=userId%> DashBoard</b></h5>
                        </div>
                        <% List<CommonDto> gloCount = (List<CommonDto>) request.getAttribute("GroupTaskDealing"); %>
                        <% List<CommonDto> ploCount = (List<CommonDto>) request.getAttribute("PersonalTaskDealing"); %>
                        <div class="card-body">
                            <h5 style="text-align:center;"><b>Application Submitted</b></h5><br>
                            <div class="row">
                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-green"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div><h6><a style="color:#4b4b5f;" href="<c:url value="/common/loadapplication"/> " >Requisition for New Construction </a> </h6></div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gloCount.get(0).getGrouptaskRTPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<100 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<50 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<20 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=ploCount.get(0).getPersonaltaskRTPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())<100 && Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())<50 && Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())<20 && Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                        <div class="card">
                                            <div class="card-status bg-yellow"></div>
                                            <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                                <div><h6><a style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"><c:param name="app_type" value="WP"/> </c:url>">
                                                    Requisition for Fencing Pole and FireWood </a></h6> </div>
                                                <div class="row">
                                                    <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                        <div class="display-4 font-weight-bold mb-4"><%=gloCount.get(0).getGrouptaskWPDealing()%></div>
                                                        <div class="progress progress-sm">
                                                            <%if(Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>100){%>
                                                            <div class="progress-bar bg-red" style="width: 100%"></div>
                                                            <%}else if(Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())<100 && Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>=50){%>
                                                            <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())<50 && Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>20){%>
                                                            <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())<20 && Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>0) {%>
                                                            <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())==0){%>
                                                            <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                            <%}%>
                                                        </div>
                                                        <div class="h8">Group Task</div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                        <div class="display-4 font-weight-bold mb-4"><%=ploCount.get(0).getPersonaltaskWPDealing()%></div>
                                                        <div class="progress progress-sm">
                                                            <%if(Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>100){%>
                                                            <div class="progress-bar bg-red" style="width: 100%"></div>
                                                            <%}else if(Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())<100 && Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>=50){%>
                                                            <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())<50 && Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>20){%>
                                                            <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())<20 && Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>0) {%>
                                                            <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())==0){%>
                                                            <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                            <%}%>
                                                        </div>
                                                        <div class="h8">My Task</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12" style="max-height: 90px!important; min-height: 93px;">
                                        <div class="card">
                                            <div class="card-status bg-red"></div>
                                            <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                                <div><h6><a style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"><c:param name="app_type" value="PRL"/> </c:url>"> Requisition for Removal of Product from Private Land </a></h6> </div>
                                                <div class="row">
                                                    <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                        <div class="display-4 font-weight-bold mb-4"><%=gloCount.get(0).getGrouptaskPRLDealing()%></div>
                                                        <div class="progress progress-sm">
                                                            <%if(Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>100){%>
                                                            <div class="progress-bar bg-red" style="width: 100%"></div>
                                                            <%}else if(Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())<100 && Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>=50){%>
                                                            <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())<50 && Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>20){%>
                                                            <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())<20 && Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>0) {%>
                                                            <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())==0){%>
                                                            <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                            <%}%>
                                                        </div>
                                                        <div class="h8">Group Task</div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                        <div class="display-4 font-weight-bold mb-4"><%=ploCount.get(0).getPersonaltaskPRLDealing()%></div>
                                                        <div class="progress progress-sm">
                                                            <%if(Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>100){%>
                                                            <div class="progress-bar bg-red" style="width: 100%"></div>
                                                            <%}else if(Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())<100 && Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>=50){%>
                                                            <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())<50 && Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>20){%>
                                                            <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())<20 && Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>0) {%>
                                                            <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                            <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())==0){%>
                                                            <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                            <%}%>
                                                        </div>
                                                        <div class="h8">My Task</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%}else if((userId.equalsIgnoreCase("CFO/PM") || (userId.equalsIgnoreCase("Officiating CFO/PM")))){%>
                <div class="col-lg-4 col-md-6">
                    <div class="card" style="height: 100%;">
                        <div class="card-status bg-blue"></div>
                        <div class="card-header">
                            <h5 class="card-title">Select Service to Take Action</h5>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div>
                                    <div class="" id="side_bar4" style="margin-top:-50px;">
                                        <ul class="navbar-nav mr-auto">
                                            <li class="dropdown nav-item pt-3">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah34" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Issuance of Rural Timber Permit
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/common/loadapplication"/> " <%--onclick="loadapplicationCfo('new','181')"--%>>
                                                        New Construction and Renovation
                                                    </a>
                                                </div>
                                            </li>
                                            <li class="dropdown nav-item">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah38" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Issuance of Fencing Pole and FireWood
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <%--<a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="WP"/> </c:url>">--%>
                                                        <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="WP"/> </c:url>">
                                                        Fencing Pole and FireWood
                                                    </a>
                                                </div>
                                            </li>
                                            <li class="dropdown nav-item">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah48" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Removal of Product From Private Land
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="PRL"/> </c:url>">
                                                        Removal of Forest Product from Pvt Registered Land
                                                    </a>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-6">
                    <div class="card">
                        <div class="card-status bg-blue"></div>
                        <div class="card-header">
                            <h5 class="card-title"><b><%=userId%> DashBoard</b></h5>
                        </div>
                        <% List<CommonDto> gloCount = (List<CommonDto>) request.getAttribute("GroupTaskDealing"); %>
                        <% List<CommonDto> ploCount = (List<CommonDto>) request.getAttribute("PersonalTaskDealing"); %>
                        <div class="card-body">
                            <h5 style="text-align:center;"><b>Application Submitted</b></h5><br>
                            <div class="row">
                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-green"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div class="h6"><a style="color:#4b4b5f;" href="<c:url value="/common/loadapplication"/>" > New Construction and Renovation</a> </div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gloCount.get(0).getGrouptaskRTPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<100 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<50 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<20 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=ploCount.get(0).getPersonaltaskRTPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())<100 && Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())<50 && Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())<20 && Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskRTPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-yellow"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div class="h6"><a style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="WP"/> </c:url>"> Fencing Pole and FireWood </a> </div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gloCount.get(0).getGrouptaskWPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())<100 && Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())<50 && Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())<20 && Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskWPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=ploCount.get(0).getPersonaltaskWPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())<100 && Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())<50 && Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())<20 && Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskWPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-red"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div class="h6"><a style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="PRL"/> </c:url>">Removal of Forest Product from Pvt Registered Land</a> </div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gloCount.get(0).getGrouptaskPRLDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())<100 && Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())<50 && Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())<20 && Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskPRLDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=ploCount.get(0).getPersonaltaskPRLDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())<100 && Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())<50 && Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())<20 && Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getPersonaltaskPRLDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%}else if((userId.equalsIgnoreCase("Beat Officer")) || (userId.equalsIgnoreCase("Range Officer"))){%>
                <div class="col-lg-4 col-md-6">
                    <div class="card" style="height: 100%;">
                        <div class="card-status bg-blue"></div>
                        <div class="card-header">
                            <h5 class="card-title">Select Service to Take Action</h5>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div>
                                    <div class="" id="side_bar7" style="margin-top:-50px;">
                                        <ul class="navbar-nav mr-auto">
                                            <li class="dropdown nav-item pt-3">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah35" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Issuance of Rural Timber Permit
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/common/loadapplication"/> " <%--onclick="loadapplicationRanger('new','181')"--%>>
                                                         New Construction and Renovation
                                                    </a>
                                                   <%-- <a class="dropdown-item" style="color:#4b4b5f;" href="#">
                                                        Other Rural constructions
                                                      </a>--%>
                                                </div>
                                            </li>
                                            <li class="dropdown nav-item">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah39" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Issuance of Fencing Pole and FireWood
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"><c:param name="app_type" value="WP"/> </c:url>">
                                                        Fencing Pole and FireWood
                                                    </a>
                                                </div>
                                            </li>
                                            <li class="dropdown nav-item">
                                                <a class="nav-link dropdown-toggle" href="#" id="ah40" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="fa fa-check-circle pr-2"></span>Removal of Product From Private Land
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-arrow bg-white" aria-labelledby="navbarDropdown3">
                                                    <a class="dropdown-item" style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="PRL"/> </c:url>">
                                                        Removal of Forest Product from Pvt Registered Land
                                                    </a>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-6">
                    <div class="card">
                        <div class="card-status bg-blue"></div>
                        <div class="card-header">
                            <h5 class="card-title"><b>Range Officer/ FMU Incharge DashBoard</b></h5>
                        </div>
                        <% List<CommonDto> gloCount = (List<CommonDto>) request.getAttribute("GroupTaskDealing"); %>
                        <% List<CommonDto> ploCount = (List<CommonDto>) request.getAttribute("PersonalTaskDealing"); %>
                        <% List<CommonDto> PRLCount = (List<CommonDto>) request.getAttribute("GroupTaskPRL"); %>
                        <% List<CommonDto> gPRLCount = (List<CommonDto>) request.getAttribute("PersonalTaskPRL"); %>
                        <div class="card-body">
                            <h5 style="text-align:center;"><b>Application Submitted</b></h5><br>
                            <div class="row">
                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-green"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div class="h6"><a style="color:#4b4b5f;" href="<c:url value="/common/loadapplication"/>">Requisition for New Construction and Renovation </a> </div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gloCount.get(0).getGrouptaskRTPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<100 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<50 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())<20 && Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gloCount.get(0).getGrouptaskRTPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gloCount.get(0).getGrouptaskRTPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=ploCount.get(0).getPersonaltaskRTPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())<100 && Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=ploCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())<50 && Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gloCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())<20 && Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=ploCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(ploCount.get(0).getPersonaltaskRTPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=ploCount.get(0).getPersonaltaskRTPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-green"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div class="h6"><a style="color:#4b4b5f;" href="<c:url value="/common/loadapplication"/>">Requisition of Other Construction </a> </div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=PRLCount.get(0).getGroupTaskOtherConsBeating()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())<100 && Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=PRLCount.get(0).getGroupTaskOtherConsBeating()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())<50 && Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=PRLCount.get(0).getGroupTaskOtherConsBeating()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())<20 && Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=PRLCount.get(0).getGroupTaskOtherConsBeating()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGroupTaskOtherConsBeating())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=PRLCount.get(0).getGroupTaskOtherConsBeating()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gPRLCount.get(0).getPersonalTaskOtherConsBeating()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())<100 && Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gPRLCount.get(0).getPersonalTaskOtherConsBeating()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())<50 && Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gPRLCount.get(0).getPersonalTaskOtherConsBeating()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())<20 && Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gPRLCount.get(0).getPersonalTaskOtherConsBeating()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonalTaskOtherConsBeating())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gPRLCount.get(0).getPersonalTaskOtherConsBeating()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            <div class="row mt-3">
                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-yellow"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div class="h6"><a style="color:#4b4b5f;" href="<c:url value="/fencingAndWood/loadapplication"><c:param name="app_type" value="WP"/> </c:url>"> Requisition for Fencing Pole and FireWood </a> </div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=PRLCount.get(0).getGrouptaskWPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())<100 && Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=PRLCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())<50 && Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=PRLCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())<20 && Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=PRLCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGrouptaskWPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=PRLCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gPRLCount.get(0).getPersonaltaskWPDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())<100 && Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gPRLCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())<50 && Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gPRLCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())<20 && Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gPRLCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonaltaskWPDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gPRLCount.get(0).getPersonaltaskWPDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                    <div class="card">
                                        <div class="card-status bg-red"></div>
                                        <div class="card-body text-center" style="padding-bottom: 2px;padding-top: 5px;">
                                            <div class="h6"><a style="color:#4b4b5f;"href="<c:url value="/fencingAndWood/loadapplication"> <c:param name="app_type" value="PRL"/> </c:url>">
                                                Removal of Forest Product from Pvt Registered Land
                                            </a> </div>
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=PRLCount.get(0).getGrouptaskPRLDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())<100 && Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=PRLCount.get(0).getGrouptaskWPDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())<50 && Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=PRLCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())<20 && Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=PRLCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(PRLCount.get(0).getGrouptaskPRLDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=PRLCount.get(0).getGrouptaskPRLDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">Group Task</div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                                                    <div class="display-4 font-weight-bold mb-4"><%=gPRLCount.get(0).getPersonaltaskPRLDealing()%></div>
                                                    <div class="progress progress-sm">
                                                        <%if(Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())>100){%>
                                                        <div class="progress-bar bg-red" style="width: 100%"></div>
                                                        <%}else if(Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())<100 && Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())>=50){%>
                                                        <div class="progress-bar bg-green" style="width: <%=gPRLCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())<50 && Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())>20){%>
                                                        <div class="progress-bar bg-blue" style="width:<%=gPRLCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())<20 && Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())>0) {%>
                                                        <div class="progress-bar bg-yellow" style="width:<%=gPRLCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}else if (Integer.parseInt(gPRLCount.get(0).getPersonaltaskPRLDealing())==0){%>
                                                        <div class="progress-bar bg-cyan-darkest" style="width:<%=gPRLCount.get(0).getPersonaltaskPRLDealing()%>%"></div>
                                                        <%}%>
                                                    </div>
                                                    <div class="h8">My Task</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </div>
    <script>
       /* function loadapplication(){
            var url='${pageContext.request.contextPath}/common/loadapplication';
            $('#loadMainPage').load(url);
        }*/
        function loadapplicationCfo(page_type,serviceId){
            var url='${pageContext.request.contextPath}/cfo/loadpagetoemptylayout/loadapplication?page_type='+page_type+'&serviceId='+serviceId;
            $('#loadMainPage').load(url);
        }
        function loadapplicationCC(page_type,serviceId){
            var url='${pageContext.request.contextPath}/ruralTimber/loadpagetoemptylayout/loadapplication?page_type='+page_type+'&serviceId='+serviceId;
            $('#loadMainPage').load(url);
        }
        function loadapplicationRanger(page_type,serviceId){
            var url='${pageContext.request.contextPath}/rangeOfficer/loadpagetoemptylayout/loadapplication?page_type='+page_type+'&serviceId='+serviceId;
            $('#loadMainPage').load(url);
        }
    </script>
    </div>
    </body>
    </html>
