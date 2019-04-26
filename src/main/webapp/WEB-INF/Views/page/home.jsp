<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/4/2019
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Wallet</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" />">
    <!-- Cdn css -->
    <!-- <link rel="stylesheet" href="//cdn.materialdesignicons.com/3.5.95/css/materialdesignicons.min.css"> -->
    <!-- SweetAlert2 -->
    <link href="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.css" />" rel="stylesheet">

    <%-- Table Data css--%>
    <link href="<c:url value="resources/assets/plugins/datatables/css/jquery.dataTables.min.css" />" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="<c:url value="resources/main/css/custom.css" />">
    <link href="<c:url value="resources/main/css/style.css" />" rel="stylesheet">
</head>

<body>
<!-- Preloader -->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10">
            </circle>
        </svg>
    </div>
</div>
<!-- End preloader -->

<!-- Main wrapper -->
<div id="main-wrapper" class="show menu-toggle">
    <!-- Nav header -->
    <jsp:include page="../component/nav_header.jsp" />
    <!-- End nav header -->

    <!-- Header -->
    <jsp:include page="../component/header.jsp" />
    <!-- End Header -->

    <!-- Menu -->
    <jsp:include page="../component/sidebar.jsp" />
    <!-- End menu -->

    <!-- Conent body -->
    <div class="content-body">
        <div class="container-fluid">
            <jsp:include page="../component/dashboard.jsp" />
        </div>
        <!-- #/ container -->
    </div>
    <!-- End content body -->

    <!-- Footer -->
    <jsp:include page="../component/footer.jsp" />
    <!-- End footer -->

    <!-- End main wrapper -->
</div>
<!-- Script -->
<script src="<c:url value="resources/assets/plugins/common/common.min.js" />"></script>
<script src="<c:url value="resources/main/js/custom.min.js" />"></script>
<script src="<c:url value="resources/main/js/settings.js" />"></script>
<script src="<c:url value="resources/main/js/gleek.js" />"></script>
<script src="<c:url value="resources/main/js/styleSwitcher.js" />"></script>

<%-- Utils --%>
<script src="<c:url value="resources/main/js/custom/chart_init/utils.js" />"></script>

<!-- SweetAlert2 -->
<script src="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.js" />"></script>

<%-- Table Data--%>
<script src="<c:url value="resources/assets/plugins/datatables/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="resources/main/js/custom/table/init_wallet_table.js" />"></script>

<%-- Form validate --%>
<script src="<c:url value="resources/assets/plugins/jquery-validation/jquery.validate.min.js" /> "></script>
<script src="<c:url value="resources/main/js/custom/validate/add_wallet_validate.js" />"></script>

<%-- Form submit --%>
<script src="<c:url value="resources/main/js/custom/submit_form/add_wallet_submit.js" />"></script>
<!-- End Script -->
</body>

</html>