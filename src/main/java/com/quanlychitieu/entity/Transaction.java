package com.quanlychitieu.entity;
import java.util.Date;

import javax.persistence.*;

@Entity(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;
	private int amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(length = 65535, columnDefinition = "text")
	private String note;

	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="walletId")
	private Wallet wallet = new Wallet();
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="categoryId")
	private Category category = new Category();
	
	public Transaction() {
		
	}

	public Transaction(int amount, Date date, String note, TransactionType type, Wallet wallet, Category category) {
		this.amount = amount;
		this.date = date;
		this.note = note;
		this.type = type;
		this.wallet = wallet;
		this.category = category;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
