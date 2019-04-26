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
		<h4 class="card-title m-t-10">Transaction</h4>
		<div class="table-action float-right">
			<form action="#">
				<!-- <div class="form-row">
                        <div class="form-group m-b-0">
                            <select class="selectpicker show-tick" data-width="auto">
                                <option selected="selected">Last 30 Days</option>
                                <option>Last 1 MOnth</option>
                                <option>Last 6 MOnth</option>
                                <option>Last Year</option>
                            </select>
                        </div>
                    </div> -->

			</form>
		</div>
	</div>

	<div class="card-body" id="view-transaction">
		<div class="row">
			<div class="col" id="transaction-day-list">
				<c:forEach items="${transactionsByDateMap}" var="entry" >
					<!-- One day -->
					<div class="card w-100 p-4" transaction-date="<fmt:formatDate value="${entry.key}" pattern="yyyy-MM-dd" />">
						<div class="card-header">
							<h3 class="card-title m-t-10">
								<fmt:formatDate value="${entry.key}" pattern="yyyy-MM-dd EEEE" />
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
								<p class="sum">${sum}</p>
							</div>
						</div>
						<!-- One transaction -->

						<c:forEach var="transaction" items="${entry.value}">
							<div class="card-body py-0">
								<hr>
								<div class="row">
									<div class="col-2">
										<img class="img-fluid" src="<c:url value="resources/main/icons/category_icon/cat_${transaction.category.categoryId}.png" />"
											 alt="des" />
									</div>
									<div class="col-6">
										<div class="row">
											<p class="w-100">
												<span class="d-block">${transaction.category.categoryName}</span> <span>${transaction.user.name}</span>
												- <span>${transaction.getDate()}</span>
											</p>
											<p class="w-100">${transaction.getNote()}</p>
										</div>
									</div>
									<div class="col-4">
										<div class="row float-right w-75">
											<form action="<c:url value="/deleteTransaction" />" method="get" class="delete-transaction-form">
												<p class="w-100 transaction-amount">
													<c:choose>
														<c:when test="${transaction.type == 'income'}" >
															+
														</c:when>
														<c:otherwise>
															-
														</c:otherwise>
													</c:choose>
														${transaction.getAmount() }
												</p>
												<input type="text" name="transaction-id" value="${transaction.transactionId}" hidden />
												<input type="text" name="wallet-id" value="${wallet.walletId}" hidden />
												<button type="submit" class="btn btn-circle btn-danger"
														data-toggle="tooltip" data-placement="right" title
														data-original-title="Delete transaction">
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
