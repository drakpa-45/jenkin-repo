<%@ page import="bt.gov.ditt.dofps.dto.Privilege" %>
<%@ page import="bt.gov.ditt.dofps.dto.Service" %>
<%@ page import="bt.gov.ditt.dofps.dto.UserRolePrivilegeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<%--<jsp:include page="/WEB-INF/pages/protocol/common/rpIFrame.jsp"/>--%>
<%--<%UserSessionDetailDTO userSessionDetailDTO =(UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);%>--%>
<jsp:include page="inc/headerAdmin.jsp"></jsp:include>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top:20px;" id="mainLayout">
    <sitemesh:write property='body'/>
</div>
</body>
<jsp:include page="inc/footer.jsp"></jsp:include>
</html>