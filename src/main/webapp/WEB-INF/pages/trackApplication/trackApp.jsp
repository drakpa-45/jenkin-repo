<%@ page import="bt.gov.ditt.dofps.dto.AppHistoryDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<%List<AppHistoryDTO> appHistoryDTO = (List<AppHistoryDTO>) request.getAttribute("appHistoryDTO");%>
<section class="content">
    <%--<div class="content__inner">--%>
    <div class="card" id="rejectTab">
        <div class="card-body">
            <div class="card-header">
                <span style="font-size: 15px"><b>Application Number</b> >> ${appNo}</span>
            </div>
            <div class="form-group   mx-auto d-flex align-items-center my-4">
                <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">Application </span>&nbsp;&nbsp;&nbsp;
                <span class="px-1 small text-muted form-label text-muted form-label"> History</span>

                <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
            </div>
            <div class="panel-body" id="viewStatusDetailDiv">
                <div class="panel panel-default">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" id="viewListGrid">
                            <thead style="background-color: #f5d2d2">
                            <tr>
                                <th>Sl.no</th>
                                <th>Application Number</th>
                                <th>Status</th>
                                <th>Action By</th>
                                <th>Action Date</th>
                                <th>Remarks</th>
                            </tr>
                            </thead>
                            <tbody id="tbody">
                            <%for (int i = 0; i < appHistoryDTO.size(); i++) {%>
                            <tr>
                                <td><%=i + 1%>
                                </td>
                                <td><%=appHistoryDTO.get(i).getApplication_Number()%>
                                <td><%=appHistoryDTO.get(i).getStatusName()%>
                                </td>
                                <td><%=appHistoryDTO.get(i).getActorName()%>
                                </td>
                                <td><%=appHistoryDTO.get(i).getActionDate()%>
                                </td>
                                <td><%=appHistoryDTO.get(i).getRemarks()%>
                                </td>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
        </div>
</section>
<%--<script type="text/javascript" src="<c:url value="/resources/js/cdb/trackApplication.js"/>"></script>--%>
</body>