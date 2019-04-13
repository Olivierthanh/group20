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

	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name = "user_wallet", joinColumns = {@JoinColumn(name = "walletId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
	private Set<User> listUser;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="transactionId")
	private Set<Transaction> listTransaction;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Enumerated(EnumType.STRING)
	private WalletType walletType;
	
	public Wallet() {
		
	}

	public Wallet(String walletName, int balance, Currency currency, Set<User> listUser, Set<Transaction> listTransaction, Date createdDate, WalletType walletType) {
		this.walletName = walletName;
		this.balance = balance;
		this.currency = currency;
		this.listUser = listUser;
		this.listTransaction = listTransaction;
		this.createdDate = createdDate;
		this.walletType = walletType;
	}

	public Wallet(String walletName, int balance, Currency currency, Set<User> listUser, Date createdDate, WalletType walletType) {
		this.walletName = walletName;
		this.balance = balance;
		this.currency = currency;
		this.listUser = listUser;
		this.createdDate = createdDate;
		this.walletType = walletType;
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

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Set<User> getListUser() {
		return listUser;
	}

	public void setListUser(Set<User> listUser) {
		this.listUser = listUser;
	}

	public Set<Transaction> getListTransaction() {
		return listTransaction;
	}

	public void setListTransaction(Set<Transaction> listTransaction) {
		this.listTransaction = listTransaction;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public WalletType getWalletType() {
		return walletType;
	}

	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}
}
