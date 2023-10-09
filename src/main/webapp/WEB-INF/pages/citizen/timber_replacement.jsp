<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<form action="#" id="replacementForm" method="post" enctype="form-data">
    <section class="content">
        <div class="content__inner" id="loadMainPage">
            <div class="card">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <span class="card-title">Timber Replacement</span>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12">
                            <label class="form-label">Enter Application Number:</label>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <input type="text" class="form-control" id="appNo" placeholder="enter app no">
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-12">
                            <button type="button" class="btn" onclick="timberReplacementRequest()" style="background-color: #d0802b"><a href="#" class="text-white">Submit</a></button>
                        </div>
                    </div>
                    <div class="card mt-4" id="replacementSection">
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
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Member of CF </label>
                                    <input type="text" class="form-control form-control-sm" id="mocf" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Product Category </label>
                                    <input type="text" class="form-control form-control-sm" id="product_Catagory" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Construction Type</label>
                                    <input type="text" class="form-control form-control-sm" id="cons_Type" readonly>
                                </div>
                            </div>
                            <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Roofing Type </label>
                                    <input type="text" class="form-control form-control-sm" id="roofing_Type" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">House Storey </label>
                                    <input type="number" class="form-control form-control-sm" id="storey_House" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Construction approval Number </label>
                                    <input type="number" class="form-control form-control-sm" id="cons_Approval_No" readonly>
                                </div>
                            </div>
                           <%-- <div class="form-group row mb-2">
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Requested Quantity</label>
                                    <input type="number" class="form-control form-control-sm" id="appQuantity" readonly>
                                   </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Approved Quantity</label>
                                    <input type="number" class="form-control form-control-sm" id="allot_Quantity" readonly>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                    <label class="form-label">Quantity to be Replaced</label>
                                    <input type="number" class="form-control form-control-sm" id="replaceQuantity" onchange="checkReplaceQuantity()">
                                </div>
                            </div>--%>
                            <div class="form-group row mb-2">
                                <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                                    <div class="border-bottom w-100 ml-5" style="border: 1px solid#888;"></div>
                                    <span class="px-2 small text-muted form-label text-muted form-label" style="margin-right: -11px;">Timber</span>&nbsp;&nbsp;&nbsp;
                                    <span class="px-1 small text-muted form-label text-muted form-label"> Details</span>
                                    <div class="border-bottom w-100 mr-5" style="border: 1px solid#888;"></div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div style="overflow-x: auto;white-space: nowrap;">
                                        <table class="table table-bordered" id="productTbls">
                                            <thead style="background-color:#478d86">
                                            <tr>
                                                <th class="text-white" style="width: 1%">Sl No#</th>
                                                <th class="text-white" style="width: 3%">Product Name</th>
                                                <th class="text-white" style="width: 2%">Unit</th>
                                                <th class="text-white" style="width: 2%">Quantity Requested</th>
                                                <th class="text-white" style="width: 3%">Approved Quantity</th>
                                                <th class="text-white" style="width: 3%">Quantity to be replaced</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row mb-2 mt-4">
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 form-control-xs">Reason For Replacement:</div>
                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                                    <textarea class=" form-control col-md-10 col-sm-12 col-lg-10 col-xs-12 form-control-sm" id="remarks" name="remarks" required></textarea>
                                </div>
                               <%-- <div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <label><b>Attachments:<span class="text-danger">*</span></b>
                                        </label>
                                        <input type="file" name="files" id="doc1" class="alert" onchange="validateAttachment(this.value,'doc1','doc_check1'),removeErrors('endorsement_err')" onclick="removeErrors('endorsement_err')">
                                        <i id="doc_check1" class="fa fa-times"></i>
                                        <span class="text-danger" id="endorsement_err"></span>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 text-right">
                                        <button class="btn bg-warning" type="button" onclick="addmoreattachemnts()">
                                            <i class="zmdi zmdi-plus"> Add More</i></button>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <span id="fileadd"></span>
                                </div>--%>

                            </div>
                            <div class="form-group text-right">
                                <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                    <a href="${pageContext.request.contextPath}/public/initiate/timberReplacement"> <button class="btn btn-md fa-pull-right mr-3 text-white" style="background-color: #F15628" type="button"><a href="#" class="text-white"><i class="zmdi zmdi-close-circle"></i>&nbsp; Cancel</a></button></a>
                                    <button class="btn btn-md fa-pull-right text-white" style="background-color: #236F67" id="btn_submit_online_timber" onclick="replaceTimber()" disabled type="button">
                                        <a href="#" class="text-white">Submit &nbsp;&nbsp;&nbsp;<i class="zmdi zmdi-check-circle"></i></a></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mt-2 text-center" id="messageDiv" style="display: none; margin-top: 20px; height: 280px">
            <div class="card-body border-dark">
                <div class="form-group row mb-2 declaration">
                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm text-white text-center">
                        <div class="alert " role="alert" id="responseText"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form>
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


    var inicount = 0;
    function addmoreattachemnts() {
        inicount++;
        var fnshow = 'validateAttachment(this.value,"file' + inicount + '","file_added' + inicount + '")';
        $('#fileadd').append('<div class="row" id="addedfile' + inicount + '"><div class="col-sm-6"><input type="file" class="alert" onchange=\'' + fnshow + '\' name="files" id="file' + inicount + '"></div><div class="col-sm-6"><button class="btn btn-danger fa fa-pull-right mt-4" type="button" onclick="deleteate(' + inicount + ')"><i class="zmdi zmdi-delete pr-2"></i>Delete</button></div></div>');
    }

    function deleteate(id) {
        $('#addedfile' + id).remove();
    }

    function validateAttachment(vl, id, checkId) {
        if (vl != "") {
            $('#' + id).prop('class', 'alert badge-info');
            //$('#' + checkId).prop('class', 'zmdi zmdi-check pl-2');
        } else {
            $('#' + id).prop('class', 'alert badge-danger');
            // $('#' + checkId).prop('class', 'zmdi zmdi-delete pl-2');
        }
    }

    function viewAttachment(uuid, type, path, name) {
        var url = '${pageContext.request.contextPath}/dealing/loadpagetoemptylayout/donwloadFiles?uuid=' + uuid + '&type=' + type;
        window.open(url, '_blank');
    }

    function timberReplacementRequest(){
        var appNo = $('#appNo').val();
        $.ajax({
            type : "GET",
            url : '${pageContext.request.contextPath}/public/initiate/timberReplacementRequest?appNo='+appNo,
            data: $('form').serialize(),
            cache : false,
            success : function(res) {
                if(res.status=='1'){
                    $("#replacementSection").show();
                    var dto = res.dto;
                    $('#cid').val(dto.cid_Number);
                    $('#name').val(dto.name);
                    $('#household').val(dto.house_Hold_No);
                    $('#dzongkhag').val(dto.dzongkhag_Name);
                    $('#gewog').val(dto.gewog_Name);
                    $('#village').val(dto.village_Name);
                    $('#mocf').val(dto.member_of_Forest_community);
                    $('#product_Catagory').val(dto.product_Catagory);
                    $('#cons_Type').val(dto.cons_Type);
                    $('#roofing_Type').val(dto.roofing_Type);
                    $('#storey_House').val(dto.storey_House);
                    $('#cons_Approval_No').val(dto.cons_Approval_No);
                    $('#appQuantity').val(dto.appl_Quantity);
                    $('#allot_Quantity').val(dto.allot_Quantity);

                    var timberdetails = dto.timberdetails;
                    var timberDtlsTbls = "";
                    var m = 0;
                    for (var i in timberdetails) {
                        m++;
                        timberDtlsTbls = timberDtlsTbls + "<tr><td>" + m + "</td>" +
                        "<td> <input type='hidden' id='fP_Product_Id_"+i+"' name='timberdetails["+i+"].fP_Product_Id' value="+timberdetails[i].fP_Product_Id +">" + timberdetails[i].product_Catagory + "</td>" +
                        "<td>" +  timberdetails[i].unit  + "</td>" +
                        "<td>" + timberdetails[i].appl_Quantity + "</td>" +
                        "<td><input type='hidden' id='allot_Quantity_"+i+"' name='timberdetails["+i+"].allot_Quantity' value="+timberdetails[i].allot_Quantity +">" + timberdetails[i].allot_Quantity + "</td>" +
                        "<td>" + "<input type='number' onchange='checkReplaceQuantity("+i+")' id='quantityToReplace_"+i+"' name='timberdetails["+i+"].quantityToReplace' class='form-control' value='0' >" + "</td>" +
                        "</tr>";
                    }
                    $('#productTbls').find('tbody').html(timberDtlsTbls);
                    $('#btn_submit_online_timber').prop('disabled',false);
                }else{
                    warningMsg(res.text);
                    $('#btn_submit_online_timber').prop('disabled',true);
                   // $("#replacementSection").hide();
                }
            }
        });
    }

    function checkReplaceQuantity(i) {
        var replaceQuantity = $('#quantityToReplace_'+i).val();
        var maxLimit = $('#allot_Quantity_'+i).val();
        if(parseInt(replaceQuantity) > parseInt(maxLimit)) {
            warningMsg("Cannot be more than approved quantity");
            $('#quantityToReplace_'+i).val("");
            $('#quantityToReplace_'+i).focus();
        }
    }

    function replaceTimber(){
        var appNo = $('#appNo').val();
        var replaceQuantity = $('#quantityToReplace_0').val();

        if(replaceQuantity !="0"){
            $.ajax({
                type : "POST",
                url : '${pageContext.request.contextPath}/public/initiate/submitTimberRR?appNo='+appNo,
                data: $('#replacementForm').serialize(),
                cache : false,
                success : function(res) {
                    if(res.status=='1'){
                        successMsg(res.text,'${pageContext.request.contextPath}/public/initiate/timberReplacement');
                    }else{
                        warningMsg(res.text);
                    }
                }
            });
        }else{
            warningMsg("Enter replace quantity.")
        }

    }
</script>
</body>