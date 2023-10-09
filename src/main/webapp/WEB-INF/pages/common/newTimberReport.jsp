<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%-- Created by IntelliJ IDEA. User: Tshedup Gyeltshen Date: 4/20/2020 Time: 9:57 AM To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    UserRolePrivilegeDTO userBean = null;
    int n = 0;
    if (session.getAttribute("UserRolePrivilege") != null) {
        userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String LocationId = "";
        for (n = 0; n < userBean.getJurisdictions().length; n++) {
            LocationId = userBean.getJurisdictions()[n].getLocationID(); //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID()); } for (int m = 0; m < userBean.getRoles().length; m++) { for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) { Service svc = userBean.getCurrentRole().getServices()[i]; for (int j = 0; j < svc.getPrivileges().length; j++) { Privilege priv = svc.getPrivileges()[j]; System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")"); } } }*/ userId = userBean.getCurrentRole().getRoleName(); System.out.println("=== current user is : " + userId); }
        }
    }
%>
<head><title>Reports</title></head>
<body>
    <div id="resultDiv" class="form-group">
        <div style="overflow-x: auto;white-space: nowrap;">
            <table class="table table-bordered" id="detailsOfRuralTimberReport">
                <thead style="background-color:#478d86">
                <tr>
                    <td style="width:1px">#</td>
                    <td style="width:3px">Division/Park Name</td>
                    <td style="width:2px">CID Number</td>
                    <td style="width:2px">House Hold</td>
                    <td style="width:3px">Dzongkha</td>
                    <td style="width:3px">Gewog</td>
                    <td style="width:3px">Range Office</td>
                    <td style="width:2px">Marking Location</td>
                    <td style="width:3px">Construction</td>
                    <td style="width:3px">Timber Form</td>
                    <td style="width:2px">Product</td>
                    <td style="width:2px">Quantity Approved</td>
                    <td style="width:2px">Unit</td>
                    <td style="width:3px">Approval Date</td>
                    <td style="width:3px">Application Status</td>
                    <td style="width:3px">Claiming Status</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="report" items="${reportTimber}" varStatus="counter">
                    <tr>
                        <td>${counter.index+1}</td>
                        <td>${report.division_Park_Name}</td>
                        <td>${report.cid_Number}</td>
                        <td>${report.house_Hold_No}</td>
                        <td>${report.dzongkhag_Name}</td>
                        <td>${report.gewog_Name}</td>
                        <td>${report.range_Office}</td>
                        <td>${report.marking_Location}</td>
                        <td>${report.cons_Type}</td>
                        <td>${report.product_desc}</td>
                        <td>${report.product_Category}</td>
                        <td>${report.quantity}</td>
                        <td>${report.unit}</td>
                        <td>${report.approval_Date}</td>
                        <td>${report.app_atatus}</td>
                        <td>${report.claim_status}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="m-4">
            <a href="#" class="" onClick="$('#detailsOfRuralTimberReport').tableExport({type:'excel',escape:'false',fileName:'report'});">
                <button style="margin-top:10px;" class="btn btn-green btn-outline-success" type="button">Get Excel</button>
            </a>&nbsp;
            <a href="#" onClick="doExportItem('#detailsOfRuralTimberReport',
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
<script>
    $('[data-toggle="tooltip"]').tooltip();
    $(document).ready(function () {
        $('#detailsOfRuralTimberReport').DataTable({
            responsive: true
        });
    });

    function doExportItem(selector, params) {
        var options = {
            tableName: 'detailsOfRuralTimberReport',
            worksheetName: 'report',
            fileName: 'report'
        };
        $.extend(true, options, params);
        $(selector).tableExport(options);
    }
</script>
</body>