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
                <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                    <i class="mdi mdi-wallet"></i>
                    <span class="nav-text">Wallet</span>
                    <span class="badge bg-lgreen text-white nav-badge">০3</span>
                </a>
                <ul aria-expanded="false">
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">View</a>
                        <ul aria-expanded="false">
                            <C:forEach items="${sessionScope.walletList}" var="wallet" >
                                <li>
                                    <a href="#">${wallet.walletName}</a>
                                </li>
                            </C:forEach>
                        </ul>
                    </li>
                    <li>
                        <a href="#" aria-expanded="false">Add Wallet</a>
                    </li>
                </ul>
            </li>
            <li class="nav-label">Profile</li>
            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">
                <i class="mdi mdi-account"></i>
                <span class="nav-text">Profile</span>
                <span class="badge badge-primary nav-badge">13</span>
            </a>
                <ul aria-expanded="false">
                    <li>
                        <a href="<c:url value="/profile" />" aria-expanded="false">View Profile</a>
                    </li>
                    <li>
                        <a href="#" aria-expanded="false">Change Password</a>
                    </li>
                </ul>
            </li>
            <li class="nav-label">Log out</li>
            <li>
                <a class="has-arrow" href="<c:url value="/logout" />" aria-expanded="false">
                    <i class="mdi mdi-power"></i>
                    <span class="nav-text">Log out</span>
                    <span class="badge badge-success nav-badge">03</span>
                </a>
            </li>
        </ul>
    </div>
</div>