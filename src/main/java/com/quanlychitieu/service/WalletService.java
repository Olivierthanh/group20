package com.quanlychitieu.service;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.quanlychitieu.utils.Utils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanlychitieu.dao.TransactionDao;
import com.quanlychitieu.dao.WalletDao;

import com.quanlychitieu.entity.Transaction;
import com.quanlychitieu.entity.TransactionType;
import com.quanlychitieu.entity.User;
import com.quanlychitieu.entity.Wallet;

import com.quanlychitieu.utils.AjaxMessage;

@Service
public class WalletService {

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private TransactionDao transactionDao;

	public Wallet getWallet(int walletId) {
		Wallet wallet = walletDao.getWalletByWalletId(walletId);
		return wallet;
	}

	public int getInflow(int walletId) {
		int result = 0;
		List<Transaction> transactions = transactionDao.getTransactionByTransactionType(walletId,
				TransactionType.income);
		for (Transaction transaction : transactions) {
			result = result + transaction.getAmount();
		}
		return result;
	}

	public int getOutflow(int walletId) {
		int result = 0;
		List<Transaction> transactions = transactionDao.getTransactionByTransactionType(walletId,
				TransactionType.expense);
		for (Transaction transaction : transactions) {
			result = result - transaction.getAmount();
		}
		return result;
	}

	public List<Transaction> getTransactionByWalletId(int walletId) {
		return transactionDao.getTransactionByWalletId(walletId);

	}

	public Set<User> getUserByWalletId(int walletId) {
		Wallet wallet = getWallet(walletId);
		Set<User> listUser = wallet.getListUser();
		return listUser;
	}

	public String deleteWallet(int walletId) {
		ObjectMapper mapper = new ObjectMapper();
		String returnMessage = "";
		AjaxMessage message;
		Wallet wallet = getWallet(walletId);
		boolean deleted = walletDao.deleteWallet(wallet);
		int deletedTransaction = transactionDao.deleteTransactionByWalletId(walletId);
		if (deleted && deletedTransaction==1) {
			message = new AjaxMessage("success", "Delete wallet successfully", "Your wallet is deleted! ");
		} else {
			message = new AjaxMessage("error", "Delete wallet unsuccessfully", "Your wallet was not deleted");
		}
		try {
			returnMessage = mapper.writeValueAsString(message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnMessage;
	}

}
