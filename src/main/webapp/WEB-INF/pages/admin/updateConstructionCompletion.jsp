<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<form action="#" id="constructionForm" method="post">
    <section class="content">
        <div class="content__inner" id="loadMainPage">
            <div class="card">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Update Construction Completion</span>
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
                            <button type="button" class="btn text-white" onclick="check4Completion()" style="background-color: #d0802b">Submit</button>
                        </div>
                    </div>
                    <div class="card mt-4" id="markingScheduleSection" style="display: none;">
                        <div class="bg-blue card-status card-status-left"></div>
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
                                    <label class="form-label">Dzongkhag</label>
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
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-control-sm">
                                    <label class="font-weight-bold">Approved Date :</label>
                                    <input type="text" class="form-control form-control-sm" id="approvedDate" readonly/>
                                    <input type="hidden" class="form-control form-control-sm" id="app_Marking_Schedule_Date" readonly/>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-control-sm">
                                    <label class="font-weight-bold">Completion Date : <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control form-control-sm datepicker" id="completionDate"/>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-control-sm">
                                    <label class="form-label">Completion Status: <span class="text-danger">*</span></label>
                                    <select class="form-control col-6" name="construction_status" id="construction_status">
                                        <option value="ALL">ALL</option>
                                        <c:forEach var="clist" items="${construction_status}" varStatus="counter">
                                            <option value="${clist.header_id}">${clist.header_Name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="allot_Range_Officer">
                            <div class="form-group text-right">
                                <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                    <a href="#"> <button class="btn btn-md fa-pull-right mr-3 text-white" style="background-color: #F15628" type="button"><i class="zmdi zmdi-close-circle"></i>&nbsp; Cancel</button></a>
                                    <button class="btn btn-md fa-pull-right text-white" style="background-color: #236F67" id="btn_submit_online_timber" onclick="updateCompletionDate()" type="button">
                                        <a href="#" class="text-white">Submit&nbsp;&nbsp;&nbsp;<i class="zmdi zmdi-check-circle"></i></a></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();
    today = dd + '-' + mm + '-' + yyyy;
    $( "#completionDate").datepicker({
        minDate: today, // Current day
       // maxDate: 10, // 30 days from the current day
        dateFormat: 'dd-mm-yy'
    });

    function check4Completion(){
        var appNo = $('#applicationNo').val();
        $.ajax({
            type : "GET",
            url : '${pageContext.request.contextPath}/gewog/loginMain/check4Completion?appNo='+appNo,
            data: $('form').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    $("#markingScheduleSection").show();
                    var dto = res.dto;
                    $('#cid').val(dto.cid_Number);
                    $('#name').val(dto.name);
                    $('#household').val(dto.house_Hold_No);
                    $('#dzongkhag').val(dto.dzongkhag_Name);
                    $('#gewog').val(dto.gewog_Name);
                    $('#village').val(dto.village_Name);
                    $('#allot_Range_Officer').val(dto.allot_Range_Officer);
                    $('#approvedDate').val(dto.app_Approval_Date);
                    $('#app_Marking_Schedule_Date').val(dto.app_Marking_Schedule_Date);
                }else{
                    warningMsg(res.text);
                    $("#markingScheduleSection").hide();
                }
            }
        });
    }

    function updateCompletionDate(){
        var appNo = $('#applicationNo').val();
        var completionDate = $('#completionDate').val();
        $.ajax({
            type : "POST",
            url : '${pageContext.request.contextPath}/gewog/loginMain/updateCompletionDate?appNo='+appNo+'&completionDate='+completionDate,
            data: $('#constructionForm').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    successMsg(res.text,'${pageContext.request.contextPath}/gewog/loginMain/updateConstructionCompletion');
                }else{
                    warningMsg(res.text);
                }
            }
        });
    }
</script>
</body>