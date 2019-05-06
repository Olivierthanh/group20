<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/15/2019
  Time: 1:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<div class="card">
	<div class="card-header pb-5">
		<h4 class="card-title m-t-10">Giao dịch</h4>
		<div class="table-action float-right">
			<form action="#">

			</form>
		</div>
	</div>

	<div class="card-body" id="view-transaction">
		<div class="row">
			<div class="col" id="transaction-day-list">
				<c:forEach items="${transactionsByDateMap}" var="entry" >
					<!-- One day -->
					<div class="card w-100 p-4 transaction-day" transaction-date="<fmt:formatDate value="${entry.key}" pattern="yyyy-MM-dd" />">
						<div class="card-header">
							<h3 class="card-title m-t-10 transaction-day-date">
								<fmt:formatDate value="${entry.key}" pattern="EEE MMM dd yyyy" />
							</h3>
							<c:set var="sum" value="${0}" />
							<c:forEach var="transaction" items="${entry.value}" >
								<c:choose>
									<c:when test="${transaction.type == 'income'}" >
										<c:set var="sum" value="${sum + transaction.amount}" />
									</c:when>
									<c:otherwise>
										<c:set var="sum" value="${sum - transaction.amount}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<div class="float-right">
								<p><span class="sum">${sum}</span> ${wallet.currency}</p>
							</div>
						</div>

						<!-- One transaction -->
						<c:forEach var="transaction" items="${entry.value}">
							<div class="card-body py-0"
								 transaction-id="${transaction.transactionId}"
								 transaction-type="${transaction.type}"
								 category-name="${transaction.category.categoryName}"
								 category-id="${transaction.category.categoryId}"
								 amount="${transaction.amount}"
								 note="${transaction.note}"
								 transaction-date="${transaction.date}"
							>
								<hr>
								<div class="row">
									<div class="col-2">
										<img class="img-fluid img-category" src="<c:url value="resources/main/icons/category_icon/cat_${transaction.category.categoryId}.png" />"
											 alt="des" />
									</div>
									<div class="col-6">
										<div class="row transaction-row">
											<p class="w-100">
												<span class="d-block category-name">${transaction.category.categoryName}</span> <span>${transaction.user.name}</span>
												- <span class="transaction-date">${transaction.date}</span>
											</p>
											<p class="w-100 note">${transaction.note}</p>
										</div>
									</div>
									<div class="col-4">
										<div class="row float-right w-75">
											<form action="<c:url value="/deleteTransaction" />" method="get" class="delete-transaction-form">
												<p class="w-100">
													<span class="transaction-amount">
													<c:choose>
														<c:when test="${transaction.type == 'income'}" >
															+
														</c:when>
														<c:otherwise>
															-
														</c:otherwise>
													</c:choose>
														${transaction.amount }
													</span>
													<%--${wallet.currency}--%>
												</p>
												<input type="text" name="transaction-id" value="${transaction.transactionId}" hidden />
												<input type="text" name="wallet-id" value="${wallet.walletId}" hidden />
												<button type="submit" class="btn btn-circle btn-danger"
														data-toggle="tooltip" data-placement="right" title
														data-original-title="Xóa">
													<i class="fa fa-trash"></i>
												</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- End one transaction-->
					</div>
					<!-- End one day -->
				</c:forEach>

			</div>
		</div>

	</div>

	<div class="card-footer text-center">
		<nav>
			<ul class="pagination pagination-rounded pagination-md float-right">
				<li class="page-item"><a class="page-link"
					data-toggle="tooltip" data-placement="left" title
					data-original-title="Previous"> << </a></li>
				<li class="page-item"><a class="page-link"
					data-toggle="tooltip" data-placement="right" title
					data-original-title="Next"> >> </a></li>
			</ul>
		</nav>
	</div>
</div>
