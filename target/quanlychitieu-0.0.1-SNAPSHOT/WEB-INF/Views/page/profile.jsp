<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/9/2019
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Thông tin cá nhân</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" />">
    <!-- Cdn css -->
    <%--Sweet Alert stylesheet--%>
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
    <div class="content-body" style="min-height: 894px;">
        <div class="container-fluid">
            <div class="row page-titles">
                <div class="col p-md-0">
                    <h4>Profile</h4>
                </div>
                <div class="col p-md-0">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<c:url value="/home" /> ">Trang chủ</a>
                        </li>
                        <!-- <li class="breadcrumb-item"><a href="javascript:void(0)">Apps</a>
                        </li> -->
                        <li class="breadcrumb-item active">Profile</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-body">
                            <div class="user-info-settings">
                                <h4 class="text-primary section-heading card-intro-title">Thông tin cá nhân</h4>
                                <form id="update-profile-form" action="<c:url value="/profile/update_profile" />" method="post" class="user-info-setting-form">
                                    <div class="form-group">
                                        <label class="text-label">Tên</label>
                                        <input type="text" class="form-control" name="name" id="name" value="${user.name}" placeholder="Tên" />
                                    </div>
                                    <div class="form-group">
                                        <label class="text-label">Email</label>
                                        <input type="email" class="form-control" name="email" id="email" value="${user.email}" disabled />
                                    </div>
                                    <div class="form-group">
                                        <label class="text-label">Xác nhận mật khẩu</label>
                                        <input type="password" class="form-control" name="password" id="password" placeholder="******" />
                                    </div>
                                    <div class="form-group">
                                        <label class="text-label">Ngày sinh</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                    <span class="input-group-text">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                            </div>
                                            <input type="date" class="form-control" name="date-of-birth" id="date-of-birth" value="<fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy-MM-dd" />" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="text-label">Địa chỉ</label>
                                        <input type="text" class="form-control" name="address" id="address" value="${user.address}" placeholder="Địa chỉ" />
                                    </div>
                                    <div class="form-group">
                                        <label class="text-label">Giới tình</label>
                                        <div class="form-check">
                                            <input id="radio1" class="radio-outlined" value="male" name="gender" type="radio" <c:if test="${user.gender == 'male'}">checked</c:if> />
                                            <label for="radio1" class="">Nam</label>
                                        </div>
                                        <div class="form-check">
                                            <input id="radio2" class="radio-outlined" value="female" name="gender" type="radio" <c:if test="${user.gender == 'female'}">checked</c:if> />
                                            <label for="radio2" class="">Nữ</label>
                                        </div>
                                    </div>
                                    <div class="submit-buttons mb-5">
                                        <button type="submit" class="btn btn-card btn-success" id="update-profile-submit-button" disabled>Cập nhật thông tin</button>
                                        <%--<button type="submit" class="btn btn-card btn-danger" id="cancel-update-profile-button" disabled>Cancel</button>--%>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 my-5 my-lg-0 d-none d-md-block">
                    <div class="card card-full-width rounded-0">
                        <div class="card-body user-details-contact text-center">
                            <div class="user-details-image mb-3">
                                <img class="rounded-circle" src="<c:url value="resources/main/images/user.png" />" alt="user-image">
                            </div>
                            <div class="user-intro">
                                <h4 class="text-primary card-intro-title mb-0">Tên</h4>
                                <p><small>@ ${user.name}</small>
                                </p>
                            </div>
                            <div class="contact-addresses">
                                <ul class="contact-address-list">
                                    <li class="email">
                                        <h5><i class="fa fa-envelope text-primary"></i> Địa chỉ email</h5>
                                        <p>${user.email}</p>
                                    </li>
                                    <li class="address">
                                        <h5><i class="fa fa-map text-primary" aria-hidden="true"></i> Địa chỉ</h5>
                                        <p>${user.address}</p>
                                    </li>
                                    <li class="social">
                                        <h5>Mạng xã hội</h5>
                                        <ul class="social-navigation">
                                            <li>
                                                <a class="bg-facebook" href="https://www.facebook.com">
                                                    <i class="fa fa-facebook color-white"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="bg-instagram" href="https://www.instagram.com">
                                                    <i class="fa fa-instagram color-white"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="bg-twitter" href="https://www.twitter.com">
                                                    <i class="fa fa-twitter color-white"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="bg-youtube" href="https://www.youtube.com">
                                                    <i class="fa fa-youtube color-white"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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

<!-- SweetAlert2 -->
<script src="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.js" />"></script>

<!-- Form validate -->
<script src="<c:url value="resources/assets/plugins/jquery-validation/jquery.validate.min.js" />"></script>
<script src="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.js" />"></script>
<script src="<c:url value="resources/main/js/custom/validate/update_profile_validate.js" />"></script>
<script src="<c:url value="resources/main/js/custom/validate/add_wallet_validate.js" />"></script>

<!-- Form submit -->
<script src="<c:url value="resources/main/js/custom/submit_form/add_wallet_submit.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/update_profile_submit.js" />"></script>
<!-- End Script -->
</body>
</html>
