<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div id="loadMainPage">
    <section class="content">
        <%--<div class="content__inner">--%>
        <div class="card" id="wholeCard">
            <div class="card-header badge-secondary">
                <b>Rural Timber Permit / </b>Permit Extension
            </div>
            <div class="card-body">
               <span class="text-danger">
                NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields.
               </span>
                <div class="row form-group">
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                            <div class="tab-content" id="nav-tabContent">
                                <div class="card">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <span class="card-title">Permit Extension</span>
                                    </div>
                                    <div class="card-body">
                                        <div class="form-group row" style="height:150px;">
                                            <div class="col-lg-3 col-md-3 col-sm-12">
                                                <label class="form-label">Enter Application Number:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-12">
                                                <input type="text" class="form-control" id="appNo" placeholder="enter app no">
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-12">
                                                <button type="button" class="btn text-white" onclick="timberExtensionRequest()" style="background-color: #d0802b"><a href="#" class="text-white">Submit</a></button>
                                            </div>
                                        </div>
                                        <div class="card mt-2" id="extentionSection" style="display: none;">
                                            <form action="#" id="extensionForm" method="post" enctype="multipart/form-data">
                                            <div class="bg-blue card-status card-status-left"></div>
                                                <input type="hidden" name="application_Number" id="application_Number">
                                            <div class="card-header">
                                                <span class="card-title">Applicant Details</span>
                                            </div>
                                            <div class="card-body">
                                                <div class="form-group row mb-2">
                                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                                        <label class="form-label">CID Number </label>
                                                        <input type="text" class="form-control form-control-sm" id="cid" readonly>
                                                        <%--<input type="hidden" name="application_Number" value="<%=App_Details.getApplication_Number()%>"/>--%>
                                                    </div>
                                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                                        <label class="form-label">Full Name </label>
                                                        <input type="text" class="form-control form-control-sm" id="name" readonly>
                                                    </div>
                                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                                        <label class="form-label">HouseHold Number </label>
                                                        <input type="text" class="form-control form-control-sm" id="household" readonly>
                                                    </div>
                                                </div>
                                                <div class="form-group row mb-2">
                                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                                        <label class="form-label">Dzongkhag </label>
                                                        <input type="text" class="form-control form-control-sm" id="dzongkhag" readonly>
                                                    </div>
                                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                                        <label class="form-label">Gewog </label>
                                                        <input type="text" class="form-control form-control-sm" id="gewog" readonly>
                                                    </div>
                                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                                        <label class="form-label">Village</label>
                                                        <input type="text" class="form-control form-control-sm" id="village" readonly>
                                                    </div>
                                                </div>
                                                <div class="form-group row mb-2 mt-4">
                                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 form-control-xs">Reason For Extension:</div>
                                                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                                                        <textarea class=" form-control col-md-10 col-sm-12 col-lg-10 col-xs-12 form-control-sm" id="remarks" name="remarks" required></textarea>
                                                    </div>
                                                    <div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                            <label><b>Relevant /supporting documents :<span class="text-danger">*</span></b></label>
                                                            <input type="file" name="files" id="doc1" class="alert" onchange="validateAttachment(this.value,'doc1','doc_check1'),removeErrors('endorsement_err')">
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
                                                <div class="form-group text-right">
                                                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                                       <%-- <button class="btn btn-md fa-pull-right mr-3 text-white" style="background-color: #F15628" type="button"><i class="zmdi zmdi-close-circle"></i>&nbsp; Cancel</button>--%>
                                                        <button class="btn btn-md fa-pull-right text-white" style="background-color: #236F67" id="btn_submit_online_timber" onclick="showConfirmation()" type="button">
                                                            Submit &nbsp;&nbsp;&nbsp;<i class="zmdi zmdi-check-circle"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                                </form>
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
                                                        <button type="button" class="btn btn-success" onclick="requestForPermitExtension()">Yes</button>
                                                        <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No</button>
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
    </section>
</div>

<script>
    // $('[data-toggle="tooltip"]').tooltip();
    $('[data-toggle="tooltip"]').tooltip({
        trigger : 'hover'
    });
    $(document).ready(function() {
        $('#group_task').DataTable({
            responsive: true
        });
        $('#mytask').DataTable({
            responsive: true
        });
    });


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

    function timberExtensionRequest(){
        var appNo = $('#appNo').val();
        $.ajax({
            type : "GET",
            url : '${pageContext.request.contextPath}/public/initiate/timberExtensionRequest?appNo='+appNo,
            data: $('form').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    $("#extentionSection").show();
                    var dto = res.dto;
                    $('#cid').val(dto.cid_Number);
                    $('#name').val(dto.name);
                    $('#household').val(dto.house_Hold_No);
                    $('#dzongkhag').val(dto.dzongkhag_Name);
                    $('#gewog').val(dto.gewog_Name);
                    $('#village').val(dto.village_Name);
                    $('#application_Number').val(dto.application_Number);
                }else{
                    warningMsg(res.text);
                    $("#extentionSection").hide();
                }
            }
        });
    }
    function showConfirmation(){
        $('#confirmationModel').modal('show');
        $('#targetId').val('acknowledgementmessage');
        $('#messages').html('You are about to submit application. Are you sure to proceed ?');
    }

    function requestForPermitExtension(){
        var appNo = $('#appNo').val();
        var remarks = $('#remarks').val();

        /*$.ajax({
            type : "POST",
            url : '${pageContext.request.contextPath}/public/initiate/submitTimberPE?appNo='+appNo,
            data: $('#extensionForm').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    successMsg(res.text,'${pageContext.request.contextPath}/public/initiate/permitExtension');
                }else{
                    warningMsg(res.text);
                }
            }
        });*/

        if(validatePE()==true){
           /* var url = '${pageContext.request.contextPath}/public/initiate/submitTimberPE?appNo='+appNo+'&remarks='+remarks;
            var options = {target: '#responseText', url: url, enctype: 'multipart/form-data', type: "POST", data: $('#extensionForm').serialize()
            };
            $("#extensionForm").ajaxSubmit(options);
            $("#loadMainPage").hide();
            $("#messageDiv").show();*/
            $('#confirmationModel').modal('hide');
            var form = $('#extensionForm')[0];
            var data = new FormData(form);
            var url = '${pageContext.request.contextPath}/public/loadpagetoemptylayout/submitTimberPE';
            var options = {target: '#responseText', url: url, type: 'POST', enctype: 'multipart/form-data', data: $('#extensionForm').serialize()};

            $("#extensionForm").ajaxSubmit(options);
            $("#wholeCard").hide();
            $("#messageDiv").show();
        }

        function validatePE() {
            var returnVal = true;
            if ($('#doc1').val() == "") {
                $('#doc_err').html('Add assessment report');
                $('#doc_err').show();
                $('#doc_err').delay(2000).fadeOut('slow');
                returnVal = false;
            }
            return returnVal;
        }
    }
</script>
</body>