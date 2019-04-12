package com.quanlychitieu.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity(name="wallet")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int walletId;
	
	private String walletName;
	private int balance;
	private String currency;
	
	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name = "user_wallet", joinColumns = {@JoinColumn(name = "walletId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
	private Set<User> listUser;
	
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
	
	public Wallet(int walletId, String walletName, int balance, String currency, Set<User> listUser, Set<Income> listIncome,
			Set<Expense> listExpense, Date createdDate) {
		super();
		this.walletId = walletId;
		this.walletName = walletName;
		this.balance = balance;
		this.currency = currency;
		this.listUser = listUser;
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

	public Set<User> getListUser() {
		return listUser;
	}

	public void setListUser(Set<User> listUser) {
		this.listUser = listUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
