<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="col">
				<!-- One day -->
				<div class="card w-100 p-4">
					<div class="card-header">
						<h3 class="card-title m-t-10">2019-10-11</h3>
						<div class="float-right">
							<p>-600000</p>
						</div>
					</div>
					<!-- One transaction -->

					<c:forEach var="transaction" items="${listTransaction}">
						<div class="card-body py-0">
							<hr>
							<div class="row">
								<div class="col-2">
									<img class="img-fluid" src="resources/data/img/cat_1.png"
										alt="des" />
								</div>
								<div class="col-6">
									<div class="row">
										<p>
											<span class="d-block">Award</span> <span>nguyen van a</span>
											- <span>${transaction.getDate()}</span>
										</p>
										<p>${transaction.getNote()}</p>
									</div>
								</div>
								<div class="col-4">
									<div class="row float-right">
										<form action="#" class="delete-transaction-form">
											<p>${transaction.getAmount() }</p>
											<input type="text" name="transaction-id" value="" hidden />
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
