package com.quanlychitieu.service;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Set;


import com.quanlychitieu.dao.UserDao;
import com.quanlychitieu.entity.TransactionType;
import com.quanlychitieu.entity.User;
import com.quanlychitieu.utils.AjaxMessage;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanlychitieu.dao.TransactionDao;
import com.quanlychitieu.entity.Transaction;
import com.quanlychitieu.utils.Utils;

@Service
public class TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
    private UserDao userDao;
	
	
//	public String addTransaction(HttpServletRequest request) {
//        ObjectMapper mapper = new ObjectMapper();
//        AjaxMessage message;
//        String ajaxResponse = "";
//        User user;
//        try {
//            Transaction = getTransactionFromFormData(request);
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            boolean isSaved = userDao.saveUser(user);
//            if (isSaved) {
//                message = new AjaxMessage("success", "Register successfully", "Please return login page to login");
//            }
//            else {
//                message = new AjaxMessage("error", "Register unsuccessfully", "This email " + user.getEmail() + " is already existed, please use another email");
//            }
//        }
//        catch (ParseException parseException) {
//            parseException.printStackTrace();
//            message = new AjaxMessage("error", "Error", "Wrong date format (expected yyyy-MM-dd)");
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//            message = new AjaxMessage("error", "Error", "Some thing wrong happen");
//        }
//
//        try {
//            ajaxResponse = mapper.writeValueAsString(message);
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return ajaxResponse;
//    }
//
//
//	private Transaction getTransactionFromFormData(HttpServletRequest request) {
//        String category = request.getParameter("category");
//        String amount = request.getParameter("amount");
//        String note = request.getParameter("note");
//        String address = request.getParameter("address");
//        Gender gender = Gender.valueOf(request.getParameter("gender"));
//        Set<Wallet> listWallet = new HashSet<Wallet>();
//        Date date = Utils.getDate(request.getParameter("date-transaction"));
//
//        return 
//	}


    public List<Transaction> getListTransactionByMonth(int walletId, YearMonth month) {
        List<Date> listDate = Utils.createDateListByYearMonth(month);
        List<Transaction> transactionList = transactionDao.getListTransactionByDateList(walletId, listDate);
        System.out.println(transactionList.size());
        return transactionList;
    }

    public String deleteTransaction(int transactionId, int walletId, String email) {
        String ajaxResponse = "";
        AjaxMessage message;
        Transaction transaction = transactionDao.getTransactionByTransactionId(transactionId);
        if (transaction != null) {
            if (transaction.getWallet().getWalletId() == walletId && transaction.getUser().getEmail().equals(email)) {
                if (transactionDao.deleteTransaction(transaction)) {
                    message = new AjaxMessage("success", "Hey, this transaction has been deleted from your wallet", "Deleted");
                }
                else {
                    message = new AjaxMessage("error", "Something wrong happen", "Please try to delete your transaction again");
                }
            }
            else {
                message = new AjaxMessage("error", "Invalid transaction", "Please only delete your transaction in this wallet");
            }
        }
        else {
            message = new AjaxMessage("error", "Transaction is not existed", "Transaction is not existed! Please reload the page and try again!");
        }

        try {
            ajaxResponse = new ObjectMapper().writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return ajaxResponse;
    }

    public String addTransaction(int categoryId, TransactionType type, int amount, String note, Date date, int walletId, String email) {
        String ajaxResponse = "";
        AjaxMessage message;
        User user = userDao.getUserByEmail(email);
        Transaction transaction = new Transaction(amount, date, note, type, walletId, categoryId, user);
        if (transactionDao.addTransaction(transaction)) {
            message = new AjaxMessage("success", "Added", "Add transaction successfully", String.valueOf(transaction.getTransactionId()));
        }
        else {
            message = new AjaxMessage("error", "Error", "Cannot add transaction, please try again");
        }
        try {
            ajaxResponse = new ObjectMapper().writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return ajaxResponse;
    }
	
}
