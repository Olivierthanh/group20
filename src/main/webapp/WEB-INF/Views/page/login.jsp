<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/4/2019
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Login</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" /> ">
    <%--Sweet Alert stylesheet--%>
    <link href="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.css" />" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="<c:url value="resources/main/css/custom.css" /> ">
    <link href="<c:url value="resources/main/css/style.css" /> " rel="stylesheet">
</head>
<body class="h-100" id="login-page">
<%--Preload--%>
<jsp:include page="../component/preload.jsp" />
<%--End preload--%>

<div class="login-bg h-100">
    <div class="container h-100">
        <div class="row justify-content-center h-100">
            <div class="col-xl-6">
                <div class="form-input-content login-form">
                    <div class="card">
                        <div class="card-body">
                            <div class="logo text-center">
                                <a href="/">
                                    <img src="<c:url value="resources/assets/images/wallet-sm.png" /> " class="img-fluid" alt="">
                                </a>
                            </div>
                            <h4 class="text-center mt-4">Log into Your Account</h4>
                            <form class="mt-5 mb-5" method="post" id="login-form" action="<c:url value="/loginProcess" /> ">
                                <div class="form-group">
                                    <label class="text-label">Email</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fa fa-at" aria-hidden="true"></i>
                                                </span>
                                        </div>
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="text-label">Password</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fa fa-lock" aria-hidden="true"></i>
                                                </span>
                                        </div>
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <div class="form-check p-l-0">
                                            <input class="form-check-input ml-2" type="checkbox"
                                                   id="remember-me" name="remember-me">
                                            <label class="form-check-label ml-3" for="remember-me">
                                                Check me out
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6 text-right">
                                        <a href="javascript:void()">Forgot Password?</a>
                                    </div>
                                </div>
                                <div class="text-center mb-4 mt-4">
                                    <button type="submit" class="btn btn-primary" id="sign-in-submit">Sign in</button>
                                </div>
                            </form>
                            <div class="text-center">
                                <!-- <h5 class="mb-5">Or with Login</h5>
                                <ul class="list-inline">
                                    <li class="list-inline-item m-t-10"><a href="javascript:void()" class="btn btn-facebook"><i class="fa fa-facebook"></i></a>
                                    </li>
                                    <li class="list-inline-item m-t-10"><a href="javascript:void()" class="btn btn-twitter"><i class="fa fa-twitter"></i></a>
                                    </li>
                                    <li class="list-inline-item m-t-10"><a href="javascript:void()" class="btn btn-linkedin"><i class="fa fa-linkedin"></i></a>
                                    </li>
                                    <li class="list-inline-item m-t-10"><a href="javascript:void()" class="btn btn-google-plus"><i class="fa fa-google-plus"></i></a>
                                    </li>
                                </ul> -->
                                <p class="mt-5">
                                    Dont have an account? <a href="<c:url value="/register" />">Register Now</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- #/ container -->

<!-- Common JS -->
<script src="<c:url value="resources/assets/plugins/common/common.min.js" /> "></script>
<!-- Custom script -->
<script src="<c:url value="resources/main/js/custom.min.js" /> "></script>
<script src="<c:url value="resources/main/js/settings.js" /> "></script>
<script src="<c:url value="resources/main/js/gleek.js" /> "></script>
<%--Sweet alert2--%>
<script src="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.js" />"></script>
<!-- Form validate -->
<script src="<c:url value="resources/assets/plugins/jquery-validation/jquery.validate.min.js" /> "></script>
<script src="<c:url value="resources/main/js/custom/validate/login_validate.js" /> "></script>
<c:if test="${param.error == true}">
    <script>
        swal('Try again !!', 'Your email is not existed or your password is incorrect !!', 'error');
    </script>
</c:if>
</body>
</html>
