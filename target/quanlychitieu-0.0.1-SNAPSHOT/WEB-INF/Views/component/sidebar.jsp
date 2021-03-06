<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/4/2019
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nk-sidebar">
    <div class="nk-nav-scroll">
        <ul class="metismenu" id="menu">

            <li class="nav-label">Wallet</li>
            <li>
                <a class="has-arrow" href="javascript:void(0)" aria-expanded="false">
                    <i class="mdi mdi-wallet"></i>
                    <span class="nav-text">Wallet</span>
                    <span class="badge bg-lgreen text-white nav-badge">০3</span>
                </a>
                <ul aria-expanded="false">
                    <li>
                        <a class="has-arrow" href="javascript:void(0)" aria-expanded="false">View</a>
                        <ul aria-expanded="false" id="wallet-list">
                            <C:forEach items="${sessionScope.walletList}" var="wallet" >
                                <li>
                                    <a href="<c:url value="/viewWallet${wallet.walletId}" />">${wallet.walletName}</a>
                                </li>
                            </C:forEach>
                        </ul>
                    </li>
                    <li>
                        <a href="#" aria-expanded="false" data-toggle="modal" data-target="#add-wallet-view">Tạo ví</a>
                    </li>
                </ul>
            </li>
            <li class="nav-label">Profile</li>
            <li><a class="has-arrow" href="javascript:void(0)" aria-expanded="false">
                <i class="mdi mdi-account"></i>
                <span class="nav-text">Profile</span>
                <span class="badge badge-primary nav-badge">13</span>
            </a>
                <ul aria-expanded="false">
                    <li>
                        <a href="<c:url value="/profile" />" aria-expanded="false">Xem profile</a>
                    </li>
                    <li>
                        <a href="<c:url value="/updatePassword" />" aria-expanded="false">Đổi mật khẩu</a>
                    </li>
                </ul>
            </li>
            <li class="nav-label">Log out</li>
            <li>
                <a class="has-arrow" href="<c:url value="/logout" />" aria-expanded="false">
                    <i class="mdi mdi-power"></i>
                    <span class="nav-text">Đăng xuất</span>
                    <span class="badge badge-success nav-badge">03</span>
                </a>
            </li>
        </ul>
    </div>
</div>

<!-- begin modal -->
<div class="modal fade" id="add-wallet-view">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Thêm ví</h4>
                <button type="button" class="close"
                        data-dismiss="modal"><span>&times;</span>
                </button>
            </div>
            <hr>
            <form action="<c:url value="/addWallet" />" method="get" id="add-wallet-form">
                <div class="modal-body px-5">
                    <div class="row px-5 mb-4">
                        <div class="col-2 align-middle">
                            <span class="mdi mdi-help mdi-36px"></span>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" name="wallet-name"
                                   id="wallet-name" placeholder="Tên ví">
                        </div>
                    </div>
                    <div class="row px-5 mb-4">
                        <div class="col-2 align-middle">
                            <span class="mdi mdi-currency-usd mdi-36px"></span>
                        </div>
                        <div class="col">
                            <select class="form-control" name="currency"
                                    id="currency">
                                <c:forEach var="currency" items="${currencyList}">
                                    <option value="${currency}">${currency}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer px-5">
                    <div class="px-5">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Tạo ví</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- End modal -->