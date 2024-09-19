<%@ page import="org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj" %>
<%@ page import="java.util.List" %>
<%@ page import="bt.gov.ditt.dofps.dto.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 1/16/2020
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% UserRolePrivilegeHierarchyObj user = (UserRolePrivilegeHierarchyObj) request.getSession().getAttribute("userdetail");
    WorkFlowDto App_Details = (WorkFlowDto) request.getAttribute("APPLICATION_DETAILS");
    List<CommonDto> attachment = App_Details.getDocuments();
    List<CommonDto> rangedtls = App_Details.getCommonDetails();
    List<TimberDetailsDto> product = App_Details.getTimberdetails();
%>
<style>
    .card{
        background-color: rgb(241,243,244);
    }
    form, input, select, textarea{
        color: black !important;
    }
    .form-label{
        font-weight: bold;
    }
</style>
<body>
<section class="content">
    <%--<div class="content__inner">--%>
    <div class="">
<div class="card">
   <%-- <div class="card-header">
        <span class="card-title form-label" style="color: #002752"><b>Application for RTP (Verification & Approval) >> New Contruction >>
            Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <b> <%=App_Details.getApp_Submission_Date()%></b></span>
    </div>--%>
    <div class="card-header">
        <b>Application for RTP (Verification & Approval)</b><span style="font-size: 12px"> >> Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <%=App_Details.getApp_Submission_Date()%></span>
    </div>
    <input type="hidden" id="applicationNumber" value="<%=App_Details.getApplication_Number()%>">
    <input type="hidden" name="Application_Number" value="<%=App_Details.getApplication_Number()%>">
    <div class="card-body">
        <form action="#" id="personalForm" method="post">
            <div class="card">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Personal Details</span>
                </div>
                <div class="card-body">
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">CID Number </label>
                            <input type="text" class="form-control form-control-sm" id="cid" value="<%=App_Details.getCid_Number()%>" readonly>
                            <input type="hidden" name="application_Number" value="<%=App_Details.getApplication_Number()%>"/>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Full Name </label>
                            <input type="text" class="form-control form-control-sm" id="name" value="<%=App_Details.getName()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">House Number </label>
                            <input type="text" class="form-control form-control-sm" id="house_no" value="<%=App_Details.getHouse_No()%>" readonly>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Household Number </label>
                            <input type="number" class="form-control form-control-sm" id="household" value="<%=App_Details.getHouse_Hold_No()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Dzongkhag </label>
                            <input type="text" class="form-control form-control-sm" id="dzongkhag" value="<%=App_Details.getDzongkhag_Name()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Gewog </label>
                            <input type="text" class="form-control form-control-sm" id="gewog" value="<%=App_Details.getGewog_Name()%>" readonly>
                        </div>
                    </div>

                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Village </label>
                            <input type="text" class="form-control form-control-sm" id="village" value="<%=App_Details.getVillage_Name()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Head of Gung </label>
                            <input type="text" class="form-control form-control-sm" id="g_cid" value="<%=App_Details.getHead_of_Gung()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Mobile Number </label>
                            <input type="number" name="phone_Number" class="form-control form-control-sm" id="phone_Number" value="<%=App_Details.getPhone_Number()%>" readonly>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Construction Approval Number </label>
                            <input type="text" class="form-control form-control-sm" readonly id="numa" value="<%=App_Details.getCons_Approval_No()%>">
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Member of community forest </label>
                            <input type="text" readonly class="form-control form-control-sm" id="memberOfCF" value="<%=App_Details.getMember_of_Forest_community()%>">
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Construction Type</label>
                            <input type="text" class="form-control form-control-sm" readonly id="ct" name="cons_Type" value="<%=App_Details.getCons_Type()%>">
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" class="form-control form-control-sm" id="markingDate" value="<%=App_Details.getMarking_Date()%>" readonly>
            <div class="card mt-2">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Inspection Details</span>
                    <span class="pull-right" id="showInspectionTeamSection" style="font-size: large; text-align: right; margin-left: 685px"><i class="form-label zmdi zmdi-plus-circle-o"></i></span>
                    <span class="pull-right"id="hideInspectionTeamSection" style="font-size: large; text-align: right; margin-left: 685px;display: none"><i class="form-label zmdi zmdi-minus-circle-outline"></i></span>
                </div>
                <div class="card-body" style="display: none"  id="inspectionSection">
                    <div class="form-group row mb-2">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Construction Site fall in Dzongkhag/Yenlag Thromde:</label>
                                    <input type="text" class="form-control form-control-sm" id="construction_site"  value="<%=App_Details.getConstruction_Site()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">2KM from the class B Thromde:</label>
                                    <input type="text" class="form-control form-control-sm" id="away_from_B_thromde" value="<%=App_Details.getAwayFrom_B_Thromde()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Nature of Applicant:</label>
                                    <input type="text" class="form-control form-control-sm" id="natureOfApplicant" value="<%=App_Details.getNatureOfApplicant()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Proposed Construction Site:</label>
                                    <input type="text" class="form-control form-control-sm" id="pcs"  value="<%=App_Details.getProposedConstructionSite()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Has applicant availed subsidized RPT from CF?:</label>
                                    <input type="text" class="form-control form-control-sm" value="<%=App_Details.getHasAvailTimberB4()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                                <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                                <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">Inspection</span>&nbsp;&nbsp;&nbsp;
                                <span class="px-1 small text-muted form-label text-muted form-label">Team</span>
                                <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                            </div>
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table table-bordered" id="hrDtlsTable">
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white" style="width: 3%">Sl No</th>
                                        <th class="text-white" style="width: 5%">CID</th>
                                        <th class="text-white" style="width: 5%">Name</th>
                                        <th class="text-white" style="width: 4%">Contact Number</th>
                                        <th class="text-white" style="width: 5%">Designation</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="noRecord">
                                        <td colspan="6">No Record Found</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mt-2">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Timber and Location Details of Your Construction</span>
                   <%-- <span class="pull-right" id="showTimberSection" style="font-size: large; text-align: right; margin-left: 535px"><i class="form-label zmdi zmdi-plus-circle-o"></i></span>
                    <span class="pull-right"id="hideTimberSection" style="font-size: large; text-align: right; margin-left: 535px;display: none"><i class="form-label zmdi zmdi-minus-circle-outline"></i></span>--%>
                </div>
                <div class="card-body" id="timberSection">
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Application Type </label>
                            <input type="text" class="form-control form-control-sm" id="app_type"  value="<%=App_Details.getApplication_Type()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Timber Form </label>
                            <%if(product.get(0).getProduct_Catagory().equalsIgnoreCase("Log")){%>
                            <input type="text" class="form-control form-control-sm" id="tim_form" value="Log Form" readonly>
                            <%}else{%>
                            <input type="text" class="form-control form-control-sm" id="tim_form" value="Standing Form" readonly>
                            <%}%>
                        </div>
                       <%-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Mode of Sawing </label>
                            <input type="text" class="form-control form-control-sm" id="mos" value="<%=App_Details.getMode_of_Swing_Desc()%>" readonly>
                        </div>--%>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">House Storey </label>
                            <input type="number" class="form-control form-control-sm" id="hs" value="<%=App_Details.getStorey_House()%>" readonly>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Construction Location </label>
                            <input type="text" class="form-control form-control-sm" id="cl" value="<%=App_Details.getConstruction_Location()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Marking Area:</label>
                            <input type="text" class="form-control form-control-sm" id="rt" value="<%=App_Details.getAllot_Area()%>" readonly>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                            <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">Timber</span>&nbsp;&nbsp;&nbsp;
                            <span class="px-1 small text-muted form-label text-muted form-label"> Details</span>
                            <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table table-bordered" id="productTbls">
                                    <%if(App_Details.getStatus_Id().equals(23)){%> <%--if replacement of timber then fee is not calculated--%>
                                    <thead style="background-color:#478d86">
                                        <tr>
                                            <th class="text-white" style="width: 1%">Sl No#</th>
                                            <th class="text-white" style="width: 3%">Product Name</th>
                                            <th class="text-white" style="width: 2%">Quantity Requested</th>
                                            <th class="text-white" style="width: 2%">Replace Quantity</th>
                                            <th class="text-white" style="width: 2%">Quantity Approved <span class="text-danger"></span></th>
                                            <th class="text-white" style="width: 2%">Unit</th>
                                        </tr>
                                    </thead>
                                    <%}else{%>
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white" style="width: 1%">Sl No#</th>
                                        <th class="text-white" style="width: 3%">Product Name</th>
                                        <th class="text-white" style="width: 2%">Quantity Requested</th>
                                        <th class="text-white" style="width: 2%">Quantity Approved <span class="text-danger"></span></th>
                                        <th class="text-white" style="width: 3%">Royalty Rate(NU)</th>
                                        <th class="text-white" style="width: 3%">Marking Fee/Tree (NU)</th>
                                        <th class="text-white" style="width: 2%">Unit</th>
                                        <th class="text-white" style="width: 3%">Royalty Amount(NU)</th>
                                    </tr>
                                    </thead>
                                    <%}%>
                                    <%if(App_Details.getStatus_Id().equals(23)){%><%--if replacement of timber then fee is not calculated--%>
                                    <tbody>
                                    <%for(int a = 0; a < product.size(); a++){%>
                                    <tr>
                                        <td><%=a + 1%></td>
                                        <td><%=product.get(a).getProduct_Catagory()%></td>
                                        <td><%=product.get(a).getAppl_Quantity()%></td>
                                        <td><%=product.get(a).getQuantityToReplace()%></td>
                                        <td><%=App_Details.getAllot_Quantity()%></td>
                                        <td><%=product.get(a).getUnit()%></td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                    <%}else{%>
                                    <tbody>
                                    <%for(int a = 0; a < product.size(); a++){%>
                                    <tr>
                                        <td><%=a + 1%></td>
                                        <td><%=product.get(a).getProduct_Catagory()%></td>
                                        <td><%=product.get(a).getAppl_Quantity()%></td>
                                        <td><%=App_Details.getAllot_Quantity()%></td>
                                        <td><%=product.get(a).getRate()%></td>
                                        <td>10</td>
                                        <td><%=product.get(a).getUnit()%></td>
                                        <td><%=product.get(a).getRoyalty_Rate()%></td>
                                    </tr>
                                    <%}%>
                                    <tr>
                                        <td colspan="7" class="text-center">Total Amount</td>
                                        <td><%=product.get(0).getTotal_Royalty()%></td>
                                    </tr>
                                    </tbody>
                                    <%}%>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="form-group row mb-2">
                                <div class="col-sm-5">
                                    <label class="form-label">Do you want to send this application to another range?</label>
                                </div>
                                <div class="col-sm-7 justify-content-center">
                                    <label class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="otherRange" onchange="sendToOtherRange(this.value)" value="yes">
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description">Yes</span>
                                    </label>
                                    <label class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="otherRange" onchange="sendToOtherRange(this.value)" value="no" checked>
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description">No</span>
                                    </label>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm" style="display: none" id="rangeId">
                                    <label class="form-label">To Range Office</label>
                                    <select name="allot_Range_Officer" style="width:100%;" class="chosen-select form-control" id="range_id">
                                        <option value="0">----- Select------</option>
                                        <c:forEach var="rlist" items="${range_list}" varStatus="counter">
                                            <option value="${rlist.range_Id}">${rlist.range_Name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mt-2" id="pastRecordSection" style="display: none">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Past Timber Records</span>
                </div>
                <div class="card-body">
                    <div class="form-group row mb-2">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table table-bordered" id="trDtlsTable">
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white" style="width: 1%">Sl No</th>
                                        <th class="text-white" style="width: 4%">CID Number</th>
                                        <th class="text-white" style="width: 4%">Timber Type</th>
                                        <th class="text-white" style="width: 3%">Quantity</th>
                                        <th class="text-white" style="width: 4%">Year</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="noRecord">
                                        <td colspan="6">No Record Found</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%if(App_Details.getPayment_status().equalsIgnoreCase("Paid")){%>
            <div class="card mt-2">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Marking Information</span>
                </div>
                <div class="card-body">
                    <div class="form-group row mb-2">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table table-bordered" id="markingDtlsTable">
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white" style="width: 8%">Species</th>
                                        <th class="text-white" style="width: 5%">Volume (cft)</th>
                                        <th class="text-white" style="width: 8%">Location of Timber</th>
                                        <th class="text-white" style="width: 8%">GPS Coordinates</th>
                                        <th class="text-white" style="width: 2%">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                                <div class="col-lg-12 text-right">
                                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" id="addMoreHrBtn" data-target="#addMIModal" style="background-color:#833785">
                                        <i class="zmdi zmdi-plus-circle-o"></i> Add More</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%}else{%>
            <div class="card mt-2">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Payment Details</span>
                </div>
                <div class="card-body">
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Amount<span class="text-danger">*</span> </label>
                            <input type="number" class="form-control form-control-sm" id="amount" name="amount">
                            <span class="text-danger" id="amount_err"></span>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Receipt Number<span class="text-danger">*</span></label>
                            <input type="text" class="form-control form-control-sm" id="receiptNo" name="receiptNo">
                            <span class="text-danger" id="receiptNo_err"></span>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Mode of Payment<span class="text-danger">*</span></label>
                            <input type="text" class="form-control form-control-sm" id="modeOfPayment" name="modeOfPayment">
                            <span class="text-danger" id="modeOfPayment_err"></span>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Payment Date<span class="text-danger">*</span></label>
                            <input type="date" class="form-control form-control-sm datepicker" id="paymentDate" name="paymentDate"/>
                            <span class="text-danger" id="paymentDate_err"></span>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
            <div class="card mt-2">
                <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                    <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                    <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">Application </span>&nbsp;&nbsp;&nbsp;
                    <span class="px-1 small text-muted form-label text-muted form-label"> History</span>
                    <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                </div>
                <div class="panel-body ml-2 mr-2" id="viewStatusDetailDiv">
                    <div class="panel panel-default">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" id="viewListGrid">
                                <thead style="background-color:#e3d5a1">
                                <tr>
                                    <td width="20%">Status</td>
                                    <td width="24%">Action By</td>
                                    <td width="24%">Action Date</td>
                                    <td width="24%">Remarks</td>
                                </tr>
                                </thead>
                                <tbody id="tbody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mt-2">
                <div class="card-body border-dark">
                    <div class="form-group row mb-2 declaration">
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 custom-switch-sm">
                            <div class="demo-inline-wrapper">
                                <div class="form-group">
                                    <div class="toggle-switch">
                                        <input type="checkbox" class="toggle-switch__checkbox" id="submit_form" onchange="display()">
                                        <i class="toggle-switch__helper"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                            <b>I have verified the details of the appilcant and the inspection team's report, and found to be true and correct.</b>
                            <b>Timber requirement is genuine and for bonafide house construction.</b>
                            <input type="hidden" value="0" name="Department_Id" id="Department_Id">
                        </div>
                    </div>
                    <div class="form-group text-right sub_button">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 form-control-sm">
                            <%if(App_Details.getPayment_status().equalsIgnoreCase("Paid")){%>
                            <button class="btn fa-pull-right text-white" id="btn_submit_final_form" disabled="true" style="background-color: #236F67;" onclick="showConfirmation()"
                                    type="button"><a href="#" class="text-white">Approve&nbsp;<i class="zmdi zmdi-check-circle"></i></a></button>
                            <%}else{%>
                            <button class="btn fa-pull-right text-white" id="btn_submit_final_form" disabled="true" style="background-color: #236F67;" onclick="updatePayment('<%=App_Details.getApplication_Number()%>')"
                                    type="button"><a href="#" class="text-white">Paid&nbsp;<i class="zmdi zmdi-money"></i></a></button>
                            <%}%>
                            <button class="btn btn-md fa-pull-right mr-2 text-white" style="background-color: #F15628" id="showRejectSection" onclick="updateReject('<%=App_Details.getApplication_Number()%>')" type="button"><a href="#" class="text-white"><i class="zmdi zmdi-xbox"></i>Reject</a></button>
                            <button class="btn fa-pull-right text-white" id="forwardToRange" style="background-color: #236F67; display: none" onclick="forwardToOtherRange('<%=App_Details.getApplication_Number()%>')"
                                    type="button"><a href="#" class="text-white">Forward&nbsp;<i class="zmdi zmdi-fast-forward"></i></a></button>&nbsp;&nbsp;
                        </div>
                    </div>
                    <div id="messageDiv" style="display:none"></div>
                </div>
            </div>
            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                 class="modal in" id="confirmationModel">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <span><b>Confirmation!</b></span>
                        </div>
                        <div class="modal-body form-horizontal">
                            <div class="alert alert-info">
                                <div class="row">
                                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                        <span id="messages"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="approveRO('<%=App_Details.getApplication_Number()%>')">Yes</button>
                            <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
</section>
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" class="modal fade in" id="addMIModal">
    <div class="modal-dialog modal-lg" id="miModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myModalLabel" class="modal-title">Add Species</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">Species Name<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <%--<input type="text" name="markingInformationEntityList[0].species_Name" class="form-control hr-cid" id="mi1" required placeholder="Species name ...">--%>
                            <select name="markingInformationEntityList[0].species_Id" class="chosen-select form-control hr-cid" id="mi1" required>
                                <option value="">-- Select --</option>
                                <c:forEach var="rlist" items="${speciesName}" varStatus="counter">
                                    <option value="${rlist.header_id}">${rlist.header_Name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">Standing Volume (in Cft):<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="markingInformationEntityList[0].volume" class="form-control hr-cid" id="mi2" required placeholder="Text...">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">Location of Timber<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="markingInformationEntityList[0].location_of_Timber" class="form-control hr-cid" id="mi3" required placeholder="Text...">
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">GPS Coordinates<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="markingInformationEntityList[0].gPS_Coordinates" class="form-control hr-cid" id="mi4" required placeholder="Text...">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="getModalData('markingDtlsTable','mi',4)" type="button">OK</button>
                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<style>
    /* Chrome, Safari, Edge, Opera */
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    /* Firefox */
    input[type=number] {
        -moz-appearance: textfield;
    }
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>


        function showConfirmation(){
            $('#confirmationModel').modal('show');
            $('#targetId').val('acknowledgementmessage');
            $('#messages').html('You are about to submit application. Are you sure to proceed ?');
        }

        var mi_modal = $("#miModal").html();
        var tr_modal = $("#pastModal").html();
        var j= 0;
        function getModalData(tableId, prefix, totalCol) {
            var td = "";
            var modal = $('#' + prefix + '1').closest('.modal');
            /* if (modal.find(':input').valid() == false) {
             warningMsg('Please provide your information');
             return false;
             }*/
            for (var i = 1; i <= totalCol; i++) {

                var $this = $("#" + prefix + i);
                var text = '', value = '', name = '';

                var input_type = $this.prop('type');

                if (~input_type.indexOf("select")) {
                    value = $this.val();
                    text = $this.find('option:selected').html();
                    name = $this.prop('name');
                } else {
                    value = $this.val();
                    text = value;
                    name = $this.prop('name');
                }

                var tdVal = "<input type='hidden' class='"+$this.attr('id')+"' name='" + name + "' value='" + value + "'/>" + text;
                td = td + "<td>" + tdVal + "</td>";
            }

            var tr = "<tr id='"+j+"'>" + td + "<td class=''>" +
                    "<a class='p-2 del_row'><i class='zmdi zmdi-delete text-danger'></i></a></td></tr>";

            $("#" + tableId).append(tr).find(".noRecord").hide();

            j= j+1;

            $('#'+tableId).find('.tbd').remove();
            closeHRModal(modal);
        }

        function closeHRModal(modal){
            modal.modal('hide');
            $("#miModal").empty().html(mi_modal);
            $("#pastModal").empty().html(tr_modal);
        }

        function delTableRow(){
            $('body').on('click','.del_row',function(){
                if($(this).closest('table').find('tbody tr').length > 1) {
                    $(this).closest('tr').remove();
                } else{
                    warningMsg("Cannot delete last row. You must have at least one row!");
                }
            });
        }

        function removeerror(id){
            $('#'+id).html('');
        }

        function removeErrors(id){
            $('#'+id).html('');
        }

        /*section hide and show js goes here*/
        $('#showTimberSection').on('click',function(){
            $("#timberSection").show();
            $("#hideTimberSection").show();
            $("#showTimberSection").hide();
        });
        $('#hideTimberSection').on('click',function(){
            $("#timberSection").hide();
            $("#showTimberSection").show();
            $("#hideTimberSection").hide();
        });
        $('#showPastRecordSection').on('click',function(){
            $("#pastRecordSection").show();
            $("#hidePastRecordSection").show();
            $("#showPastRecordSection").hide();
        });
        $('#hidePastRecordSection').on('click',function(){
            $("#pastRecordSection").hide();
            $("#showPastRecordSection").show();
            $("#hidePastRecordSection").hide();
        });

        $('#showInspectionTeamSection').on('click',function(){
            $("#inspectionSection").show();
            $("#hideInspectionTeamSection").show();
            $("#showInspectionTeamSection").hide();
        });
        $('#hideInspectionTeamSection').on('click',function(){
            $("#inspectionSection").hide();
            $("#showInspectionTeamSection").show();
            $("#hideInspectionTeamSection").hide();
        });
        /*end here*/

        function calculateRoyalty(val){
            if(validateQtyEntered(val)==true){
                //var enteredQty = val;
                <% for(int a=0;a<product.size();a++){ %>
                var qty = $('#qty_app<%=a%>').val();
                var rate = $('#rate_<%=a%>').val();
                var royalAmount = parseInt(qty * rate);
                $('#tot_amt<%=a%>').val(royalAmount);

                $('#tot_roy_amt').val(royalAmount);
                $('#royal_tot').val(royalAmount);

                var tot_amt = document.getElementById("royal_tot").value;
                var permitFee = document.getElementById("permitFee").value;
                var sum = parseFloat(tot_amt) + parseFloat(permitFee);
                $('#grt_tot').val(sum);
                <%}%>
            }
        }

        function validateQtyEntered(val){
            var valid = true;
            var requestedQty = parseInt($('#request_Qty').val());
            var enteredQty = parseInt($('#qty_app0').val());
            if(enteredQty > requestedQty){
                <% for(int a=0;a<product.size();a++){ %>
                $('#tot_amt<%=a%>').val('0');
                <%}%>
                $('#tot_roy_amt').val('0');
                $('#royal_tot').val('0');
                $('#grt_tot').val('0');
                $('#limit_err').html('Enter quantity less than or equal to requested quantity!');
                valid = false;
            }
            return valid;
        }

        function rejectReason(val){
            //alert(val);
            if(val == "Others"){
                $('#rej_other_reason').show();
            }else{
                $('#rej_other_reason').hide();
            }
        }

        function Reject(){
            $('#btn_submit_final_form').prop('disabled',true);
            $('#btnreject').hide();
            $('#rejectReason').show();
            $('#showRejectSection').hide();
            $('.declaration').hide();
            $('.sub_button').hide();
            $('#submit_form').prop('disabled',true);
        }

        function Cancel(){
            $('#btn_submit_final_form').prop('disabled',true);
            $('#btnreject').show();
            $('#rejectReason').hide();
            $('#showRejectSection').show();
            $('.declaration').show();
            $('.sub_button').show();
            $('#submit_form').prop('disabled',false);
        }

        function display() {
            if ($('#submit_form').prop('checked')) {
                $('#btn_submit_final_form').prop('disabled', false);
            }
            else {
                $('#btn_submit_final_form').prop('disabled', true);
            }
        }
        function validateLocation() {
            var retval = true;
            if ($('#Indu_Classification').val() == "") {
                $('#Indu_Classification_err').html('Please select classification for this application');
                $('#Indu_Classification').focus();
                retval = false;
            }
            if ($('#Classification_Type').val() == "") {
                $('#Classification_Type_err').html('Please select classification type for this application');
                $('#Classification_Type').focus();
                retval = false;
            }
            return retval;
        }
        var inicount = 1;
        function addmoreattachemnts() {
            inicount++;
            var fnshow = 'validateAttachment(this.value,"file' + inicount + '","file_added' + inicount + '")';
            $('#fileadd').append('<div class="row" id="addedfile' + inicount + '"><div class="col-sm-6"><input type="file" class="alert badge-danger" onchange=\'' + fnshow + '\' name="files" id="file' + inicount + '"><i id="file_added' + inicount + '" class="fa fa-times"></i></div><div class="col-sm-6"><button class="btn btn-danger fa fa-pull-right mt-4" type="button" onclick="deleteate(' + inicount + ')"><i class="fa fa-times pr-4"></i>Delete this</button></div></div>');
        }

        function deleteate(id) {
            $('#addedfile' + id).remove();
        }

        function validateAttachment(vl, id, checkId) {
            if (vl != "") {
                $('#' + id).prop('class', 'alert badge-info');
                $('#' + checkId).prop('class', 'fa fa-check pl-2');
            }
            else {
                $('#' + id).prop('class', 'alert badge-danger');
                $('#' + checkId).prop('class', 'fa fa-times pl-2');
            }
        }

        function viewAttachment(uuid, type, path, name) {
            var url = '${pageContext.request.contextPath}/dealing/loadpagetoemptylayout/donwloadFiles?uuid=' + uuid + '&type=' + type;
            window.open(url, '_blank');
        }

        function approveRO(appNo) {
            dofpsGlobal.formIndexing($('#markingDtlsTable').find('tbody'));
            var remarks = $('#remarks').val();

            if(validate_hr()==false){
                returnVal = false;
            }else{
                $('#confirmationModel').modal('hide');
                $.ajax({
                    type : "POST",
                    url : '${pageContext.request.contextPath}/gewog/approveRO?remarks=' + remarks + '&appNo='+appNo,
                    data: $('#personalForm').serialize(),
                    cache : false,
                    success : function(res) {
                        if(res.status==1){
                            successMsg(res.text, '${pageContext.request.contextPath}/loginMain');
                        }else{
                            warningMsg(res.text);
                        }
                    }
                });
            }
        }

        function forwardToOtherRange(appNo) {
            var remarks = $('#remarks').val();
            var modeOfPayment = $('#modeOfPayment').val();
            var rangeId = $('#range_id').val();
            if(remarks !="" && rangeId !=''){
                $.ajax({
                    type : "POST",
                    url : '${pageContext.request.contextPath}/gewog/forwardToOtherRange?remarks='+ remarks + '&appNo='+appNo+ '&rangeId='+rangeId,
                    data: $('#personalForm').serialize(),
                    cache : false,
                    success : function(res) {
                        if(res.status==1){
                            successMsg(res.text, '${pageContext.request.contextPath}/loginMain');
                        }else{
                            warningMsg(res.text);
                        }
                    }
                });
            }else{
                $('#remarks').focus();
            }

        }

        function updatePayment(appNo) {
            var remarks = $('#remarks').val();
            var modeOfPayment = $('#modeOfPayment').val();
            var receiptNo = $('#receiptNo').val();
            var amount = $('#amount').val();
            var constructionType = $('#ct').val();
            if(validateRO()==true){
                $.ajax({
                    type : "POST",
                    // url : '${pageContext.request.contextPath}/gewog/updatePayment?remarks=' + remarks + '&appNo='+appNo+'&modeOfPayment='+modeOfPayment+'&receiptNo='+receiptNo+'&amount='+amount,
                    url : '${pageContext.request.contextPath}/gewog/updatePayment?remarks=' + remarks + '&appNo='+appNo+'&constructionType='+constructionType,
                    data: $('#personalForm').serialize(),
                    cache : false,
                    success : function(res) {
                        if(res.status==1){
                            successMsg(res.text, '${pageContext.request.contextPath}/loginMain');
                        }else{
                            warningMsg(res.text);
                        }
                    }
                });
            }
        }

        function validateRO() {
            var returnVal = true;

            if ($('#amount').val() == "") {
                $('#amount_err').html('amount is mandatory');
                $('#amount_err').show();
                $('#amount_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#receiptNo').val() == "") {
                $('#receiptNo_err').html('Receipt Number is mandatory');
                $('#receiptNo_err').show();
                $('#receiptNo_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#modeOfPayment').val() == "") {
                $('#modeOfPayment_err').html('Mode of Paymnet is mandatory');
                $('#modeOfPayment_err').show();
                $('#modeOfPayment_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#paymentDate').val() == "") {
                $('#paymentDate_err').html('Payment date is mandatory');
                $('#paymentDate_err').show();
                $('#paymentDate_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            return returnVal;
        }

        function validate_hr() {
            var isValid = true;
            var hrAdded = false;
            $('#markingDtlsTable').find(':input').each(function () {
                hrAdded = true;
                if (isValid == true) {
                    isValid = $('#personalForm').validate().element(this);
                } else {
                    $('#personalForm').validate().element(this);
                }
            });

            if($("#markingDate").val()=="null" || $("#markingDate").val()==""){
                isValid = false;
                warningMsg("Marking Date not yet scheduled for this applicant !!");
            }
            if(hrAdded == false){
                isValid = false;
                warningMsg("Add at least one marking details.")
            }
            return isValid;
        }

        function updateReject(val) {
            var remarks = $('#remarks').val();
            var appNo =$('#applicationNumber').val();
            if(remarks !=""){
                $.ajax({
                    type : "POST",
                    url : '${pageContext.request.contextPath}/gewog/reject?remarks=' + remarks + '&appNo='+appNo,
                    data: $('#personalForm').serialize(),
                    cache : false,
                    success : function(res) {
                        if(res.status==1){
                            successMsg(res.text, '${pageContext.request.contextPath}/loginMain');
                        }else{
                            warningMsg(res.text);
                            $('#remarks').focus();
                        }
                    }
                });
            }else{
                $('#remarks').focus();
            }
        }

        function validateReject(){
            var returnval=true;
            if($('#Rejection_Reason').val()==""){
                $('#Rejection_Reason_err').html('Please select reject reason');
                returnval=false;
            }
            return returnval;
        }
        $('[data-toggle="tooltip"]').tooltip();

        function validateQty(val){
            var verifiedQty = parseInt(val);
            var appliedQty = parseInt($('#request_Qty_Log').val());
            if(verifiedQty > appliedQty){
                $('#qty_app_log').val('0');
                $('#qty_app_log').focus();
                $('#qtyLog_err').html('Quantity should be less than or equal to applied quantity!');
            }
        }

        function validateLogForm(){
            var validateForm = true;
            var approvedQty = parseInt($('#qty_app_log').val());
            var reqQty = parseInt($('#request_Qty_Log').val());
            /*var range = $('#range_id').val();
            alert(range);*/

            if($('#range_id').val()=="" || $('#range_id').val()==0){
                $('#Range_err').html('Select the Range Office');
                $('#range_id').focus();
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            } if(approvedQty > reqQty){
                $('#qty_app_log').focus();
                $('#qtyLog_err').html('Enter quantity less than or equal to quantity requested');
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            } if($('#qty_app_log').val()=="0" || $('#qty_app_log').val()=="" || $('#qty_app_log').val()=="NULL" ){
                $('#qty_app_log').focus();
                $('#qtyLog_err').html('Enter the quantity');
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            }
            return validateForm;
        }

        function validateForm(){
            var validateForm = true;
            if($('#range_id').val()=="" || $('#range_id').val()==0){
                $('#Range_err').html('Select the Range Office');
                $('#range_id').focus();
                validateForm = false;
            }
            <% for (int a = 0; a < product.size(); a++) { %>
            var allot = parseInt($('#qty_app<%=a%>').val());
            var req = parseInt($('#request_Qty').val());
            if(allot > req){
                $('#limit_err').html("Enter the value less than or equal to request quantity");
                $('#qty_app<%=a%>').focus();
                $('#tot_amt<%=a%>').val('0');
                $('#royal_tot').val('0');
                $('#grt_tot').val('0');
                $('#tot_roy_amt').val('0');
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            }if($('#tot_roy_amt').val()==0 ||$('#tot_roy_amt').val()=="" ){
                $('#limit_err').html("Enter the value less than or equal to request quantity");
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            }if($('#royal_tot').val() ==0 ||$('#royal_tot').val() ==""){
                $('#limit_err').html("Enter the value less than or equal to request quantity");
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            }if($('#grt_tot').val()==0||$('#grt_tot').val()==""){
                $('#limit_err').html("Enter the value less than or equal to request quantity");
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            }if($('#qty_app0').val()=="" || $('#qty_app0').val()=="0"){
                $('#limit_err').html("Enter the value less than or equal to request quantity");
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled',true);
                validateForm = false;
            }
            return validateForm;
            <%}%>
            return validateForm;
        }

        function getApplicationDetails() {
            var applicationNumberID = $('#applicationNumber');
            $.ajax({
                url:'${pageContext.request.contextPath}/gewog/getApplicationDetails',
                type: 'GET',
                data: {applicationNumber: applicationNumberID.val()},
                success: function (res) {
                    var tr="";
                    //populate(res.dto);
                    $('#applicationNo').text(applicationNumberID.val());
                    $('#statusName').text(res.dto.statusName);
                    for(var i=0;i<res.dto.length;i++){
                        tr+='<tr>'
                        +'<td>'+res.dto[i].statusName+'</td>'
                        +'<td>'+res.dto[i].actorName+'</td>'
                        +'<td>'+res.dto[i].actionDate+'</td>'
                        +'<td>'+res.dto[i].remarks+'</td>'
                        +'</tr>';
                    }
                    $('#tbody').append(tr);
                    $('#messageHide').hide();

                }, complete: function () {
                    $('#btnViewDetails').attr('disabled', false);
                    $('#viewStatusDetailDiv').show();
               //     isSubmitted = false;
                }
            });
        }

        function sendToOtherRange(val){
            if(val=='yes'){
                $('#rangeId').show();
                $('#forwardToRange').show();
                $('#btn_submit_final_form').hide();
                $('#remarks').prop('required',true);
            }else{
                $('#rangeId').hide();
                $('#forwardToRange').hide();
                $('#btn_submit_final_form').show();
            }
        }

        function getInspectionTeamMembers(){
            $.ajax({
                url: '${pageContext.request.contextPath}/gewog/getInspectionTeamMembers',
                type: 'GET',
                data: {applicationNumber: $('#applicationNumber').val()},
                success: function (res) {
                    var hrDtlsTable = $('#hrDtlsTable');
                    hrDtlsTable.find('tbody').find('.noRecord').remove();
                    var tr = '';
                    var m=0;
                    var inspectionDtls=res.dto;
                    if(inspectionDtls.length>0){
                        for (var i in inspectionDtls) {
                            m++;
                            tr = tr + "<tr><td>"+ m +"</td>" +
                            "<td>" + inspectionDtls[i].inspectionTeam_CID + "</td>" +
                            "<td>" + inspectionDtls[i].inspectionTeam_Name + "</td>" +
                            "<td>" + inspectionDtls[i].inspectionTeam_ContactNo + "</td>" +
                            "<td>" + inspectionDtls[i].designationDesc + "</td></tr>";
                            }
                        hrDtlsTable.find('tbody').append(tr);
                    }
                }
            })
        }

        function getPastRecordDetails(){
            $.ajax({
                url: '${pageContext.request.contextPath}/gewog/getPastRecordDetails',
                type: 'GET',
                data: {applicationNumber: $('#applicationNumber').val()},
                success: function (res) {
                    var trDtlsTable = $('#trDtlsTable');
                    trDtlsTable.find('tbody').find('.noRecord').remove();
                    var tr = '';
                    var m=0;
                    var pastRecordDtls=res.dto;
                    if(pastRecordDtls.length>0){
                        $('#pastRecordSection').show();
                        for (var i in pastRecordDtls) {
                            m++;
                            tr = tr + "<tr><td>"+ m +"</td>" +
                            "<td>" + pastRecordDtls[i].applicant_CID + "</td>" +
                            "<td>" + pastRecordDtls[i].timber_Type + "</td>" +
                            "<td>" + pastRecordDtls[i].quantity_Taken + "</td>" +
                            "<td>" + pastRecordDtls[i].year_of_allotment + "</td></tr>";
                        }
                        trDtlsTable.find('tbody').append(tr);
                    }else{
                        $('#pastRecordSection').hide();
                    }
                }
            })
        }


        $(document).ready(function () {

            <% for (int a = 0; a < product.size(); a++) { %>
            //called when key is pressed in textbox
            $("#qty_app<%=a%>").keypress(function (e) {
                //if the letter is not digit then display error and don't type anything
                if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                    //display error message
                    $("#limit_err").html("Digits Only").show().fadeOut("slow");
                    return false;
                }
            });
            <%}%>
            $("#qty_app_log").keypress(function (e) {
                //if the letter is not digit then display error and don't type anything
                if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                    //display error message
                    $("#qtyLog_err").html("Digits Only").show().fadeOut("slow");
                    return false;
                }
            });

            var id = $('#fp_Product_Id').val();
            //alert(id);
            if(id==5 || id == 15){
                $('.log_table').show();
            }else{
                $('.standing_table').show();
                $('.stand_table').show();
            }

            var cf=$('#memberOfCF').val();
            if(cf =='Yes'){
                $('#checkForCF').show();
            }else{
                $('#checkForCF').hide();
            }
            getApplicationDetails();
            getInspectionTeamMembers();
            getPastRecordDetails();
            delTableRow();
        });

        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        today = dd + '-' + mm + '-' + yyyy;
        $( "#paymentDate").datepicker({
            minDate: today, // Current day
            // maxDate: 10, // 30 days from the current day
            dateFormat: 'dd-mm-yy'
        });

    </script>
</body>

