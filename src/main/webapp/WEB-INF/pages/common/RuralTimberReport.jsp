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
                        <%if (userId.equalsIgnoreCase("Dealing Officer") || (userBean.getCurrentRole().getRoleName().equalsIgnoreCase("CFO/PM")) || (userBean.getCurrentRole().getRoleName().equalsIgnoreCase("Officiating CFO/PM"))) {%>
                        <form action="#" id="RuralTimberReport" method="get">
                            <input type="hidden" id="divisionBase" value="divisionBase"/>
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
                                            <option>--Select--</option>
                                            <c:forEach var="plist" items="${Division}" varStatus="counter">
                                                <option value="${plist.header_id}">${plist.header_Name}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="alert-danger" role="alert" id="divisionError"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Construction Type:</b></label>
                                        <select class="form-control col-6" name="cons_Type" id="cons_Type_id" onchange="getDropdown(this.value,'timber_form','Product_Desc_id')">
                                            <option value="ALL">ALL</option>
                                            <c:forEach var="clist" items="${cons_type}" varStatus="counter">
                                                <option value="${clist.header_Name}">${clist.cons_Type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Product Category:</b></label>
                                        <select class="form-control col-6" name="Product_Desc" id="Product_Desc_id"<%-- onchange="getDropdown(this.value,'product_category','product_Catagory')"--%>>
                                            <option value="ALL">ALL</option>
                                            <%--<c:forEach var="tlist" items="${timber_Form}" varStatus="counter"> <option  value="${tlist.certify_Tag}">${tlist.header_Name}</option> </c:forEach>--%>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
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
                                        <button  type="button" class="btn btn-sm-2 text-white float-right" style="background-color: indianred" onclick="generateReport()" id="submitForm"> Generate Report</button>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </form>
                        <%}else if (userBean.getCurrentRole().getRoleName().equalsIgnoreCase("GUP")){%>
                        <form action="#" id="RuralTimberReport" method="get">
                            <input type="hidden" id="gupId" value="<%=LocationId%>">
                            <div class="card-body">
                                <div class="form-group row mb-4">
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Start Date :</b></label>
                                        <input class="form-control col-6" type="date" name="start_Date" id="gup_start_Date" onclick="removeError('start_Date_Error')">
                                        <span class="text-danger" id="gup_start_Date_Error"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>End Date:</b></label>
                                        <input class="form-control col-6" type="date" name="end_Date" id="gup_end_Date" onclick="removeError('end_Date_Error')">
                                        <span class="text-danger" role="alert" id="gup_end_Date_Error"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Construction Type:</b></label>
                                        <select class="form-control col-6" name="cons_Type" id="gup_cons_Type" onchange="getDropdown(this.value,'timber_form','Product_Desc_id')">
                                            <option value="ALL">ALL</option>
                                            <c:forEach var="clist" items="${cons_type}" varStatus="counter">
                                                <option value="${clist.header_Name}">${clist.cons_Type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                 <%--   <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Division/Park:</b></label>
                                        <select name="division_Park_Id" class="form-control col-6" id="division_Park" onchange="getDropdown(this.value,'dzongkhag_list','div_Dzongkhag')" onclick="removeError('divisionError')">
                                            <option>--Select--</option>
                                            <c:forEach var="plist" items="${Division}" varStatus="counter">
                                                <option value="${plist.header_id}">${plist.header_Name}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="alert-danger" role="alert" id="divisionError"></span>
                                    </div>--%>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Product Category:</b></label>
                                        <select class="form-control col-6" name="Product_Desc" id="gup_product_Catagory"<%-- onchange="getDropdown(this.value,'product_category','product_Catagory')"--%>>
                                            <option value="ALL">ALL</option>
                                            <%--<c:forEach var="tlist" items="${timber_Form}" varStatus="counter"> <option  value="${tlist.certify_Tag}">${tlist.header_Name}</option> </c:forEach>--%>
                                        </select>
                                    </div>
                                </div>
                              <%--  <div class="form-group row mb-4">
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="gup_dzongkhag_id" style="display: none">
                                        <label><b>Dzongkhag:</b></label>
                                        <select name="dzongkhag_Name" class="form-control col-6" id="gup_div_Dzongkhag" onchange="getDropdown(this.value,'gewog_list','dzo_gewog')" onclick="removeError('div_Dzongkhag_Error')">
                                            <option value="ALL">ALL</option>
                                        </select>
                                        <span class="text-danger" id="gup_div_Dzongkhag_Error"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="gup_gewog_id" style="display: none">
                                        <label><b>Gewog:</b></label>
                                        <select class="form-control col-6" name="gewog_Name" id="gup_dzo_gewog">
                                            <option value="ALL">ALL</option>
                                        </select>
                                    </div>
                                </div>--%>
                                <div class="form-group">
                                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 ">
                                        <button  type="button" class="btn btn-sm-2 text-white float-right" style="background-color: indianred" onclick="generateReportGup()" id="gup_submitForm"> Generate Report</button>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </form>
                        <%}else if (userId.equalsIgnoreCase("Range Officer")){%>
                        <form action="#" id="RuralTimberReport" method="GET">
                            <div class="card-body"><span> <b><i><u><i class="fa fa-book-open fa-md"> &nbsp;&nbsp;&nbsp;</i>Report of Rural Timber Permit. </u></i></b> </span> <br/>
                                <hr/>
                                <br/>
                                <div class="form-group row mb-4">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><label><b>Range Office:</b></label>
                                        <select name="division_Park_Id" class="form-control col-6" id="range" onclick="removeError('rangeError')" <%-- onchange="getCommonDropdown(this.value,'dzongkhag_list','dzongkhag_Name')" --%>>
                                            <option value="ALL">All</option>
                                            <c:forEach var="plist" items="${Range}" varStatus="counter">
                                                <option value="${plist.header_id}">${plist.header_Name}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="text-danger" id="rangeError"></span>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label><b>Construction Type:</b></label>
                                        <select class="form-control col-6" name="cons_Type" id="range_cons_Type" onchange="getRangeDropdown(this.value,'timber_form','range_product_Catagory')">
                                            <option value="ALL">ALL</option>
                                                <c:forEach var="clist" items="${cons_type}" varStatus="counter">
                                                    <option value="${clist.header_Name}">${clist.cons_Type}</option>
                                                </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label><b>Product Category:</b></label>
                                        <select class="form-control col-6" name="product_Catagory" id="range_product_Catagory">
                                            <option value="ALL">ALL</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label><b>Start Date :</b></label>
                                        <input class="form-control col-6" type="date" name="start_Date" id="range_start_Date" onclick="removeError('range_start_Date_Error')">
                                        <span class="text-danger" id="range_start_Date_Error"></span>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label><b>End Date:</b></label>
                                        <input class="form-control col-6" type="date" name="end_Date" id="range_end_Date" onclick="removeError('range_end_Date_Error')">
                                        <span class="text-danger" id="range_end_Date_Error"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 ">
                                        <button class="btn btn-sm-2 text-white float-right" style="background-color: indianred" id="submit_form" onclick="generateRangeReport()" type="button" id="submit_form_Range">Generate Report</button>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </form>
                        <%} else {%>
                        <form action="#" id="RuralTimberReport" method="GET">
                            <div class="card-body">
                                <div class="form-group row mb-4">
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Start Date:</b></label>
                                        <input class="form-control col-6" type="date" name="start_Date" id="start_Date">
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>End Date:</b></label>
                                        <input class="form-control col-6" type="date" name="end_Date" id="end_Date">
                                        <div class="control-label alert alert-danger" role="alert" id="showAlertDiv" style="display:none"></div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Division/Park:</b></label>
                                        <select name="division_Park_Id" class="form-control col-6" id="division_Park_Id" onchange="getCommonDropdown(this.value,'dzongkhag_list','dzongkhag_Name')">
                                            <option value="">--Select--</option>
                                            <option value="ALL">All</option>
                                            <c:forEach var="plist" items="${Park_list}" varStatus="counter">
                                                <option value="${plist.header_id}">${plist.header_Name}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="control-label alert alert-danger" role="alert" id="showAltDivision" style="display:none"></div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Construction Type:</b></label>
                                        <select class="form-control col-6" name="cons_Type" id="cons_Type" onchange="getDropdown(this.value,'timber_form','product_Catagory')">
                                            <option value="">--Select--</option>
                                            <option value="ALL">ALL</option>
                                                <c:forEach var="clist" items="${cons_type}" varStatus="counter">
                                                    <option value="${clist.header_Name}">${clist.cons_Type}</option>
                                                </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                        <label><b>Product Category :</b></label>
                                        <select class="form-control col-6" name="product_Catagory" id="product_Catagory">
                                            <option value="">--Select--</option>
                                            <option value="ALL">ALL</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="dzongkhag" style="display: none">
                                        <label><b>Dzongkhag :</b></label>
                                        <select name="dzongkhag_Name" class="form-control col-6" id="dzongkhag_Name" onchange="getCommonDropdown(this.value,'gewog_list','gewog_Name')">
                                            <option value="">--Select--</option>
                                            <option value="ALL">ALL</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="gewog" style="display: none">
                                        <label><b>Gewog :</b></label>
                                        <select class="form-control col-6" name="gewog_Name" id="gewog_Name">
                                            <option value="">--Select--</option>
                                            <option value="ALL">ALL</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                        <button class="btn btn-sm-2 text-white float-right" style="background-color: indianred" id="btn_submit_final_form" onclick="generateReport()" type="button" id="submit">Generate Report</button>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </form>
                        <%}%>
                        <div id="reportResult"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script language="javascript" type="text/javascript">
    $('[data-toggle="tooltip"]').tooltip();
/*
    $(document).ready(function () {
        $('#detailsOfRuralTimberReport').DataTable({
            responsive: true
        });

    });
*/

    function removeError(id) {
        $('#'+id).html('');
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

    function getRangeDropdown(slNo, type, targetId) {
        $('#' + targetId).empty();
        var cons = $('#cons_Type').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/report/getDropdown?sl_no=' + slNo + '&type=' + type + '&cons_Type=' + cons,
            type: 'GET',
            success: function (res) {
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
            if (validateDetails() == true) {
                var type = "timber";
                var division_ID = $('#division_Park').val();
                var dzongkhag = $('#div_Dzongkhag').val();
                //alert(dzongkhag);
                var geog = $('#dzo_gewog').val();
                //alert(geog);
                var Construction_type = $('#cons_Type_id').val();
                var product_Desc = $('#Product_Desc_id').val();
                var product_Category = $('#Product_Desc_id').val();
                var from_Date = $('#start_Date_id').val();
                var to_Date = $('#end_Date_Id').val();
                var form = $('#RuralTimberReport')[0];
                var data = new FormData(form);
                var url = '${pageContext.request.contextPath}/report/loadpagetoemptylayout/generateReport?division_Park_Id=' + division_ID + '&dzongkhag_Name=' + dzongkhag +
                        '&gewog_Name=' + geog + '&cons_Type=' + Construction_type + '&Product_Desc=' + product_Desc + '&product_Catagory=' + product_Category +
                        '&from_Date=' + from_Date + '&to_Date=' + to_Date + '&type=' + type;
                var options = {
                    target: '#reportResult',
                    url: url,
                    type: 'GET',
                    enctype: 'form-data',
                    data: $('#RuralTimberReport').serialize()
                };
                $("#RuralTimberReport").ajaxSubmit(options);
            }
        } else {
            var type = "timber";
            var division_ID = $('#division_Park_Id').val();
            if (division_ID == "") {
                $('#showAltDivision').html("Please select the Division Park.");
                $('#showAltDivision').show();
            }
            var dzongkhag = $('#dzongkhag_Name').val();
            var geog = $('#gewog_Name').val();
            var Construction_type = $('#cons_Type').val();
            var product_Desc = $('#product_Catagory').val();
            var product_Category = $('#product_Catagory').val();
            var from_Date = $('#start_Date').val();
            var to_Date = $('#end_Date').val();
            if (from_Date == "") {
                $('#showAlertDiv').html("Please select start date.");
                $('#showAlertDiv').show();
                $('#submit').disable();
            }
            if (to_Date == "") {
                $('#showAlertDiv').html("Please select end date.");
                $('#showAlertDiv').show();
                $('#submit').disable();
            }
            var form = $('#RuralTimberReport')[0];
            var data = new FormData(form);
            var url = '${pageContext.request.contextPath}/report/loadpagetoemptylayout/generateReport?division_Park_Id=' + division_ID + '&dzongkhag_Name=' + dzongkhag + '&gewog_Name=' + geog + '&cons_Type=' + Construction_type + '&Product_Desc=' + product_Desc + '&product_Catagory=' + product_Category + '&from_Date=' + from_Date + '&to_Date=' + to_Date + '&type=' + type;
            var options = {
                target: '#reportResult',
                url: url,
                type: 'GET',
                enctype: 'form-data',
                data: $('#RuralTimberReport').serialize()
            };
            $("#RuralTimberReport").ajaxSubmit(options);
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

    function generateRangeReport() {
        if (validateRange() == true) {
            var type = "timber";
            var range_ID = $('#range').val();
            var Construction_type = $('#range_cons_Type').val();
            var product_Category = $('#range_product_Catagory').val();
            var from_Date = $('#range_start_Date').val();
            var to_Date = $('#range_end_Date').val();
            var form = $('#RuralTimberReport')[0];
            var data = new FormData(form);
            var url = '${pageContext.request.contextPath}/report/loadpagetoemptylayout/generateRangeReport?range_Id=' + range_ID + '&cons_Type=' + Construction_type + '&product_Catagory=' + product_Category + '&from_Date=' + from_Date + '&to_Date=' + to_Date + '&type=' + type;
            var options = {
                target: '#reportResult',
                url: url,
                type: 'GET',
                enctype: 'form-data',
                data: $('#RuralTimberReport').serialize()
            };
            $("#RuralTimberReport").ajaxSubmit(options);
        }
    }

    function generateReportGup() {
    //    if (validateRange() == true) {
            var type = "timber";
            var gupId = $('#gupId').val();
            var Construction_type = $('#gup_cons_Type').val();
            var product_Category = $('#gup_product_Catagory').val();
            var from_Date = $('#gup_start_Date').val();
            var to_Date = $('#gup_end_Date').val();
            var form = $('#RuralTimberReport')[0];
            var data = new FormData(form);
            var url = '${pageContext.request.contextPath}/report/loadpagetoemptylayout/generateReportGup?gupId=' + gupId + '&cons_Type=' + Construction_type + '&product_Catagory=' + product_Category + '&from_Date=' + from_Date + '&to_Date=' + to_Date + '&type=' + type;
            var options = {
                target: '#reportResult',
                url: url,
                type: 'GET',
                enctype: 'form-data',
                data: $('#RuralTimberReport').serialize()
            };
            $("#RuralTimberReport").ajaxSubmit(options);
      //  }
    }

    function validateRange() {
        var valid = true;
        if ($('#range_start_Date').val() == "") {
            $('#range_start_Date_Error').html('Required');
            valid = false;
        }
        if ($('#range_end_Date').val() == "") {
            $('#range_end_Date_Error').html('Required');
            valid = false;
        }
        return valid;
    }

</script>
</body>