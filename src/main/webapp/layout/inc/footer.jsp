<%--
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
--%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />

<!------ Include the above in your HEAD tag ---------->

<style>
    .ft-copyright {
        background-color: #000a1f;
        border-top: 1px solid #121d6d;
        display: flex;
    }

    .pb-2,
    .py-2 {
        padding-bottom: .5rem !important;
        padding-top: .5rem !important;
    }

    .footer-top {
        background-color: #385543;
        border-bottom: 1px solid rgba(198, 203, 219, .64);
    }
    .container-fluid{
        bottom: 0;
    }
    .text-center {
        text-align: center !important;
    }

    h4.ft-text-title {
        color: #fff;
        margin: 20px 0 10px;
        font-weight: 700;
        text-align: center;
    }

    h4 {
        display: block;
        -webkit-margin-before: 1.33em;
        -webkit-margin-after: 1.33em;
        -webkit-margin-start: 0px;
        -webkit-margin-end: 0px;
        font-weight: bold;
        font-size: 1.3em;
    }

    h6.ft-desp {
        color: #FFF;
        padding: 2px;
    }

    h6 {
        font-size: 15px;
    }
    h1 {
        display: flex;
        align-items: center;
        justify-content: center;
    }
    h1.heading-title a{
        color: #012061 !important;
        text-decoration: none;
    }
    h1 {
        font-size: 22px;
        font-weight: 800;
        line-height: 1.4;
        color: #000;
        letter-spacing: -0.04em;
        text-transform: uppercase;
        position: relative;
        padding-bottom: 10px;
    }
    a.contact,
    a.mail {
        border: 1px solid yellow;
        border-bottom-right-radius: 15px;
        border-bottom-left-radius: 15px;
        color: #fff;
        padding: 5px;
        font-size: 15px;
        text-decoration: none;
    }

    a.contact:hover,
    a.mail:hover {
        background: #900;
    }

    i.fa-phone,
    i.fa-envelope-o {
        padding: 3px;
    }

    i.fa-envelope-o {
        padding: 4px;
    }

    .border-left {
        border-left: dotted #ddd 1px;
    }

    .pspt-dtls {
        margin-top: 20px !important;
    }

    .pspt-dtls a {
        margin: 8px !important;
    }

    a.about,
    a.team,
    a.advertise {
        padding: 8px;
        border: 1px yellow solid;
        padding-left: 15px;
        padding-right: 15px;
        border-radius: 5px;
        color: #FFF;
        text-decoration: none;
        font-size: 15px;
    }

    a.about:hover,
    a.team:hover,
    a.advertise:hover {
        background: #900;
    }

    footer p {
        color: #fff;
        margin-bottom: 10px;
    }

    .text-pp-crt,
    .text-pp-crt-rg {
        color: #FFFFFF;
    }

    .developer {
        text-align: center;
    }
    p.member{
        color:#FFF;
    }
    i.develop {
        border: 1px red dotted;
    }

    .developer b {
        color: red;
    }


    .footer-widget .section-heading {
        margin-bottom: 35px; }

    .footer-widget h3 {
        font-size: 24px;
        color: #fff;
        position: relative;
        margin-bottom: 15px;
        max-width: -webkit-fit-content;
        max-width: -moz-fit-content;
        max-width: fit-content; }

    .footer-widget ul {
        width: 50%;
        float: left;
        list-style: none;
        margin: 0;
        padding: 0; }

    .footer-widget li {
        margin-bottom: 18px; }

    .footer-widget p {
        margin-bottom: 27px; }

    .footer-widget a {
        color: #878787;
        -webkit-transition: all 0.3s;
        -o-transition: all 0.3s;
        transition: all 0.3s; }
    .footer-widget a:hover {
        color: #007bff; }

    .footer-widget:after {
        content: "";
        display: block;
        clear: both;
    }

    .animate-border {
        position: relative;
        display: block;
        width: 115px;
        height: 3px;
        background: #007bff; }

    .animate-border:after {
        position: absolute;
        content: "";
        width: 35px;
        height: 3px;
        left: 0;
        bottom: 0;
        border-left: 10px solid #fff;
        border-right: 10px solid #fff;
        -webkit-animation: animborder 2s linear infinite;
        animation: animborder 2s linear infinite; }

    @-webkit-keyframes animborder {
        0% {
            -webkit-transform: translateX(0px);
            transform: translateX(0px); }
        100% {
            -webkit-transform: translateX(113px);
            transform: translateX(113px); } }

    @keyframes animborder {
        0% {
            -webkit-transform: translateX(0px);
            transform: translateX(0px); }
        100% {
            -webkit-transform: translateX(113px);
            transform: translateX(113px); } }


    .animate-border.border-white:after {
        border-color: #fff; }

    .animate-border.border-yellow:after {
        border-color: #F5B02E; }

    .animate-border.border-orange:after {
        border-right-color: #007bff;
        border-left-color: #007bff; }

    .animate-border.border-ash:after {
        border-right-color: #EEF0EF;
        border-left-color: #EEF0EF; }

    .animate-border.border-offwhite:after {
        border-right-color: #F7F9F8;
        border-left-color: #F7F9F8; }


    .back-to-top {
        position: relative;
        z-index: 2; }
    .back-to-top .btn-dark {
        width: 35px;
        height: 35px;
        border-radius: 50%;
        padding: 0;
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: #2e2e2e;
        border-color: #2e2e2e;
        display: none;
        z-index: 999;
        -webkit-transition: all 0.3s linear;
        -o-transition: all 0.3s linear;
        transition: all 0.3s linear; }
    .back-to-top .btn-dark:hover {
        cursor: pointer;
        background: #FA6742;
        border-color: #FA6742; }
</style>
<body>
<div class="container-fluid">
    <div class="row footer-top">
        <div class="col-sm-2 text-center">
            <figure>
                <section>
                    <iframe width="100%" height="200" id="gmap_canvas" src="https://maps.google.com/maps?q=DITT&t=&z=10&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
                   <%-- <div class="mapouter">
                        <div class="gmap_canvas">
                            <iframe width="770" height="510" id="gmap_canvas" src="https://maps.google.com/maps?q=DITT&t=&z=10&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
                            <a href="https://2yu.co">2yu</a>
                            <br>
                            <style>.mapouter{position:relative;text-align:right;height:510px;width:770px;}</style>
                            <a href="https://embedgooglemap.2yu.co">html embed google map</a>
                            <style>.gmap_canvas {overflow:hidden;background:none!important;height:510px;width:770px;}</style>
                        </div>
                    </div>--%>
                </section>
            </figure>
        </div>
        <div class="col-sm-3 text-center">
            <h4 class="ft-text-title">Address</h4>
            <h6 class="ft-desp">Gov-Tech Bhutan<br> Thori
                Lam,Upper Chubachu,<br> Thimphu, Bhutan.
            </h6>
            <h4 class="details">
                <a class="contact" href="tel:+977-1-4107223">
                    <i class="fa fa-phone" aria-hidden="true"></i> Tel: +975-02-323215
                </a>
            </h4>
           <%-- <div class="column  col-lg-3 col-md-3 col-sm-3"><h2 class="text-white">Address</h2>
                <p id="address">Department of IT and Telecom,<br> Ministry of Information and Communications,<br> Thori
                    Lam,Upper Chubachu,<br> Thimphu, Bhutan.<br> Tel: +975-02-323215<br> Fax: +975-02-328440<br> Post box :482 </p></div>--%>
        </div>
        <div class="col-sm-3 text-center border-left">
            <%--<h4 class="ft-text-title">Our Team</h4>--%>
            <div class="address-member mt-5">
                <div id="rgob"><img src="${pageContext.request.contextPath}/resources/images/India-Bhutan.jpg" align="center" style="width:280px;"/></div>
            </div>
        </div>
        <div class="col-sm-4 col-xs-12 text-center border-left">
            <div class="footer-widget footer-left-widget">
                <div class="section-heading">
                    <h3>Useful Links</h3>
                    <span class="animate-border border-black"></span>
                </div>
                <ul>
                    <li><a href="http://www.btcirt.bt" title="">Bhutan Computer Incident Response Team</a></li>
                    <li><a href="http://scs.rbp.gov.bt/" title="">Citizen Security Clearance Portal</a></li>
                    <li><a href="https://www.citizenservices.gov.bt/home" title="">Citizen Services Portal</a></li>
                </ul>
                <ul>
                    <li><a href="http://www.moic.gov.bt" title="">Ministry of Information and Communication</a></li>
                    <li><a href="http://www.lesspaper.gov.bt" title="">Learning site for Government Email</a></li>
                    <li><a href="https://www.gov.bt/covid19/" title="">COVID-19 Information Link</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row ft-copyright pt-2 pb-2" style="padding-left: 25px;">

        <div class="col-sm-4 text-pp-crt-rg">Developed by: <b><a href="https://yangkhor.com" title="YPL" target="_blank">Yangkhor Private Limited</a></b>, @2023 </div>
        <div class="col-sm-4 text-pp-crt">  &copy; 2023 G2C Project Office | OFFICE OF THE PRIME MINISTER. All Rights Reserved.</div>
       <%-- <div class="col-sm-4 developer">
            <a href="https://topline-tech.com" target="_blank" class="text-pp-crt">By : <b>T</b>op<b>L</b>ine</a>
        </div>--%>
    </div>
</div>
<%--<script src="https://dunggramer.github.io/disable-devtool/disable-devtool.min.js" defer></script>
<script src="https://cdn.staticaly.com/gh/DungGramer/disable-devtool/cbf447f/disable-devtool.min.js" defer></script>
<script src="https://gitcdn.xyz/repo/DungGramer/disable-devtool/master/disable-devtool.min.js" defer></script>--%>
</body>
<%--<script>
    // Disable right-click
    document.addEventListener('contextmenu', (e) => e.preventDefault());

    function ctrlShiftKey(e, keyCode) {
        return e.ctrlKey && e.shiftKey && e.keyCode === keyCode.charCodeAt(0);
    }

    document.onkeydown = (e) => {
        // Disable F12, Ctrl + Shift + I, Ctrl + Shift + J, Ctrl + U
        if (
                event.keyCode === 123 ||
                ctrlShiftKey(e, 'I') ||
                ctrlShiftKey(e, 'J') ||
                ctrlShiftKey(e, 'C') ||
                (e.ctrlKey && e.keyCode === 'U'.charCodeAt(0))
        )
            return false;
    };
</script>--%>
