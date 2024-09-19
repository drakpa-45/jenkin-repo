<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.WorkFlowDto" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 27/2/2023
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    System.out.println("############################ INSIDE THE cf_home.jsp %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    UserRolePrivilegeDTO userBean = null;
    if (session.getAttribute("UserRolePrivilege") != null) {
        userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String LocationId = "";
        for (int n = 0; n < userBean.getJurisdictions().length; n++) {
            LocationId = userBean.getJurisdictions()[n].getLocationID();
            //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID());
        }
        /*for (int m = 0; m < userBean.getRoles().length; m++) {
            for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) {
                Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                    // System.out.println("role name : " + dto.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                }
            }
        }*/
        userId = userBean.getUserType();
        System.out.println("=== THE USERID IS : " + userId);
    }%>
<body>

<div id="loadResponseContent">
    <section class="content">
        <div class="content__inner">
            <div class="card" id="appNoSection" style="height: 240px">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Forest Produce Clearance</span>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12">
                            <label class="form-label">Enter Application Number:</label>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <input type="text" class="form-control" id="applicationNo" placeholder="enter app no">
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-12">
                            <button type="button" class="btn text-white" onclick="fetchForestProduceDtls()" style="background-color: #d0802b"><a href="#" class="text-white">Submit</a></button>
                        </div>
                    </div>
                </div>
            </div>
    <div id="print" style="display:none">
        <% List<WorkFlowDto> dtls=(List<WorkFlowDto>) request.getAttribute("applicationDetails"); %>
        <div class="card">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/ruralTimber/afterPrintingApp" method="post" class="form-horizontal" id="application">
                    <c:forEach var="dis" items="${applicationDetails}" varStatus="counter">
                    </c:forEach>
                <div class="modal-body form-horizontal table-responsive">
                    <table cellspacing="3" cellpadding="3" width="100%" border="0" id="tab_id">
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/resources/images/DOFLogo1.jpg" align="left" style="width:139px;"/></td>
                            <td style="text-align:center;">
                                <br/>
                                <strong style="font-size:28px;">དཔལ་ལྡན་འབྲུག་གཞུངདཔལ་ལྡན་འབྲུག་གཞུང་། ནུས་ཤུགས་དང་རང་བཞིན་ཐོན་སྐྱེད་ལྷན་ཁག</strong><br/>
                                <strong style="font-size:28px;">ནགས་ཚལ་དང་གླིང་ཀ་ཞབས་ཏོག་ལས་ཁུངས།</strong><br/>
                                <strong style="font-size:28px;">ROYAL GOVERNMENT OF BHUTAN</strong><br/>
                                <strong style="font-size:25px;">Ministry of Energy and Natural Resources</strong><br/>
                                <strong style="font-size:20px;">Department of Forest And Park Services</strong><br/>
                                <strong style="font-size:16px;">Forest Produce Clearance</strong><br/>
                            </td>
                            <td><img src="${pageContext.request.contextPath}/resources/images/DOFLogo2.png" align="right" style="width:139px;"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>Date of Issuance</td>
                            <td><span id="issuanceDate"></span></td>
                        </tr>
                        <tr>
                            <td>Application Number</td>
                            <td><b><span id="applicationNumber"></span></b></td>
                            <%--<td>Date of Expiry</td>
                            <td><span id="permitExpiryDate"></span></td>--%>
                        </tr>
                    </table>
                </div>
                    <div class="card mt-4" id="markingScheduleSection">
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                            <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">APPLICANT</span>&nbsp;&nbsp;&nbsp;
                            <span class="px-1 small text-muted form-label text-muted form-label">DETAILS</span>
                            <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                        </div>
                        <div class="card-body">
                            <input type="hidden" class="form-control" id="appNo">
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
                                    <label class="form-label">Dzongkhag </label>
                                    <input type="text" class="form-control form-control-sm" id="dzongkhag" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Gewog </label>
                                    <input type="text" class="form-control form-control-sm" id="gewog" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Village</label>
                                    <input type="text" class="form-control form-control-sm" id="village" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Household Number:</label>
                                    <input type="number" class="form-control form-control-sm" id="household" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">House Number:</label>
                                    <input type="text" class="form-control form-control-sm" id="houseNo" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Thram Number:</label>
                                    <input type="text" class="form-control form-control-sm" id="thramNo" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row mb-2">
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                            <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -7px;">LAND</span>&nbsp;&nbsp;&nbsp;
                            <span class="px-1 small text-muted form-label text-muted form-label"> DETAILS</span>
                            <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table id="landTbls" class="table table-hover table-bordered">
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
                                        <%--<tr>
                                            <td><input type="text" class="form-control" id="plot_no" readonly/></td>
                                            <td><textarea id="peg_no" cols="20" rows="2" class="form-control" readonly></textarea></td>
                                            <td><textarea id="gps_coordinates" cols="20" rows="2" class="form-control" readonly></textarea></td>
                                            <td><input type="text" class="form-control" id="land_cat" readonly/></td>
                                            <td><input type="number" class="form-control" id="acres" readonly/></td>
                                        </tr>--%>
                                    </tbody>
                                </table>
                                <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                                    <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                                    <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -7px;">FOREST</span>&nbsp;&nbsp;&nbsp;
                                    <span class="px-1 small text-muted form-label text-muted form-label"> PRODUCE</span>
                                    <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                                </div>
                                <table id="land_table_details" class="table table-hover table-bordered">
                                    <thead class="text-white" style="background-color:#478d86">
                                        <tr>
                                            <th>Sl No#</th>
                                            <th>Product Name</th>
                                            <th>Nos. Of Trees</th>
                                            <th>Nos. Of Poles</th>
                                            <th>Nos. Of Bamboos</th>
                                            <th>Total Volume</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <%java.time.LocalDate localDate = java.time.LocalDate.now();%>
                        <span class="text-black"><i>This is electronically generated document. No signature(s) required. Document generated on :<%=localDate%></i></span>
                    </div>
                </form>
                <div class="table-responsive text-nowrap text-center">
                    <input type="hidden"  id='totalVolume'>
                    <a href="#" class="text-white"><input type="button" class="btn btn-outline-primary" value="GENERATE" id="print_id" name="print" onclick="generateAllotmentOrderPDF()"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
</section>
    </div>
<br/><br/>
<script language="javascript" type="text/javascript">

    function fetchForestProduceDtls(){
        var appNo = $('#applicationNo').val();
        $.ajax({
            type : "GET",
            url : '${pageContext.request.contextPath}/public/initiate/fetchForestProduceDtls?appNo='+appNo,
            data: $('form').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    $("#appNoSection").hide();
                    $("#print").show();
                    var dto = res.dto;
                    $('#applicationNumber').html(dto.application_Number);
                    $('#appNo').val(dto.application_Number);
                    $('#cid').val(dto.cid_Number);
                    $('#name').val(dto.name);
                    $('#household').val(dto.house_Hold_No);
                    $('#houseNo').val(dto.house_No);
                    $('#thramNo').val(dto.thram_No);
                    $('#rangeOfficerId').val(dto.document_Name);
                    $('#dzongkhag').val(dto.dzongkhag_Name);
                    $('#gewog').val(dto.gewog_Name);
                    $('#village').val(dto.village_Name);
                    $('#cons_Type').val(dto.cons_Type);
                    $('#issuanceDate').html(dto.app_Approval_Date);
                    $('#permitExpiryDate').html(dto.permitExpiryDate);

                    $('#plot_no').val(dto.plot_No);
                    $('#peg_no').html(dto.peg_No);
                    $('#gps_coordinates').html(dto.gps_Coordinates);
                    $('#land_cat').val(dto.land_Category_Name);
                    $('#acres').val(dto.acres);


                    var m=0;
                    var landDtls=dto.landDetailsDtos;

                    var landTbls = $('#landTbls');
                    landTbls.find('tbody').find('.noRecord').remove();

                    if(landDtls.length>0){
                        var tr = '';
                        var totalVolume =0;
                        for (var i in landDtls) {
                            m++;
                            tr = tr + "<tr><td>"+ m +"</td>" +
                            "<td>" + landDtls[i].plot_No + "</td>" +
                            "<td>" + landDtls[i].peg_No + "</td>" +
                            "<td>" + landDtls[i].gps_Coordinates + "</td>" +
                            "<td>" + landDtls[i].land_Category_Name + "</td>" +
                            "<td>" + landDtls[i].areas + "</td>" +
                            "</tr>";
                        }
                        landTbls.find('tbody').append(tr);
                    }

                    var n=0;
                    var timberdetails=dto.timberdetails;

                    var land_table_details = $('#land_table_details');
                    land_table_details.find('tbody').find('.noRecord').remove();



                    if(timberdetails.length>0){
                        var tr = '';
                        var totalVolume =0;
                        for (var k in timberdetails) {
                            n++;
                            totalVolume=totalVolume+timberdetails[k].volume;
                            tr = tr + "<tr><td>"+ n +"</td>" +
                            "<td>" + timberdetails[k].product_Catagory + "</td>" +
                            "<td>" + timberdetails[k].appl_Quantity + "</td>" +
                            "<td>" + timberdetails[k].allot_Quantity + "</td>" +
                            "<td>" + timberdetails[k].estimated_Value + "</td>" +
                            "<td>" + timberdetails[k].volume + "</td>" +
                            "</tr>";
                        }
                        tr = tr +"<tr>" +
                        "<td colspan='5' class='text-center'>Total Volume</td>" +
                        "<td>"+totalVolume+"</td>"+
                        "</tr>";
                        $('#totalVolume').val(totalVolume);
                        land_table_details.find('tbody').append(tr);
                    }
                }else{
                    warningMsg(res.text);
                    $("#appNoSection").show();
                    $("#print").hide();
                }
            }
        });
    }

    function overAllSum() {
        var tot_amt = document.getElementById("royal_tot").value;

        var scharge = document.getElementById("scharge_").value;

        var sum = parseFloat(tot_amt) + parseFloat(scharge);
        $('#grt_tot').val(sum);
    }

    function PrintDiv() {
        var divToPrint = document.getElementById('print');
        var popupWin = window.open('', '_blank', 'width=800,height=750');
        popupWin.document.open();
        popupWin.document.write('<html><body onload="window.print()">' + divToPrint.innerHTML + '</html>');
        popupWin.document.close();
       /* var url = '${pageContext.request.contextPath}/ruralTimber/afterPrintingApp';
        var options = {
            target: '#loadResponseContent',
            url: url,
            type: 'POST',
            enctype: 'form-data',
            data: $('#application').serialize()
        };
        $("#application").submit();*/
    }

    function generateAllotmentOrderPDF() {
        var applicationNumber = $('#appNo').val();
        var totalVolume = $('#totalVolume').val();
        var fileType = "FPC";
        window.open('${pageContext.request.contextPath}/public/initiate/generatePDF?appNo=' + applicationNumber+'&fileType='+fileType+'&totalVolume='+totalVolume);
    }
</script>
</body>
</html>
