<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String roleName = "";
    String userId = "";
    UserRolePrivilegeDTO userBean = null;
    String LocationID = "";
    int n = 0;
    if (session.getAttribute("UserRolePrivilege") != null) {
        userBean = (UserRolePrivilegeDTO) session.getAttribute("UserRolePrivilege");
        String LocationId = "";
        for (n = 0; n < userBean.getJurisdictions().length; n++) {
            LocationId = userBean.getJurisdictions()[n].getLocationID();
            LocationID = userBean.getJurisdictions()[n].getLocationID();
            //System.out.println("Jurisdiction Id: " + dto.getJurisdictions()[n].getJurisdiction() + " Jurisdiction Type:  " + dto.getJurisdictions()[n].getJurisdictionType() + " Location Id: " + dto.getJurisdictions()[n].getLocationID());
        }
        /*for (int m = 0; m < userBean.getRoles().length; m++) {
            for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) {
                Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                    //  System.out.println("role name : " + userBean.getRoles()[m].getRoleCode() + " svc name : " + svc.getServiceName() + " && priv code : " + priv.getPrivilegeCode() + "(" + priv.getPrivilegeId() + ")");
                }
            }
        }*/
        userId = userBean.getCurrentRole().getRoleName();

        System.out.println("=== current user is : " + userId);
    }%>
<body>
<style>
    form, input, select, textarea{
        color: black !important;
    }
    .card{
        background-color: rgb(241,243,244);
    }
    .form-label{
        font-weight: bold;
    }
</style>
<div id="loadMainPage">
    <section class="content">
        <%--<div class="content__inner">--%>
        <div class="card">
            <div class="card-body">
               <span class="text-danger">
                NOTE: Label which are marked with * are mandatory fields, you cannot proceed further without those fields.
                  <a href="#" data-toggle="tooltip" data-placement="top" title=""
                     data-original-title="Please fill up the application form.">
                      <%--<img src="<c:url value="/resources/images/questionMark.jpg"/>" class="user-image" style="width:20px; margin-top:-10px;"/>--%><br/><br/>
                  </a>
               </span>
                <span class=" text-center" style="display: none" id="draftInfoWrapper">
                    <div class="row form-group">
                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 alert-info">
                            <span id="draftInfo"></span>
                        </div>
                        <span id="err_msgs"></span>
                    </div>
                 </span>
                <div class="row form-group">
                    <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                       <%-- <nav>
                            <div class="nav nav-tabs" id="nav-tab" role="tablist" style=" background: #f8f9fa;">
                                <a class="nav-item nav-link active border col-md-6 col-sm-6 col-lg-6 col-xs-12" id="personalDetials" data-toggle="tooltip" href="#personal_tab" data-placement="top" title="" data-original-title='Please use buttons to change tabs.' role="tab" aria-controls="nav-contact" aria-selected="true">
                                    <i class="fa fa-user mr-2"></i>Personal Details <span class="text-info" style="font-size: 20px" id="personal_check"></span>
                                </a>
                                <a class="nav-item nav-link border col-md-6 col-sm-6 col-lg-6 col-xs-12" id="locationDetails" data-toggle="tooltip" href="#location_tab" data-title='Please use buttons to change tabs.' role="tab" aria-controls="nav-contact" aria-selected="false">
                                    <i class="fa fa-globe mr-2"></i> Wood & Pole Details<span class="text-info" style="font-size: 20px" id="location_check"></span>
                                </a>
                            </div>
                        </nav>--%>
                        <form action="#" id="personalForm">
                            <div class="nav nav-tabs" id="nav-tab" role="tablist" style=" background: #B1C0BF;">
                                <a class="nav-item nav-link active border col-md-6 col-sm-6 col-lg-6 col-xs-12 form-control-sm" id="personalDetials" data-toggle="tooltip" href="#personal_tab" data-placement="top" title="" data-original-title='Please use buttons to change tabs.' role="tab" aria-controls="nav-contact" aria-selected="true">
                                    <i class="fa fa-user mr-2"></i>Personal Details <span class="text-info" style="font-size: 15px"id="personal_check"></span>
                                </a>
                                <a class="nav-item nav-link border col-md-6 col-sm-6 col-lg-6 col-xs-12 form-control-sm" id="timberDetaials" data-toggle="tooltip" href="#timber_tab" data-title='Please use buttons to change tabs.' role="tab" aria-controls="nav-contact" aria-selected="false">
                                    <i class="fa fa-globe mr-2"></i> Wood & Pole Details<span class="text-info"style="font-size: 20px"id="timber_check"></span>
                                </a>
                            </div>
                            <div class="tab-content" id="nav-tabContent">
                                <!-- Modal -->
                                <div class="modal fade" id="check_hoh" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header" style="background: khaki">
                                                <h5 class="modal-title" id="exampleModalCenterTitle">Application could not be processed!</h5>
                                                <button type="button" class="close reset-btn" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <div id="hoh_check" style="display: none">
                                                    Sorry!! You are not the head of Gung,So you are not applicable for the Rural Timber Permit.
                                                </div>
                                                <div id="hohCID_check" style="display: none">
                                                    Sorry!! No Head of HouseHold found for the CID Number entered. Please seek further
                                                    clarification from Department of Civil Registration and Census.
                                                </div>
                                                <div id="availedRTP" style="display: none">
                                                    <span class="text-danger" id="availedServicesErr"></span>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary reset-btn" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade show active" id="personal_tab" role="tabpanel" aria-labelledby="nav-home-tab">
                                    <%--<input type="hidden" name="nwfp_Management_Group_Sl_No" value="0"/>--%>
                                    <%--<input type="hidden" name="division_Park_Id_1" value="0"/>--%>
                                    <%--<input type="hidden" name="geog_No" value="0"/>--%>
                                    <%--<input type="hidden" name="permit_Fee" value="0"/>--%>
                                    <%--<input type="hidden" name="service_Fees" value="0"/>--%>
                                    <%--<input type="hidden" name="application_Fees" value="0"/>--%>
                                    <%--<input type="hidden" name="sync_G2C" value="0"/>--%>
                                    <%--<input type="hidden" name="request_Service_Id" value="0"/>--%>
                                    <%--<input type="hidden" name="cons_Loc_Village_Serial_No" value="0"/>--%>
                                    <%--<input type="hidden" name="mode_of_Swing_Id" value="0"/>--%>
                                    <%--<input type="hidden" name="application_Number" id="draftNo" value="0"/>--%>
                                    <%--<input type="hidden" id="yearValidation"/>--%>
                                    <%--<input type="hidden" id="locationID" value="<%=LocationID%>"/>--%>
                                    <div class="card">
                                        <div class="card-body border-dark">
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>CID Number: <span class="text-danger">*</span></b>
                                                        <a href="#" data-toggle="tooltip" data-placement="top" title="" data-original-title="Enter applicant's BHUTANESE CITIZENSHIP IDENTITY CARD NUMBER">
                                                            <%--<img src="<c:url value="/resources/images/questionMark.jpg"/>" class="user-image" width="20px">--%>
                                                        </a>
                                                    </label>
                                                    <input type="number" class="form-control" id="cid" name="" placeholder="Enter the CID" onchange="fetchDetails()" min="1" onclick="removeErrors('cider')"/>
                                                    <%--<input type="number" class="form-control" id="cid" name="" placeholder="Enter your CID " onchange="fetchCidDetails()"/>--%>
                                                    <span id="cider" class="text-danger"></span> </label>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Full Name:</b></label>
                                                    <input type="text" class="form-control" id="name" name="name" readonly>
                                                    <span id="gewog_sl_no_err" class="text-danger"></span>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Gender:</b></label>
                                                    <input type="text" class="form-control" id="gender" name="genderType" readonly>
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Dzongkhag :</b> </label>
                                                    <input type="text" class="form-control" id="dzongkhag" name="dzongkhag_Name" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Gewog :</b></label>
                                                    <input type="text" class="form-control" id="gewog" name="gewog_Name" readonly>
                                                    <input type="hidden"  id="gewogId" name="gewogId"/>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Village:</b></label>
                                                    <input type="text" class="form-control" id="village" name="village_Name" readonly>
                                                    <input type="hidden" class="form-control" id="village_Serial_No" name="village_Serial_No"/>
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Household Number :</b> </label>
                                                    <input type="number" class="form-control" id="household" name="house_Hold_No" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Gung CID number :</b></label>
                                                    <input type="number" class="form-control" id="g_cid" name="cid_Number" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>House Number:</b></label>
                                                    <input type="text" class="form-control" id="house_no" name="house_No" readonly>
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Thram Number:</b></label>
                                                    <input type="number" class="form-control" id="thram" name="thram_No" readonly>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Member of community forest:</b></label>
                                                    <input type="text" class="form-control" id="member_of_Forest_community" name="member_of_Forest_community" onclick="removeErrors('member_of_Forest_community_err')">
                                                    <span id="member_of_Forest_community_err" class="text-danger"></span>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Mobile Number:</b>
                                                        <a href="#" data-toggle="tooltip" data-placement="top" title="" data-original-title="Enter the applicant mobile number or alternative person's number." onclick="removeErrors('phone_Number_err')"></a>
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <input type="number" class="form-control" onclick="removeErrors('phone_Number_err')" id="phone_Number" name="phone_Number" min="1">
                                                    <span id="phone_Number_err" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <div class="form-group row mb-4">
                                               <%-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Alternative Person Name And Relation :</b>
                                                        <a href="#" data-toggle="tooltip" data-placement="top" title="" data-original-title="If this is alternative person's number, enter the relation and address details."> </a>
                                                    </label>
                                                    <input type="text" class="form-control" id="tel_num" name="AlternativeNumberRelation" placeholder="name, relation">
                                                </div>--%>
                                                <input type="hidden" name="register_Geog" value="Yes"/>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Division/Park : </b></label>
                                                    <select name="division_Park_Id" class="chosen-select form-control" id="division_Park_Id" onclick="removeErrors('division_Park_Id_err')">
                                                        <option value="">--select--</option>
                                                    </select>
                                                    <div class="alert alert-danger" id="division_Park_Id_err" style="display:none;"></div>
                                                </div>
                                            </div>
                                            <div class="form-group pull-right">
                                                <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                                    <button class="btn btn-md text-white" style="background-color: #236F67" value="personal" id="personalDetails" onclick="next_tab(this.value)" type="button">
                                                        <a href="#" class="text-white"><b>Next&nbsp;&nbsp;<i class="zmdi zmdi-fast-forward"></i></b></a></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class="form-control" name="cons_Type" id="cons_type" value="${cons_type}">
                            <div class="tab-content" id="nav-tabContent2">
                                <div class="tab-pane fade" id="timber_tab" role="tabpanel" aria-labelledby="nav-home-tab">
                                    <div class="card">
                                        <div class="card-body border-dark">
                                            <span clas=""><b>NOTE:</b></span>
                                            <div class="mb-3">Availing flag poles for general purpose and not for dead purpose. For dead purpose,process in hardcopy.<br/>If you are applying for flag poles more than 29 numbers than select
                                                the poles under Flag poles (0-29 numbers) and Flag poles (30-108 numbers).
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-body border-dark">
                                            <div class="form-group row mb-4">
                                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                                                    <button class="btn btn-info fa-pull-right" type="button" value="RTP(S)" id="add_more" onclick="add_row_Type(this.value,'product_list','prod_id_', 'WP')">
                                                        <i class="fa fa-plus">Add More Products</i></button>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                                    <select name="timberDetails[0].fP_Product_Id" class="chosen-select form-control" id="fP_Product_Id_0" onchange="getCommonDropdown(this.value,'select_Unit','unit_id')" onclick="removeErrors('fP_Product_Id_err')" required>
                                                        <option value="">--Select product details--</option>
                                                        <c:forEach var="plist" items="${Prod_List}" varStatus="counter">
                                                            <option value="${plist.header_id}">${plist.header_Name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                                                   <%-- <select name="timberDetails[0].fP_Product_Id" id="unit_id" value="" onclick="removeErrors('fP_Product_Id_err')" style="width:100%;" onchange="chooseUnit(this.value, '0')" class="chosen-select form-control">
                                                        <option>--Select unit--</option>
                                                    </select> --%>
                                                    <select name="timberDetails[0].unit" id="unit_id" value="" onclick="removeErrors('fP_Product_Id_err')"  style="width:100%;" class="chosen-select form-control">
                                                        <option>--Select unit--</option>
                                                    </select>
                                                       <span class="text-danger" id="fP_Product_Id_err"></span>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                                    <input type="number" class="form-control" placeholder="enter quantity" name="timberDetails[0].appl_Quantity" value="" id="cft_0" onchange="validateQty(this.value,count)" onclick="removeErrors('qty_err_0')" min="1">
                                                    <span class="text-danger" id="qty_err_0"></span><br/>
                                                    <%--<span class="text-danger" id="truckLoads_0" style="display: none">2 Truckload/16 m3 for place with no electricity <br> 1 Truckload/8 m3 for place with electricity.</span>
                                                    <span class="text-danger" id="truck_0" style="display: none">Maximum limit: [1 truckload/8m3]</span>
                                                    <span class="text-danger" id="nos_0" style="display: none">Maximum limit: [50 Nos.]</span>
                                                    <span class="text-danger" id="flag_nos_0" style="display: none">Maximum limit: [0-29 Nos.]</span>
                                                    <span class="text-danger" id="flag_0" style="display: none">Maximum limit: [30-108 Nos.]</span>--%>
                                                </div>
                                                <span id="addProd" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 mt-3"></span>
                                            </div>
                                            <div class="card mt-2">
                                                <div class="card-body border-dark">
                                                    <div class="form-group row mb-2 declaration">
                                                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 custom-switch-sm">
                                                            <div class="demo-inline-wrapper">
                                                                <div class="form-group">
                                                                    <div class="toggle-switch">
                                                                        <input type="checkbox" class="toggle-switch__checkbox" id="submit_form" onchange="declarationCheck()">
                                                                        <i class="toggle-switch__helper"></i>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm">
                                                            <b>I hereby certify that the details given above are true to the best of my knowledge. In case of any false or wrong information, I shall be liable to be penalized under the Forest and Nature Conservation Act and the Rule and Regulations made there under. </b>
                                                            <input type="hidden" value="0" name="Department_Id" id="Department_Id">
                                                        </div>
                                                    </div>
                                                    <div class="form-group text-right">
                                                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                                            <button class="btn btn-md fa-pull-right text-white" style="background-color: #236F67" id="btn_submit_online_timber" onclick="showConfirmation()" disabled="true" type="button">
                                                                <a href="#" class="text-white"> Submit &nbsp;&nbsp;&nbsp;<i class="zmdi zmdi-mail-send"></i></a></button>
                                                            <button class="btn btn-md fa-pull-right mr-3 text-white" style="background-color: #F15628" onclick="pre_tab('timber')" type="button"><a href="#" class="text-white"><i class="zmdi zmdi-mail-reply-all"></i>&nbsp; Previous</a></button>
                                                        </div>
                                                    </div>
                                                    <div id="messageDiv" style="display:none"></div>
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
                                                            <button type="button" class="btn btn-success" onclick="submit_final_form()">Yes</button>
                                                            <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%--</div>--%>
    </section>
</div>
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

<script language="javascript" type="text/javascript">
    function removeErrors(id) {
        $('#' + id).html('');
    }
    $(document).ready(function () {
        //called when key is pressed in textbox
        $("#num").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        <% for (int a = 0; a < 10; a++) { %>
        $("#cft_<%=a%>").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                return false;
            }
        });
        <%}%>
    });

    $("input[type='file']").change(
            function () {
                var fileExtension = ['png', 'pdf', 'jpg', 'docx', 'doc'];
                if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
                    alert("Only '.png','.pdf','.jpg','.docx','.doc' format is allowed.");
                    this.value = ''; // Clean field
                    $("input[type='file']").prop('class', 'alert badge-danger');
                    return false;
                }
                if (this.files[0].size > 8000000) {
                    alert("Please upload file less than 8MB. Thanks!!");
                    $(this).val('');
                    return false;
                }
            });

    function fetchDetails() {
        var cid = $('#cid').val();
        var location = $('#locationID').val();
        var cons_type = $('#cons_type').val();
        if (validatecid(cid)) {
            //  alert(cid);
            $.ajax({
                url: '${pageContext.request.contextPath}/getCitizenDetailsOnline?cid=' + cid +'&cons_type=' + cons_type,
                type: 'GET',
                success: function (res) {
                    if(res.status_Id==1) {
                        $('#cid').val(res.cid_Number);
                        $('#name').val(res.name);
                        $('#house_no').val(res.house_No);
                        $('#household').val(res.house_Hold_No);
                        $('#dzongkhag').val(res.dzongkhag_Name);
                        $('#dzongkhag_name').val(res.dzongkhag_Name);
                        $('#dzongkhag_Id').val(res.dzongkhag_Id);
                        $('#gewog').val(res.gewog_Name);
                        $('#gewog_name').val(res.gewog_Name);
                        $('#village').val(res.village_Name);
                        $('#village_Serial_No').val(res.village_Serial_No);
                        $('#g_cid').val(res.cid_Number);
                        $('#gender').val(res.genderType);
                        $('#thram').val(res.thram_No);
                        $('#gewogId').val(res.gewog_Id);

                        $('#hoh').val(res.hoh);
                        var hoh = res.hoh;
                        if (hoh == "NO") {
                            $("#check_hoh").modal('show');
                            $("#hoh_check").show();
                            $('#cid').val('');
                        }
                        $('#hoh').val(res.hoh_Cid);
                        var hoh_Cid = res.hoh_Cid;
                        if (hoh_Cid == "FALSE") {
                            $("#check_hoh").modal('show');
                            $("#hohCID_check").show();
                            $('#cid').val('');
                        }
                        if (res.status_Id == '0') {
                            $("#check_hoh").modal('show');
                            $("#availedRTP").show();
                            $("#availedServicesErr").html(res.actor_Name);
                            $('#cid').val('');
                        }
                        getParkDropdown(res.dzongkhag_Id,'parkList');
                    }else{
                        $('#cid').val("").focus();
                        warningMsg("Invalid CID/Citizen API issue! please check and try again. If problem persists contact system administrator");
                    }
                }
            });
        }
    }

    //DivisionPark
    function getParkDropdown(dzongkhagId,type){
        $.ajax({
            url: '${pageContext.request.contextPath}/public/getParkDropdown?dzongkhagId=' + dzongkhagId + '&type=' + type,
            type: 'GET',
            success: function (res) {
                var str = "";
                for (var i = 0; i < res.length; i++) {
                    $('#division_Park_Id').append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>").trigger('chosen:updated');
                }
            }
        });
    }

    function valid_limit() {
        var truck = $("#cft_0").val();
        if (truck > 1) {
            $("#loads_err").html('Truck load is only one!');
            $("#cft_0").focus();
        }
    }

    function next_tab(val) {
        if (val == "personal") {
            if (validatePersonal() == true) {
                $('#personalDetials').removeClass("active");
                $("#personalDetials").css("color", "white");
                $("#personalDetials").css("background-color", "rgb(100, 150, 145)");
                $('#personal_tab').removeClass("active");
                $('#personal_tab').hide();
                $('#timber_tab').show();
                $('#personal_check').html('&nbsp;<i class="fa fa-check text-white"></i>');
                $('#timber_tab').prop('class', 'tab-pane active');
                $("#timberDetaials").css("background-color", "rgb(47, 102, 96)");
                $("#timberDetaials").css("color", "white");
            }
        }
    }

    function pre_tab(val) {
        if (val == "timber") {
            $('#personal_tab').prop('class', 'tab-pane active');
            $("#personalDetials").css("background-color", "rgb(47, 102, 96)");
            $('#timberDetaials').removeClass("active");
            $('#timber_tab').removeClass("active");
            $("#timberDetaials").css("background-color", "rgb(100, 150, 145)");
            $('#personal_tab').show();
            $('#timber_tab').hide();
            $('#personal_check').html('');
        }
    }

    $("#personalDetials").css("color", "white");
    $("#timberDetaials").css("color", "white");
    $("#personalDetials").css("background-color", "rgb(47, 102, 96)");
    $("#timberDetaials").css("background-color", "rgb(100, 150, 145)");

    function getCommonDropdown(slNo, type, targetId) {
        if (yearValidation(slNo, type, targetId)) {
            //alert(slNo);
            $('#fP_Product_Id_err').hide();
            $('#' + targetId).empty();
            $.ajax({
                url: '${pageContext.request.contextPath}/getUnitDetails?sl_no=' + slNo + '&type=' + type,
                type: 'POST',
                success: function (res) {
                    $('#' + targetId).append("<option>--Select unit ---</option>");
                    var str = "";
                    for (var i = 0; i < res.length; i++) {
                        $('#' + targetId).append("<option value=" + res[i].header_Name + ">" + res[i].header_Name + "</option>").trigger('chosen:updated');
                    }
                }
            });
        }
    }

    function yearValidation(slNo, type, targetId) {
        var previousSelectProd = $('#fP_Product_Id_0').val();
        if (slNo == previousSelectProd) {

        }
        //alert(slNo);
        var returnVal;
        var household_no = $('#household').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/getYearValidation?sl_no=' + slNo + '&household_no=' + household_no,
            type: 'GET',
            success: function (res) {
                $('#counter').val(res);
                if (res == 1) {
                    returnVal = false;
                    $('#fP_Product_Id_err').html('This product is already claimed and please select other product');
                    $('#claimed_err').html('This product is already claimed and please select other product');
                } else {
                    returnVal = true;
                    $('#fP_Product_Id_err').hide();
                    $('#fP_Product_Id_err').hide();
                    $('#' + targetId).empty();
                    $.ajax({
                        url: '${pageContext.request.contextPath}/getUnitDetails?sl_no=' + slNo + '&type=' + type,
                        type: 'GET',
                        success: function (res) {
                            $('#' + targetId).append("<option>--Select unit ---</option>");
                            var str = "";
                            for (var i = 0; i < res.length; i++) {
                                $('#' + targetId).append("<option value=" + res[i].header_Name + ">" + res[i].header_Name + "</option>").trigger('chosen:updated');
                            }
                        }
                    });
                }
                return returnVal;
                jQuery.removeData();
            }
        });
    }

    function chooseUnit(val, i) {
        //alert(val);
        if (val == 6 || val == 7 || val == 187) {
            $('#truckLoads_' + i).show();
            $('#flag_' + i).hide();
            $('#truck_' + i).hide();
            $('#nos_' + i).hide();
            $('#cft_' + i).val('0');
        }
        if (val == 201) {
            $('#flag_nos_' + i).show();
            $('#flag_' + i).hide();
            $('#truck_' + i).hide();
            $('#truckLoads_' + i).hide();
            $('#nos_' + i).hide();
            $('#cft_' + i).val('0');
        }
        if (val == 9 || val == 10) {
            $('#truck_' + i).show();
            $('#truckLoads_' + i).hide();
            $('#flag_' + i).hide();
            $('#nos_' + i).hide();
            $('#cft_' + i).val('0');
        }
        if (val == 202) {
            $('#flag_' + i).show();
            $('#truckLoads_' + i).hide();
            $('#truck_' + i).hide();
            $('#flag_nos_' + i).hide();
            $('#nos_' + i).hide();
            $('#cft_' + i).val('0');
        }
        if (val == 188 || val == 189) {
            $('#nos_' + i).show();
            $('#flag_' + i).hide();
            $('#truckLoads_' + i).hide();
            $('#truck_' + i).hide();
            $('#flag_nos_' + i).hide();
            $('#cft_' + i).val('0');
        }
    }

    var count = 1;
    var j = 0;
    function add_row_Type(val, type, targetId, cons_type) {

        $('#truckLoads_' + j).hide();
        $('#flag_' + j).hide();
        $('#truck_' + j).hide();
        $('#nos_' + j).hide();

        $.ajax({
            url: '${pageContext.request.contextPath}/productListWoodAndPole?cons_type=WP',
            type: 'GET',
            success: function (res) {
                if (res[0].header_id != null) {
                    var value = "select_Unit";
                    var target = "unit_" + count;
                    $('#' + targetId);
                    $('#addProd').append('<div class="row" id="product' + count + '">' +
                    '<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12"></div>' +
                    '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">' +
                    '<select name="timberDetails[' + count + '].fP_Product_Id" style="width:100%;" class="chosen-select form-control" id="fP_Product_Id_' + count + '" onchange="getCommonDropdown(this.value,' + "'" + value + "'" + ',' + "'" + target + "'" + ')">' +
                    '<option value="">--Select product details--</option>' +
                    '<c:forEach var="plist" items="${Prod_List}" varStatus="counter">' +
                    '<option value="${plist.header_id}">${plist.header_Name}</option>' +
                    '</c:forEach></select>' +
                    '<span class="text-danger" id="type_err_' + count + '"></span></div>' +
                    '<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">' +
                   // '<select name="timberDetails[' + count + '].fP_Product_Id"  id="unit_' + count + '" style="width:100%;" class="chosen-select form-control" onchange="chooseUnit(this.value,' + "'" + count + "'" + ')">' +
                    '<select name="timberDetails[' + count + '].unit"  id="unit_' + count + '" style="width:100%;" class="chosen-select form-control">' +
                    '<option value="--select--">--select--</option> </select><span class="text-danger" id="claimed_err"></span>' +
                    '<span class="text-danger" id="unit_err_' + count + '"></span></div>' +
                    '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">' +
                    '<input type="number" class="form-control" placeholder="enter quantity" id="cft_' + count + '" name="timberDetails[' + count + '].appl_Quantity" onchange="validateAddQty(this.value, ' + "'" + count + "'" + ')" min="1">' +
                    '<span class="text-danger" id="qty_err_' + count + '"></span>' +
                    '<span class="text-danger" id="truckLoads_' + count + '" style="display: none">2 Truckload/16 m3 for place with no electricity <br> 1 Truckload/8 m3 for place with electricity.</span>' +
                    '<span class="text-danger" id="nos_' + count + '" style="display: none">Maximum limit: [50 Nos.]</span>' +
                    '<span class="text-danger" id="flag_nos_' + count + '" style="display: none">Maximum limit: [0-29 Nos.]</span>' +
                    '<span class="text-danger" id="truck_' + count + '" style="display: none">Maximum limit: [1 truckload/8m3]</span>' +
                    '<span class="text-danger" id="flag_' + count + '" style="display: none">Maximum limit: [0-79 Nos.]</span>' + '</div>' +
                    '<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12"><button class="btn btn-danger fa" type="button" onclick="deleteRow(' + count + ')"><i class="fa fa-times pr-4"></i>Delete</button></div>');
                    count++;
                    j++;
                }
            }
        });
    }

    function deleteRow(id) {
        $('#product' + id).remove();
        count--;
    }

    $(document).on("keypress", "input[type=number]", function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
    });

    function validatecid(cid) {
        var retval = true;
        if (cid.substring(0, 1) == "3") {
            $('#cider').html('Cid starting with 3 is not allow');
            retval = false;
        }
        else if (cid.length != 11) {
            $('#cider').html('Bhutanese CID should have 11 digit long');
            retval = false;
        }
        return retval;
    }
    $('#cid').click(function () {
        $('#cider').html('');
    });

    function validatePersonal() {
        var returnVal = true;
        if ($('#cid').val() == "") {
            $('#cider').html('Please enter the CID number');
            $('#cid').focus();
            returnVal = false;
        }else if ($('#member_of_Forest_community').val() == "") {
            $('#member_of_Forest_community_err').html('Mention your Community Membership');
            $('#member_of_Forest_community').focus();
            returnVal = false;
        } else if ($('#phone_Number').val() == "") {
            $('#phone_Number_err').html('Please provide your mobile phone number');
            $('#phone_Number').focus();
            returnVal = false;
        } else if ($('#phone_Number').val().length != "8") {
            $('#phone_Number_err').html('Your mobile number should be 8 digit');
            $('#phone_Number').focus();
            returnVal = false;
        } else if ($('#phone_Number').val().length > "8") {
            $('#phone_Number_err').html('Your mobile number should be 8 digit');
            $('#phone_Number').focus();
            returnVal = false;
        } else if ($('#division_Park_Id').val() == "") {
            $('#division_Park_Id_err').html('Please select the division park');
            $('#division_Park_Id').focus();
            returnVal = false;
        } else {
            $('#_err').html('');
        }
        return returnVal;
    }

    function validateWoodAndPole() {
        var valid = true;
        for (var i = 0; i < count; i++) {
            var quantity = $('#cft_' + i).val();
            var fp_prod = $('#fP_Product_Id_' + i).val();
            if ($('#file1').val() == "") {
                $('#file1').focus();
                $('#noAttachment').html('Attach Endorsement letter from Gup');
                valid = false;
            }
            if ($('input[name="member_of_Forest_community"]:checked').val() == "y") {
                if ($('#copy').val() == "") {
                    $('#copy').focus();
                    $('#noFile').html('Attach  letter from chairperson of Community Forest ');
                    valid = false;
                }
            }
            if ($('#fP_Product_Id_' + i).val() == "0") {
                $('#fP_Product_Id_err').html('Select the products');
                $('#fP_Product_Id_' + i).focus();
                valid = false;
            }
            if ($('#fP_Product_Id_' + i).val() == "" || $('#fP_Product_Id_' + i).val() == "0") {
                $('#type_err_' + i).html('Select the product');
                $('#fP_Product_Id_' + i).removeAttr("readonly");
                $('#unit_' + i).removeAttr("readonly");
                $('#cft_' + i).removeAttr("readonly");
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled', true);
                $('#add_more').prop('disabled', false);
                valid = false;
            }
            if ($('#unit_' + i).val() == "" || $('#unit_' + i).val() == "0") {
                $('#unit_err_' + i).html('Select the unit');
                $('#unit_' + i).removeAttr("readonly");
                $('#cft_' + i).removeAttr("readonly");
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled', true);
                $('#add_more').prop('disabled', false);
                valid = false;
            }
            if ($('#cft_' + i).val() == "" || $('#cft_' + i).val() == "0") {
                $('#qty_err_' + i).html('Enter the quantity');
                $('#cft_' + i).removeAttr("readonly");
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled', true);
                $('#add_more').prop('disabled', false);
                valid = false;
            }
        }
        return valid;
    }

    function declarationCheck() {
        if ($('#submit_form').prop('checked')) {
            $('#btn_submit_online_timber').prop('disabled', false);
        } else {
            $('#btn_submit_online_timber').prop('disabled', true);
        }
    }
    function showConfirmation(){
        $('#confirmationModel').modal('show');
        $('#targetId').val('acknowledgementmessage');
        $('#messages').html('You are about to submit application. Are you sure to proceed ?');
    }

    function submit_final_form() {
        if (validateWoodAndPole() == true) {
            $('#confirmationModel').modal('hide');
            $.ajax({
                type : "POST",
                url : '${pageContext.request.contextPath}/gewog/loginMain/saveWoodandPoles',
                data: $('#personalForm').serialize(),
                cache : false,
                success : function(res) {
                    // $('#btn_submit_online_timber').prop('disabled',true);
                    //  $("#messageDiv").html(responseText);
                    if(res.status==1){
                        successMsg(res.text, '${pageContext.request.contextPath}/loginMain/');
                    }else{
                        warningMsg(res.text);
                    }
                }
            });
        }
    }

    function showCopy(val) {
        if (val == 'y') {
            $('#copy_from_gup').show();
            $('#endrosment').show();
            $('#addMore').show();
        } else if (val == 'n') {
            $('#copy_from_gup').hide();
            $('#endrosment').hide();
            $('#addMore').hide();
        }
    }

    function validateAddQty(val, c) {
        var returnWoodAndPole = true;
        var quantity = val;
        var val1 = c - 1;
        var fp_prod = $('#fP_Product_Id_'+c).val();

        if(fp_prod !=0){

            //alert(fp_prod);
            if (fp_prod == 6 || fp_prod == 7 || fp_prod == 187) {
                if (quantity > 2) {
                    warningMsg('2 Truckload/16 m3 for place with no electricity 1 Truckload/8 m3 for place with electricity.');
                    $('#cft_'+c).val('');
                    $('#cft_'+c).focus();
                    returnWoodAndPole = false;
                    count++;
                }
            }

            if (fp_prod == 201) {
                if (quantity > 29) {
                    warningMsg('Maximum limit: [0-29 Nos.]');
                    $('#cft_'+c).val('');
                    $('#cft_'+c).focus();
                    returnWoodAndPole = false;
                    count++;
                }
            }
            if (fp_prod == 202) {
                if (quantity > 108) {
                    warningMsg('Maximum limit: [0-108 Nos.]');
                    $('#cft_'+c).val('');
                    $('#cft_'+c).focus();
                    returnWoodAndPole = false;
                    count++;
                }
            }
            if (fp_prod == 188 || fp_prod == 189) {
                if (quantity > 50) {
                    warningMsg('Maximum limit: [50 Nos.]');
                    $('#cft_'+c).val('');
                    $('#cft_'+c).focus();
                    returnWoodAndPole = false;
                    count++;
                }
            } else {
                returnWoodAndPole = true;
            }
        }
        for(var k=0;k<c;k++) {
            //if (k > 0) {
                var additionalProd = $('#fP_Product_Id_' + k).val();
                var previousProd = $('#fP_Product_Id_' + c).val();

              /*  if (previousProd == fp_prod) {
                    $('#product' + count).remove();
                    count--;
                    warningMsg("You have selected the same product! Select other!");
                }else */if (previousProd == additionalProd) {
                    $('#product' + c).remove();
                    count--;
                    warningMsg("You have selected the same product! Select other!");
                return count;
                }
            //}
        }
        return count;
        jQuery.removeData();
    }

    function validateQty(val, count) {
        var returnWoodAndPole = true;
        var quantity = val;
        var fp_prod = $('#fP_Product_Id_0').val();
        if (fp_prod == 6 || fp_prod == 7 || fp_prod == 187) {
            if (quantity > 2) {
                warningMsg('2 Truckload/16 m3 for place with no electricity 1 Truckload/8 m3 for place with electricity.');
                $('#cft_0').val('');
                $('#cft_0').focus();
                returnWoodAndPole = false;
                count++;
            }
        }
        if (fp_prod == 201) {
            if (quantity > 29) {
                warningMsg('Maximum limit: [0-29 Nos.]');
                $('#cft_0').val('');
                $('#cft_0').focus();
                returnWoodAndPole = false;
                count++;
            }
        }
        if (fp_prod == 202) {
            if (quantity > 108) {
                warningMsg('Maximum limit: [0-108 Nos.]');
                $('#cft_0').val('');
                $('#cft_0').focus();
                returnWoodAndPole = false;
                count++;
            }
        }
        if (fp_prod == 188 || fp_prod == 189) {
            if (quantity > 50) {
                warningMsg('Maximum limit: [50 Nos.]');
                $('#cft_0').val('');
                $('#cft_0').focus();
                returnWoodAndPole = false;
            }
        } else {
            returnWoodAndPole = true;
        }
        return returnWoodAndPole;
        count++;
        jQuery.removeData();
    }

    $('[data-toggle="tooltip"]').tooltip();

</script>
</body>

