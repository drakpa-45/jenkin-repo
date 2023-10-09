<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.WorkFlowDto" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Tshedup Gyeltshen
  Date: 5/9/2020
  Time: 11:44 AM
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
            <%--<div class="card" id="appNoSection" style="height: 240px">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Print Permit</span>
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
                            <button type="button" class="btn text-white" onclick="showPermitOrder()" style="background-color: #d0802b"><a href="#" class="text-white">Submit</a></button>
                        </div>
                    </div>
                </div>
            </div>--%>
                <div class="card" id="appNoSection" style="height: 240px">
                    <div class="bg-blue card-status card-status-left"></div>
                    <div class="card-header">
                        <span class="card-title">Print Permit</span>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col-lg-3 col-md-3 col-sm-12 form-label">Enter Application Number:</label>
                            <div class="col-lg-4 col-md-4 col-sm-12">
                                <input type="text" class="form-control" id="applicationNo" placeholder="enter app no">
                            </div>
                            <div class="col-lg-2 col-md-2 col-sm-12">
                                <button type="button" class="btn text-white" style="background-color: #d0802b" onclick="showPermitOrder()">Submit</button>
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
                                <strong style="font-size:28px;">དཔལ་ལྡན་འབྲུག་གཞུང་། ནུས་ཤུགས་དང་རང་བཞིན་ཐོན་སྐྱེད་ལྷན་ཁག</strong><br/>
                                <strong style="font-size:28px;">ནགས་ཚལ་དང་གླིང་ཀ་ཞབས་ཏོག་ལས་ཁུངས།</strong><br/>
                                <strong style="font-size:28px;">ROYAL GOVERNMENT OF BHUTAN</strong><br/>
                                <strong style="font-size:25px;">Ministry of Energy and Natural Resources</strong><br/>
                                <strong style="font-size:20px;">Department of Forest And Park Services</strong><br/>
                                <strong style="font-size:20px;">Division/Park: </strong><br/>
                                <strong style="font-size:16px;">Rural Timber Permit</strong><br/>
                            </td>
                            <td><img src="${pageContext.request.contextPath}/resources/images/DOFLogo2.png" align="right" style="width:139px;"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>Allotment Order Date :</td>
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
                                    <label class="form-label">Permit Number:</label>
                                    <input type="text" class="form-control form-control-sm" id="permit_Number" readonly>
                                    <%--<input type="hidden" name="application_Number" value="<%=App_Details.getApplication_Number()%>"/>--%>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Permit Date :</label>
                                    <input type="text" class="form-control form-control-sm" id="receipt_Date" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Expiry Date :</label>
                                    <input type="text" class="form-control form-control-sm" id="permitExpiryDate" readonly>
                                </div>
                            </div>
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
                                    <label class="form-label">Construction Type</label>
                                    <input type="text" class="form-control form-control-sm" id="cons_Type" readonly>
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
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Household Number:</label>
                                    <input type="number" class="form-control form-control-sm" id="household" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">House Number:</label>
                                    <input type="text" class="form-control form-control-sm" id="houseNo" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Tharm Number:</label>
                                    <input type="text" class="form-control form-control-sm" id="thramNo" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Marking Area:</label>
                                    <input type="text" class="form-control form-control-sm" id="ma" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<div class="form-group row mb-2">
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                            <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">ALLOTMENT</span>&nbsp;&nbsp;&nbsp;
                            <span class="px-1 small text-muted form-label text-muted form-label"> ORDER</span>
                            <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                        </div>
                        <div class="card-body">
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">To Range Office</label>
                                    <input type="text" class="form-control form-control-sm" id="rangeOfficerId" readonly>
                                </div>
                            </div>
                        </div>
                    </div>--%>
                    <div class="form-group row mb-2">
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                            <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">TIMBER</span>&nbsp;&nbsp;&nbsp;
                            <span class="px-1 small text-muted form-label text-muted form-label"> DETAILS</span>
                            <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table table-bordered" id="productTbls">
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white" style="width: 1%">Sl No#</th>
                                        <th class="text-white" style="width: 3%">Product Name</th>
                                       <%-- <th class="text-white" style="width: 2%">Quantity Requested</th>--%>
                                        <th class="text-white" style="width: 2%">Quantity Approved</th>
                                        <th class="text-white" style="width: 2%">Unit</th>
                                        <th class="text-white" style="width: 2%">Royalty Rate(NU)</th>
                                        <th class="text-white" style="width: 2%">Royalty Amount(NU)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                           <%-- <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table">
                                    <tbody>
                                    <tr> <td colspan="9"></td></tr>
                                    <tr style="margin-top: 2%;">
                                        <td></td>
                                        <td></td><td><b>Department Seal:</b></td><td>_______________________________________________
                                        </br>Seal and Signature of Approving Authority</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>--%>
                            <%java.time.LocalDate localDate = java.time.LocalDate.now();%>
                            <span class="text-black"><i>This is electronically generated document. No signature(s) required. Document generated on :<%=localDate%></i></span>
                        </div>
                    </div>
                </form>
                <div class="table-responsive text-nowrap text-center">
                    <input type="button" class="btn btn-outline-primary" value="GENERATE" id="print_id" name="print" onclick="generateAllotmentOrderPDF()"/>
                </div>
            </div>
        </div>
    </div>
</div>
        </section>
    </div>
<br/><br/>
<script language="javascript" type="text/javascript">

    function showPermitOrder(){
        var appNo = $('#applicationNo').val();

        $.ajax({
            type : "GET",
            url : '${pageContext.request.contextPath}/public/initiate/fetchPermitDtls?appNo='+appNo,
            data: $('form').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    $("#appNoSection").hide();
                    $("#print").show();
                    var dto = res.dto;
                    $('#applicationNumber').html(dto.application_Number);
                    $('#appNo').val(dto.application_Number);
                    $('#permit_Number').val(dto.permit_Number);
                    $('#receipt_Date').val(dto.receipt_Date);
                    $('#permitExpiryDate').val(dto.permitExpiryDate);
                    $('#cid').val(dto.cid_Number);
                    $('#name').val(dto.name);
                    $('#household').val(dto.house_Hold_No);
                    $('#houseNo').val(dto.house_No);
                    $('#thramNo').val(dto.thram_No);
                    $('#ma').val(dto.allot_Area);
                    $('#rangeOfficerId').val(dto.document_Name);
                    $('#dzongkhag').val(dto.dzongkhag_Name);
                    $('#gewog').val(dto.gewog_Name);
                    $('#village').val(dto.village_Name);
                    $('#cons_Type').val(dto.cons_Type);
                    $('#issuanceDate').html(dto.app_Approval_Date);
                    $('#permitExpiryDate').html(dto.permitExpiryDate);
                    $('#app_Marking_Schedule_Date').val(dto.app_Marking_Schedule_Date);

                    var productTbls = $('#productTbls');
                    productTbls.find('tbody').find('.noRecord').remove();
                    var tr = '';
                    var m=0;
                    var totAmount=0;
                    var royalAmount=0;
                    var timberDtls=dto.timberdetails;
                    if(timberDtls.length>0){
                        for (var i in timberDtls) {
                            m++;
                            royalAmount=(timberDtls[i].allot_Quantity * timberDtls[i].rate);
                            totAmount=royalAmount+10;
                            tr = tr + "<tr><td>"+ m +"</td>" +
                            "<td>" + timberDtls[i].product_Catagory + "</td>" +
                           /* "<td>" + timberDtls[i].appl_Quantity + "</td>" +*/
                            "<td>" + timberDtls[i].allot_Quantity + "</td>" +
                            "<td>" + timberDtls[i].unit + "</td>" +
                            "<td>" + timberDtls[i].rate + "</td>" +
                            "<td>" + royalAmount+ "</td>" +
                            "</tr>";
                        }
                        tr = tr +"<tr>" +
                        "<td colspan='5' class='text-center'>Service Fee</td>" +
                        "<td >10</td>" +
                        "</tr>";
                        tr = tr +"<tr>" +
                        "<td colspan='5' class='text-center'>Total Amount = (Royalty Amount + Service Fee)</td>" +
                        "<td>"+timberDtls[0].total_Royalty+"</td>"+
                        "</tr>";
                        productTbls.find('tbody').append(tr);
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
        var fileType = "permit";
        window.open('${pageContext.request.contextPath}/public/initiate/generatePDF?appNo=' + applicationNumber+'&fileType='+fileType);
    }
</script>
</body>
</html>
