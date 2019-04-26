<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/12/2019
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Change Password</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="resources/assets/images/wallet.png" />">
    <%--Sweet Alert stylesheet--%>
    <link href="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.css" />" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link href="<c:url value="resources/main/css/style.css" />" rel="stylesheet">

</head>

<body class="h-100">
<%--Preload--%>
<jsp:include page="../component/preload.jsp"/>
<%--End preload--%>
<div class="login-bg h-100">
    <div class="container h-100">
        <div class="row justify-content-center h-100">
            <div class="col-xl-6">
                <div class="form-input-content">
                    <div class="card">
                        <div class="card-body">
                            <div class="logo text-center">
                                <a href="<c:url value="/" />">
                                    <img src="<c:url value="resources/assets/images/wallet-sm.png" />" alt="">
                                </a>
                            </div>
                            <h4 class="text-center mt-5">Reset Password</h4>
                            <p class="text-center">Enter password to reset your password!</p>
                            <form id="reset-password-form" action="<c:url value="/changePasswordProcess" />"
                                  login-url="<C:url value="/login" />" class="mt-5 mb-5">
                                <div class="form-group">
                                    <input type="password" class="form-control" id="password" name="password"
                                           placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="confirm-password"
                                           name="confirm-password" placeholder="Confirm Password">
                                </div>
                                <c:if test="${userId != null && token != null}">
                                    <input type="text" value="${userId}" name="userId" id="userId" hidden/>
                                    <input type="text" value="${token}" name="token" id="token" hidden/>
                                </c:if>
                                <div class="text-center mb-4 mt-5">
                                    <button type="submit" class="btn btn-primary">Reset</button>
                                </div>
                            </form>
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
<%--Sweet alert2--%>
<script src="<c:url value="resources/assets/plugins/sweetalert2/dist/sweetalert2.min.js" />"></script>
<%--Validate--%>
<script src="<c:url value="resources/assets/plugins/jquery-validation/jquery.validate.min.js" /> "></script>
<script src="<c:url value="resources/main/js/custom/validate/change_password_validate.js" />"></script>
<script src="<c:url value="resources/main/js/custom/validate/add_wallet_validate.js" />"></script>
<%-- Submit --%>
<script src="<c:url value="resources/main/js/custom/submit_form/change_password_submit.js" />"></script>
<script src="<c:url value="resources/main/js/custom/submit_form/add_wallet_submit.js" />"></script>
</body>

</html>
