<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/13/2019
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="row page-titles">
    <div class="col p-md-0">
        <h4>Dashboard</h4>
    </div>
    <div class="col p-md-0">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="javascript:void(0)">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">
                <a href="">Wallet</a>
            </li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-sm-6 col-md-4 col-xl-3">
        <div class="card">
            <div class="card-body stat-widget-seven gradient-7">
                <div class="media">
                    <img class="mr-5 m-t-13 m-l-5" src="<c:url value="resources/assets/images/icons/15.png" />" alt="">
                    <div class="media-body">
                        <h2 class="mt-0 mb-1">${noWallet}</h2>
                        <h5>No. Wallets</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-4 col-xl-3">
        <div class="card">
            <div class="card-body stat-widget-seven gradient-5">
                <div class="media">
                    <img class="mr-5 m-t-13 m-l-5" src="<c:url value="resources/assets/images/icons/7.png" />" alt="">
                    <div class="media-body">
                        <h2 class="mt-0 mb-1">${noPersonalWallet}</h2>
                        <h5>No. Personal Wallets</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-4 col-xl-3">
        <div class="card">
            <div class="card-body stat-widget-seven gradient-3">
                <div class="media">
                    <img class="mr-5 m-t-13 m-l-5" src="<c:url value="resources/assets/images/icons/8.png" />" alt="">
                    <div class="media-body">
                        <h2 class="mt-0 mb-1">${noSharedWallet}</h2>
                        <h5>No. Shared Wallets</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-4 col-xl-3">
        <div class="card">
            <div class="card-body stat-widget-seven gradient-4">
                <div class="media">
                    <img class="mr-5 m-t-13 m-l-5" src="<c:url value="resources/assets/images/icons/18.png" />" alt="">
                    <div class="media-body">
                        <h2 class="mt-0 mb-1">50</h2>
                        <h5>No. Transactions</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-header pb-0">
                <h4 class="card-title">List of Wallet</h4>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table id="wallet-table" class="table display" style="min-width: 845px">
                        <thead>
                        <tr>
                            <th>Wallet Name</th>
                            <th>Balance</th>
                            <th>Currency</th>
                            <th>Created Date</th>
                            <th>Type</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.walletList}" var="wallet" >
                            <tr>
                                <td><a href="<c:url value="/viewWallet${wallet.walletId}" />">${wallet.walletName}</a> </td>
                                <td class="text-right" style="padding-right: 5%;"><fmt:formatNumber value="${wallet.balance}" type="number" maxFractionDigits="3" /></td>
                                <td class="text-center">${wallet.currency}</td>
                                <td class="text-center"><fmt:formatDate value="${wallet.createdDate}" pattern="yyyy-MM-dd" /></td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${wallet.walletType == 'personal'}">
                                            Cá nhân
                                        </c:when>
                                        <c:otherwise>
                                            Dùng chung
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Balance</th>
                            <th>Currency</th>
                            <th>Created Date</th>
                            <th>Type</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
