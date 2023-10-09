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
    List<LandDetailsDto> landDetailsDtos = App_Details.getLandDetailsDtos();
    List<TimberDetailsDto> product = App_Details.getTimberdetails();
%>
<body>
<section class="content">
    <%--<div class="content__inner">--%>
    <div class="">
        <div class="card" id="wholeCard">
            <div class="card-header">
                <b>Application for PRL (Verification & Approval)</b><span style="font-size: 12px"> >> Application Number : <%=App_Details.getApplication_Number()%> >> Application Submission Date: <%=App_Details.getApp_Submission_Date()%></span>
            </div>
            <input type="hidden" id="applicationNumber" value="<%=App_Details.getApplication_Number()%>">

            <form action="#" id="personalForm" method="post" enctype="multipart/form-data">
                <div class="card-body">
               <span class="text-danger">
                NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields.
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
                                    <input type="text" class="form-control" id="tharmNo" value="<%=App_Details.getThram_No()%>" readonly>
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
                                    <label><b>Allocated Range Office:</b> </label>
                                    <input type="text" class="form-control" value="<%=App_Details.getRange_Officer()%>" readonly/>
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
                            <span> <b><i><u><i class="fa fa-file">&nbsp;&nbsp;&nbsp;</i>Attachments.</u></i></b> </span>
                            <br/><hr/>
                            <div class="table-responsive">
                                <table id="attachment" class="table table-bordered table-hover">
                                    <thead style="background-color:#478d86">
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
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <h6><b><i class="fa fa-book">&nbsp;&nbsp;&nbsp;</i>Details of the forest produce</b></h6>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 mt-5 mb-3">
                                <label><b>Registered land adjoining the State Reserve Forest Land (SRFL) :</b> <span class="text-danger">*</span></label>
                                <div class="form-check form-check-inline ml-5"><%=App_Details.getLandAdjoining()%>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 mt-5 mb-3">
                                <label><b>Plot Number :</b> <span class="text-danger">*</span></label>
                                <textarea class="form-control" value=""><%=App_Details.getPlot_No()%></textarea>
                            </div>
                            <div class="table-responsive">
                                <table id="total_table_id" class="table table-hover table-bordered">
                                    <thead class="text-white" style="background-color:#478d86">
                                    <tr>
                                        <td>Sl No#</td>
                                        <td>Plot No#</td>
                                        <td>Peg. No</td>
                                        <td>Location (GPS coordinates)</td>
                                        <td>Land category</td>
                                        <td>Areas (acres)</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% for (int a = 0; a < landDetailsDtos.size(); a++) { %>
                                    <tr>
                                        <td><%=a + 1%></td>
                                        <td><%=landDetailsDtos.get(a).getPlot_No()%></td>
                                        <td><%=landDetailsDtos.get(a).getPeg_No()%></td>
                                        <td><%=landDetailsDtos.get(a).getGps_Coordinates()%></td>
                                        <td><%=landDetailsDtos.get(a).getLand_Category_Name()%></td>
                                        <td><%=landDetailsDtos.get(a).getAreas()%></td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                </table>
                                <table id="land_table_details" class="table table-hover table-bordered">
                                    <thead class="text-white" style="background-color:#478d86">
                                    <tr>
                                        <th>Sl No#</th>
                                        <th>Product Name</th>
                                        <th>Nos. Of Trees</th>
                                        <th>Nos. Of Poles</th>
                                        <th>Nos. Of Bamboos</th>
                                        <th>Volume (m3)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% for (int a = 0; a < product.size(); a++) { %>
                                    <tr>
                                        <td><%=a + 1%></td>
                                        <td><%=product.get(a).getProduct_Catagory()%></td>
                                        <td><%=product.get(a).getAppl_Quantity()%></td>
                                        <td><%=product.get(a).getAllot_Quantity()%></td>
                                        <td><%=product.get(a).getEstimated_Value()%></td>
                                        <td><%=product.get(a).getVolume()%></td>
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
                                        <b>I have verified the details of the applicant and the inspection team's
                                            report, and found to be true and correct.</b>
                                        <input type="hidden" value="0" name="Department_Id" id="Department_Id">
                                    </div>
                                </div>
                                <div class="form-group text-right sub_button">
                                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 form-control-sm">
                                        <button class="btn fa-pull-right text-white" id="btn_submit_final_form" style="background-color: #236F67;" disabled="true"
                                                onclick="showConfirmation()" type="button">APPROVE&nbsp;<i class="fa fa-arrow-alt-circle-right"></i>
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
                                        <button type="button" class="btn btn-success" onclick="approvedPrivateLand('<%=App_Details.getApplication_Number()%>')">Yes</button>
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
    $(document).ready(function () {
        getApplicationDetails();
        $('#table_id').DataTable({
            responsive: true
        });
        $('#mytask').DataTable({
            responsive: true
        });
    });

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

    function rejectReason(val) {
        //alert(val);
        if (val == "Others") {
            $('#rej_other_reason').show();
        } else {
            $('#rej_other_reason').hide();
        }
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
        //var url= '${pageContext.request.contextPath}/FileDownloadServlet?uuid='+uuid+'&type='+type;
        var url = '${pageContext.request.contextPath}/common/loadpagetoemptylayout/donwloadFiles?uuid=' + uuid + '&type=' + type;
        window.open(url, '_blank');
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

    function showConfirmation(){
        $('#confirmationModel').modal('show');
        $('#targetId').val('acknowledgementmessage');
        $('#messages').html('You are about to submit application. Are you sure to proceed ?');
    }

    function approvedPrivateLand(appNo) {
        var remarks = $('#remarks').val();
        $('#confirmationModel').modal('hide');
        $.ajax({
            type: "POST",
            url: '${pageContext.request.contextPath}/adminPrivateLandProduce/approvedPrivateLand?remarks=' + remarks + '&appNo=' + appNo,
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


