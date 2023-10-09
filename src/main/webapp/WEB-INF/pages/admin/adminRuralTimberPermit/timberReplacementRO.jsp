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
    .card {
        background-color: rgb(241, 243, 244);
    }

    form, input, select, textarea {
        color: black !important;
    }

    .form-label {
        font-weight: bold;
    }
</style>
<body>
<section class="content">
    <%--<div class="content__inner">--%>
    <div class="">
        <div class="card" id="wholeCard">
            <%-- <div class="card-header">
                 <span class="card-title form-label" style="color: #002752"><b>Application for RTP (Verification & Approval) >> New Contruction >>
                     Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <b> <%=App_Details.getApp_Submission_Date()%></b></span>
             </div>--%>
            <div class="card-header">
                <b>Application for RTP (Verification & Approval)</b><span style="font-size: 12px"> >> Application Number :  App_Details.getApplication_Number()%> >> Application Submission Date: <%=App_Details.getApp_Submission_Date()%></span>
            </div>
            <input type="hidden" id="applicationNumber" value="<%=App_Details.getApplication_Number()%>">
            <div class="card-body">
                <%-- <form method="post" enctype="multipart/form-data" id="replacementVerifyForm" action="#">--%>
                <form method="post" enctype="multipart/form-data" id="replacementVerifyForm" action="#">
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
                            <%-- <span class="pull-right" id="showTimberSection" style="font-size: large; text-align: right; margin-left: 535px"><i class="form-label zmdi zmdi-plus-circle-o"></i></span>
                             <span class="pull-right"id="hideTimberSection" style="font-size: large; text-align: right; margin-left: 535px;display: none"><i class="form-label zmdi zmdi-minus-circle-outline"></i></span>--%>
                        </div>
                        <div class="card-body" id="timberSection">
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Application Type </label>
                                    <input type="text" class="form-control form-control-sm" id="app_type" value="<%=App_Details.getApplication_Type()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Timber Form </label>
                                    <%if (product.get(0).getProduct_Catagory().equalsIgnoreCase("Log")) {%>
                                    <input type="text" class="form-control form-control-sm" id="tim_form" value="Log Form" readonly>
                                    <%} else {%>
                                    <input type="text" class="form-control form-control-sm" id="tim_form" value="Standing Form" readonly>
                                    <%}%>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Mode of Sawing </label>
                                    <input type="text" class="form-control form-control-sm" id="mos" value="<%=App_Details.getMode_of_Swing_Desc()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-2">
                                <%-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
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
                                                <th class="text-white" style="width: 3%">Replacement Quantity Request</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%for (int a = 0; a < product.size(); a++) {%>
                                            <tr>
                                                <td><%=a + 1%>
                                                </td>
                                                <td><%=product.get(a).getProduct_Catagory()%>
                                                </td>
                                                <td><%=product.get(a).getAppl_Quantity()%>
                                                </td>
                                                <td><%=App_Details.getAllot_Quantity()%>
                                                </td>
                                                <td><%=product.get(a).getQuantityToReplace()%>
                                                </td>
                                            </tr>
                                            <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Assessment Report :<span class="text-danger">*</span></b></label>
                                    <input type="file" name="files" id="doc1" class="alert" onchange="validateAttachment(this.value,'doc1','doc_check1'),removeErrors('endorsement_err')" onclick="removeErrors('endorsement_err')">
                                    <span class="text-danger" id="doc_err"></span>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 text-right">
                                    <button class="btn bg-warning" type="button" onclick="addmoreattachemnts()"><i class="zmdi zmdi-plus"> Add More</i></button>
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <span id="fileadd"></span>
                            </div>
                        </div>
                    </div>
                    <div class="card mt-2">
                        <div class="card-body border-dark">
                            <div class="form-group row mb-2">
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 form-control-xs">Remarks:</div>
                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                                    <textarea class=" form-control col-md-10 col-sm-12 col-lg-10 col-xs-12 form-control-sm" name="remarks" id="remarks"></textarea>
                                </div>
                            </div>
                            <div class="form-group row mb-2 ml-5" id="rejectReason" style="display: none">
                                <span class="text-danger"> <b>Note:</b> Choose one of the rejecting reasons. Below are the standard rejection reasons that will be send to the applicant via <b>SMS</b>. </span>
                                <br/><hr/>
                                <div class="form-group row mb-2">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm"><b>Rejection Reason: <span class="text-danger">*</span></b></div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-control-sm">
                                        <select name="rejection_Reason" class="chosen-select form-control form-control-sm" id="rej_reason" onchange="rejectReason(this.value)">
                                            <option value="0">Select Rejection Reason</option>
                                            <c:forEach var="rlist" items="${rejection_list}" varStatus="counter">
                                                <option value="${rlist.header_Name}">${rlist.header_Name}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="text-danger" id="Rejection_Reason_err"></span>
                                        <input type="text" id="rej_other_reason" class="form-control mt-2" placeholder="Other reject reason." name="document_Type" style="display: none;"/>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 form-control-sm">
                                        <button type="button" class="btn btn-danger btn-sm fa-pull-right mr-5" onclick="updateReject('<%=App_Details.getApplication_Number()%>')"><i class="fa fa-times"></i> Reject</button>
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 form-control-sm">
                                        <button type="button" class="btn btn-success btn-sm fa-pull-right" onclick="Cancel()">Cancel&nbsp;<i class="fa fa-ban"></i></button>
                                    </div>
                                </div>
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
                                    <b>I have verified the details of the appilcant and the inspection team's report,and found to be true and correct.</b>
                                    <b>Timber requirement is genuine and for bonafide house construction.</b>
                                    <input type="hidden" value="0" name="Department_Id" id="Department_Id">
                                </div>
                            </div>
                            <div class="form-group text-right sub_button">
                                <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 form-control-sm">
                                    <button class="btn fa-pull-right text-white" id="btn_submit_final_form" disabled="true" style="background-color: #236F67;"
                                            onclick="verifyReplacement('<%=App_Details.getApplication_Number()%>')" type="button"><a href="#" class="text-white">Submit&nbsp;<i class="zmdi zmdi-check-circle"></i></a></button>
                                    <%-- <button class="btn btn-md fa-pull-right mr-2 text-white" style="background-color: #F15628" id="showRejectSection" onclick="Reject()" type="button"><i class="zmdi zmdi-xbox"></i>Reject</button>--%>
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
    var mi_modal = $("#miModal").html();
    var tr_modal = $("#pastModal").html();
    var j = 0;
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

            var tdVal = "<input type='hidden' class='" + $this.attr('id') + "' name='" + name + "' value='" + value + "'/>" + text;
            td = td + "<td>" + tdVal + "</td>";
        }

        var tr = "<tr id='" + j + "'>" + td + "<td class=''>" +
                "<a class='p-2 del_row'><i class='zmdi zmdi-delete text-danger'></i></a></td></tr>";

        $("#" + tableId).append(tr).find(".noRecord").hide();

        j = j + 1;

        $('#' + tableId).find('.tbd').remove();
        closeHRModal(modal);
    }

    function closeHRModal(modal) {
        modal.modal('hide');
        $("#miModal").empty().html(mi_modal);
        $("#pastModal").empty().html(tr_modal);
    }

    function delTableRow() {
        $('body').on('click', '.del_row', function () {
            if ($(this).closest('table').find('tbody tr').length > 1) {
                $(this).closest('tr').remove();
            } else {
                warningMsg("Cannot delete last row. You must have at least one row!");
            }
        });
    }

    function removeerror(id) {
        $('#' + id).html('');
    }

    function removeErrors(id) {
        $('#' + id).html('');
    }

    function rejectReason(val) {
        //alert(val);
        if (val == "Others") {
            $('#rej_other_reason').show();
        } else {
            $('#rej_other_reason').hide();
        }
    }

    function Reject() {
        $('#btn_submit_final_form').prop('disabled', true);
        $('#btnreject').hide();
        $('#rejectReason').show();
        $('#showRejectSection').hide();
        $('.declaration').hide();
        $('.sub_button').hide();
        $('#submit_form').prop('disabled', true);
    }

    function Cancel() {
        $('#btn_submit_final_form').prop('disabled', true);
        $('#btnreject').show();
        $('#rejectReason').hide();
        $('#showRejectSection').show();
        $('.declaration').show();
        $('.sub_button').show();
        $('#submit_form').prop('disabled', false);
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

    function verifyReplacement(appNo) {
        var remarks = $('#remarks').val();
        /*  $.ajax({
         type : "POST",
         url : '
        ${pageContext.request.contextPath}/gewog/verifyReplacement?remarks=' + remarks + '&appNo='+appNo,
         data: $('#replacementVerifyForm').serialize(),
         cache : false,
         enctype: "form-data",
         success : function(res) {
         if(res.status==1){
         successMsg(res.text, '
        ${pageContext.request.contextPath}/loginMain');
         }else{
         warningMsg(res.text);
         }
         }
         });*/
    if(validateRRO()==true){
        var url = '${pageContext.request.contextPath}/gewog/verifyReplacement?remarks=' + remarks + '&appNo=' + appNo;
        var options = {
            target: '#responseText',
            url: url,
            enctype: 'multipart/form-data',
            type: "POST",
            data: $('#replacementVerifyForm').serialize()
        };
        $("#replacementVerifyForm").ajaxSubmit(options);
        $("#wholeCard").hide();
        $("#messageDiv").show();
        }
    }

    function validateRRO() {
        var returnVal = true;
        if ($('#doc1').val() == "") {
            $('#doc_err').html('Add assessment report');
            $('#doc_err').show();
            $('#doc_err').delay(2000).fadeOut('slow');
            returnVal = false;
        }
        return returnVal;
    }

    function updatePayment(appNo) {
        var remarks = $('#remarks').val();
        var modeOfPayment = $('#modeOfPayment').val();
        var receiptNo = $('#receiptNo').val();
        var amount = $('#amount').val();
        $.ajax({
            type: "POST",
            url: '${pageContext.request.contextPath}/gewog/updatePayment?remarks=' + remarks + '&appNo=' + appNo + '&modeOfPayment=' + modeOfPayment + '&receiptNo=' + receiptNo + '&amount=' + amount,
            data: $('#replacementVerifyForm').serialize(),
            cache: false,
            success: function (res) {
                if (res.status == 1) {
                    successMsg(res.text, '${pageContext.request.contextPath}/loginMain');
                } else {
                    warningMsg(res.text);
                }
            }
        });
    }

    function updateReject(val) {
        if (validateReject()) {
            var url = '${pageContext.request.contextPath}/dealing/rejectApplication?value=' + val;
            var options = {
                target: '#loadMainPage',
                url: url,
                enctype: 'multipart/form-data',
                type: "POST",
                data: $('#replacementVerifyForm').serialize()
            };
            $("#replacementVerifyForm").ajaxSubmit(options);
        }
    }

    function validateReject() {
        var returnval = true;
        if ($('#Rejection_Reason').val() == "") {
            $('#Rejection_Reason_err').html('Please select reject reason');
            returnval = false;
        }
        return returnval;
    }

    $('[data-toggle="tooltip"]').tooltip();

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
        if (id == 5 || id == 15) {
            $('.log_table').show();
        } else {
            $('.standing_table').show();
            $('.stand_table').show();
        }

        var cf = $('#memberOfCF').val();
        if (cf == 'Yes') {
            $('#checkForCF').show();
        } else {
            $('#checkForCF').hide();
        }
        getApplicationDetails();
        delTableRow();
    });
</script>
</body>

