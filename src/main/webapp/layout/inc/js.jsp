<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/jQuery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/popper.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/chosen.jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/dataTables.bootstrap4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/plugins/charts-c3/plugin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/plugins/input-mask/plugin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/jquery.base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/FileSaver/FileSaver.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/js-xlsx/xlsx.core.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF/jspdf.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF-AutoTable/jspdf.plugin.autotable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/js/nprogress.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/js/script.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/js/jquery.blockUI.js"/>"></script>
<script src="<c:url value='/resources/sweetalert/sweetalert.min.js' />"></script>
<script src="<c:url value="/resources/dofps.js"/>"></script>



<%--<script type="text/javascript" src="<c:url value="/resources/jquery/jQuery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/popper.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/chosen.jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/bootstrap4-toggle.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/additional-methods.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/bootstrap-validate.js"/>"></script>
&lt;%&ndash;datatable&ndash;%&gt;

<script type="text/javascript" src="<c:url value="/resources/dataTable/jquery.base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/FileSaver/FileSaver.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/dataTables.bootstrap4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/jspdf.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/html2canvas.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/js/dashboard.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/plugins/charts-c3/plugin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/plugins/input-mask/plugin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/js-xlsx/xlsx.core.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF/jspdf.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF-AutoTable/jspdf.plugin.autotable.js"/>"></script>

<link rel="stylesheet" href="https://jqueryvalidation.org/files/demo/site-demos.css">--%>

<script>

    $('.dropdown-menu a.dropdown-toggle').on('click', function (e) {
        if (!$(this).next().hasClass('show')) {
            $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
        }
        var $subMenu = $(this).next(".dropdown-menu");
        $subMenu.toggleClass('show');


        $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function (e) {
            $('.dropdown-submenu .show').removeClass("show");
        });


        return false;
    });
</script>

