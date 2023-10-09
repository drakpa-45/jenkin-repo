<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="<c:url value='/resources/framework/css/app.min.css'/>"/>
<link rel="stylesheet" href="resources/assets/css/nprogress.css"/>
<!------ Include the above in your HEAD tag ---------->
<script src="https://cdn.jsdelivr.net/npm/qrious@4.0.2/dist/qrious.min.js"></script>
<script src="resources/assets/js/nprogress.js"></script>
<script src="resources/assets/js/script.js"></script>
<!------ Include the above in your HEAD tag ---------->

<style>
    #home_quicklinks {
        padding: 20px 0;
        text-align: center;
    }
    a.quicklink.link1 {
        background: #fc6719;
    }
    a.quicklink {
        display: inline-block;
        width: 302px;
        height: 173px;
        position: relative;
        margin: 90px 30px;
    }
    a.quicklink .ql_caption {
        display: block;
        height: 100%;
        width: 100%;
        position: relative;
    }
    .outer {
        display: table;
        position: relative;
        vertical-align: middle;
        text-align: center;
        height: 100%;
        width: 100%;
        border-spacing: 0px;
        padding: 0px;
    }
    .inner {
        display: table-cell;
        position: relative;
        vertical-align: middle;
        text-align: center;
        height: 100%;
        width: 100%;
        border-spacing: 0px;
        padding: 0px;
    }

    a.quicklink .ql_caption h2 {
        margin: 0px;
        padding: 0px;
        text-transform: uppercase;
        line-height: 1.46em;
        color: #fff;
    }

    a.quicklink.link1 .ql_top {
        border-bottom-color: #fc6719;
    }

    a.quicklink.link1 .ql_bottom {
        border-top-color: #fc6719;
    }

    .ql_top {
        bottom: 173px;
        border-bottom: 89px solid #ccc;
    }

    .ql_bottom {
        top: 173px;
        border-top: 89px solid #ccc;
    }

    .ql_top, .ql_bottom {
        position: absolute;
        left: 0px;
        width: 0px;
        border-left: 151px solid transparent;
        border-right: 151px solid transparent;
    }

    a.quicklink.link2 {
        background: #fcf4e7;
    }

    a.quicklink.link2 .ql_top {
        border-bottom-color: #fcf4e7;
    }

    a.quicklink.link2 .ql_bottom {
        border-top-color: #fcf4e7;
    }

    a.quicklink.link3 .ql_top {
        border-bottom-color: #bcbdc0;
    }

    a.quicklink.link3 .ql_bottom {
        border-top-color: #bcbdc0;
    }

    a.quicklink.link3 {
        background: #bcbdc0;
    }

    a.quicklink {
        font-size: 36px;
        font-weight: 500;
        text-decoration: none;
    }

    .hexagon {
        position: relative;
        width: 300px;
        height: 173.21px;
        margin: 86.60px 0;
        background-image: url(http://csshexagon.com/img/meow.jpg);
        background-size: auto 334.8632px;
        background-position: center;
        box-shadow: 0 0 20px rgba(0, 128, 192, 0.6);
        border-left: solid 5px #4a401c;
        border-right: solid 5px #4a401c;
    }

    .hexTop,
    .hexBottom {
        position: absolute;
        z-index: 1;
        width: 212.13px;
        height: 212.13px;
        overflow: hidden;
        -webkit-transform: scaleY(0.5774) rotate(-45deg);
        -ms-transform: scaleY(0.5774) rotate(-45deg);
        transform: scaleY(0.5774) rotate(-45deg);
        background: inherit;
        left: 38.93px;
        box-shadow: 0 0 20px rgba(0, 128, 192, 0.6);
    }

    /*counter transform the bg image on the caps*/
    .hexTop:after,
    .hexBottom:after {
        content: "";
        position: absolute;
        width: 290.0000px;
        height: 167.4315780649915px;
        -webkit-transform: rotate(45deg) scaleY(1.7321) translateY(-83.7158px);
        -ms-transform: rotate(45deg) scaleY(1.7321) translateY(-83.7158px);
        transform: rotate(45deg) scaleY(1.7321) translateY(-83.7158px);
        -webkit-transform-origin: 0 0;
        -ms-transform-origin: 0 0;
        transform-origin: 0 0;
        background: inherit;
    }

    .hexTop {
        top: -106.0660px;
        border-top: solid 7.0711px #4a401c;
        border-right: solid 7.0711px #4a401c;
    }

    .hexTop:after {
        background-position: center top;
    }

    .hexBottom {
        bottom: -106.0660px;
        border-bottom: solid 7.0711px #4a401c;
        border-left: solid 7.0711px #4a401c;
    }

    .hexBottom:after {
        background-position: center bottom;
    }

    .hexagon:after {
        content: "";
        position: absolute;
        top: 2.8868px;
        left: 0;
        width: 290.0000px;
        height: 167.4316px;
        z-index: 2;
        background: inherit;
    }

</style>

<div id="home_quicklinks">
    <a class="quicklink link1" href="#">
        <span class="ql_caption">
            <span class="outer">
                <span class="inner">
                    <h2>SSO</h2>
                </span>
            </span>
        </span>
        <span class="ql_top"></span>
        <span class="ql_bottom"></span>
    </a>
    <a class="quicklink link3" href="#" >
        <span class="ql_caption">
            <span class="outer" onclick="passwordLessLogin()">
                <span class="inner">
                    <h2>NDI</h2>
                </span>
            </span>
        </span>
        <span class="ql_top"></span>
        <span class="ql_bottom"></span>
    </a>
    <div class="clear"></div>
</div>
<div class="container">
<div class="form-group row">
    <div class="col-lg-5 col-md-5 col-sm-5"></div>
    <div class="col-lg-5 col-md-5 col-sm-5"><canvas id="qrcode"></canvas></div>
    <div class="col-lg-2 col-md-2 col-sm-2"></div>
</div>
</div>


<%--<div class="hexagon">
    <div class="hexTop"></div>
    <div class="hexBottom"></div>
</div>--%>

<%--<script defer type="module">--%>
    <%--import {--%>
        <%--connect,--%>
        <%--StringCodec,--%>
      <%--} from "https://cdn.jsdelivr.net/npm/nats.ws@1.10.0/esm/nats.js";--%>

<%--</script>--%>


<script>
    function passwordLessLogin() {
        /* $('#myModal').modal();
         $('.modal-header h3').html('Processing...');*/
        $.ajax({
            url:  '${pageContext.request.contextPath}/passwordLessLogin', type: "GET", async: true, success: function (res) {
                // alert(res);
                $('#socialBx').hide();
                $('#qrcodeDisplay').show();
                var canvas = document.getElementById("qrcode");
                var qr = new QRious({
                    element: canvas,
                    value: res.proofRequestURL,
                    size: 260
                });

                debugger;

                // Create an Image element for the overlay image
                var overlayImg = new Image();
                overlayImg.src = '<img class="img-fluid" src="<c:url value="/resources/images/NDIlogobg.png"/>" alt="card image">'; // Replace with the path to your overlay image

                // Wait for the overlay image to load
                overlayImg.onload = function () {
                    // Get the canvas context
                    var ctx = canvas.getContext('2d');

                    // Draw the QR code on the canvas
                    qr.draw();

                    // Draw the overlay image on top of the QR code
                    ctx.drawImage(overlayImg, 50, 50, 160, 160);
                };


                if(res.deepLinkURL!=null) {
                    $("#deeplink").prop("href", "" + res.deepLinkURL + "");
                }

                $('.counterForScan').removeClass("hidden");
                $('#successMsge').removeClass("hidden");
                $('#spinner').addClass("hidden");
                registerEvent(res.proofRequestThreadId);
            }
        });
    }
    // Import required dependencies

    function getProofRequest() {
        var data = JSON.stringify({
            proofName: "verifyUser",
            proofAttributes: [
                {
                    name: "Full Name",
                    restrictions: [{schema_id: "2acnD7masG23MBd7nn4xna:2:Foundational ID:1.0.1"}]
                },
                {
                    name: "ID Number",
                    restrictions: [{schema_id: "2acnD7masG23MBd7nn4xna:2:Foundational ID:1.0.1"}]
                },
                {
                    name: "Mobile Number",
                    restrictions: [],
                    self_attest_allowed: true
                },
            ]
        });

        $.ajax({
            type: "POST",
           // url: 'http://119.2.117.161:3004/proof-request',
            url: 'http://devclient.bhutanndi.com/verifier/proof-request',
            headers: {"Content-Type": "application/json"},
            data: data,
            cache: false,
            success: function (response) {
                debugger;
                console.log(JSON.stringify(response.data));
                // Get the QR code canvas element
                var canvas = document.getElementById("qrcode");

                // Generate QR code
                // function generateQRCode() {
                var qr = new QRious({
                    element: canvas,
                    value: response.data.proofRequestURL,
                    size: 160
                });
                // Remove QR code after 3 minutes (adjust as needed)
                setTimeout(function() {
                    canvas.parentNode.removeChild(canvas);
                }, 50000);// 3 minutes = 180,000 milliseconds

                // check if QR code generated is scanned or not
                natsListener(response.data.proofRequestThreadId,response.data.deepLinkURL,response.data.proofRequestURL);
               // PasswordLessLogin(props)
              //  resolve(response);
            }
        });
    }

    function natsListener(threadId,deepLinkURL,proofRequestURL) {

        const WebSocket = window.WebSocket; // Use the WebSocket object from the browser's global scope
        const socket = new WebSocket("ws://18.142.249.134:443");
        //const socket = new WebSocket("ws://119.2.117.161:443");

        socket.onopen = function() {
            socket.send("SUB ${threadId}\r\n");
            const sc = new TextDecoder("utf-8");

            socket.binaryType = 'arraybuffer'; // Set the binary type to 'arraybuffer'

            socket.onmessage = function(event) {
                const message = new Uint8Array(event.data); // Convert the received data to Uint8Array
                // Process the received message
                const [subject, data] = sc.decode(message).split(" ");
                console.log("[${subject}]${pad} #${s1.getProcessed()} - ${subject}${data ? ' ' + data : ''}");
                debugger;
                const jsonData = JSON.parse(data);
                /*if (jsonData && jsonData.data && jsonData.data.verification_result === 'ProofValidated') {
                    socket.close();
                    window.location.href = "${pageContext.request.contextPath}/loginDashboard";
                }*/

                console.log("===================== Json data response ====================" + jsonData );

                if (jsonData) {
                    socket.close();
                    setTimeout(function() {
                        window.location.href = "${pageContext.request.contextPath}/loginDashboard";
                    }, 30000);// 3 minutes = 180,000 milliseconds
                }
            };
        };

        socket.onmessage = function (event) {
        // Handle incoming WebSocket messages if needed
            console.log("=============== Received message222222:", event.data + "================");
        };
    }

</script>