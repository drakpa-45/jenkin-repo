<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<%--<jsp:include page="/WEB-INF/pages/protocol/common/rpIFrame.jsp"/>--%>
<%--<%UserSessionDetailDTO userSessionDetailDTO =(UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);%>--%>
<%
    System.out.println("############################ INSIDE THE LAYOUT %%%%%%%%%%%");
    String userName = "";
    String cid = "";
    if (session.getAttribute("userdetail") != null) {
        UserRolePrivilegeDTO userBean = (UserRolePrivilegeDTO) request.getSession().getAttribute("userdetail");
        String LocationId = "";
        userName = userBean.getFullName();
        cid = userBean.getCid();
        System.out.println("=== current user is : " + userName);
    }
%>
<%--<%if(cid.equalsIgnoreCase("") || cid.equalsIgnoreCase("null")){%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<div class="container">
    <div class="alert alert-danger alert-dismissible">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Session Time Out!</strong> Login via NDI App to avail Rural Timber Service. <a href="http://brtp.citizenservices.gov.bt/">Click Here</a>
    </div>
</div>
<%}else{%>--%>
<jsp:include page="inc/headerCitizen.jsp"></jsp:include>

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top:20px;"id="mainLayout">

            <section class="content">
               <%-- <div class="row mb-3">
                   <div class="col-xl-6 col-lg-6">
                        <div class="card card-inverse card-success" style="background-color: #ee9e64">
                            <h2 class="text-center">Vission</h2>
                            <small>“Sustaining and conserving a healthy forest ecosystem for a progressive Bhutan.”</small>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6">
                        <div class="card card-inverse card-danger" style="background-color: #ee9e64">
                            <h2 class="text-center">Mission</h2>
                            <small> “To conserve and manage Bhutan’s forest biological resources to ensure socio- economic and
                                environmental wellbeing, with a minimum of 60% of the land under forest cover for all times to
                                come.”</small>
                        </div>
                    </div>
                </div>--%>
                <section id="team" class="pb-5">
                    <div class="container">
                        <span class="text-danger" style="font-size: larger"><strong><i class="zmdi zmdi-info-outline mr-3"></i>NOTICE :</strong> Rural Timber Permit system is under development and currently in testing phase.
                            Data/application submitted by any client is considered as test data only !!!</span>
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 figure">
                            <!-- ======= Intro Section ======= -->
                            <%--<figure>--%>
                                <div class="effect slide">
                                    <%--<img src="<c:url value="/resources/images/Gedu.jpg"/>" alt="card image">--%>
                                    <h2>Vission</h2>
                                    <h4>“Sustaining and conserving a healthy forest ecosystem for a progressive Bhutan.”</h4>
                                </div>
                                <div class="effect slide">
                                    <%--<img src="<c:url value="/resources/images/tiger.jpg"/>" alt="card image">--%>
                                    <h2>Mission</h2>
                                    <h4> “To conserve and manage Bhutan’s forest biological resources to ensure socio- economic and
                                        environmental wellbeing, <br> with a minimum of 60% of the land under forest cover for all times to
                                        come.”</h4>
                                </div>
                            <%--</figure>--%>
                            <script type="text/javascript">
                                var index=0;
                                show();

                                function show(){
                                    var slides=document.getElementsByClassName("slide");
                                    for(i=0;i<slides.length;i++){
                                        slides[i].style.display="none";
                                    }
                                    index++;
                                    if(index>slides.length){
                                        index=1;
                                    }
                                    slides[index-1].style.display="block";
                                    setTimeout(show,6000);
                                }
                            </script>

                            <style>
                                .figure{
                                    width:100%;
                                    height:150px;
                                    position:relative;
                                    margin: 10px;
                                    padding:30px;
                                    border: 2px solid green;
                                    border-radius: 10px;
                                    box-shadow: 1px 2px 10px 10px white;
                                }

                                .figure img{
                                    width:100%;
                                    height:350px;
                                }

                                .effect{
                                    animation-name: effect;
                                    animation-duration: 3s;
                                }

                                @keyframes effect {
                                    from{
                                        opacity: 0.5;
                                    }
                                    to{
                                        opacity: 1;
                                    }
                                }

                                .figure h2{
                                    text-align: center;
                                    font-family: Comic Sans MS;
                                    color: #db6008;
                                    position: absolute;
                                    top:15%;
                                    width:100%;
                                }

                                .figure h4{
                                    text-align: left;
                                    font-family: Comic Sans MS;
                                    color:darkgreen;
                                    position: absolute;
                                    top:20%;
                                    width:100%;
                                }
                            </style>
                            <!-- End Intro Section -->
                        </div>
                        <h5 class="section-title h1">OUR SERVICES</h5>
                        <div class="row">
                            <!-- Team member -->
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="image-flip" >
                                    <div class="mainflip flip-0">
                                        <div class="frontside">
                                            <div class="card">
                                                <div class="card-body text-center">
                                                    <p><img class="img-fluid" src="<c:url value="/resources/images/newCons.jpg"/>" alt="card image"></p>
                                                    <h4 class="card-title">NEW CONSTRUCTION</h4>
                                                    <p class="card-text">Subsidized Rural Timber is alloted for new rural house construction.</p>
                                                    <a href="https://www.fiverr.com/share/qb8D02" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="backside">
                                            <div class="card">
                                                <div class="card-body text-center mt-4">
                                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/public/initiate/citizenDashboard?cons=n" target="_self">NEW CONSTRUCTION</a></h4>
                                                    <p class="card-text">Subsidized Rural Timber is alloted for new rural house construction. The applicant should be a head of household. Applicant
                                                        should not have received subsidized rural timber in last 25 years.</p>
                                                    <%-- <ul class="list-inline">
                                                         <li class="list-inline-item">
                                                             <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                 <i class="fa fa-facebook"></i>
                                                             </a>
                                                         </li>
                                                         <li class="list-inline-item">
                                                             <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                 <i class="fa fa-twitter"></i>
                                                             </a>
                                                         </li>
                                                         <li class="list-inline-item">
                                                             <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                 <i class="fa fa-skype"></i>
                                                             </a>
                                                         </li>
                                                         <li class="list-inline-item">
                                                             <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                 <i class="fa fa-google"></i>
                                                             </a>
                                                         </li>
                                                     </ul>--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ./Team member -->
                            <!-- Team member -->
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                                    <div class="mainflip">
                                        <div class="frontside">
                                            <div class="card">
                                                <div class="card-body text-center">
                                                    <p><img class="img-fluid" src="<c:url value="/resources/images/renov.jpg"/>" alt="card image"></p>
                                                    <h4 class="card-title">RENOVATION OF RURAL HOUSES</h4>
                                                    <p class="card-text">Subsidized Rural Timber is alloted for renovation and repair of the house.</p>
                                                    <a href="https://www.fiverr.com/share/qb8D02" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="backside">
                                            <div class="card">
                                                <div class="card-body text-center mt-4">
                                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/public/initiate/citizenDashboard?cons=r">RENOVATION OF RURAL HOUSES</a></h4>
                                                    <p class="card-text">Subsidized Rural Timber is alloted for renovation and repair of the house. The applicant should be a head of household.
                                                        The Applicant should not have received Subsidized Rural Timber in last 12 Years.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ./Team member -->
                            <!-- Team member -->
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                                    <div class="mainflip">
                                        <div class="frontside">
                                            <div class="card">
                                                <div class="card-body text-center">
                                                    <p><img class="img-fluid" src="<c:url value="/resources/images/other.jpg"/>" alt="card image"></p>
                                                    <h4 class="card-title">OTHER CONSTRUCTIONS</h4>
                                                    <p class="card-text">Subsidized Rural Timber is alloted for Other Construction.</p>
                                                    <a href="https://www.fiverr.com/share/qb8D02" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="backside">
                                            <div class="card">
                                                <div class="card-body text-center mt-4">
                                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/public/initiate/citizenDashboard?cons=o">OTHER CONSTRUCTIONS</a></h4>
                                                    <p class="card-text">Subsidized Rural Timber is alloted for Other Construction. The applicant should be a head of household. The Applicant should not have received
                                                        Subsidized Rural Timber in last 5 Years</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <!-- Team member -->
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="image-flip" >
                                    <div class="mainflip flip-0">
                                        <div class="frontside">
                                            <div class="card">
                                                <div class="card-body text-center">
                                                    <p><img class="img-fluid" src="<c:url value="/resources/images/forestProd.jpg"/>" alt="card image"></p>
                                                    <h4 class="card-title">Forest Produce from Private Land</h4>
                                                    <p class="card-text">Documents required for removal of forest produce from private registered land</p>
                                                    <a href="https://www.fiverr.com/share/qb8D02" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="backside">
                                            <div class="card">
                                                <div class="card-body text-center mt-4">
                                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/public/initiate/privateLandPermit" target="_self">Forest Produce from Private Land</a></h4>
                                                    <p class="card-text">Documents required for removal of forest produce from private registered land</p>
                                                    <%--<ul class="list-inline">
                                                        <li class="list-inline-item">
                                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                <i class="fa fa-facebook"></i>
                                                            </a>
                                                        </li>
                                                        <li class="list-inline-item">
                                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                <i class="fa fa-twitter"></i>
                                                            </a>
                                                        </li>
                                                        <li class="list-inline-item">
                                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                <i class="fa fa-skype"></i>
                                                            </a>
                                                        </li>
                                                        <li class="list-inline-item">
                                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                                <i class="fa fa-google"></i>
                                                            </a>
                                                        </li>
                                                    </ul>--%>
                                                    <ul>
                                                        <li>A copy of Cadastral Map of the land/plot where removal of forest produce is proposed;</li>
                                                        <li>A copy of Lagthram; and</li>
                                                        <li>A disposal plan of forest produce</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ./Team member -->
                            <!-- Team member -->
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                                    <div class="mainflip">
                                        <div class="frontside">
                                            <div class="card">
                                                <div class="card-body text-center">
                                                    <p><img class="img-fluid" src="<c:url value="/resources/images/poles.jfif"/>" alt="card image"></p>
                                                    <h4 class="card-title">Firewood, Fencing Post & Flag Poles</h4>
                                                    <p class="card-text">Subsidized Firewood, Fencing Post & Flag Poles.</p>
                                                    <a href="https://www.fiverr.com/share/qb8D02" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="backside">
                                            <div class="card">
                                                <div class="card-body text-center mt-4">
                                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/public/initiate/firewoodAndFencingPoles?cons_desc=WP">Firewood, Fencing Post & Flag Poles</a></h4>
                                                    <p class="card-text">The applicant should be a head of household.
                                                        <ul>
                                                            <li>Firewood - 1/2 truck loads once a year/household</li>
                                                            <li>Fencing Post - 50 nos. once a year/household</li>
                                                            <li>Flag Poles - 29 nos. at 50% royalty,30-108 nos. at 100% royalty  endorsement by gup based on the needs of the applicant</li>
                                                            <li>Bamboo  - Small size 1000 nos.
                                                                Big Size 100 nos.
                                                                Once a year/ household
                                                            </li>
                                                        </ul>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ./Team member -->
                        </div>
                    </div>
                </section>
                <%--<div class="row MT-4">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Important Links</h4>
                                <div>
                                    <ol>
                                        <li><a class="nav-link" href="#">www.dofps.gov.bt</a></li>
                                        <li><a class="nav-link" href="https://www.citizenservices.gov.bt/">Citizen Services</a></li>
                                    </ol>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>--%>
            </section>
        <%--<sitemesh:write property='body'/>--%>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
<%--<%}%>--%>
</body>
</html>