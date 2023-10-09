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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% UserRolePrivilegeHierarchyObj user = (UserRolePrivilegeHierarchyObj) request.getSession().getAttribute("userdetail");
    WorkFlowDto App_Details = (WorkFlowDto) request.getAttribute("APPLICATION_DETAILS");
    List<TimberDetailsDto> product = App_Details.getTimberdetails();
%>
<body>
<section class="content">
    <%--<div class="content__inner">--%>
    <div class="">
    <div class="card" id="wholeCard">
        <div class="card-header">
            <b>Application for RTP (Verification & Approval)</b><span style="font-size: 12px"> >>Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <%=App_Details.getApp_Submission_Date()%></span>
        </div>
        <div class="card-body">
            <%--<span class="text-danger">--%>
            <%--NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields.--%>
            <input type="hidden" id="applicationNumber" value="<%=App_Details.getApplication_Number()%>">
            <%--</span>--%>
            <form action="#" enctype="multipart/form-data" method="post" id="personalFormGAO">
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
                    </div>
                    <div class="card-body">
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
                           <%-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                <label class="form-label">Roofing Type </label>
                                <input type="text" class="form-control form-control-sm" id="rt" value="<%=App_Details.getRoofing_Type()%>" readonly>
                            </div>--%>
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                <label class="form-label">Construction Location </label>
                                <input type="text" class="form-control form-control-sm" id="cl" value="<%=App_Details.getConstruction_Location()%>" readonly>
                            </div>
                        </div>
                        <div class="form-group row mb-2">
                            <%--<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                <label class="form-label">Request Quantity </label>
                                <input type="text" class="form-control form-control-sm" id="aq" value="<%=App_Details.getAppl_Quantity()%>" readonly>
                            </div>--%>
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
                                                <th class="text-white" style="width: 2%">Unit</th>
                                                <th class="text-white" style="width: 3%">Royalty Rate(NU)</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%for(int a = 0; a < product.size(); a++){%>
                                            <tr>
                                                <td><%=a + 1%></td>
                                                <td><%=product.get(a).getProduct_Catagory()%></td>
                                                <td><input type="hidden" id="fP_Product_Id_" name="workFlowDtoList[<%=a%>].fp_Product_Id" value="<%=product.get(a).getfP_Product_Id()%>">
                                                    <input type="hidden" class="form-control" id="apply_qty" value="<%=product.get(a).getAppl_Quantity()%>" readonly/><%=product.get(a).getAppl_Quantity()%>
                                                </td>
                                                <td><input type="hidden" id="unit_" value="<%=product.get(a).getUnit()%>"><%=product.get(a).getUnit()%></td>
                                                <td><input type="hidden" id="rate_" value="<%=product.get(a).getRate()%>"><%=product.get(a).getRate()%></td>
                                            </tr>
                                            <%}%>
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
                        <span class="card-title">Inspection Details</span>
                    </div>
                    <div class="card-body">
                        <div class="form-group row mb-2">
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                <label class="form-label">Construction Site fall in Dzongkhag/Yenlag Thromde<span class="text-danger">*</span></label>
                                <select name="contructionSite" class="chosen-select form-control form-control-sm" id="contructionSite" onclick="removeErrors('Division_err')" onchange="removeErrors('Division_err')">
                                    <option value="">--- select ---</option>
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                </select>
                                <span class="text-danger" id="contructionSite_err"></span>
                                <%--<input type="hidden" name="register_Geog" value="y"/>--%>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                <label class="form-label"><b>2KM from the class B Thromde :</b> <span class="text-danger">*</span></label>
                                <select name="awayFromThromB" class="chosen-select form-control form-control-sm" id="awayFromThromB" onclick="removeErrors('Range_err')" onchange="removeErrors('Range_err')">
                                    <option value="">-- select --</option>
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                </select>
                                <span class="text-danger" id="awayFromThromB_err"></span>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                <label class="form-label"><b>Nature of Applicant:</b> <span class="text-danger">*</span></label>
                                <select name="natureOfApplicant" class="chosen-select form-control form-control-sm" id="natureOfApplicant" onclick="removeErrors('Range_err')" onchange="removeErrors('Range_err')">
                                    <option value="">-- select --</option>
                                    <option value="Native">Native</option>
                                    <option value="Immigrant">Immigrant</option>
                                </select>
                                <span class="text-danger" id="natureOfApplicant_err"></span>
                            </div>
                        </div>
                        <div class="form-group row mb-2">
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                <label class="form-label">Proposed Construction site is:<span class="text-danger">*</span></label>
                                <select name="proposedConstructionSite" class="chosen-select form-control form-control-sm" id="proposedConstructionSite" onclick="removeErrors('Range_err')" onchange="removeErrors('Range_err')">
                                    <option value="">-- select --</option>
                                    <option value="Inherited">Inherited</option>
                                    <option value="Purchased">Purchased</option>
                                </select>
                                <span class="text-danger" id="proposedConstructionSite_err"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-2">
                    <div class="bg-blue card-status card-status-left"></div>
                    <div class="card-header">
                        <span class="card-title">Inspection Team Information</span>
                    </div>
                    <div class="card-body">
                        <div class="form-group row mb-2">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div style="overflow-x: auto;white-space: nowrap;">
                                    <table class="table table-bordered" id="hrDtlsTable">
                                        <thead style="background-color:#478d86">
                                        <tr>
                                            <th class="text-white" style="width: 8%">CID</th>
                                            <th class="text-white" style="width: 5%">Name</th>
                                            <th class="text-white" style="width: 8%">Designation</th>
                                            <th class="text-white" style="width: 8%">Contact Number</th>
                                            <th class="text-white" style="width: 5%">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div class="col-lg-12 text-right">
                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" id="addMoreHrBtn" data-target="#addHRModal" style="background-color:#833785">
                                            <i class="zmdi zmdi-plus-circle-o"></i> Add More</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mb-2">
                    <div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                            <label><b>Attach inspection report here:<span class="text-danger">*</span></b></label>
                            <input type="file" name="files" id="doc1" class="alert" onchange="validateAttachment(this.value,'doc1','doc_check1'),removeErrors('endorsement_err')" onclick="removeErrors('endorsement_err')">
                            <span class="text-danger" id="endorsement_err"></span>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 text-right">
                            <button class="btn bg-warning" type="button" onclick="addmoreattachemnts()">
                                <i class="zmdi zmdi-plus"> Add More</i></button>
                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <span id="fileadd"></span>
                    </div>
                    <div class="col-sm-5">
                        <label class="form-label">Has applicant availed subsidized timber from CF?:  <span class="text-danger">*</span></label>
                    </div>
                    <div class="col-sm-7 justify-content-center">
                        <label class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="hasAvailTimberB4" id="pit" onchange="availedRPT(this.value)" value="yes">
                            <span class="custom-control-indicator"></span>
                            <span class="custom-control-description">Yes</span>
                        </label>
                        <label class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="hasAvailTimberB4" id="stationary_sawmill" onchange="availedRPT(this.value)" value="no">
                            <span class="custom-control-indicator"></span>
                            <span class="custom-control-description">No</span>
                        </label>
                    </div>
                    <div class="alert alert-danger" id="Import_TypeErrorMsg" style="display:none"></div>
                </div>
                <div class="card mt-2" id="pastRecoedSection" style="display: none;">
                    <div class="bg-blue card-status card-status-left"></div>
                    <div class="card-header">
                        <span class="card-title">Applicant past records</span>
                    </div>
                    <div class="card-body">
                        <div class="form-group row mb-2">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div style="overflow-x: auto;white-space: nowrap;">
                                    <table class="table table-bordered" id="trDtlsTable">
                                        <thead style="background-color:#478d86">
                                        <tr>
                                            <th class="text-white" style="width: 8%">CID Number</th>
                                            <th class="text-white" style="width: 8%">Timber Type</th>
                                            <th class="text-white" style="width: 5%">Quantity</th>
                                            <th class="text-white" style="width: 8%">Year</th>
                                            <th class="text-white" style="width: 5%">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                    <div class="col-lg-12 text-right">
                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" id="addMoreTimberBtn" data-target="#addPastModal"  style="background-color:#833785"><i class="zmdi zmdi-plus-circle-o"></i> Add More</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-2">
                    <div class="card-body border-dark">
                        <div class="form-group row mb-2">
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 form-control-xs">Remarks:</div>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                                <textarea class=" form-control col-md-10 col-sm-12 col-lg-10 col-xs-12 form-control-sm" name="remarks" id="comment"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                        <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                        <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">Application </span>&nbsp;&nbsp;&nbsp;
                        <span class="px-1 small text-muted form-label text-muted form-label"> History</span>
                        <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                    </div>
                    <div class="panel-body  ml-2 mr-2" id="viewStatusDetailDiv">
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
                                <b>The details and recommendation provided by the inspection team is correct. In case of any false or wrong information, we are liable to be penalized under the Forest & Nature Conservation Act 1995 its Rules and Regulations made there under. </b>
                                <input type="hidden" value="0" name="Department_Id" id="Department_Id">
                            </div>
                        </div>
                        <div class="form-group text-right sub_button">
                            <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 form-control-sm">
                                <button type="button" class="btn fa-pull-right text-white" id="btn_submit_final_form" disabled="true" style="background-color: #236F67" onclick="showConfirmation()"><a href="#" class="text-white">Submit&nbsp;&nbsp;<i class="zmdi zmdi-check-circle"></i></a></button>
                               <%-- <button class="btn btn-md fa-pull-right mr-2 text-white" style="background-color: #F15628"  id="showRejectSection" onclick="Reject()" type="button">&nbsp;&nbsp;<i class="zmdi zmdi-xbox"></i>Reject</button>--%>
                            </div>
                        </div>
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
                                <button type="button" class="btn btn-success" onclick="updateInspectionDetails()">Yes</button>
                                <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
        <div class="card mt-2 text-center" id="messageDiv" style="display: none; margin-top: 20px; height: 450px">
            <div class="card-body border-dark">
                <div class="form-group row mb-2 declaration">
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm text-black text-center">
                        <div class="alert " role="alert" id="responseText"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" class="modal fade in" id="addHRModal">
    <div class="modal-dialog modal-lg" id="hrModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myModalLabel" class="modal-title">Add Team Member</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">CID Number <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="inspectionTeamEntityList[0].InspectionTeam_CID" onchange="fetchCidDetails(this.value)" min="11" maxlength="11" class="form-control hr-cid" id="hr1" required placeholder="CID No ...">
                        </div>
                    <label class="col-lg-2 col-md-2 col-sm-2">Name:<span class="text-danger">*</span>:</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <input type="text" name="inspectionTeamEntityList[0].InspectionTeam_Name" class="form-control hr-cid" id="hr2" required placeholder="Text..." readonly>
                    </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">Designation<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select name="inspectionTeamEntityList[0].designation" id="hr3" required class="form-control custom-select text-left select-beast">
                                <option value="">Select Designation</option>
                                <c:forEach var="item" items="${designationListHr}">
                                    <option value="${item.serviceId}"><c:out value="${item.remarks}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">Phone Number<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="inspectionTeamEntityList[0].inspectionTeam_ContactNo" id="hr4" class="form-control name" min="8" maxlength="8" required placeholder="number...">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="getModalData('hrDtlsTable','hr',4)" type="button">OK</button>
                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<div aria-hidden="true" aria-labelledby="pastModalLabel" role="dialog" class="modal fade in" id="addPastModal">
    <div class="modal-dialog modal-lg" id="pastModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="pastModalLabel" class="modal-title">Past Records</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">CID Number <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="pastRecordDetailEntityList[0].applicant_CID" class="form-control hr-cid" id="pr1" min="11" maxlength="11" required="" placeholder="CID No ...">
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">Timber Type <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select name="pastRecordDetailEntityList[0].timber_Type" id="pr2" required="" class="form-control custom-select text-left select-beast">
                                <option value="">-- Select Timber Type --</option>
                                <c:forEach var="item" items="${timberType}">
                                    <option value="${item.serviceId}"><c:out value="${item.remarks}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-md-2 col-sm-2">Quantity:<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="pastRecordDetailEntityList[0].quantity_Taken" class="form-control hr-cid" id="pr3" required="" placeholder="number...">
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2">Year<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="pastRecordDetailEntityList[0].year_of_allotment" class="form-control hr-cid" id="pr4" required="" placeholder="year ...">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="getModalData('trDtlsTable','pr',4)" type="button">OK</button>
                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<%--    <script src="<c:url value='/resources/framework/vendors/bower_components/jquery/dist/jquery.min.js' />"></script>
    <script src="<c:url value='/resources/framework/vendors/bower_components/popper.js/dist/umd/popper.min.js' />"></script>
    <script src="<c:url value='/resources/framework/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/framework/vendors/bower_components/jquery.scrollbar/jquery.scrollbar.min.js' />"></script>
    <script src="<c:url value='/resources/framework/vendors/bower_components/jquery-scrollLock/jquery-scrollLock.min.js' />"></script>
    <script src="<c:url value='/resources/sweetalert/sweetalert.min.js' />"></script>
    <script src="<c:url value='/resources/jquery/jquery.validate.js' />"></script>
    <!-- App functions and actions -->
    <script src="<c:url value="/resources/dofps.js"/>"></script>
    <script src="<c:url value='/resources/framework/js/app.min.js' />"></script>--%>
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
    <script>
        var hr_modal = $("#hrModal").html();
        var tr_modal = $("#pastModal").html();
        var j= 0;

        function getModalData(tableId, prefix, totalCol) {
            var td = "";
            var modal = $('#' + prefix + '1').closest('.modal');

            /*if (modal.find(':input').valid() === false) {
                warningMsg('Please provide valid information');
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
            $("#hrModal").empty().html(hr_modal);
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


        function fetchCidDetails(cid) {
            $("input[name=cons]").attr('disabled', true);
            var cons_type = "";
            if (validatecid(cid)) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/getCitizenDetailsOnline?cid=' + cid +'&cons_type=' + cons_type,
                    type: 'GET',
                    success: function (res) {
                        if(res.status_Id==1){
                            $('#cid').val(res.cid_Number);
                            $('#hr2').val(res.name);
                            $('#hr4').val(res.phoneNumber);
                        }else{
                            $('#cid').val("").focus();
                            warningMsg("Invalid CID/Citizen API issue! please check and try again. If problem persists contact system administrator");
                        }
                    }
                });
            }
        }

        function validatecid(cid) {
            var retval = true;
            if (cid.substring(0, 1) == "3") {
                //  $('#cider').html('Cid starting with 3 is not allow');
                warningMsg("Cid starting with 3 is not allow");
                retval = false;
            }
            else if (cid.length != 11) {
                //   $('#cider').html('Bhutanese CID should have 11 digit long');
                warningMsg("Bhutanese CID should have 11 digit long");
                retval = false;
            }
            return retval;
        }
        $('#cid').click(function () {
            $('#cider').html('').focus();
        });

        function removeerror(id){
            $('#'+id).html('');
        }

        function removeErrors(id){
            $('#'+id).html('');
        }

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

        var inicount = 0;
        function addmoreattachemnts() {
            inicount++;
            var fnshow = 'validateAttachment(this.value,"file' + inicount + '","file_added' + inicount + '")';
            $('#fileadd').append('<div class="row" id="addedfile' + inicount + '"><div class="col-sm-6"><input type="file" class="alert" onchange=\'' + fnshow + '\' name="files" id="file' + inicount + '"></div><div class="col-sm-6"><button class="btn btn-danger fa fa-pull-right mt-4" type="button" onclick="deleteate(' + inicount + ')"><i class="zmdi zmdi-delete pr-2"></i>Delete</button></div></div>');
        }

        function deleteate(id) {
            $('#addedfile' + id).remove();
        }

        function validateAttachment(vl, id, checkId) {
            if (vl != "") {
                $('#' + id).prop('class', 'alert badge-info');
                //$('#' + checkId).prop('class', 'zmdi zmdi-check pl-2');
            } else {
                $('#' + id).prop('class', 'alert badge-danger');
                // $('#' + checkId).prop('class', 'zmdi zmdi-delete pl-2');
            }
        }

        function viewAttachment(uuid, type, path, name) {
            var url = '${pageContext.request.contextPath}/dealing/loadpagetoemptylayout/donwloadFiles?uuid=' + uuid + '&type=' + type;
            window.open(url, '_blank');
        }

        function showConfirmation(){
            $('#confirmationModel').modal('show');
            $('#targetId').val('acknowledgementmessage');
            $('#messages').html('You are about to submit application. Are you sure to proceed ?');
        }

        function updateInspectionDetails() {
            var id = $('#fp_Product_Id').val();
            dofpsGlobal.formIndexing($('#hrDtlsTable').find('tbody'));
            dofpsGlobal.formIndexing($('#trDtlsTable').find('tbody'));
/*         $.ajax({
                type : "POST",
                url : '${pageContext.request.contextPath}/gewog/updateInspectionDetails',
                enctype: 'form-data',
                data: $('#personalFormGAO').serialize(),
                success : function(res) {
                    if(res.status==1){
                        successMsg(res.text,'${pageContext.request.contextPath}/loginMain');
                    }else{
                        warningMsg(res.text);
                    }
                }
            });*/
            debugger;
            if(validateGAO()==true){
                var url = '${pageContext.request.contextPath}/gewog/updateInspectionDetails';
                var options = {target: '#responseText', url: url, enctype: 'multipart/form-data',type : "POST", data: $('#personalFormGAO').serialize()};
                $("#personalFormGAO").ajaxSubmit(options);
                $("#wholeCard").hide();
                $("#messageDiv").show();
                $('#confirmationModel').modal('hide');
            }
        }

        function validateGAO() {
            var returnVal = true;
            if(validate_hr()==false){
                returnVal = false;
            }
            if ($('#contructionSite').val() == "") {
                $('#contructionSite_err').html('Construciton site is mandatory');
                $('#contructionSite_err').show();
                $('#contructionSite_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#awayFromThromB').val() == "") {
                $('#awayFromThromB_err').html('Distance from throm B is mandatory');
                $('#awayFromThromB_err').show();
                $('#awayFromThromB_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#natureOfApplicant').val() == "") {
                $('#natureOfApplicant_err').html('Nature of Applicant is mandatory');
                $('#natureOfApplicant_err').show();
                $('#natureOfApplicant_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#proposedConstructionSite').val() == "") {
                $('#proposedConstructionSite_err').html('Construction site is mandatory');
                $('#proposedConstructionSite_err').show();
                $('#proposedConstructionSite_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            if ($('#doc1').val() == "") {
                $('#endorsement_err').html('Add inspection report');
                $('#endorsement_err').show();
                $('#endorsement_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            return returnVal;
        }

        function validate_hr() {
                var isValid = true;
                var hrAdded = false;
                $('#hrDtlsTable').find(':input').each(function () {
                    hrAdded = true;
                    if (isValid == true) {
                        isValid = $('#personalFormGAO').validate().element(this);
                    } else {
                        $('#personalFormGAO').validate().element(this);
                    }
                });
                if(hrAdded == false){
                    isValid = false;
                    warningMsg("Add at least three Inspection team member.")
                }
            return isValid;
        }

        function updateReject(val) {
            if(validateReject()){
            var url = '${pageContext.request.contextPath}/dealing/rejectApplication?value=' + val;
            var options = {target: '#loadMainPage', url: url, enctype: 'form-data', data: $('#personalFormGAO').serialize()};
            $("#personalFormGAO").ajaxSubmit(options);
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
                    //isSubmitted = false;
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
            delTableRow();
        });
    </script>
</body>

