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
<% UserRolePrivilegeHierarchyObj user = (UserRolePrivilegeHierarchyObj) request.getSession().getAttribute("userdetail");
    WorkFlowDto App_Details = (WorkFlowDto) request.getAttribute("APPLICATION_DETAILS");
    List<CommonDto> attachment = App_Details.getDocuments();
    List<CommonDto> rangedtls = App_Details.getCommonDetails();
    List<TimberDetailsDto> product = App_Details.getTimberdetails();
%>
<body>
<section class="content">
    <%--<div class="content__inner">--%>
    <div class="">
        <div class="card">
            <div class="card-header">
                <b>Application for WP (Verification & Approval)</b><span style="font-size: 12px"> >> Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <%=App_Details.getApp_Submission_Date()%></span>
            </div>
            <input type="hidden" id="applicationNumber" value="<%=App_Details.getApplication_Number()%>">
            <form action="#" id="personalForm" method="get">
                <div class="card-body">
               <span class="text-danger">
                NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields.
               </span>
                    <div class="card">
                        <div class="card-body border-dark">
                            <span> <b><i><u><i class="fa fa-address-book"> &nbsp;&nbsp;&nbsp;</i>Applicant Details.</u></i></b> </span>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>CID Number : </b></label>
                                    <input type="text" class="form-control" id="cid" value="<%=App_Details.getCid_Number()%>" readonly>
                                    <input type="hidden" name="application_Number" value="<%=App_Details.getApplication_Number()%>"/>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Full Name :</b></label>
                                    <input type="text" class="form-control" id="name" value="<%=App_Details.getName()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Mobile Number : </b></label>
                                    <input type="number" class="form-control" name="phone_Number" id="phone_Number" value="<%=App_Details.getPhone_Number()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>House Number :</b></label>
                                    <input type="text" class="form-control" id="house_no" value="<%=App_Details.getHouse_No()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Household Number :</b></label>
                                    <input type="number" class="form-control" id="household" value="<%=App_Details.getHouse_Hold_No()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Tharm Number :</b></label>
                                    <input type="text" class="form-control" id="thramNo" value="<%=App_Details.getThram_No()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Dzongkhag : </b></label>
                                    <input type="text" class="form-control" id="dzongkhag" value="<%=App_Details.getDzongkhag_Name()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Gewog :</b></label>
                                    <input type="text" class="form-control" id="gewog" value="<%=App_Details.getGewog_Name()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Village :</b></label>
                                    <input type="text" class="form-control" id="village" value="<%=App_Details.getVillage_Name()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Head of Gung :</b></label>
                                    <input type="text" class="form-control" id="g_cid" value="<%=App_Details.getHead_of_Gung()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Alternative person's details :</b></label>
                                    <input type="text" class="form-control" readonly id="tel_num" value="<%=App_Details.getAlternativeNumberRelation()%>">
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Member of community forest : </b></label>
                                    <input type="text" readonly class="form-control" id="ff" value="<%=App_Details.getMember_of_Forest_community()%>">
                                </div>
                            </div>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Application Type :</b></label>
                                    <input type="text" class="form-control" id="app_type" value="<%=App_Details.getApplication_Type()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Timber Form :</b></label>
                                    <input type="text" class="form-control" id="tim_form" value="Pole and FireWood" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Location :</b></label>
                                    <input type="text" class="form-control" id="cl" value="<%=App_Details.getVillage_Name()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Census registered within particular Geog :</b></label>
                                    <input type="text" class="form-control" readonly id="rg" value="<%=App_Details.getRegister_Geog()%>">
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Division/Park :</b></label>
                                    <input type="text" class="form-control" readonly id="division_Park_Id" value="<%=App_Details.getDivision_Park_Name()%>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="text" class="form-control" readonly id="markingDate" value="<%=App_Details.getMarking_Date()%>">
                    <div class="form-group row mb-2">
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                            <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">WP</span>&nbsp;&nbsp;&nbsp;
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
                                        <th class="text-white" style="width: 3%">Royalty Rate(NU)</th>
                                        <th class="text-white" style="width: 2%">Unit</th>
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
                                        <td><%=product.get(a).getRate()%></td>
                                        <td><%=product.get(a).getUnit()%></td>
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
                    <%if(App_Details.getPayment_status().equalsIgnoreCase("Unpaid")){%>
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
                    <div class="card">
                        <div class="card-body border-dark">
                            <div class="form-group row mb-4">
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">Remarks:</div>
                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                    <textarea class=" form-control col-md-10 col-sm-12 col-lg-10 col-xs-12" name="remarks" placeholder="Remark" id="remarks"></textarea>
                                </div>
                            </div>
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
                    </div>
                    <div class="card mt-2">
                        <div class="card-body border-dark">
                            <div class="form-group row mb-2 declaration">
                                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 custom-switch-sm">
                                    <div class="demo-inline-wrapper">
                                        <div class="form-group">
                                            <div class="toggle-switch">
                                                <input type="checkbox" class="toggle-switch__checkbox" id="submit_form" onchange="display()"><i class="toggle-switch__helper"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                                    <b>I have thoroughly verified the details of the applicant and found to be true and correct. Other forest produce requirement is genuine and applied for bonafide
                                        purpose/reason. I hereby recommend for allotting other forest produce to be applicant as per the requirement and entitlement.</b>
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
                </div>
            </form>
        </div>
    </div>
</section>
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
    $(document).ready(function () {
        <% for (int a = 0; a < product.size(); a++) { %>
        $("#qty_app<%=a%>").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                return false;
            }
        });
        <%}%>
        getApplicationDetails();
    });

    function removeError(id) {
        $('#' + id).html('');
    }
    function display() {
        if ($('#submit_form').prop('checked')) {
            $('#btn_submit_final_form').prop('disabled', false);
        }
        else {
            $('#btn_submit_final_form').prop('disabled', true);
        }
    }

    function showConfirmation(){
        $('#confirmationModel').modal('show');
        $('#targetId').val('acknowledgementmessage');
        $('#messages').html('You are about to submit application. Are you sure to proceed ?');
    }

    function approveRO(appNo) {
        //dofpsGlobal.formIndexing($('#markingDtlsTable').find('tbody'));
        var remarks = $('#remarks').val();
        var phoneNo = $('#phone_Number').val();
        var markingDate = $('#markingDate').val();
        if(markingDate != "null" || markingDate !=""){
            $('#confirmationModel').modal('hide');
            $.ajax({
                type : "POST",
                url : '${pageContext.request.contextPath}/adminWoodAndPoles/woodAndPolesROApprove?remarks=' + remarks + '&appNo='+appNo+'&phoneNo='+phoneNo,
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
            warningMsg("Marking date not yet scheduled for this applicant !!");
        }
    }

    function updatePayment(appNo) {
        var remarks = $('#remarks').val();
        var modeOfPayment = $('#modeOfPayment').val();
        var receiptNo = $('#receiptNo').val();
        var amount = $('#amount').val();
        var constructionType = $('#app_type').val();
        if(validateRO()==true){
            $.ajax({
                type : "POST",
                // url : '${pageContext.request.contextPath}/gewog/updatePayment?remarks=' + remarks + '&appNo='+appNo+'&modeOfPayment='+modeOfPayment+'&receiptNo='+receiptNo+'&amount='+amount,
                url : '${pageContext.request.contextPath}/adminWoodAndPoles/updatePayment?remarks=' + remarks + '&appNo='+appNo+'&constructionType='+constructionType,
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

    function forwardToOtherRange(appNo) {
        var remarks = $('#remarks').val();
        var modeOfPayment = $('#modeOfPayment').val();
        var rangeId = $('#range_id').val();
        if(remarks !="" && rangeId !=''){
            $.ajax({
                type : "POST",
                url : '${pageContext.request.contextPath}/adminWoodAndPoles/forwardToOtherRange?remarks='+ remarks + '&appNo='+appNo+ '&rangeId='+rangeId,
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

    function updateReject() {
        var remarks = $('#remarks').val();
        var appNo = $('#applicationNumber').val();
        var phone_Number = $('#phone_Number').val();
        if (remarks != "") {
            $.ajax({
                type: "POST",
                url: '${pageContext.request.contextPath}/gewog/reject?remarks=' + remarks + '&appNo=' + appNo + '&phone_Number=' + phone_Number,
                data: $('#personalForm').serialize(),
                cache: false,
                success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text, '${pageContext.request.contextPath}/loginMain');
                    } else {
                        warningMsg(res.text);
                        $('#remarks').focus();
                    }
                }
            });
        } else {
            $('#remarks').focus();
        }
    }

    function getApplicationDetails() {
        var applicationNumberID = $('#applicationNumber');
        $.ajax({
            url: '${pageContext.request.contextPath}/gewog/getApplicationDetails',
            type: 'GET',
            data: {applicationNumber: applicationNumberID.val()},
            success: function (res) {
                var tr = "";
                //populate(res.dto);
                $('#applicationNo').text(applicationNumberID.val());
                $('#statusName').text(res.dto.statusName);
                for (var i = 0; i < res.dto.length; i++) {
                    tr += '<tr>'
                    + '<td>' + res.dto[i].statusName + '</td>'
                    + '<td>' + res.dto[i].actorName + '</td>'
                    + '<td>' + res.dto[i].actionDate + '</td>'
                    + '<td>' + res.dto[i].remarks + '</td>'
                    + '</tr>';
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
</script>
</body>

