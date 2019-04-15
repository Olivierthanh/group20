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
        <h4 class="card-title">Wallet Detail</h4>
        <div class="table-action float-right">
            <form action="#" id="delete-wallet-form">
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
                <p>Ví cá nhân</p>
            </div>
        </div>
        <div class="row">
            <div class="offset-2 col-4">
                <p>Người sử dụng:</p>
            </div>
            <div class="col">
                <span class="d-block">
                    <form action="#" class="delete-shared-user-form">
                        <span class="shared-user-name">Nguyễn Văn A</span>
                        <input type="text" name="wallet-id" value="" hidden/>
                        <input type="text" name="user-id" value="" hidden/>
                        <button type="submit" class="text-danger btn-primary-outline"
                                data-toggle="tooltip"
                                data-placement="right" title
                                data-original-title="Delete Nguyễn Văn A">
                            <i class="fa fa-times"></i>
                        </button>
                    </form>
                </span>
                <span class="d-block">
                    <form action="#" class="delete-shared-user-form">
                        <span class="shared-user-name">Nguyễn Văn B</span>
                        <input type="text" name="wallet-id" value="" hidden/>
                        <input type="text" name="user-id" value="" hidden/>
                        <button type="submit" class="text-danger btn-primary-outline"
                                data-toggle="tooltip"
                                data-placement="right" title
                                data-original-title="Delete Nguyễn Văn B">
                            <i class="fa fa-times"></i>
                        </button>
                    </form>
                </span>
            </div>
        </div>
    </div>
</div>
