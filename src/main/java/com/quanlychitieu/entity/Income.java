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

@Entity(name="income")
public class Income {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int incomeId;
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
	
	
	public Income() {
		
	}

	public Income(int incomeId, int amount, Date dATE, String note, Wallet wallet, Category category) {
		super();
		this.incomeId = incomeId;
		this.amount = amount;
		DATE = dATE;
		this.note = note;
		this.wallet = wallet;
		this.category = category;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
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
