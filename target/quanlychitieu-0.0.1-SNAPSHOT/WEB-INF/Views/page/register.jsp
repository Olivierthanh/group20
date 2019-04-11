<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/10/2019
  Time: 2:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Registration</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" />">
    <%--Sweet Alert stylesheet--%>
    <link href="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.css" />" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="<c:url value="resources/main/css/custom.css" />">
    <link href="<c:url value="resources/main/css/style.css" />" rel="stylesheet">
</head>

<body class="h-100" id="register-page">
<!-- Preloader -->
<jsp:include page="../component/preload.jsp" />
<!-- End preloader -->
<div class="login-bg h-100">
    <div class="container h-100">
        <div class="row justify-content-center h-100">
            <div class="col-xl-6">
                <div class="form-input-content login-form">
                    <div class="card">
                        <div class="card-body">
                            <!-- <div class="logo text-center">
                                <a href="#">
                                    <img src="../../assets/images/wallet-sm.png" class="img-fluid" alt="">
                                </a>
                            </div> -->
                            <h2 class="text-center mt-4">Register</h2>
                            <form id="register-form" class="mt-5 mb-5" action="<c:url value="/register_user" />" login-url="<c:url value="/login" />" method="post">
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
                                    <label class="text-label">Name</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fa fa-id-card" aria-hidden="true"></i>
                                                </span>
                                        </div>
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Name">
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
                                <div class="form-group">
                                    <label class="text-label">Confirm Password</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fa fa-lock" aria-hidden="true"></i>
                                                </span>
                                        </div>
                                        <input type="password" class="form-control" id="confirm-password" name="confirm-password" placeholder="Confirm Password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="text-label">Address</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fa fa-address-card"></i>
                                                </span>
                                        </div>
                                        <input type="text" class="form-control" id="address" name="address" placeholder="Address" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label class="text-label">Date Of Birth</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                    <span class="input-group-text">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                            </div>
                                            <input type="date" class="form-control" id="date-of-birth" name="date-of-birth" placeholder="Date Of Birth" />
                                        </div>
                                    </div>
                                    <!-- <div class="col-md-6">
                                        <label class="text-label">Gender</label>
                                        <div class="form-check">
                                            <input type="radio" class="radio-outlined" value="male" name="gender"
                                                id="gender-1" />
                                            <label class="form-check-label">Male</label>
                                        </div>
                                        <div class="form-check">
                                            <input type="radio" class="radio-outlined" value="female"
                                                name="gender" id="gender-2" />
                                            <label class="form-check-label">Female</label>
                                        </div>
                                    </div> -->
                                    <div class="form-group col-md-6">
                                        <label class="text-label">Gender</label>
                                        <div class="form-check">
                                            <input id="radio1" class="radio-outlined" name="gender" value="male" type="radio">
                                            <label for="radio1" class="">Male</label>
                                        </div>
                                        <div class="form-check">
                                            <input id="radio2" class="radio-outlined" name="gender" value="female" type="radio">
                                            <label for="radio2" class="">Female</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="text-center mb-4 mt-4">
                                    <button type="submit" class="btn btn-primary">Register</button>
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
                                    Already have an account? <a href="<c:url value="/login" />">Login Now</a>
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
<script src="<c:url value="resources/assets/plugins/common/common.min.js" />"></script>
<!-- Custom script -->
<script src="<c:url value="resources/main/js/custom.min.js" />"></script>
<script src="<c:url value="resources/main/js/settings.js" />"></script>
<script src="<c:url value="resources/main/js/gleek.js" />"></script>
<!-- Form validate -->
<script src="<c:url value="resources/assets/plugins/jquery-validation/jquery.validate.min.js" />"></script>
<script src="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.js" />"></script>
<script src="<c:url value="resources/main/js/custom/validate/register_validate.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/register_submit.js" />"></script>
</body>

</html>