<%@ page import="org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj" %>
<%@ page import="java.util.List" %>
<%@ page import="bt.gov.ditt.dofps.dto.*" %>

<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 27/2/2023
  Time: 3:34 PM
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
                <b>Application for PRL (Verification & Approval)</b><span style="font-size: 12px"> >> Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <%=App_Details.getApp_Submission_Date()%></span>
            </div>
            <input type="hidden" id="applicationNumber" value="<%=App_Details.getApplication_Number()%>">
            <form action="#" id="personalForm" method="post" enctype="form-data">
                <div class="card-body">
                    <span class="text-danger">NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields.
                        <a href="#" data-toggle="tooltip" data-placement="top" title=""
                           data-original-title="Please fill up the application form.">
                            <img src="<c:url value="/resources/images/questionMark.jpg"/>" class="user-image" style="width:20px; margin-top:-10px;"/><br/><br/>
                        </a>
                    </span>
                    <div class="card">
                        <div class="card-body border-dark">
                            <span> <b><i><u><i class="fa fa-address-book"> &nbsp;&nbsp;&nbsp;</i>Personal Details.</u></i></b> </span>
                            <br/><hr/>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>CID Number : </b></label>
                                    <input type="text" class="form-control" id="cid" value="<%=App_Details.getCid_Number()%>" readonly>
                                    <input type="hidden" name="application_Number" value="<%=App_Details.getApplication_Number()%>"/>
                                    <input type="hidden" name="service_Name" value="<%=App_Details.getService_Name()%>"/>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Full Name :</b></label>
                                    <input type="text" class="form-control" id="name" value="<%=App_Details.getName()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Mobile Number : </b></label>
                                    <input type="number" class="form-control" onchange="removeerrors('_err')" id="num" name="phone_Number" value="<%=App_Details.getPhone_Number()%>" readonly>
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
                                    <label><b>House Number :</b></label>
                                    <input type="text" class="form-control" id="house_no" value="<%=App_Details.getHouse_No()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Household Number :</b></label>
                                    <input type="number" class="form-control" id="household" value="<%=App_Details.getHouse_Hold_No()%>" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Tharm Number :</b></label>
                                    <input type="number" class="form-control" id="tharmNo" value="<%=App_Details.getThram_No()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Alternative Person's relation :</b></label>
                                    <input type="text" class="form-control" readonly id="alternativePerson" value="<%=App_Details.getAlternativeNumberRelation()%>">
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Division/Park :</b></label>
                                    <input type="text" class="form-control" readonly id="division_Park_Id" value="<%=App_Details.getDivision_Park_Name()%>">
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label class="form-label">To Range Office</label>
                                    <select name="allot_Range_Officer" style="width:100%;" class="chosen-select form-control" id="range_id">
                                        <option value="">----- Select------</option>
                                        <c:forEach var="rlist" items="${range_list}" varStatus="counter">
                                            <option value="${rlist.range_Id}">${rlist.range_Name}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="text-danger" id="range_err"></span>
                                </div>
                            </div>
                            <div class="form-group row mb-4">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <label><b>Details of Forest Produced:</b></label>
                                    <textarea class=" form-control col-md-10 col-sm-12 col-lg-10 col-xs-12" readonly> <%=App_Details.getPrivate_LandForm_Remarks()%></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body border-dark">
                            <span> <b><i><u><i class="fa fa-file">&nbsp;&nbsp;&nbsp;</i>Attachments.</u></i></b> </span><br/>
                            <hr/>
                            <div class="table-responsive">
                                <table id="attachment" class="table table-bordered table-hover">
                                    <thead class="bg-gray-dark-lighter">
                                    <tr>
                                        <td>Sl No#</td>
                                        <td>Attachment</td>
                                        <td colspan="2">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% for (int a = 0; a < attachment.size(); a++) {%>
                                    <tr>
                                        <td><%=a + 1%></td>
                                        <td><%=attachment.get(a).getDocument_Name()%></td>
                                        <td>
                                            <button class="btn btn-outline-warning" type="button" onclick="viewAttachment('<%=attachment.get(a).getuUID()%>','view')" target="_blank"><i class="fa fa-eye"></i> View</button>
                                        </td>
                                        <td>
                                            <button class="btn btn-outline-primary" type="button" onclick="viewAttachment('<%=attachment.get(a).getuUID()%>','download')">
                                                <i class="fa fa-download"></i> Download
                                            </button>
                                        </td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
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
                                        <b>I have thoroughly verified the details of the applicant and the inspection
                                            team’s report, and found to be true and correct. Timber requirement is
                                            genuine and for bonafide house construction</b>
                                        <input type="hidden" value="0" name="Department_Id" id="Department_Id">
                                    </div>
                                </div>
                                <div class="form-group text-right sub_button">
                                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 form-control-sm">
                                        <button class="btn fa-pull-right text-white" id="btn_submit_final_form" disabled="true" style="background-color: #236F67;"
                                                onclick="showConfirmation()" type="button">VERIFY&nbsp;<i class="fa fa-arrow-alt-circle-right"></i>
                                        </button>
                                        <button class="btn btn-md fa-pull-right mr-2 text-white" style="background-color: #F15628" id="showRejectSection"
                                                onclick="updateReject('<%=App_Details.getApplication_Number()%>')" type="button"><a href="#" class="text-white"><i class="zmdi zmdi-xbox"></i>Reject</a></button>
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
                                        <button type="button" class="btn btn-success" onclick="forwardToRO('<%=App_Details.getApplication_Number()%>')">Yes</button>
                                        <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No</button>
                                    </div>
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
    /*$(document).ready(function(){
     $("#acres").keypress(function (e) {
     //if the letter is not digit then display error and don't type anything
     if (e.which != 8 && e.which != 0 && (e.which < 46 || e.which > 57)) {
     return false;
     }
     });

     });

     $(document).on("keypress", "input[type=number]", function(e){
     //alert(e.which);
     if (e.which != 8 && e.which != 0 && (e.which < 46 || e.which > 57)) {
     return false;
     }
     });*/

    /*$(document).ready(function () {
     $("#acres").keypress(function (e) {
     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57 || e.which == 46)) {
     return false;
     }
     })
     });*/

    function removeError(id) {
        $('#' + id).html('');
    }

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

    function rejectReason(val) {
        //alert(val);
        if (val == "Others") {
            $('#rej_other_reason').show();
        } else {
            $('#rej_other_reason').hide();
        }
    }

    function viewAttachment(uuid, type, path, name) {
        var url = '${pageContext.request.contextPath}/dealing/loadpagetoemptylayout/donwloadFiles?uuid=' + uuid + '&type=' + type;
        window.open(url, '_blank');
    }

    function enterTrees(val) {
        if (val == 179 || val == 180 || val == 1903) {
            $('#trees').prop('readonly', false);
            $('#poles').prop('readonly', false);
            $('#bamboos').prop('readonly', true);
        } else if (val == 184) {
            $('#trees').prop('readonly', true);
            $('#poles').prop('readonly', true);
            $('#bamboos').prop('readonly', false);
        } else {
            $('#trees').prop('readonly', true);
            $('#poles').prop('readonly', true);
            $('#bamboos').prop('readonly', true);
        }
        jQuery.removeData();
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

    function formatDate(date) {
        var d = new Date(date),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;
        alert([year, month, day].join('-'));
        $('#date_').val([year, month, day].join('-'));
    }

    $('[data-toggle="tooltip"]').tooltip();
    $(document).ready(function () {
        getApplicationDetails();
        $('#table_id').DataTable({
            responsive: true
        });
        $('#mytask').DataTable({
            responsive: true
        });
    });

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

    function forwardToRO(appNo) {
        var remarks = $('#remarks').val();
        var range_id = $('#range_id').val();
        $('#confirmationModel').modal('hide');
        $.ajax({
            type: "POST",
            url: '${pageContext.request.contextPath}/adminPrivateLandProduce/privateLandProduceGupForwardToRO?remarks=' + remarks + '&appNo=' + appNo + '&range_id=' + range_id,
            data: $('#personalForm').serialize(),
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

    function validateForm() {
        debugger;
        var valid = true;
        if ($('#file1').val() == "") {
            $('#file1').focus();
            $('#field_inspection_err').html('Attach Field Inspection Report');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            valid = false;
        }
        if ($('#file2').val() == "") {
            $('#file2').focus();
            $('#utilization_err').html('Attach Utilization and disposal plan');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            valid = false;
        }
        if ($('#plot_no').val() == "0") {
            $('#plot_no').focus();
            $('#plot_no_err').html('Enter the plot Nos.');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            valid = false;
        }
        /*if($('#peg_no').val()==""){
         $('#peg_no_err').html('Enter the Peg No.');
         $('#submit_form').prop('checked', false);
         $('#btn_submit_final_form').prop('disabled',true);
         valid = false;
         }*/
        if ($('input[name="landAdjoining"]:checked').length == 0) {
            $('#adjoiningError').html('Required');
            valid = false;
        }

        if ($('input[name="landAdjoining"]:checked').length != 0) {
            var radioValue = $("input[name='landAdjoining']:checked").val();
            if (radioValue == "Y") {
                if ($('#peg_no').val() == "") {
                    $('#peg_no_err').html('Enter the Peg No.');
                    $('#submit_form').prop('checked', false);
                    $('#btn_submit_final_form').prop('disabled', true);
                    valid = false;
                }
            }
        }

        if ($('#gps_coordinates').val() == "") {
            $('#gps_coordinates_err').html('Enter the Latitude and Longitude');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            valid = false;
        }
        if ($('#land_cat').val() == "") {
            $('#land_cat_err').html('Enter the Land category');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            valid = false;
        }
        if ($('#acres').val() == "0") {
            $('#acres_err').html('Enter the Areas');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            valid = false;
        }
        return valid;
    }

    function validateAttachment(vl, id, check_id) {
        if (vl != "") {
            $('#' + id).prop('class', 'alert badge-primary');
            $('#' + check_id).prop('class', 'fa fa-check pl-2');
        }
        else {
            $('#' + id).prop('class', 'alert badge-danger');
            $('#' + check_id).prop('class', 'fa fa-times pl-2');
        }
    }

    function updateReject(val) {
        if (validateReject()) {
            var url = '${pageContext.request.contextPath}/dealing/rejectApplication?value=' + val;
            var options = {
                target: '#loadMainPage',
                url: url,
                enctype: 'form-data',
                data: $('#personalForm').serialize()
            };
            $("#personalForm").ajaxSubmit(options);
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

    var count = 1;
    function addmoreproducts() {
        var tr = "<tr><td>";
        tr = tr + ('<select style="width:100%;" id="forest_prod_' + count + '" name="privateLandDtos[' + count + '].fP_Product_Id" class="chosen-select form-control " onchange="enterAdditionalTrees(this.value, this.id)">' +
        '<option value="">Select product details</option>' +
        '<c:forEach var="plist" items="${Forest_Produce}" varStatus="counter">' +
        '<option value="${plist.header_id}">${plist.header_Name}</option>' +
        '</c:forEach></select></td><td><input type="number" readonly="readonly" class="form-control" id="trees_' + count + '" name="privateLandDtos[' + count + '].no_trees" value="0" min="1">' +
        '</td><td><input type="number" readonly="readonly" class="form-control" id="poles_' + count + '" name="privateLandDtos[' + count + '].no_poles" value="0" min="1"></td><td>' +
        '<input type="number" readonly="readonly" class="form-control" id="bamboos_' + count + '" name="privateLandDtos[' + count + '].no_bamboos" value="0" min="1"></td><td><input type="number" class="form-control" name="privateLandDtos[' + count + '].volume" min="1"></td>"+' +
        '<td><button class="btn btn-danger" rowspan="' + count + '" onclick="deleteProduct(' + count + ')">Delete</button></td></tr>');
        $('#productType').append(tr);
        count++;
    }

    //var row = 1;
    function enterAdditionalTrees(val, id) {
        //alert(val);
        //alert(count);
        var row = count - 1;
        //alert(id);
        if (val == 179 || val == 180 || val == 1903) {
            $('#trees_' + row).attr('readonly', false);
            $('#poles_' + row).attr('readonly', false);
            $('#bamboos_' + row).attr('readonly', true);
        } else if (val == 184) {
            $('#trees_' + row).attr('readonly', true);
            $('#poles_' + row).attr('readonly', true);
            $('#bamboos_' + row).attr('readonly', false);
        } else {
            $('#trees_' + row).attr('readonly', true);
            $('#poles_' + row).attr('readonly', true);
            $('#bamboos_' + row).attr('readonly', true);
        }
        jQuery.removeData();
        //row++;
        //alert(row);
    }

    function deleteProduct(val) {
        //var id = $('#forest_prod_'+val).val();
        //alert(id);
        $('#forest_prod_' + val).parents("tr").remove();
        jQuery.removeData();
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


