<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <div class="content__inner">
        <div class="card">
            <div class="card-body">
                <form  action="#" id="manualRecord" method="post" enctype="multipart/form-data">
                    <div id="loadPage">
                    <div class="nav nav-tabs" id="nav-tab" role="tablist" style=" background: #B1C0BF;">
                        <a class="nav-item nav-link active border col-md-6 col-sm-6 col-lg-6 col-xs-12 form-control-sm" id="personalDetials" data-toggle="tooltip" href="#personal_tab" data-placement="top" title="" data-original-title='Please use buttons to change tabs.' role="tab" aria-controls="nav-contact" aria-selected="true">
                            <i class="fa fa-user mr-2"></i>Personal Details <span class="text-info" style="font-size: 15px"id="personal_check"></span>
                        </a>
                        <a class="nav-item nav-link border col-md-6 col-sm-6 col-lg-6 col-xs-12 form-control-sm" id="timberDetaials" data-toggle="tooltip" href="#timber_tab" data-title='Please use buttons to change tabs.' role="tab" aria-controls="nav-contact" aria-selected="false">
                            <i class="fa fa-globe mr-2"></i> Timber Details<span class="text-info"style="font-size: 20px"id="timber_check"></span>
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
                            <div class="card">
                                <div class="card-body border-dark">
                                    <div class="form-group row mb-2">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">CID Number : <span class="text-danger">*</span></label>
                                            <input type="number" class="form-control form-control-sm" id="cid" name="cid" onchange="fetchCidDetails(this.value)"
                                                   onKeyPress="if(this.value.length==11) return false;" min="1"/>
                                            <%-- <input type="hidden" id="hoh"/>--%>
                                            <div id="cid_Number_err" class="alert alert-danger" style="display:none;"></div>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Full Name </label>
                                            <input type="text" class="form-control form-control-sm" id="fullName"  name="name" readonly>
                                            <div id="fullName_err" class="alert alert-danger" style="display:none;"></div>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Gender </label>
                                            <input type="text" class="form-control form-control-sm" id="gender" name="genderType" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group row mb-2">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Dzongkhag  </label>
                                            <input type="text" class="form-control form-control-sm" id="dzongkhag" name="dzongkhag_Name" readonly>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Gewog </label>
                                            <input type="text" class="form-control form-control-sm" id="gewog" name="gewog_Name" readonly>
                                            <input type="hidden"  id="gewogId" name="gewogId"/>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Village </label>
                                            <input type="text" class="form-control form-control-sm" id="village" name="village_Name" readonly>
                                            <input type="hidden"  id="village_Serial_No" name="village_Serial_No"/>
                                            <div id="village_Name_err" class="alert alert-danger" style="display:none;"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row mb-2">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Household Number </label>
                                            <input type="number" class="form-control form-control-sm" id="household" name="house_Hold_No" readonly>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Gung CID number </label>
                                            <input type="number" class="form-control form-control-sm" id="gung_cid_Number" name="cid_Number" readonly>
                                            <div id="gung_cid_Number_err" class="alert alert-danger" style="display:none;"></div>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">House Number </label>
                                            <input type="text" class="form-control form-control-sm" id="house_no" name="house_No" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group row mb-2">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Thram Number  &nbsp; <span class="text-danger">*</span></label>
                                            <input type="text" class="form-control form-control-sm" id="thram" name="thram_No" readonly>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Member of community forest&nbsp; <span class="text-danger">*</span></label>
                                            <input type="text" class="form-control form-control-sm" name="member_of_Forest_community" id="community_forest">
                                            <div class="alert alert-danger" id="community_forest_error" style="display:none;"></div>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Construction Approval Number &nbsp; <span class="text-danger">*</span></label>
                                            <input type="text" class="form-control form-control-sm" name="cons_Approval_No" id="cons_Approval_No">
                                            <div class="alert alert-danger" id="Approval_err" style="display:none;"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row mb-2">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Location of construction :<span class="text-danger">*</span></label>
                                            <select name="cons_Loc_Village_Serial_No" class="chosen-select form-control form-control-sm" id="cons_Loc" >
                                                <option value="">Select construction location</option>
                                            </select>
                                            <div class="alert alert-danger" id="cons_Loc_err" style="display:none;"></div>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Division/Park : <span class="text-danger">*</span></label>
                                            <select name="division_Park_Id" class="chosen-select form-control form-control-sm" id="division_Park">
                                                <option value="">Select Division/Park</option>
                                            </select>
                                            <div class="alert alert-danger" id="division_Park_err" style="display:none;"></div>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                            <label class="form-label">Mobile Number : <span class="text-danger">*</span></label>
                                            <input type="number" class="form-control form-control-sm" onclick="removeErrors('_err')" onchange="removeErrors('_err')" id="num" name="phone_Number"
                                                   onKeyPress="if(this.value.length==8) return false;" min="1">&nbsp;
                                            <span id="errmsg" style="color: #ff0000;"></span>
                                            <div class="alert alert-danger" id="_err" style="display:none;"></div>
                                        </div>
                                    </div>
                                    <div class="form-group text-right">
                                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                                            <button class="btn btn-md text-white" style="background-color: #236F67" value="personal" id="personalDetails"
                                                    onclick="next_tab(this.value)" type="button">
                                                <a href="#" class="text-white"><b>Next&nbsp;&nbsp;<i class="zmdi zmdi-fast-forward"></i></b></a></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-content" id="nav-tabContent1">
                            <div class="tab-pane fade" id="timber_tab" role="tabpanel" aria-labelledby="nav-home-tab">
                                <div class="card">
                                    <div class="card-body border-dark">
                                        <div class="form-group row mb-2">
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm">
                                                <label class="form-label">Construction Type: <span class="text-danger">*</span></label>
                                                <select class="form-control form-control-sm" name="cons_Type" id="cons_type" onchange="chooseConstructionType(this.value)">
                                                    <option value="">-- Select Construction Type --</option>
                                                    <option value="n">New Construction</option>
                                                    <option value="r">Renovation</option>
                                                    <option value="o">Other Constructions</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm" id="storey" style="display: none">
                                                <label class="form-label">House Storey : <span class="text-danger">*</span></label>
                                                <select class="form-control form-control-sm" name="storey_House" id="select_story" onchange="chooseStoried(this.value)">
                                                    <option value="0">-- Select Storey --</option>
                                                    <option value="1">1 storied</option>
                                                    <option value="2">2 storied</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 form-control-sm" id="productCategoryDetails" style="display: none">
                                                <label class="form-label">Timber Form : <span class="text-danger">*</span></label>
                                                <select class="form-control form-control-sm" name="timberDetails[0].product_Catagory" id="productCatagory" onclick="chooseTimberForm()">
                                                    <option value="">Select Timber Form</option>
                                                    <option value="RTP(S)">Standing Form</option>
                                                    <option value="RTP(l)">Log Form</option>
                                                </select>
                                            </div>
                                        </div>
                                        </br>
                                        <div id="row">
                                            <span class="btn btn-md text-white float-right" id="add_more" style="display: none; background: #FF994E"><i class="zmdi zmdi-plus-square">&nbsp;&nbsp; Add More</i> </span>
                                            <div class="form-group row mb-2" id="otherDetails" style="display: none;">
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                    <label class="form-label">Product Category:</label>
                                                    <select class="form-control form-control-sm" name="timberDetails[0].fP_Product_Id" id="fp_product_Catagory" onchange="chooseMaxDetails(this.value)">
                                                        <option value="0" >Select Category</option>
                                                        <c:forEach var="category" items="${fpProductCategory}">
                                                            <option value="${category.header_id}">${category.header_Name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm" >
                                                    <label class="form-label">Maximum Quantity Permit:</label>
                                                    <input type="text" class="form-control form-control-sm" id="maxLimit1" value="${category.header_Name}" readonly><span clas="unit"></span>
                                                    <input type="hidden" class="form-control form-control-sm" name="timberDetails[0].unit" id="unit1" readonly><span clas="unit"></span>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                    <label class="form-label">Request Quantity:</label>
                                                    <input type="number" class="form-control form-control-sm" value="0"  name="timberDetails[0].appl_Quantity" id="quantity_appl" min="1" onchange="checkQuantity()">
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                    <label class="form-label">Quantity Approved:</label>
                                                    <input type="number" class="form-control form-control-sm" value="0"  name="timberDetails[0].allot_Quantity" id="allot_Quantity0" min="1">
                                                </div>
                                            </div>
                                        </div>
                                        <div id="adddiv">
                                            <div class="form-group row mb-2" id="otherDetails1" style="display: none;">
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                    <label class="form-label">Product Category:</label>
                                                    <select class="form-control form-control-sm" name="timberDetails[1].fP_Product_Id" id="fp_product_Catagory1" onchange="chooseMaxDetails1(this.value)">
                                                        <option value="0">Select Category</option>
                                                        <c:forEach var="category" items="${fpProductCategory}">
                                                            <option value="${category.header_id}">${category.header_Name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm" >
                                                    <label class="form-label">Maximum Quantity Permit:</label>
                                                    <input type="text" class="form-control form-control-sm" id="maxLimit2" value="${category.header_Name}" readonly>
                                                    <input type="hidden" class="form-control form-control-sm" id="unit2" name="timberDetails[1].unit" readonly>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                    <label class="form-label">Request Quantity:</label>
                                                    <input type="number" class="form-control form-control-sm" value="0"  name="timberDetails[1].appl_Quantity" id="quantity_app1l" min="1" onchange="checkQuantity1()">
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 form-control-sm">
                                                    <label class="form-label">Quantity Approved:</label>
                                                    <input type="number" class="form-control form-control-sm" value="0"  name="timberDetails[1].allot_Quantity" id="allot_Quantity1" min="1">
                                                </div>
                                                <div class="col-lg-1 col-md-1 col-sm-1">
                                                    <label class="mt-5"></label>
                                                    <button class="btn btn-danger btn-md" type="button" onclick="deleteCurrentRow()"><i class="zmdi zmdi-delete"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row mb-2" id="timberDetails" style="display: none;">
                                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                <label class="form-label">Product Name:</label>
                                                <input type="text" class="form-control form-control-sm" id="product_name" readonly>
                                                <input type="hidden" class="form-control form-control-sm" id="prod_id_0" value="0" name="fp_Product_Id">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                <label class="form-label">Maximum Quantity Permit:</label>
                                                <input type="text" class="form-control form-control-sm" id="maxLimit" readonly>
                                                <input type="hidden" class="form-control form-control-sm" id="unit" name="unit" readonly>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                <label class="form-label">Request Quantity:</label>
                                                <input type="number" class="form-control form-control-sm numeric"  name="appl_Quantity" id="appl_Quantity" min="1" value="0" onchange="checkApplQuantity()">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 form-control-sm">
                                                <label class="form-label">Quantity Approved:</label>
                                                <input type="number" class="form-control form-control-sm numeric"  name="allot_Quantity" id="allot_Quantity" min="1" value="0">
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                    <label><b>Attach Manual Reports</b>
                                                    <ul>
                                                        <li>Application Form<span class="text-danger">*</span></li>
                                                        <li>Inspection Report<span class="text-danger">*</span></li>
                                                        <li>Allotment Report<span class="text-danger">*</span></li>
                                                    </ul></label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 text-right">
                                                <input type="file" name="files" id="doc1" class="alert" onchange="validateAttachment(this.value,'doc1','doc_check1'),removeErrors('endorsement_err')" onclick="removeErrors('endorsement_err')">
                                                <button class="btn bg-warning pull-right" type="button" onclick="addmoreattachemnts()"><i class="zmdi zmdi-file-plus"> Add More</i></button>
                                            </div>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <span id="fileadd"></span>
                                            </div>
                                        </div>
                                    </div>
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
                                                <button class="btn btn-md fa-pull-right text-white" style="background-color: #236F67" id="btn_submit_online_timber" onclick="submit_online_timber()" disabled="true" type="button">
                                                    <a href="#" class="text-white"> Submit &nbsp;&nbsp;&nbsp;<i class="zmdi zmdi-mail-send"></i></a></button>
                                                <button class="btn btn-md fa-pull-right mr-3 text-white" style="background-color: #F15628" onclick="pre_tab('timber')" type="button"><a href="#" class="text-white"><i class="zmdi zmdi-mail-reply-all"></i>&nbsp; Previous</a></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
                <div class="card mt-2 text-center" id="messageDiv" style="display: none; margin-top: 20px; height: 450px">
                    <div class="card-body border-dark">
                        <div class="form-group row mb-2 declaration">
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 form-control-sm text-black text-center">
                                <div class="alert " role="alert" id="responseText"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
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
    $('[data-toggle="tooltip"]').tooltip();

    $(document).ready(function(){
        $(".reset-btn").click(function(){
            $("#manualRecord").trigger("reset");
        });
    });

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
                if($('#o').is(':checked')) {
                    $("#productCategoryDetails").hide();
                    $('#otherDetails').show();
                    $('#add_more').show();
                }
            }
        } else if (val == "timber") {
            if (validateTimber() == true) {
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

    function validatePersonal() {
        var returnVal = true;
        if ($('#cid').val() == "") {
            $('#cid_Number_err').html('Cid Number is mandatory');
            $('#cid_Number_err').show();
            $('#cid_Number_err').delay(2000).fadeOut('slow');
            returnVal = false;
        }
        if ($('#cons_Loc').val() == "") {
            $('#cons_Loc_err').html('Construction Location is mandatory');
            $('#cons_Loc_err').show();
            $('#cons_Loc_err').delay(2000).fadeOut('slow');
            returnVal = false;
        }
        if ($('#division_Park').val() == "") {
            $('#division_Park_err').html('Division Park is mandatory');
            $('#division_Park_err').show();
            $('#division_Park_err').delay(2000).fadeOut('slow');
            returnVal = false;
        }
        if ($('#num').val() == "") {
            $('#_err').html('Mobile Number is mandatory');
            $('#_err').show();
            $('#_err').delay(2000).fadeOut('slow');
            returnVal = false;
        }
        return returnVal;
    }

    $(document).on("keyup blur", function() {
        $('#cid').on('keyup blur', function () {
            var cid = $(this).val();
            if (cid != '') {
                $('#cid').removeClass('error');
                $('#cid_Number_err').text('');
            }
        });
        $('#cons_Loc').on('keyup blur', function () {
            var cons_Loc = $(this).val();
            if (cons_Loc != '') {
                $('#cons_Loc').removeClass('error');
                $('#cons_Loc_err').text('');
            }
        });
        $('#division_Park').on('keyup blur', function () {
            var division_Park = $(this).val();
            if (division_Park != '') {
                $('#division_Park').removeClass('error');
                $('#division_Park_err').text('');
            }
        });
    });

    function declarationCheck() {
        if ($('#submit_form').prop('checked')) {
            $('#btn_submit_online_timber').prop('disabled', false);
        } else {
            $('#btn_submit_online_timber').prop('disabled', true);
        }
    }

    var selectedStorey=0;
    function chooseStoried(val) {
        selectedStorey =val;
        $("#timberDetails").hide();
        $("#productCatagory")[0].selectedIndex = 0;
    }

    function chooseTimberForm() {
        var cons_type = $("#cons_type").val();
        var val = $("#productCatagory").val();

        if (cons_type == "n") {
            $("#timberDetails").show();
            var storey = $("#select_story").val();
        }else if (cons_type == "r"){
            $("#timberDetails").show();
        }
        $.ajax({
            url: '${pageContext.request.contextPath}/ruralTimber/productListOnline?cons_desc=' + val + '&storey=' + storey + '&cons_type=' + cons_type,
            type: 'GET',
            success: function (res) {
                for (var i = 0; i < res.length; i++) {
                    $('#prod_id_0').val(res[i].header_id);
                    $('#product_name').val(res[i].header_Name);
                    $('#maxLimit').val(res[i].maxLimit + '('+res[i].product_desc+')');
                    $('#unit').val(res[i].product_desc);
                }
            }
        });
    }

    function chooseMaxDetails(val) {
        $.ajax({
            url: '${pageContext.request.contextPath}/ruralTimber/productDetailsList?cons_desc=' + 'RTP(s)' + '&cons_type=' + 'o'+ '&product_id=' + val,
            type: 'GET',
            success: function (res) {
                for (var i = 0; i < res.length; i++) {
                    $('#maxLimit1').val(res[i].maxLimit + '('+res[i].product_desc+')');
                    $('#unit1').val(res[i].product_desc);

                }
            }
        });
    }

    function chooseMaxDetails1(val) {
        $.ajax({
            url: '${pageContext.request.contextPath}/ruralTimber/productDetailsList?cons_desc=' + 'RTP(s)' + '&cons_type=' + 'o'+ '&product_id=' + val,
            type: 'GET',
            success: function (res) {
                for (var i = 0; i < res.length; i++) {
                    $('#maxLimit2').val(res[i].maxLimit + '('+res[i].product_desc+')');
                    $('#unit2').val(res[i].product_desc);
                }
            }
        });
    }

    function fetchCidDetails(cid) {
        $("input[name=cons]").attr('disabled', true);
        var cons_type = $('#cons_type').val();
        if (validatecid(cid)) {
            $.ajax({
                url: '${pageContext.request.contextPath}/getCitizenDetailsOnline?cid=' + cid +'&cons_type=' + cons_type,
                type: 'GET',
                success: function (res) {
                    if(res.status_Id==1){
                        $('#cid').val(res.cid_Number);
                        $('#fullName').val(res.name);
                        $('#house_no').val(res.house_No);
                        $('#household').val(res.house_Hold_No);
                        $('#householdOne').val(res.house_Hold_No);
                        $('#dzongkhag').val(res.dzongkhag_Name);
                        $('#dzongkhag_Id').val(res.dzongkhag_Id);
                        $('#gewog').val(res.gewog_Name);
                        $('#gewogId').val(res.gewog_Id);
                        $('#village').val(res.village_Name);
                        $('#village_Serial_No').val(res.village_Serial_No);
                        $('#gung_cid_Number').val(res.cid_Number);
                        $('#gender').val(res.genderType);
                        $('#thram').val(res.thram_No);

                        $('#hoh').val(res.hoh);
                        var hoh = res.hoh;
                        if(hoh=="NO") {
                            $("#check_hoh").modal('show');
                            $("#hoh_check").show();
                        }
                        $('#hoh').val(res.hoh_Cid);
                        var hoh_Cid = res.hoh_Cid;
                        if(hoh_Cid=="FALSE") {
                            $("#check_hoh").modal('show');
                            $("#hohCID_check").show();
                        }
                        if(res.status_Id=='0'){
                            $("#check_hoh").modal('show');
                            $("#availedRTP").show();
                            $("#availedServicesErr").html(res.actor_Name);
                        }
                        getParkDropdown(res.dzongkhag_Id,'parkList');
                        getConstructionLoc(res.gewog_Id,'villegeList');
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
                    $('#division_Park').append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>").trigger('chosen:updated');
                }
            }
        });
    }

    //getConstructionLocation
    function getConstructionLoc(gewog_Id,type){
        $.ajax({
            url: '${pageContext.request.contextPath}/public/getConstructionLocation?gewogId=' + gewog_Id + '&type=' + type,
             type: 'GET',
            success: function (res) {
                //$('#division_Park').append("<option value=''>Select</option>");
                var str = "";
                for (var i = 0; i < res.length; i++) {
                    $('#cons_Loc').append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>").trigger('chosen:updated');
                }
            }
        });
    }

    function validatecid(cid) {
        var retval = true;
        if (cid.substring(0, 1) == "3") {
          //  $('#cider').html('Cid starting with 3 is not allow');
            warningMsg("Cid starting with 3 is not allow");
            retval = false;
        }
        else if (cid.length != 11) {
         //   $('#cider').html('Bhutanese CID should have 11 digit long');
            warningMsg("Bhutanese CID should have 11 digit long");
            retval = false;
        }
        return retval;
    }
    $('#cid').click(function () {
        $('#cider').html('').focus();
    });

    function validateTimber() {
        var valid = true;
        return valid;
    }

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

    function submit_online_timber(){
       /* $.ajax({
            type : "POST",
            url : '${pageContext.request.contextPath}/gewog/loginMain/submitManualRecord',
            data: $('#manualRecord').serialize(),
            cache : false,
            success : function(res) {
             //$('#btn_submit_online_timber').prop('disabled',true);
             //$("#messageDiv").html(responseText);
                if(res.status==1){
                    successMsg(res.text, '${pageContext.request.contextPath}/loginMain');
                }else{
                    warningMsg(res.text);
                }
            }
        });*/
        var url = '${pageContext.request.contextPath}/gewog/loginMain/submitManualRecord';
        var options = {
            target: '#responseText',
            url: url,
            enctype: 'multipart/form-data',
            type: "POST",
            data: $('#manualRecord').serialize()
        };
        $("#manualRecord").ajaxSubmit(options);
        $("#manualRecord").hide();
        $("#messageDiv").show();
    }

    function removeErrors(id) {
        $('#' + id).html('');
    }

    var count = 1;
    function add_row_Type(val, type, targetId, cons_type) {
        $.ajax({
            url: '${pageContext.request.contextPath}/ruralTimber/productList?cons_desc=' + val + '&type=' + type + '&cons_type=' + cons_type,
            type: 'GET',
            success: function (res) {
                $('#' + targetId);
                $('#addProd').append('<div class="form-group row" id="product' + count + '">' +
                '<label class="col-lg-4 control-label"></label> <label class="col-lg-3 control-label">' +
                '<select name="timberDetails[' + count + '].fP_Product_Id" style="width:100%;" class="chosen-select form-control" id="prod_id_' + count + '">' +
                '<option value="0">--select--</option> </select></label><label class="col-lg-2 control-label">' +
                '<input type="number" class="form-control" placeholder="enter quantity (in cft)"id="cft_' + count + '" name="timberDetails[' + count + '].appl_Quantity"></label>' +
                '<label class="col-lg-2 control-label"><button class="btn btn-danger fa" type="button" onclick="deleteRow(' + count + ')"><i class="fa fa-times pr-4"></i>Delete</button></label></div>');
                // alert(targetId);
                count++;
                var str = "";
                for (var i = 0; i < res.length; i++) {
                    $('#' + targetId).append("<option value=" + res[i].header_id + ">" + res[i].header_Name + "</option>");
                }
            }
        });
    }

    function deleteRow(id) {
        $('#product' + id).remove();
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
        $("#cft_0").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#valerrmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#cft_log_0").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#logerrmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#cid").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#logerrmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#thram").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#thram_err").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
    });

    $("input[type='file']").change(
            function () {
                var fileExtension = ['png', 'pdf', 'jpg', 'docx', 'doc'];
                if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
                    alert("Only '.png','.pdf','.jpg','.docx','.doc' format is allowed.");
                    this.value = ''; // Clean field
                    //$("input[type='file']").unbind("onchange", handler);
                    $("input[type='file']").prop('class', 'alert badge-danger');
                    return false;
                }
                if (this.files[0].size > 8000000) {
                    alert("Please upload file less than 8MB. Thanks!!");
                    $(this).val('');
                    return false;
                }
            });


    function validateFiles() {
        var valid = true;
        if ($('#village_Serial_No_0').val() == "") {
            $('#village_err').html('Select the Village');
            $('#village_Serial_No_0').focus();
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            valid = false;
        }
        if ($('#file1').val() == "") {
            $('#construction_approval_file_err').html('Attach the Construction approval');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            $('#file1').focus();
            valid = false;
        }
        if ($('#doc1').val() == "") {
            $('#endorsement_err').html('Attach the Endorsement Letter from Gup');
            $('#submit_form').prop('checked', false);
            $('#btn_submit_final_form').prop('disabled', true);
            $('#doc1').focus();
            valid = false;
        }
        if ($('input[name="member_of_Forest_community"]:checked').val() == "y") {
            if ($('#copy').val() == "") {
                $('#copy').focus();
                $('#copy_err').html('Attach  letter from chairperson of Community Forest ');
                $('#submit_form').prop('checked', false);
                $('#btn_submit_final_form').prop('disabled', true);
                valid = false;
            }
        }
        for (var i = 2; i <= inicount; i++) {
            if ($('#file' + i).val() == "") {
                $('#moreFile_err' + i).html('Attach the file!');
                valid = false;
            }
        }
        return valid;
    }

    $(document).ready(function(){
        $("#add_more").click(function () {
              $('#otherDetails1').show();

          /*  var row = '<tr><td>'+
                    '<select class="form-control form-control-sm" name="timberDetails[0].fP_Product_Id" id="fp_product_Catagory" onchange="chooseMaxDetails(this.value)">'+
                    '<option value="0" >Select Category</option>'+
                    '<c:forEach var="category" items="${fpProductCategory}">'+
                    '<option value="${category.header_id}">${category.header_Name}</option>'+
                    '</c:forEach>'+
                    '</select>'+
                    '</td> <td style="width: 2%"></td>'+
                    '<td>'+
                    '<input type="text" class="form-control form-control-sm" id="maxLimit1" value="${category.header_Name}" placeholder="maxi quantity permited" readonly>'+
                    '</td> <td style="width: 2%"></td>'+
                    '<td>'+
                    '<input type="number" class="form-control form-control-sm" value="0" placeholder="request quantity (In Number)" name="timberDetails[0].appl_Quantity" id="quantity_appl" min="1">'+
                    '</td> <td style="width: 2%"></td>' +
                    '<td><div class="col-sm-6"> \
                    <button type="button" class="btn btn-outline-danger btn-sm" id="btnRemove"> \
                <i class="fa fa-trash"></i> Remove</button></div></td></tr>';
            $('#addmoreCategoryGrid').find('tbody').append(row);*/

            $('#add_more').hide();
        });
      //  removeFileRow();
    });

    /**
     * To remove
     * @returns {*}
     */
    function removeFileRow() {
        $('#addmoreCategoryGrid').on('click', 'tbody tr #btnRemove', function () {
            $(this).closest('tr').remove();
            $('#addmoreCategoryGrid').find('tbody'), $('#addmoreCategoryGrid').find('tbody tr');
        });
    }

/*
    $(document).ready(function(){
            var cons_type = $("#cons_type").val();
            if (cons_type == "n") {
                $('#storey').show();
            }else if (cons_type == "r"){
                $("#timberDetails").show();
                $('#storey').hide();
            }else{
                $("#timberDetails").hide();
                $("#productCategoryDetails").hide();
                $('#otherDetails').show();
                $('#add_more').show();
            }
    });
*/

    function chooseConstructionType(cons_type){
        if (cons_type == "n") {
            $('#storey').show();
            $('#productCategoryDetails').show();
            $('#otherDetails').hide();
            $('#otherDetails1').hide();
            $('#timberDetails').hide();

        }else if (cons_type == "r"){
            $("#timberDetails").hide();
            $('#productCategoryDetails').show();
            $('#otherDetails').hide();
            $('#otherDetails1').hide();
            $('#storey').hide();
        }else{
            $("#timberDetails").hide();
            $("#productCategoryDetails").hide();
            $('#otherDetails').show();
            $('#add_more').show();
            $('#storey').hide();
        }
    }

    function deleteCurrentRow() {
        $('#add_more').show();
        $('#otherDetails1').hide();
    }
    function checkApplQuantity() {
         var applQuantity = $('#appl_Quantity').val();
         var maxLimit = $('#maxLimit').val();
        if(parseInt(applQuantity) > parseInt(maxLimit)) {
            warningMsg("Cannot Be More Than Maximum Quantity Permitted");
            $('#appl_Quantity').val("");
            $('#appl_Quantity').focus();
        }
    }

    function checkQuantity() {
        var applQuantity = $('#quantity_appl').val();
        var maxLimit = $('#maxLimit1').val();

        if(parseInt(applQuantity) >parseInt(maxLimit)) {
            warningMsg("Cannot Be More Than Maximum Quantity Permitted");
            $('#quantity_appl').val("");
            $('#quantity_appl').focus();
        }
    }

    function checkQuantity1() {
        var quantity_app1l = $('#quantity_app1l').val();
        var maxLimit2 = $('#maxLimit2').val();

        if(parseInt(quantity_app1l) > parseInt(maxLimit2)) {
            warningMsg("Cannot Be More Than Maximum Quantity Permitted");
            $('#quantity_app1l').val("");
            $('#quantity_app1l').focus();
        }
    }
</script>
</body>

