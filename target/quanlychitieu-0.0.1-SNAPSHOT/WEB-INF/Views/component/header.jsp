<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/4/2019
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="header-content">
        <div class="header-left">
        </div>
        <div class="header-right">
            <ul>
                <li class="icons">
                    <a href="javascript:void(0)" class="log-user">
                        <img src="<c:url value="resources/assets/images/avatar/1.jpg" />" alt=""> <span id="username">${sessionScope.username}</span> <i
                            class="fa fa-caret-down f-s-14" aria-hidden="true"></i>
                    </a>
                    <div class="drop-down dropdown-profile animated bounceInDown">
                        <div class="dropdown-content-body">
                            <ul>
                                <li><a href="<c:url value="/profile" />"><i class="icon-user"></i> <span>My Profile</span></a>
                                </li>
                                <li><a href="<c:url value="/home" />"><i class="icon-wallet"></i> <span>My Wallet</span></a>
                                </li>
                                <li><a href="<c:url value="/logout" />"><i class="icon-power"></i> <span>Logout</span></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
