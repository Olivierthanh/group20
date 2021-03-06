package com.quanlychitieu.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.OptimisticLock;

import java.util.*;

import javax.persistence.*;

@Entity(name="wallet")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "walletId")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int walletId;
	
	private String walletName;
	private int balance;

	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_wallet", joinColumns = {@JoinColumn(name = "walletId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    @JsonBackReference
	private Set<User> listUser = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true, mappedBy = "wallet")
//	@JoinColumn(name="transactionId")
	@OptimisticLock(excluded = true)
	@JsonIgnore
	private List<Transaction> listTransaction = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Enumerated(EnumType.STRING)
	private WalletType walletType;
	
	public Wallet() {
		
	}

	public Wallet(String walletName, int balance, Currency currency, Set<User> listUser, List<Transaction> listTransaction, Date createdDate, WalletType walletType) {
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

	public Wallet(String walletName, Currency currency, User user) {
		this.walletName = walletName;
		this.balance = 0;
		this.listUser = new HashSet<>();
		this.listUser.add(user);
		this.listTransaction = new ArrayList<>();
		this.currency = currency;
		this.createdDate = new Date();
		this.walletType = WalletType.personal;
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

	public List<Transaction> getListTransaction() {
		return listTransaction;
	}

	public void setListTransaction(List<Transaction> listTransaction) {
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
