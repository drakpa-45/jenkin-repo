<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 1/15/2020
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Department of Forest and Park Services</title>
</head>
<body>
<div class="card card-primary" style="margin-top:25px;">
    <div class="card-body">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                <%--<h5>Guidance:</h5> <label> <ol> <c:forEach var="guide" items="${ServiceGuidelinesList}"> <li> ${guide} </li> </c:forEach> </ol> </label>--%>
            </div>
            <div class="col-md-4 col-sm-4-col-lg-4 col-xs-12">
                <h5>For official only</h5>
                <hr/>
                <c:if test="${not empty error}">
                    <div style="color:red; font-weight: bold; margin: 30px 0px;">${error}</div>
                </c:if>
                <c:if test="${not empty expired}">
                    <div style="color:blue; font-weight: bold; margin: 30px 0px;">${expired}</div>
                </c:if>
                <form name='login' id="loginformId" action="<c:url value="/loginMain"/>" method='POST'>
                    <%-- <form name='login' id="loginformId" action="<c:url value="/Login"/>" method='POST'>--%>
                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
                    <div class="form-group">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                            <input type="text" placeholder="User Name" name="userId" id="userId" class="form-control">
                            <span class="text-danger" id="user_Err"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                            <input type="text" placeholder="Password" name="password" id="password"
                                   class="form-control">
                            <span class="text-danger" id="pass_Err"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                            <button type="submit" class="btn btn-primary"><i class="fa fa-user"></i>Login</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function systemlogin() {
        if (validate()) {
            $("#loginformId").submit();
        }
    }
    function validate() {
        var return_value = true;
        if ($('#username').val() == "") {
            $('#user_Err').html('Please provide user name');
            return_value = false;
        }
        if ($('#password').val() == "") {
            $('#pass_Err').html('Please provide password');
            return_value = false;
        }
        return return_value;
    }
    $('#username').click(function () {
        $('#user_Err').html('');
    });
    $('#password').click(function () {
        $('#pass_Err').html('');
    });
    function redirect(param) {
        var url = '${pageContext.request.contextPath}/redirectToPage?param=' + param;
        window.location = url;
    }
</script>
</body>
</html>
