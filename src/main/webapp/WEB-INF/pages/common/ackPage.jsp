<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${acknowledgement_message.length()>0}">
    <div class="alert alert-success alert-dismissible fade show">
        <h5>${acknowledgement_message}</h5>
       <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
</c:if>
<c:if test="${acknowledgement_danger.length()>0}">
    <div class="alert alert-danger alert-dismissible fade show">
        <h5>${acknowledgement_danger}</h5>
        <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
</c:if>
