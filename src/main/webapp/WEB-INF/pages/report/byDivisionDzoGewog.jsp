<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%-- Created by IntelliJ IDEA. User: Tshedup Gyeltshen Date: 4/20/2020 Time: 9:57 AM To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    String LocationId = "";
    UserRolePrivilegeDTO userBean = null;
    int n = 0;
    if (session.getAttribute("UserRolePrivilege") != null) {
        userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        for (n = 0; n < userBean.getJurisdictions().length; n++) {
            LocationId = userBean.getJurisdictions()[n].getLocationID(); //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID()); } for (int m = 0; m < userBean.getRoles().length; m++) { for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) { Service svc = userBean.getCurrentRole().getServices()[i]; for (int j = 0; j < svc.getPrivileges().length; j++) { Privilege priv = svc.getPrivileges()[j]; System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")"); } } }*/ userId = userBean.getCurrentRole().getRoleName(); System.out.println("=== current user is : " + userId); }
        }
    }
%>
<head><title>Reports</title></head>
<body>
<section class="content">
    <div class="content__inner"><br/>
        <div class="card">
            <div class="card-header badge-secondary"><b>Rural Timber Permit / </b>Report</div>
            <div class="card-body"><span class="text-danger"> NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields. <a href="#" data-toggle="tooltip" data-placement="top" title=""
                    data-original-title="Please fill up the application form."> <img src="<c:url value="/resources/images/questionMark.jpg"/>" class="user-image" style="width:20px; margin-top:-10px;"/><br/><br/> </a> </span>
                <div class="row form-group" style="margin-top:-10px;">
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                        <%if ((userBean.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM")) || (userBean.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM"))) {%>
                        <form action="#" id="RuralTimberReport" method="get">
                            <input type="hidden" id="divisionBase" value="divisionBase"/>
                            <input type="hidden" id="cons_Type"/>
                            <div class="card-body">
                                <div class="form-group row mb-4">
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Start Date :</b></label>
                                        <input class="form-control col-6" type="date" name="start_Date" id="start_Date_id" onclick="removeError('start_Date_Error')">
                                        <span class="text-danger" id="start_Date_Error"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>End Date:</b></label>
                                        <input class="form-control col-6" type="date" name="end_Date" id="end_Date_Id" onclick="removeError('end_Date_Error')">
                                        <span class="text-danger" role="alert" id="end_Date_Error"></span>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Division/Park:</b></label>
                                        <select name="division_Park_Id" class="form-control col-6" id="division_Park" onchange="getDropdown(this.value,'dzongkhag_list','div_Dzongkhag')" onclick="removeError('divisionError')">
                                            <option value="ALL">ALL</option>
                                            <c:forEach var="plist" items="${Division}" varStatus="counter">
                                                <option value="${plist.header_id}">${plist.header_Name}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="alert-danger" role="alert" id="divisionError"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="dzongkhag_id" style="display: none">
                                        <label><b>Dzongkhag:</b></label>
                                        <select name="dzongkhag_Name" class="form-control col-6" id="div_Dzongkhag" onchange="getDropdown(this.value,'gewog_list','dzo_gewog')" onclick="removeError('div_Dzongkhag_Error')">
                                            <option value="ALL">ALL</option>
                                        </select>
                                        <span class="text-danger" id="div_Dzongkhag_Error"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="gewog_id" style="display: none">
                                        <label><b>Gewog:</b></label>
                                        <select class="form-control col-6" name="gewog_Name" id="dzo_gewog">
                                            <option value="ALL">ALL</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 ">
                                        <button  type="button" class="btn btn-sm-2 text-white float-right" style="background-color: indianred" onclick="generateReport()" id="submitForm"><a href="#" class="text-white">Generate Report</a></button>
                                    </div>
                                </div>
                                <br/>
                                <div id="resultDiv" class="form-group mt-3" style="display: none">
                                    <div style="overflow-x: auto;white-space: nowrap;">
                                        <table class="table table-bordered" id="reportTbls">
                                            <thead style="background-color:#478d86" class="text-white">
                                            <tr>
                                                <td style="width:1px">#</td>
                                                <td style="width:3px">Application Number</td>
                                                <td style="width:2px">CID Number</td>
                                                <td style="width:2px">House Hold</td>
                                                <td style="width:3px">Dzongkhag</td>
                                                <td style="width:3px">Gewog</td>
                                               <%-- <td style="width:3px">Village</td>--%>
                                                <td style="width:3px">Range Office</td>
                                                <td style="width:3px">Construction Type</td>
                                                <td style="width:3px">Approval Date </td>
                                                <td style="width:4px">Application Status</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="m-4">
                                        <a href="#" class="" onClick="$('#reportTbls').tableExport({type:'excel',escape:'false',fileName:'report'});">
                                            <button style="margin-top:10px;" class="btn btn-green btn-outline-success" type="button">Get Excel</button>
                                        </a>&nbsp;
                                        <a href="#" onClick="doExportItem('#reportTbls',
                                        {type: 'pdf',
                                         jspdf: {orientation: 'l',
                                         margins: {right: 20, left: 20, top: 15, bottom: 15},
                                         autotable: {styles: {rowHeight: 16, halign: 'left'},
                                         tableWidth: 'auto'}
                                         }});">
                                            <button style="margin-top:10px;" type="button" class="btn btn-dribbble btn-outline-danger">Get Pdf</button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script language="javascript" type="text/javascript">
    $('[data-toggle="tooltip"]').tooltip();

    function removeError(id) {
        $('#'+id).html('');
    }

    function doExportItem(selector, params) {
        var options = {
            tableName: 'reportTbls',
            worksheetName: 'report',
            fileName: 'report'
        };
        $.extend(true, options, params);
        $(selector).tableExport(options);
    }

    function getCommonDropdown(slNo, type, targetId) {
        $('#' + targetId).empty();
        var cons = $('#cons_Type').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/report/getCommonDropdown?sl_no=' + slNo + '&type=' + type + '&cons_Type=' + cons,
            type: 'GET',
            success: function (res) {
                if (slNo == "ALL" && type == "dzongkhag_list") {
                    $("#dzongkhag").hide();
                } else if (slNo != null && type == "dzongkhag_list") {
                    $("#dzongkhag").show();
                } else if (slNo == "ALL" && type == "gewog_list") {
                    $("#gewog").hide();
                } else if (slNo != null && type == "gewog_list") {
                    $("#gewog").show();
                }
                $('#' + targetId).append("<option value='ALL'>ALL</option>");
                var str = "";
                for (var i = 0; i < res.length; i++) {
                    $('#' + targetId).append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>");
                }
            }
        });
    }

    function getDropdown(slNo, type, targetId) {
        //alert(slNo);
        $('#' + targetId).empty();
        var cons = $('#cons_Type').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/report/getDropdown?sl_no=' + slNo + '&type=' + type + '&cons_Type=' + cons,
            type: 'GET',
            success: function (res) {
                if (slNo == "ALL" && type == "dzongkhag_list") {
                    $("#dzongkhag_id").hide();
                } else if (slNo != null && type == "dzongkhag_list") {
                    $("#dzongkhag_id").show();
                } else if (slNo == "ALL" && type == "gewog_list") {
                    $("#gewog_id").hide();
                } else if (slNo != null && type == "gewog_list") {
                    $("#gewog_id").show();
                }
                $('#' + targetId).append("<option value='ALL'>ALL</option>");
                var str = "";
                for (var i = 0; i < res.length; i++) {
                    $('#' + targetId).append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>");
                }
            }
        });
    }

    function generateReport() {
        if ($('#divisionBase').val() == "divisionBase") {
            debugger;
            if (validateDetails() == true) {
                var type = "byDivisionDzoGewog";
                var division_ID = $('#division_Park').val();
                var dzongkhag = $('#div_Dzongkhag').val();
                //alert(dzongkhag);
                var geog = $('#dzo_gewog').val();
                //alert(geog);
                var from_Date = $('#start_Date_id').val();
                var to_Date = $('#end_Date_Id').val();
                var form = $('#RuralTimberReport')[0];
                var data = new FormData(form);
                $.ajax({
                    url: '${pageContext.request.contextPath}/report/loadpagetoemptylayout/generateReportCFO',
                    type: 'GET',
                    data: {division_Park_Id:division_ID,dzongkhag_Name:dzongkhag,gewog_Name:geog,from_Date:from_Date,to_Date:to_Date,type:type},
                    success: function (res) {
                        $('#resultDiv').show();
                        var reportTbls = $('#reportTbls');
                        reportTbls.find('tbody').find('.noRecord').remove();
                        var tr="";
                        var n=0;
                        for(var j=0;j<res.dto.length;j++){
                            n++;
                            tr = tr + '<tr><td>'+ n +'</td>'
                            +'<td>'+res.dto[j].application_Number+'</td>'
                            +'<td>'+res.dto[j].cid_Number+'</td>'
                            +'<td>'+res.dto[j].house_Hold_No+'</td>'
                            +'<td>'+res.dto[j].dzongkhag_Name+'</td>'
                            +'<td>'+res.dto[j].gewog_Name+'</td>'
                            +'<td>'+res.dto[j].range_Office+'</td>'
                            /*+'<td>'+res.dto[j].village_Name+'</td>'*/
                            +'<td>'+res.dto[j].cons_Type+'</td>'
                            +'<td>'+res.dto[j].approval_Date+'</td>'
                            +'<td>'+res.dto[j].app_atatus+'</td>'
                            +'</tr>';
                        }
                        reportTbls.find('tbody').html(tr);
                    }
                });
/*
                var url = '${pageContext.request.contextPath}/report/loadpagetoemptylayout/generateReportCFO?division_Park_Id=' + division_ID + '&dzongkhag_Name=' + dzongkhag + '&gewog_Name=' + geog + '&cons_Type=' + Construction_type + '&Product_Desc=' + product_Desc + '&product_Catagory=' + product_Category + '&from_Date=' + from_Date + '&to_Date=' + to_Date + '&type=' + type;
                var options = {
                    target: '#reportResult',
                    url: url,
                    type: 'GET',
                    enctype: 'form-data',
                    data: $('#RuralTimberReport').serialize()
                };
                $("#RuralTimberReport").ajaxSubmit(options);*/
            }
        }
    }

    function validateDetails() {
        var valid = true;
        if ($('#division_Park').val() == "") {
            $('#divisionError').html('Required');
            valid = false;
        }
        if ($('#division_Park').val() != "") {
            if ($('#div_Dzongkhag').val() == "") {
                $('#div_Dzongkhag_Error').html('Required');
                valid = false;
            }
        }
        if ($('#start_Date_id').val() == "") {
            $('#start_Date_Error').html('Required');
            valid = false;
        }
        if ($('#end_Date_Id').val() == "") {
            $('#end_Date_Error').html('Required');
            valid = false;
        }
        return valid;
    }


</script>
</body>