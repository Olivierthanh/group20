<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/15/2019
  Time: 1:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>${wallet.walletName}</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" />">
    <!-- Cdn css -->
    <link rel="stylesheet" href="//cdn.materialdesignicons.com/3.5.95/css/materialdesignicons.min.css">
    <!-- SweetAlert2 -->
    <link href="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.css" />" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="<c:url value="resources/main/css/custom.css" />">
    <link href="<c:url value="resources/main/css/style.css" />" rel="stylesheet">
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

    <!-- Conent body -->
    <div class="content-body">
        <div class="container-fluid">
            <!-- <div class="row page-titles">
            <div class="col p-0">
                <h4>Hello, <span>Welcome here</span></h4>
            </div>
            <div class="col p-0">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">App</a>
                    </li>
                    <li class="breadcrumb-item active">Profile</li>
                </ol>
            </div>
        </div> -->
            <div class="row">
                <div class="col-md-5">
                    <!-- begin overview -->
                    <jsp:include page="../component/view_wallet/overview.jsp" />
                    <!-- End overview -->

                    <!-- Wallet Detail -->
                    <jsp:include page="../component/view_wallet/wallet_detail.jsp" />
                    <!-- End Wallet Detail -->

                    <!-- Begin view transaction -->
                    <jsp:include page="../component/view_wallet/view_transaction.jsp" />
                    <!-- End view transaction -->
                </div>

                <div class="col-md-7">
                    <!-- Chart -->
                    <jsp:include page="../component/view_wallet/static_chart.jsp" />
                    <!-- End chart -->
                </div>
            </div>
        </div>
        <!-- #/ container -->
    </div>
    <!-- End content body -->

    <!-- Footer -->
    <jsp:include page="../component/footer.jsp" />
    <!-- End footer -->

</div>
<!-- End main wrapper -->

<!-- Script -->
<script src="<c:url value="resources/assets/plugins/common/common.min.js" />"></script>
<script src="<c:url value="resources/assets/plugins/jquery-xpath/jquery.xpath.min.js" />"></script>
<script src="<c:url value="resources/main/js/custom.min.js" />"></script>
<script src="<c:url value="resources/main/js/settings.js" />"></script>
<script src="<c:url value="resources/main/js/gleek.js" />"></script>
<script src="<c:url value="resources/main/js/styleSwitcher.js" />"></script>
<!-- Sweetalert2 -->
<script src="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.js" />"></script>
<!-- Echart -->
<script src="<c:url value="resources/assets/plugins/echarts/echarts.min.js" />"></script>

<%-- Init chart --%>
<script src="<c:url value="resources/main/js/custom/chart_init/utils.js" />"></script>
<script src="<c:url value="resources/main/js/custom/chart_init/balance-chart.js" />"></script>
<script src="<c:url value="resources/main/js/custom/chart_init/income-pie-chart.js" />"></script>
<script src="<c:url value="resources/main/js/custom/chart_init/expense-pie-chart.js" />"></script>
<script src="<c:url value="resources/main/js/custom/chart_init/ajax-init-chart.js" />"></script>

<%-- Pagination --%>
<script src="<c:url value="resources/main/js/custom/click/pagination_view_transaction_click.js" />"></script>

<%-- Submit event --%>
<script src="<c:url value="resources/main/js/custom/submit_form/delete_wallet_submit.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/delete_shared_user_submit.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/delete_transaction_submit.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/add_transaction_submit.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/add_shared_user_submit.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/add_wallet_submit.js" />"></script>

<%-- Category select --%>
<script src="<c:url value="resources/main/js/custom/click/category_select_click.js" />"></script>

<%-- On change event --%>
<script src="<c:url value="resources/main/js/custom/change/chart_by_yearmonth_on_change.js" />"></script>
<script src="<c:url value="resources/main/js/custom/change/transaction_by_date_on_change.js" />"></script>

<%-- Validate form --%>
<script src="<c:url value="resources/assets/plugins/jquery-validation/jquery.validate.min.js" /> "></script>
<script src="<c:url value="resources/main/js/custom/validate/add_transaction_validate.js" />"></script>
<script src="<c:url value="resources/main/js/custom/validate/add_shared_user_validate.js" />"></script>
<script src="<c:url value="resources/main/js/custom/validate/add_wallet_validate.js" />"></script>

<%-- Click --%>
<script src="<c:url value="resources/main/js/custom/click/transaction_row_click.js" />"></script>
</body>
</html>
