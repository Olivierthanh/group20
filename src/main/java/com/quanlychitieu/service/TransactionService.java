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
        Wallet wallet = walletDao.getWalletByWalletId(walletId);
        updateBalance(wallet, type, amount, "add");
        if (walletDao.updateWallet(wallet) && transactionDao.addTransaction(transaction)) {
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
                    message = new AjaxMessage("success", "Updated transaction", "Your transaction is updated");
                }
                else {
                    message = new AjaxMessage("error", "Cannot updated transaction", "Please try again");
                }
            }
            else {
                message = new AjaxMessage("error", "Cannot updated transaction", "You can only update your transaction");
            }
        }
        else {
            message = new AjaxMessage("error", "Transaction is not existed", "Please reload page and try again");
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
