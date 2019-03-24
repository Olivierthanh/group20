package com.quanlychitieu.entity;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="expense")
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int expenseId;
	private int amount;
	
	@Temporal(TemporalType.DATE)
	private Date DATE;
	
	@Column(length = 65535, columnDefinition = "text")
	private String note;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="walletId")
	private Wallet wallet;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="categoryId")
	private Category category;
	
	public Expense() {
		
	}

	public Expense(int expenseId, int amount, Date dATE, String note, Wallet wallet, Category category) {
		super();
		this.expenseId = expenseId;
		this.amount = amount;
		DATE = dATE;
		this.note = note;
		this.wallet = wallet;
		this.category = category;
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDATE() {
		return DATE;
	}

	public void setDATE(Date dATE) {
		DATE = dATE;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
