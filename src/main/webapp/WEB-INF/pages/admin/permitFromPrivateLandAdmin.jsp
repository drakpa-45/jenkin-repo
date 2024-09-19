<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 1/16/2020
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    UserRolePrivilegeDTO userBean = null;
    int n = 0;
    if (session.getAttribute("UserRolePrivilege") != null) {
        userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String LocationId = "";
        for (n = 0; n < userBean.getJurisdictions().length; n++) {
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
<body>
<div id="loadMainPage">
    <section class="content">
        <%--<div class="content__inner">--%>
            <div class="card" id="wholeCard">
                <div class="card-header badge-secondary">
                    <b>Rural Timber Permit / </b>Removal of Forest Produce From Private Land
                </div>
                <div class="card-body">
               <span class="text-danger">
                NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields.
               </span>
                    <div class="row form-group">
                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                            <form action="#" id="personalForm" method="post" enctype="multipart/form-data">
                                <div class="tab-content" id="nav-tabContent">
                                    <div class="card">
                                        <div class="card-body border-dark">
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>CID Number: <span class="text-danger">*</span></b>
                                                        <a href="#" data-toggle="tooltip" data-placement="top" title="" data-original-title="Enter applicant's BHUTANESE CITIZENSHIP IDENTITY CARD NUMBER"></a>
                                                    </label>
                                                    <input type="number" class="form-control" id="cid" name="" placeholder="Enter your CID " onchange="fetchCidDetails(this.value)" min="1" onclick="removeError('cider')"/>
                                                    <span id="cider" class="text-danger"></span> </label>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Full Name:</b></label>
                                                    <input type="text" class="form-control" id="name" name="name" readonly>
                                                    <span id="gewog_sl_no_err" class="text-danger"></span>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Gender:</b></label>
                                                    <input type="text" class="form-control" id="gender" name="genderType" readonly>
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Dzongkhag :</b> </label>
                                                    <input type="text" class="form-control" id="dzongkhag" name="dzongkhag_Name" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Gewog :</b></label>
                                                    <input type="text" class="form-control" id="gewog" name="gewog_Name" readonly>
                                                    <input type="hidden"  id="gewogId" name="gewogId"/>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Village:</b></label>
                                                    <input type="text" class="form-control" id="village" name="village_Name" readonly>
                                                    <input type="hidden" class="form-control" id="village_Serial_No" name="village_Serial_No"/>
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Household Number :</b> </label>
                                                    <input type="number" class="form-control" id="household" name="house_Hold_No" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Gung CID number :</b></label>
                                                    <input type="number" class="form-control" id="g_cid" name="cid_Number" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>House Number: </b></label>
                                                    <input type="text" class="form-control" id="house_no" name="house_No" readonly>
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Thram Number: </b></label>
                                                    <input type="text" class="form-control" id="thram" name="thram_No" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Mobile Number:</b><span class="text-danger">*</span></label>
                                                    <input type="number" class="form-control" id="tel_num" name="phone_Number" maxlength="8" min="1" onclick="removeError('_err')" onKeyPress="if(this.value.length==8) return false;">
                                                    <span id="_err" class="text-danger"></span>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Alternative Person Name And Relation :</b></label>
                                                    <input type="text" class="form-control" id="num" name="AlternativeNumberRelation" placeholder="name,relation">
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Division/Park: </b><span class="text-danger">*</span></label>
                                                    <select name="division_Park_Id" class="chosen-select form-control" id="division_Park" onclick="removeError('division_err')">
                                                        <option value="">-- Select --</option>
                                                    </select>
                                                    <span class="text-danger" id="division_err"></span>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Plot Number : <span class="text-danger">*</span></b></label>
                                                    <input type="text" class="form-control" id="plot_no" name="header_Name" onclick="removeError('plot_no_err')">
                                                    <span class="text-danger" id="plot_no_err"></span>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Details of Forest Produce to be removed :<span class="text-danger">*</span></b></label>
                                                   <%-- <input type="text" class="form-control" id="private_LandForm_Remarks" name="private_LandForm_Remarks" onclick="removeError('private_LandForm_Remarks_err')">--%>
                                                    <select name="private_LandForm_Remarks" id="private_LandForm_Remarks" multiple class="selectpicker form-control">
                                                        <option value="">-- Select -- </option>
                                                        <c:forEach var="vlist" items="${Forest_Produce}" varStatus="counter">
                                                           <%-- <option value="${vlist.header_id}">${vlist.header_Name}</option>--%>
                                                            <option value="${vlist.header_Name}">${vlist.header_Name}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <span class="text-danger" id="private_LandForm_Remarks_err"></span>
                                                </div>
                                            </div>
                                            <span class="text-danger"><b>Note:</b> File type accepted are pdf,png,jpg,docx,doc</span><br/>
                                            <div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Attachments Required</b>
                                                    <ul>
                                                        <li>Copy of Cadastal Map<span class="text-danger">*</span></li>
                                                        <li>Copy of Thram<span class="text-danger">*</span></li>
                                                    </ul>
                                                    </label>
                                                    <input type="file" name="files" id="doc1" class="alert" onchange="validateAttachment(this.value,'doc1','doc_check1'),removeError('endorsement_err')" onclick="removeErrors('endorsement_err')">
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
                                            <div class="card mt-2">
                                                <div class="card-body border-dark">
                                                    <div class="form-group row mb-2 declaration">
                                                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 custom-switch-sm">
                                                            <div class="demo-inline-wrapper">
                                                                <div class="form-group">
                                                                    <div class="toggle-switch">
                                                                        <input type="checkbox" class="toggle-switch__checkbox" id="submit_form" onchange="declarationCheck()">
                                                                        <i class="toggle-switch__helper"></i>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                                                            <b>I hereby certify that the details given above are true to the best of my knowledge. In case of any false or wrong information, I shall be liable to be penalized under the Forest and Nature Conservation Act and the Rule and Regulations made there under. </b>
                                                            <input type="hidden" value="0" name="Department_Id" id="Department_Id">
                                                        </div>
                                                    </div>
                                                    <div class="form-group text-right">
                                                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                                            <button class="btn btn-success fa-pull-right " id="btn_submit_final_form" disabled="true" style="background-color: #236F67" onclick="showConfirmation()" type="button">Submit&nbsp;&nbsp;&nbsp;<i class="fa fa-arrow-alt-circle-right"></i></button>
                                                        </div>
                                                       <%-- <button type="button" class="btn btn-secondary" data-toggle="popover" title="Popover title" data-content="Popover content goes here.">Popover</button>--%>
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
                                                            <button type="button" class="btn btn-success" onclick="submit_final_form()">Yes</button>
                                                            <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
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
        <%--</div>--%>
    </section>
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
    .popover {
        background-color: #1e88e5;
        color: #fff;
        border-color: #1e88e5;
    }
</style>

<script language="javascript" type="text/javascript">
    function removeError(id) {
        $('#' + id).html('');
    }

    $(function () {
        $('[data-toggle="popover"]').popover()
    });

    function declarationCheck() {
        if ($('#submit_form').prop('checked')) {
            $('#btn_submit_final_form').prop('disabled', false);
        } else {
            $('#btn_submit_final_form').prop('disabled', true);
        }
    }

    $(document).ready(function () {
        $("#cid").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        //called when key is pressed in textbox
        $("#tel_num").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
    });

    $("input[type='file']").change(
            function () {
                var fileExtension = ['png', 'pdf', 'jpg', 'docx', 'doc'];
                if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
                    alert("Only '.png','.pdf','.jpg','.docx','.doc' format is allowed.");
                    this.value = ''; // Clean field
                    //$("input[type='file']").unbind("onchange", handler);
                    $("input[type='file']").prop('class', 'alert badge-danger');
                    return false;
                }
                if (this.files[0].size > 8000000) {
                    alert("Please upload file less than 8MB. Thanks!!");
                    $(this).val('');
                    return false;
                }
            });

    function fetchCidDetails(cid) {
        var cons_type = 'PRL';
        if (validatecid(cid)) {
            $.ajax({
                url: '${pageContext.request.contextPath}/getCitizenDetailsOnline?cid=' + cid +'&cons_type=' + cons_type,
                type: 'GET',
                success: function (res) {
                    $('#cid').val(res.cid_Number);
                    $('#name').val(res.name);
                    $('#house_no').val(res.house_No);
                    $('#household').val(res.house_Hold_No);
                    $('#thram').val(res.thram_No);
                    $('#dzongkhag').val(res.dzongkhag_Name);
                    $('#dzongkhag_name').val(res.dzongkhag_Name);
                    $('#dzongkhag_Id').val(res.dzongkhag_Id);
                    $('#gewog').val(res.gewog_Name);
                    $('#gewogId').val(res.gewog_Id);
                    $('#gewog_name').val(res.gewog_Name);
                    $('#village').val(res.village_Name);
                    $('#village_Serial_No').val(res.village_Serial_No);
                    $('#g_cid').val(res.cid_Number);
                    $('#gender').val(res.genderType);
                    $('#hoh').val(res.hoh);
                    getParkDropdown(res.dzongkhag_Id,'parkList');
                }
            });
        }
    }


    //DivisionPark
    function getParkDropdown(dzongkhagId,type){
        $.ajax({
            url: '${pageContext.request.contextPath}/public/getParkDropdown?dzongkhagId=' + dzongkhagId + '&type=' + type,
            type: 'GET',
            success: function (res) {
                var str = "";
                for (var i = 0; i < res.length; i++) {
                    $('#division_Park').append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>").trigger('chosen:updated');
                }
            }
        });
    }

    function validatecid(cid) {
        var retval = true;
        if (cid.substring(0, 1) == "3") {
            $('#cider').html('Cid starting with 3 is not allow');
            retval = false;
        }
        else if (cid.length != 11) {
            $('#cider').html('Bhutanese CID should have 11 digit long');
            retval = false;
        }
        return retval;
    }
    $('#cid').click(function () {
        $('#cider').html('');
    });

    function removeerrors(id) {
        $('#' + id).html('');
    }
    function validateattachment() {
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


    function submit_final_form() {
        if (validatePersonal()) {
            $('#confirmationModel').modal('hide');
            var form = $('#personalForm')[0];
            var data = new FormData(form);
            var url = '${pageContext.request.contextPath}/gewog/loginMain/submitPrivateLand';
            var options = {
                target: '#responseText',
                url: url,
                type: 'POST',
                enctype: 'multipart/form-data',
                data: $('#personalForm').serialize()
            };
            $("#personalForm").ajaxSubmit(options);
            $("#wholeCard").hide();
            $("#messageDiv").show();
        }else{
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
        }
    }

    function validatePersonal() {
        var returnVal = true;
        if ($('#cid').val() == "") {
            $('#cider').html('Please provide your CID number');
            returnVal = false;
        }
        if ($('#tel_num').val() == "") {
            $('#_err').html('Please provide your mobile phone number');
            returnVal = false;
        }
        if ($('#tel_num').val().length != "8") {
            $('#_err').html('Your mobile number should be 8 digit');
            returnVal = false;
        }
        if ($('#tel_num').val().length > "8") {
            $('#_err').html('Your mobile number should be 8 digit');
            returnVal = false;
        }
        if ($('#division_Park').val() == "" || $('#division_Park').val() == "0") {
            $('#division_err').html('Select the division park');
            returnVal = false;
        }
        if ($('#plot_no').val() == "") {
            $('#plot_no_err').html('Enter the Construction Approval Number');
            returnVal = false;
        }
        if ($('#rangeOffice').val() == "") {
            $('#rangeOfficer_err').html('Select Range Office');
            returnVal = false;
        }
        if ($('#doc1').val() == "") {
            $('#endorsement_err').html('Attachment required');
            returnVal = false;
        }
        if ($('#file2').val() == "") {
            $('#tharm_err').html('Attach Thram');
            returnVal = false;
        }
        if ($('#file3').val() == "") {
            $('#gup_letter_err').html('Attach Endorsement Letter by Gup/Thromde authority ');
            returnVal = false;
        }
        if ($('#private_LandForm_Remarks').val() == "") {
            $('#private_LandForm_Remarks_err').html('choose forest produce to be removed');
            returnVal = false;
        }
        return returnVal;
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

    function selectRangeOffice(val, target) {
        $.ajax({
            url: '${pageContext.request.contextPath}/privateLand/selectRangeOffice?Division_Park_Id=' + val,
            type: 'GET',
            success: function (res) {
                $('#' + target).empty();
                $('#' + target).append("<option value='select'>Select</option>");
                for (var i = 0; i < res.length; i++) {
                    $('#' + target).append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>");
                }
            }
        });
    }
</script>
</body>

