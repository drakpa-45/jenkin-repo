<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<form action="#" id="sawingPermitForm" method="post">
    <section class="content">
        <div class="content__inner" id="loadMainPage">
            <div class="card">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Schedule Sawing Permit</span>
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
                            <button type="button" class="btn text-white" onclick="checkForSawingPermit()" style="background-color: #d0802b"><a href="#" class="text-white">Submit</a></button>
                        </div>
                    </div>
                    <div class="card mt-4" id="sawingPermitScheduleSection">
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
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-control-sm">
                                    <label class="font-weight-bold">Name of sawmill/power chain Operator :<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control form-control-sm" id="nameOfOperator"/>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">License/Registration No.:<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control form-control-sm" id="licenseNo">
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-control-sm">
                                    <label class="font-weight-bold">Location of Sawmill:</label>
                                    <input type="text" class="form-control form-control-sm" id="locationOfSawmill"/>
                                </div>
                            </div>
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-control-sm">
                                    <label class="font-weight-bold">Rate of sawing:<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control form-control-sm" id="rateOfSawing"/>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-control-sm">
                                    <label class="font-weight-bold">Sawing Permit Date : <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control form-control-sm" id="sawingPermitDate"/>
                                </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col-sm-3">
                                    <label class="form-label">Mode of Sawing <span class="text-danger">*</span></label>
                                </div>
                                <div class="col-sm-8 justify-content-center">
                                    <%--<label class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="mode_of_Swing_Id" id="pit" value="1">
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description">Pit</span>
                                    </label>--%>
                                    <label class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input mode_of_Swing_Id" name="mode_of_Swing_Id" id="stationary_sawmill" value="2">
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description">Stationary Sawmill</span>
                                    </label>
                                    <label class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input mode_of_Swing_Id" name="mode_of_Swing_Id" id="power_chain" value="3">
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description">Power Chain</span>
                                    </label>
                                    <label class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input mode_of_Swing_Id" name="mode_of_Swing_Id" id="mobile_sawmill" value="4">
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description">Mobile Saw Mill</span>
                                    </label>
                                </div>
                                <div class="alert alert-danger" id="Import_TypeErrorMsg" style="display:none"></div>
                            </div>
                            <div style="overflow-x: auto;white-space: nowrap;">
                                <table class="table table-bordered" id="markingInfoTbls">
                                    <thead style="background-color:#478d86">
                                    <tr>
                                        <th class="text-white">Sl No#</th>
                                        <th class="text-white">Species Name</th>
                                        <th class="text-white">Volume</th>
                                        <th class="text-white">Location</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>

                            <div class="form-group text-right">
                                <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                    <a href="${pageContext.request.contextPath}/public/initiate/timberReplacement"> <button class="btn btn-md fa-pull-right mr-3 text-white" style="background-color: #F15628" type="button"><i class="zmdi zmdi-close-circle"></i>&nbsp; Cancel</button></a>
                                    <button class="btn btn-md fa-pull-right text-white" style="background-color: #236F67" id="btn_submit_online_timber" onclick="scheduleSawingPermitDate()" disabled type="button">
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
    $( "#sawingPermitDate").datepicker({
        dateFormat: 'dd-mm-yy'
    });

    function checkForSawingPermit(){
        var appNo = $('#applicationNo').val();
        $.ajax({
            type : "GET",
            url : '${pageContext.request.contextPath}/public/initiate/checkForSawingPermit?appNo='+appNo,
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    $("#sawingPermitScheduleSection").show();
                    var dto = res.dto;
                    $('#cid').val(dto.cid_Number);
                    $('#name').val(dto.name);
                    $('#household').val(dto.house_Hold_No);
                    $('#dzongkhag').val(dto.dzongkhag_Name);
                    $('#gewog').val(dto.gewog_Name);
                    $('#village').val(dto.village_Name);
                    $('#approvedDate').val(dto.app_Approval_Date);

                    var m=0;
                    var n=0;
                    var markingInfo=dto.markingInformationEntityList;

                    var markingInfoTbls = $('#markingInfoTbls');
                    markingInfoTbls.find('tbody').find('.noRecord').remove();

                    if(markingInfo.length>0){
                        var tr = '';
                        for (var j in markingInfo) {
                            n++;
                            tr = tr + "<tr><td>"+ n +"</td>" +
                            "<td>" + markingInfo[j].species_Name + "</td>" +
                            "<td>" + markingInfo[j].volume + "</td>" +
                            "<td>" + markingInfo[j].location_of_Timber + "</td>" +
                            "</tr>";
                        }
                        markingInfoTbls.find('tbody').append(tr);
                    }
                    $('#btn_submit_online_timber').prop('disabled',false);
                }else{
                    warningMsg(res.text);
                    $('#btn_submit_online_timber').prop('disabled',true);
                    //$("#sawingPermitScheduleSection").hide();
                }
            }
        });
    }

    function scheduleSawingPermitDate(){
        var appNo = $('#applicationNo').val();
        var sawingPermitDate = $('#sawingPermitDate').val();
        var nameOfOperator = $('#nameOfOperator').val();
        var licenseNo = $('#licenseNo').val();
        var locationOfSawmill = $('#locationOfSawmill').val();
        var rateOfSawing = $('#rateOfSawing').val();
        var mode_of_Swing_Id = $('.mode_of_Swing_Id').val();
        $.ajax({
            type : "POST",
            url : '${pageContext.request.contextPath}/public/initiate/scheduleSawingPermitDate?appNo='+appNo+
            '&sawingPermitDate='+sawingPermitDate+'&nameOfOperator='+nameOfOperator+'&licenseNo='+licenseNo+
            '&locationOfSawmill='+locationOfSawmill+'&rateOfSawing='+rateOfSawing+'&mode_of_Swing_Id='+mode_of_Swing_Id,
            data: $('#sawingPermitForm').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    successMsg(res.text,'${pageContext.request.contextPath}/public/initiate/sowingPermit');
                }else{
                    warningMsg(res.text);
                }
            }
        });
    }
</script>
</body>