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
<% /*UserRolePrivilegeHierarchyObj user = (UserRolePrivilegeHierarchyObj) request.getSession().getAttribute("userdetail");*/
    WorkFlowDto App_Details = (WorkFlowDto) request.getAttribute("APPLICATION_DETAILS");
    List<CommonDto> attachment = App_Details.getDocuments();
    List<CommonDto> rangedtls = App_Details.getCommonDetails();
    List<TimberDetailsDto> product = App_Details.getTimberdetails();
%>
<head>
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
</head>
<body>
<section class="content">
    <%--<div class="content__inner">--%>
    <div class="">
    <div class="card">
    <div class="card-header">
        <b>Application for RTP (Verification & Approval)</b><span style="font-size: 12px"> >> Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <%=App_Details.getApp_Submission_Date()%></span>
    </div>
    <input type="hidden" id="applicationNumber" value="<%=App_Details.getApplication_Number()%>">
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
                            <input type="number" name="phone_Number" class="form-control form-control-sm" id="num" value="<%=App_Details.getPhone_Number()%>" readonly>
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
                            <input type="text" class="form-control form-control-sm" readonly id="ct" value="<%=App_Details.getCons_Type()%>">
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mt-2">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Timber and Location Details of Your Construction</span>
                    <span class="pull-right" id="showTimberSection" style="font-size: large; text-align: right; margin-left: 535px"><i class="form-label zmdi zmdi-plus-circle-o"></i></span>
                    <span class="pull-right"id="hideTimberSection" style="font-size: large; text-align: right; margin-left: 535px;display: none"><i class="form-label zmdi zmdi-minus-circle-outline"></i></span>
                </div>
                <div class="card-body" id="timberSection" style="display: none">
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Application Type </label>
                            <input type="text" class="form-control form-control-sm" id="app_type"  value="<%=App_Details.getApplication_Type()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Timber Form </label>
                            <%if(product.get(0).getProduct_Catagory().equalsIgnoreCase("Log)")){%>
                            <input type="text" class="form-control form-control-sm" id="tim_form" value="Log Form" readonly>
                            <%}else{%>
                            <input type="text" class="form-control form-control-sm" id="tim_form" value="Standing Form" readonly>
                            <%}%>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Mode of Sawing </label>
                            <input type="text" class="form-control form-control-sm" id="mos" value="<%=App_Details.getMode_of_Swing_Desc()%>" readonly>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <%--<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Roofing Type </label>
                            <input type="text" class="form-control form-control-sm" id="rt" value="<%=App_Details.getRoofing_Type()%>" readonly>
                        </div>--%>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">House Storey </label>
                            <input type="number" class="form-control form-control-sm" id="hs" value="<%=App_Details.getStorey_House()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Construction Location </label>
                            <input type="text" class="form-control form-control-sm" id="cl" value="<%=App_Details.getConstruction_Location()%>" readonly>
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
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white" style="width: 1%">Sl No#</th>
                                        <th class="text-white" style="width: 3%">Product Name</th>
                                        <th class="text-white" style="width: 2%">Quantity Requested</th>
                                        <th class="text-white" style="width: 2%">Quantity Approved <span class="text-danger"></span></th>
                                        <th class="text-white" style="width: 2%">Unit</th>
                                        <th class="text-white" style="width: 3%">Royalty Rate(NU)</th>
                                        <th class="text-white" style="width: 3%">Royalty Amount(NU)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%for(int a = 0; a < product.size(); a++){%>
                                    <tr>
                                        <td><%=a + 1%></td>
                                        <td><%=product.get(a).getProduct_Catagory()%></td>
                                        <td><%=product.get(a).getAppl_Quantity()%></td>
                                        <td><%=App_Details.getAllot_Quantity()%></td>
                                        <td><%=product.get(a).getUnit()%></td>
                                        <td><%=product.get(a).getRate()%></td>
                                        <td><%=product.get(a).getRoyalty_Rate()%></td>
                                    </tr>
                                    <%}%>
                                    <tr>
                                        <td colspan="6" class="text-center">Total Amount</td>
                                        <td><%=product.get(0).getTotal_Royalty()%></td>
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
                    <span class="card-title">Sawing Permit Information</span>
                </div>
                <div class="card-body">
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Sawing Permit Date</label>
                            <input type="text" class="form-control form-control-sm" id="spd" value="<%=App_Details.getSawingPermitDate()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Name of sawmill/operator:</label>
                            <input type="text" class="form-control form-control-sm" id="nso" value="<%=App_Details.getName_of_Sawmill()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">License No. :<span class="text-danger">*</span></label>
                            <input type="text" class="form-control form-control-sm" id="lno" value="<%=App_Details.getLicenseNo()%>" readonly>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Location of Sawmill:</label>
                            <input type="text" class="form-control form-control-sm" id="los" value="<%=App_Details.getLocation_of_Sawmill()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Sawing rate:</label>
                            <input type="text" class="form-control form-control-sm" id="sr" value="<%=App_Details.getSawing_Rate()%>" readonly>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Validity of Permit</label>
                            <input type="date" class="form-control form-control-sm" id="permitValidityDate" name="permitValidityDate">
                            <span class="text-danger" id="permitValidityDate_err"></span>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                            <label class="form-label">Officer on Duty:</label>
                            <input type="text" class="form-control form-control-sm" id="officerOnDuty" name="officerOnDuty">
                            <span class="text-danger" id="officerOnDuty_err"></span>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="form-group row mb-2">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table table-bordered" id="sawingPermitDtlsTbl">
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white" style="width: 4%">Produce</th>
                                        <th class="text-white" style="width: 8%">Species</th>
                                        <th class="text-white" style="width: 4%">Timber Location</th>
                                        <th class="text-white" style="width: 5%">Log/Block</th>
                                        <th class="text-white" style="width: 5%">Length</th>
                                        <th class="text-white" style="width: 5%">Breath</th>
                                        <th class="text-white" style="width: 5%">Width/Girth</th>
                                        <th class="text-white" style="width: 3%">Quantity</th>
                                        <th class="text-white" style="width: 6%">Total Volume</th>
                                        <th class="text-white" style="width: 8%">Unit</th>
                                        <th class="text-white" style="width: 2%">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                                <div class="col-lg-12 text-right">
                                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" id="addMoreHrBtn" data-target="#addSPIModal" style="background-color:#833785">
                                        <a href="#" class="text-white"><i class="zmdi zmdi-plus-circle-o"></i> Add More</a></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
                            <button class="btn fa-pull-right text-white" id="btn_submit_final_form" disabled="true" style="background-color: #236F67;" onclick="approveSWP('<%=App_Details.getApplication_Number()%>')" type="button"><a href="#" class="text-white">Approve&nbsp;<i class="zmdi zmdi-check-circle"></i></a></button>
                            <button class="btn btn-md fa-pull-right mr-2 text-white" style="background-color: #F15628" id="showRejectSection"  onclick="updateReject('<%=App_Details.getApplication_Number()%>')" type="button"><a href="#" class="text-white"><i class="zmdi zmdi-xbox"></i>Reject</a></button>
                        </div>
                    </div>
                    <div id="messageDiv" style="display:none"></div>
                </div>
            </div>
        </form>
    </div>
    </div>
</div>
</section>
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" class="modal fade in" id="addSPIModal">
    <div class="modal-dialog modal-lg" id="spiModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myModalLabel" class="modal-title">Add Species</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">Produce<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select name="cosdtmoItmosList[0].produce" class="chosen-select form-control form-control-sm" id="spi1">
                                <option value="">--select--</option>
                                <option value="Log">Log</option>
                                <option value="Standing">Standing</option>
                            </select>
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">Species:<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <%--<input type="text" name="inspectionTeamEntityList[0].speciesName" class="form-control" id="spi2" required placeholder="Text...">--%>
                            <select name="cosdtmoItmosList[0].species_Id" class="chosen-select form-control hr-cid" id="spi2" required>
                                <option value="">-- Select --</option>
                                <c:forEach var="rlist" items="${speciesName}" varStatus="counter">
                                    <option value="${rlist.header_id}">${rlist.header_Name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">Location of Timber<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="cosdtmoItmosList[0].location_of_Timber" class="form-control" id="spi3" required placeholder="Text...">
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">Log/Block<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select name="cosdtmoItmosList[0].logBlock" class="chosen-select form-control form-control-sm" id="spi4" onchange="logOrBlock()">
                                <option value="">--select--</option>
                                <option value="Log">log</option>
                                <option value="Block">Block</option>
                            </select>
                        </div>
                    </div>
                   <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">Length(In feet)<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="number" name="cosdtmoItmosList[0].length" class="form-control" id="spi5" onchange="calcTotVol()" required placeholder="Text...">
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">Breath|(In inches)<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="number" name="cosdtmoItmosList[0].breath" class="form-control" id="spi6" value="0" onchange="calcTotVol()"  required placeholder="Text...">
                        </div>
                    </div>
                   <div class="form-group row">
                       <label class="col-lg-2 col-md-2 col-sm-2">Width/Girth(In inches)<span class="text-danger">*</span>:</label>
                       <div class="col-lg-4 col-md-4 col-sm-4">
                           <input type="number" name="cosdtmoItmosList[0].girth" class="form-control" id="spi7" onchange="calcTotVol()" required placeholder="Text...">
                       </div>
                       <label class="col-lg-2 col-md-2 col-sm-2">Quantity<span class="text-danger">*</span>:</label>
                       <div class="col-lg-4 col-md-4 col-sm-4">
                           <input type="number" name="cosdtmoItmosList[0].quantity" class="form-control" id="spi8" onchange="calcTotVol()"  required placeholder="Text...">
                       </div>
                   </div>
                   <div class="form-group row">
                       <label class="col-lg-2 col-md-2 col-sm-2">Measured Total Volume (cft)<span class="text-danger">*</span>:</label>
                       <div class="col-lg-4 col-md-4 col-sm-4">
                           <input type="number" name="cosdtmoItmosList[0].total_Volume" class="form-control" id="spi9" required placeholder="Text...">
                       </div>
                       <label class="col-lg-2 col-md-2 col-sm-2">Unit<span class="text-danger">*</span>:</label>
                       <div class="col-lg-4 col-md-4 col-sm-4">
                           <select name="cosdtmoItmosList[0].unit" class="chosen-select form-control form-control-sm" id="spi10">
                               <option value="">--select--</option>
                               <option value="CFT">cft</option>
                           </select>
                       </div>
                   </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="getModalData('sawingPermitDtlsTbl','spi',10)" type="button">OK</button>
                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
            </div>
        </div>
    </div>
</div>

<%--
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
</style>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        today = dd + '-' + mm + '-' + yyyy;

       /* $("#validityPermit").datepicker({
            dateFormat: 'dd-mm-yy'
        });
       */

        function logOrBlock(){
            $("#spi5").val('0');
            $("#spi6").val('0');
            $("#spi7").val('0');
            $("#spi8").val('0');
            $("#spi9").val('0');
        }

        function calcTotVol(){
            var checkLB = $("#spi4").val();
            var totalVol=0;
            if(checkLB=='Log'){

                // totalval=girth*girth*length*quantity/1809.56
                totalVol=($("#spi7").val() * $("#spi7").val() * $("#spi5").val() * $("#spi8").val()) / 1809.56;
                totalVol =parseFloat(totalVol).toFixed(2);
            }else{
                // totalval=length*breath*girth*quantity/144
                totalVol=($("#spi7").val() * $("#spi6").val() * $("#spi5").val() * $("#spi8").val()) / 144;
                totalVol =parseFloat(totalVol).toFixed(2);
            }
            $("#spi9").val(totalVol).prop("disabled",true);
        }

        var spi_modal = $("#spiModal").html();
        var j= 0;
        function getModalData(tableId, prefix, totalCol) {
            debugger;
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
           $("#spiModal").empty().html(spi_modal);
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
        /*end here*/

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

        function approveSWP(appNo) {
            var remarks = $('#remarks').val();
            var permitValidityDate = $('#permitValidityDate').val();
            var officerOnDuty = $('#officerOnDuty').val();
            dofpsGlobal.formIndexing($('#sawingPermitDtlsTbl').find('tbody'));
            if(validateSPRO()==true){
                $.ajax({
                    type : "POST",
                    url : '${pageContext.request.contextPath}/gewog/approveSWP?remarks=' + remarks + '&appNo='+appNo,
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

        function validateSPRO() {
            var returnVal = true;
            if(validate_hr()==false){
                returnVal = false;
            }
            if ($('#permitValidityDate').val() == "") {
                $('#permitValidityDate_err').html('Construciton site is mandatory');
                $('#permitValidityDate_err').show();
                $('#permitValidityDate_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#officerOnDuty').val() == "") {
                $('#officerOnDuty_err').html('Distance from throm B is mandatory');
                $('#officerOnDuty_err').show();
                $('#officerOnDuty_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            return returnVal;
        }

        function validate_hr() {
            var isValid = true;
            var hrAdded = false;
            $('#sawingPermitDtlsTbl').find(':input').each(function () {
                hrAdded = true;
                if (isValid == true) {
                    isValid = $('#personalForm').validate().element(this);
                } else {
                    $('#personalForm').validate().element(this);
                }
            });
            if(hrAdded == false){
                isValid = false;
                warningMsg("Add at one COSDTMO/ITMO details.")
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

        function validateQty(val){
            var verifiedQty = parseInt(val);
            var appliedQty = parseInt($('#request_Qty_Log').val());
            if(verifiedQty > appliedQty){
                $('#qty_app_log').val('0');
                $('#qty_app_log').focus();
                $('#qtyLog_err').html('Quantity should be less than or equal to applied quantity!');
            }
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

        function availedRPT(val){
            if(val=='yes'){
                $('#pastRecoedSection').show();
            }else{
                $('#pastRecoedSection').hide();
            }
        }

        $(document).ready(function () {
            getApplicationDetails();
            delTableRow();
        });
    </script>
</body>

