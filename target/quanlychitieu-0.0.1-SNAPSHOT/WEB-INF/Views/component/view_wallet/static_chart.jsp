<%--
  Created by IntelliJ IDEA.
  User: toila
  Date: 4/15/2019
  Time: 1:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="card h-100">
    <div class="card-header">
        <h4 class="card-title m-t-10">Statics</h4>
        <!-- <select class="selectpicker float-right">
                <option>This month</option>
                <option>2019-03</option>
                <option>2019-02</option>
        </select>  -->
        <input class="form-control float-right" type="month" max="2019-05" id="month"
               name="month" style="width: 20%;" />
    </div>

    <div class="card-body">
        <div class="row">
            <div class="card w-100">
                <div class="card-body">
                    <h4 class="card-title">Balance </h4>
                    <div id="b-line" style="height: 370px"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <div class="card w-100">
                    <div class="card-body">
                        <h4 class="card-title">Income</h4>
                        <div id="income-Pie" style="height: 370px"></div>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="card w-100">
                    <div class="card-body">
                        <h4 class="card-title">Expense</h4>
                        <div id="expense-Pie" style="height: 370px"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
