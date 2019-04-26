<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/26/2019
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>503 - Server Unavailable</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" />">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="<c:url value="resources/main/css/custom.css" />">
    <link href="<c:url value="resources/main/css/style.css" />" rel="stylesheet">
</head>

<body>
<!-- Preloader -->
<jsp:include page="../../component/preload.jsp" />
<!-- End preloader -->

<div class="container h-100">
    <div class="row justify-content-between h-100">
        <div class="col-md-6 offset-md-3">
            <div class="error-content">
                <div class="card mb-0">
                    <div class="card-body text-center">
                        <!-- <div class="error-img ">
                            <img src="../../../assets/images/wallet-sm.png" alt="" class="img-fluid">
                        </div> -->
                        <h1 class="error-text text-primary">503</h1>
                        <h4 class="mt-4"><i class="fa fa-times-circle text-danger"></i> Service Unavailable</h4>
                        <p>Sorry,were under maintenance!</p>
                        <div class="mt-5 mb-5">
                            <div class="text-center mb-4 mt-4"><a href="<c:url value="/" />"
                                                                  class="btn btn-primary">Go to Homepage</a>
                            </div>
                        </div>
                        <hr class="mt-5 mb-5">
                        <div class="text-center">
                            <p>Copyright &copy; Developed by <a href="#">Group 20 - Cong nghe web</a> 2018</p>
                            <ul class="list-inline">
                                <li class="list-inline-item">
                                    <a href="javascript:void(0)" class="btn btn-facebook">
                                        <i class="fa fa-facebook"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="javascript:void(0)" class="btn btn-twitter">
                                        <i class="fa fa-twitter"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="javascript:void(0)" class="btn btn-linkedin">
                                        <i class="fa fa-linkedin"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="javascript:void(0)" class="btn btn-google-plus">
                                        <i class="fa fa-google-plus"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- #/ container -->
<!-- Common JS -->
<script src="<c:url value="resources/assets/plugins/common/common.min.js" />"></script>
<!-- Custom script -->
<script src="<c:url value="resources/main/js/custom.min.js" />"></script>
<script src="<c:url value="resources/main/js/settings.js" />"></script>
<script src="<c:url value="resources/main/js/gleek.js" />"></script>
</body>

</html>
