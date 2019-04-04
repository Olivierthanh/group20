<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/4/2019
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Wallet</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" />">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="<c:url value="resources/main/css/custom.css" />">
    <link href="<c:url value="resources/main/css/style.css" /> " rel="stylesheet">
</head>
<body>
<!-- Preloader -->
<jsp:include page="../component/preload.jsp" />
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

    <!-- Content body -->
    <div class="content-body">
        <div class="container-fluid">

        </div>
        <!-- #/ container -->
    </div>
    <!-- End content body -->

    <!-- Footer -->
    <jsp:include page="../component/footer.jsp" />
    <!-- End footer -->

    <!-- End main wrapper -->

    <!-- Script -->
    <script src="<c:url value="resources/assets/plugins/common/common.min.js" /> "></script>
    <script src="<c:url value="resources/main/js/custom.min.js" /> "></script>
    <script src="<c:url value="resources/main/js/settings.js" /> "></script>
    <script src="<c:url value="resources/main/js/gleek.js" /> "></script>
    <script src="<c:url value="resources/main/js/styleSwitcher.js" /> "></script>
    <!-- End Script -->
</div>

</body>
</html>
