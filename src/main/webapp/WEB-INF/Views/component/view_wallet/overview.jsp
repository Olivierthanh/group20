<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/15/2019
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="card">
    <div class="card-header pb-0">
        <h4 class="card-title m-t-10">Overview</h4>
        <div class="table-action float-right">
            <span data-toggle="modal" data-target="#add-transaction-view">
                <button type="button" class="btn btn-circle btn-success"
                        id="add-transaction-button" data-toggle="tooltip" data-placement="right"
                        title data-original-title="Add transaction"><i class="fa fa-plus"
                                                                       aria-hidden="true"></i>
                </button>
            </span>
            <!-- begin modal -->
            <div class="modal fade" id="add-transaction-view">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Add transaction</h5>
                            <button type="button" class="close"
                                    data-dismiss="modal"><span>&times;</span>
                            </button>
                        </div>
                        <hr>
                        <form action="#" id="add-transaction-form">
                            <div class="modal-body px-5">
                                <div class="row px-5 mb-4">
                                    <div class="col-2 align-middle">
                                        <span class="mdi mdi-help mdi-36px"></span>
                                    </div>
                                    <div class="col">
                                        <input class="form-control" type="text" name="category" id="category" value="" category-id="" placeholder="Category" />
                                    </div>
                                </div>
                                <div class="row px-5 mb-4">
                                    <div class="col-2 align-middle">
                                        <span class="mdi mdi-currency-usd mdi-36px"></span>
                                    </div>
                                    <div class="col">
                                        <input type="text" class="form-control" name="amount"
                                               id="amount" placeholder="How much?">
                                    </div>
                                </div>
                                <div class="row px-5 mb-4">
                                    <div class="col-2 align-middle">
                                       <span class="mdi mdi-note-multiple-outline mdi-36px"></span>
                                    </div>
                                    <div class="col">
                                       <textarea class="form-control" name="note" cols="30" rows="5" id="note" placeholder="Note"></textarea>
                                    </div>
                                </div>
                                <div class="row px-5 mb-4">
                                    <div class="col-2 align-middle">
                                        <span class="mdi mdi-calendar-clock mdi-36px"></span>
                                    </div>
                                    <div class="col">
                                        <input type="datetime-local" class="form-control"
                                               name="date-transaction" id="date-transaction">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer px-5">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- End modal -->
            <!-- Begin category modal -->
            <div class="modal fade" id="transaction-category-select">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header pb-0">
                            <ul class="nav nav-tabs mb-4">
                                <li class="nav-item"><a class="nav-link active" id="income-tab" data-toggle="tab" href="#income">Income</a>
                                </li>
                                <li class="nav-item"><a class="nav-link" id="expense-tab" data-toggle="tab" href="#expense">Expense</a>
                                </li>
                            </ul>
                            <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                            </button>
                        </div>
                        <div class="modal-body py-0">
                            <div class="container-fluid">
                                <div class="row">
                                    <!-- Begin tab category -->
                                    <!-- Nav tabs -->
                                    <div class="custom-tab-1 col">
                                        <div class="tab-content tab-content-default">
                                            <div class="tab-pane fade show active" id="income" role="tabpanel">
                                                <div class="row">
                                                    <%-- Begin one income category --%>
                                                    <div class="col-3">
                                                        <a href="#" class="img-category" category-id="1" category-name="Awards">
                                                            <figure class="figure">
                                                                <img src="<c:url value="resources/main/icons/category_icon/cat_1.png" />" class="figure-img img-fluid rounded" alt="Awards">
                                                                <figcaption class="figure-caption text-center">Awards</figcaption>
                                                            </figure>
                                                        </a>
                                                    </div>
                                                    <%-- End one income category--%>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="expense">
                                                <div class="row">
                                                    <%-- Begin one expense category --%>
                                                    <div class="col-3">
                                                        <a href="#" class="img-category" category-id="18" category-name="Investment">
                                                            <figure class="figure">
                                                                <img src="<c:url value="resources/main/icons/category_icon/cat_18.png" />" class="figure-img img-fluid rounded" alt="Awards">
                                                                <figcaption class="figure-caption text-center">Investment</figcaption>
                                                            </figure>
                                                        </a>
                                                    </div>
                                                    <%-- End one expense category --%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End tab category -->
                                </div>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End category modal -->
        </div>
    </div>
    <div class="card-body p-0">
        <div class="row">
            <div class="offset-2 col-4">
                <div class="card-content">
                    <div class="row p-2">
                        <p>Inflow</p>
                    </div>
                    <div class="row p-2">
                        <p>Outflow</p>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="card-content">
                    <div class="row p-2">
                        <p class="w-100 text-right">+1000000</p>
                    </div>
                    <div class="row p-2">
                        <p class="w-100 text-right">-5000000</p>
                    </div>
                    <hr>
                    <div class="row p-2">
                        <p class="w-100 text-right">+500000</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>