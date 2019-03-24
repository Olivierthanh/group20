package com.quanlychitieu.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="wallet")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int walletId;
	
	private String walletName;
	private int balance;
	private String currency;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="incomeId")
	private Set<Income> listIncome;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="expenseId")
	private Set<Expense> listExpense;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	public Wallet() {
		
	}
	
	public Wallet(int walletId, String walletName, int balance, String currency, User user, Set<Income> listIncome,
			Set<Expense> listExpense, Date createdDate) {
		super();
		this.walletId = walletId;
		this.walletName = walletName;
		this.balance = balance;
		this.currency = currency;
		this.user = user;
		this.listIncome = listIncome;
		this.listExpense = listExpense;
		this.createdDate = createdDate;
	}

	public Set<Expense> getListExpense() {
		return listExpense;
	}

	public void setListExpense(Set<Expense> listExpense) {
		this.listExpense = listExpense;
	}

	public Set<Income> getListIncome() {
		return listIncome;
	}

	public void setListIncome(Set<Income> listIncome) {
		this.listIncome = listIncome;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
