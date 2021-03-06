<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/15/2019
  Time: 1:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="card">
    <div class="card-header pb-0">
        <h4 class="card-title">Thông tin ví</h4>
        <div class="table-action float-right">
            <form action="<c:url value="/deleteWallet" />" home="<c:url value="/home" />" method="get" id="delete-wallet-form">
                <input type="text" name="wallet-id" value="${wallet.walletId}" hidden />
                <button type="submit" class="btn btn-circle btn-danger" data-toggle="tooltip"
                        data-placement="right" title data-original-title="Delete wallet"
                        id="delete-wallet-button"><i class="fa fa-trash" aria-hidden="true"></i>
                </button>
            </form>
        </div>
    </div>

    <div class="card-body px-0 pt-0">
        <div class="row">
            <div class="offset-2 col-4">
                <p>Loại:</p>
            </div>
            <div class="col">
                <p id="wallet-type">
                    <c:choose>
                        <c:when test="${wallet.getWalletType() == 'personal'}">
                            Ví cá nhân
                        </c:when>
                        <c:otherwise>
                            Ví dùng chung
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="offset-2 col-4">
                <p>Người dùng chung:</p>
            </div>
            <div class="col" id="shared-user-view">
                <c:choose>
                    <c:when test="${fn:length(userList) > 0}" >
                        <c:forEach var="sharedUser" items="${userList}" >
                            <span class="d-block shared-user">
                                <form action="<c:url value="/deleteSharedUser" />" class="delete-shared-user-form">
                                    <span class="shared-user-name">${sharedUser.name}</span>
                                    <input type="text" name="wallet-id" value="${wallet.walletId}" hidden/>
                                    <input type="text" name="shared-user-id" value="${sharedUser.userId}" hidden/>
                                    <button type="submit" class="text-danger btn-primary-outline"
                                            data-toggle="tooltip"
                                            data-placement="right" title
                                            data-original-title="Delete ${sharedUser.name}">
                                        <i class="fa fa-times"></i>
                                    </button>
                                </form>
                            </span>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <span id="no-user">
                            Không có
                        </span>
                    </c:otherwise>
                </c:choose>

                <span class="d-block">
                    <a href="#" data-toggle="modal" data-target="#add-shared-user-view">Thêm người dùng chung</a>
                    <!-- begin add user modal -->
                    <div class="modal fade" id="add-shared-user-view">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Thêm người dùng chung</h5>
                                    <button type="button" class="close"
                                            data-dismiss="modal"><span>&times;</span>
                                    </button>
                                </div>
                                <hr>
                                <form action="<c:url value="/addSharedUser" />" method="get" id="add-shared-user-form">
                                    <div class="modal-body px-5">
                                        <div class="row px-5 mb-4">
                                            <div class="col-2 align-middle">
                                                <span class="mdi mdi-account-circle-outline mdi-36px"></span>
                                            </div>
                                            <div class="col">
                                                <input class="form-control" type="text" name="shared-user" id="shared-user" value="" placeholder="Người dùng chung" />
                                                <input type="text" name="wallet-id" hidden value="${wallet.walletId}" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer px-5">
                                        <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">Đóng</button>
                                        <button type="submit" class="btn btn-primary">Thêm</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- End add user modal -->
                </span>
            </div>
        </div>
    </div>
</div>
