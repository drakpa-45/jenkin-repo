<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="<c:url value='/resources/framework/css/app.min.css'/>"/>
<link rel="stylesheet" href="resources/assets/css/nprogress.css"/>
<!------ Include the above in your HEAD tag ---------->

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Custom CSS -->
<style>
    .line-container {
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 20px 0;
    }

    .line {
        flex-grow: 1;
        border-top: 1px solid #000;
    }

    .text {
        margin: 0 10px;
    }

    body {
        margin: 0;
        padding: 0;
    }

    header {
        background-color: #0ba5d9;
        color: #fff;
        display: flex;
        align-items: center;
        padding: 10px;
    }

    .toggle-button {
        background: #333;
        border: none;
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin-right: 10px;
    }

    .header-text {
        margin-right: 10px;
        font-weight: bold;
    }
    img{
        width: 90px;
        height: 55px;
    }

    h5 {
        margin: 0;
        display: inline-block;
        vertical-align: middle;
        color:#0ba5d9;
    }

    .btn-bg{
        background-color: #124143;
        color: white;
        border-radius: 7px;
    }

    .cus-button {width: 50%;}
</style>
<header>
    <%--<button class="toggle-button">&#9776;</button>--%>
    <span class="header-text">Toll Free Number:1199 / E-mail:g2c@cabinet.govt.bt</span>
</header>
<div>
    <img  class="pl-5 mt-3" src="<c:url value="/resources/images/logo.png"/> " alt="">
    <h5>CITIZEN SERVICE PORTAL </h5>
    <br>
    <h6 style='text-align:center;color:#0ba5d9'> ROYAL GOVERNMENT OF BHUTAN - In pursuit improving public service delivery </h6>
</div>
<hr>
<div class="container" id="loadMainPage">
    <div class="row justify-content-center mt-5">
        <div class="col-lg-6 col-md-6 col-sm-6">
            <div class="card">
                <div class="card-body">
                    <div class="col-lg-12 col-md-12 col-sm-12" id="socialBx">
                        <h6 style="text-align: center;" class="card-title">Click to Login as citizen to avail the service </h6>
                        <div style="text-align: center;" class="form-group">
                            <a href="http://brtp.citizenservices.gov.bt/loginDashboard"><button style='background: #ffa500;border-color: #ffa500;' type="submit" class="btn text-white cus-button">Login with SSO </button></a>
                            <%--<a href="${authorizeUriUtf8}"><button style='background: #ffa500;border-color: #ffa500;' type="submit" class="btn text-white cus-button">Login with SSO </button></a>--%>
                        </div>
                        <div style="text-align: center;" class="form-group">
                            <div class="line-container">
                                <div class="line"></div>
                                <span class="text">OR</span>
                                <div class="line"></div>
                            </div>
                            <div class="form-group">
                                <button style='background: #097969; border-color: #097969;' type="submit" class="btn text-white btn-bg cus-button" onclick="passwordLessLogin()">
                                    <img src="<c:url value="/resources/images/NDI logonoBG.png"/>" style="width: 25px; height: 25px">
                                    Login with Bhutan NDI
                                </button>
                                <hr>
                            </div>
                            <div class="form-group">
                                <label for="password">Click to Login as executing agency/official </label>
                                <div style="text-align: center;" class="form-group">
                                    <a href="http://brtp.citizenservices.gov.bt/loginMain"><button style='background-color: #0ba5d9;border-color:#0ba5d9;' type="submit" class="btn text-white cus-button">Agency Login </button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <img id="logo" src="<c:url value="/resources/images/NDIlogobg.png"/>" alt="Logo" style="display:none; position: absolute; top: 100px; left: 100px; width: 60px; height: 60px; z-index: 2;">
                    <div class="col-lg-12 col-md-12 col-sm-12" id="qrcodeDisplay" style="display: none">
                        <h5 style="text-align: center;" class="card-title">
                            <img src="<c:url value="/resources/images/NDIlogobg.png"/>" class="mr-3 ml-4" style="width: 35px; height: 35px">Login with Bhutan NDI </h5>
                        <div style="text-align: left;" class="form-group">
                            <span>Scan the QR-code using Bhutan NDI App to Login</span>
                        </div>
                        <div style="text-align: center;" class="form-group">
                            <div class="form-group">
                                <canvas id="qrcode"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/qrious@4.0.2/dist/qrious.min.js"></script>

<script src="resources/assets/js/script.js"></script>
<script src="resources/assets/js/nprogress.js"></script>
<script>
    /*----------------------------- Click on login and Sign Up to change and view the effect ---------------------------*/
    function passwordLessLogin() {

        $.ajax({
            url: '${pageContext.request.contextPath}/passwordLessLogin',
            type: "GET",
            async: true,
            success: function (res) {
                $('#socialBx').hide();
                $('#qrcodeDisplay').show();

                var canvas = document.getElementById("qrcode");
                var qr = new QRious({
                    element: canvas,
                    value: res.proofRequestURL,
                    size: 260
                });

                if (res.deepLinkURL != null) {
                    $("#deeplink").prop("href", res.deepLinkURL);
                }

                $('.counterForScan').removeClass("hidden");
                $('#successMsge').removeClass("hidden");
                $('#spinner').addClass("hidden");
              //  registerEvent(res.proofRequestThreadId);

                // Insert the logo into the QR code
                var logoImage = document.getElementById("logo");
                var ctx = canvas.getContext("2d");
                ctx.drawImage(logoImage, 100, 100, 60, 60); // Adjust the positioning and size as needed

            }
        });


    }


    // Function to load the image and convert it to binary data
    function loadImageAndEncode(imageUrl) {

    }





    function registerEvent(threadId) {
        let endPoint = '${pageContext.request.contextPath}/subscribe?threadId=' + threadId;
        let eventSource = new EventSource(endPoint);

        eventSource.addEventListener('proofSuccessEvent', event => {
            var data = JSON.parse(event.data);
        alert(data.status);
        if (data.status === 'exists') {
            $('.respondReceived').val(1);
            $('.counterForScan').addClass("hidden");
        }
    })
    }

    function agencyLogin(){
        var url='http://localhost:8083/dofps/loginMain';
        alert(url);
        $('#loadMainPage').load(url);
    }
</script>