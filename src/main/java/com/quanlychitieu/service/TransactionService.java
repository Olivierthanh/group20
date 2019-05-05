package com.quanlychitieu.service;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Set;


import com.quanlychitieu.dao.UserDao;
import com.quanlychitieu.dao.WalletDao;
import com.quanlychitieu.entity.TransactionType;
import com.quanlychitieu.entity.User;
import com.quanlychitieu.entity.Wallet;
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

	@Autowired
    private WalletDao walletDao;

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
                Wallet wallet = walletDao.getWalletByWalletId(walletId);
                updateBalance(wallet, transaction.getType(), transaction.getAmount(), "delete");
                if (walletDao.updateWallet(wallet) && transactionDao.deleteTransaction(transaction)) {
                    message = new AjaxMessage("success", "Giao dịch này đã được xóa khỏi ví của bạn", "Đã xóa");
                }
                else {
                    message = new AjaxMessage("error", "Có lỗi xảy ra", "Hãy thử xóa giao dịch lại lần nữa");
                }
            }
            else {
                message = new AjaxMessage("error", "Giao dịch không hợp lệ", "Hãy xóa giao dịch trong ví của bạn");
            }
        }
        else {
            message = new AjaxMessage("error", "Giao dịch không tồn tại", "Tải lại trang và thử lại!");
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
        Wallet wallet = walletDao.getWalletByWalletId(walletId);
        updateBalance(wallet, type, amount, "add");
        if (walletDao.updateWallet(wallet) && transactionDao.addTransaction(transaction)) {
            message = new AjaxMessage("success", "Đã thêm", "Thêm giao dịch thành công", String.valueOf(transaction.getTransactionId()));
        }
        else {
            message = new AjaxMessage("error", "Lỗi", "Không thể thêm giao dịch, hãy thử lại");
        }
        try {
            ajaxResponse = new ObjectMapper().writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return ajaxResponse;
    }

    public String updateTransaction(int categoryId, TransactionType type, int amount, String note, Date date, int walletId, String email, int transactionId) {
        String ajaxResponse = "";
        AjaxMessage message;
        Wallet wallet = walletDao.getWalletByWalletId(walletId);
        Transaction oldTransaction = null;
        for (Transaction transaction: wallet.getListTransaction()) {
            if (transaction.getTransactionId() == transactionId){
                oldTransaction = transaction;
                break;
            }
        }

        if (oldTransaction != null) {
            Transaction newTransaction = new Transaction(transactionId, amount, date, note, type, walletId, categoryId);
            if (oldTransaction.getUser().getEmail().equals(email)) {
                updateBalance(wallet, oldTransaction, newTransaction);
                updateTransaction(oldTransaction, newTransaction);

                if (walletDao.updateWallet(wallet)) {
                    message = new AjaxMessage("success", "Đã cập nhật giao dịch", "Giao dịch đã được cập nhật");
                }
                else {
                    message = new AjaxMessage("error", "Không thể cập nhật giao dịch", "Hãy thử lại");
                }
            }
            else {
                message = new AjaxMessage("error", "Không thể cập nhật giao dịch", "Bạn chỉ có thể cập nhật các giao dịch của chính bạn");
            }
        }
        else {
            message = new AjaxMessage("error", "Giao dịch không tồn tại", "Hãy tải lại trang và thử lại");
        }

        try {
            ajaxResponse = new ObjectMapper().writeValueAsString(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return ajaxResponse;
    }

    private void updateBalance(Wallet wallet, TransactionType type, int amount, String action) {
        int realAmount;
        if (action.equals("add")) {
            realAmount = amount;
        }
        else {
            realAmount = -amount;
        }

        int oldBalance = wallet.getBalance();

        if (type.equals(TransactionType.income)) {
            wallet.setBalance(oldBalance + realAmount);
        }
        else {
            wallet.setBalance(oldBalance - realAmount);
        }
    }

    private void updateBalance(Wallet wallet, Transaction oldTransaction, Transaction newTransaction) {
        int oldAmount = oldTransaction.getType().equals(TransactionType.income) ? oldTransaction.getAmount() : -oldTransaction.getAmount();
        int newAmount = newTransaction.getType().equals(TransactionType.income) ? newTransaction.getAmount() : -newTransaction.getAmount();
        int oldBalanceAmount = wallet.getBalance();
        wallet.setBalance(oldBalanceAmount - oldAmount + newAmount);
    }

    private void updateTransaction(Transaction oldTransaction, Transaction newTransaction) {
        oldTransaction.setAmount(newTransaction.getAmount());
        oldTransaction.setCategory(newTransaction.getCategory());
        oldTransaction.setDate(newTransaction.getDate());
        oldTransaction.setNote(newTransaction.getNote());
        oldTransaction.setType(newTransaction.getType());
    }
	
}
