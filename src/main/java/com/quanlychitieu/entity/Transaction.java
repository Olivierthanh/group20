package com.quanlychitieu.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

import javax.persistence.*;

@Entity(name="transaction")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "transactionId")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;
	private int amount;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(length = 65535, columnDefinition = "text")
	private String note;

	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "walletId")
    private Wallet wallet = new Wallet();

	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category = new Category();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user = new User();
	
	public Transaction() {
		
	}


    public Transaction(int amount, Date date, String note, TransactionType type, Wallet wallet, Category category, User user) {
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.type = type;
        this.wallet = wallet;
        this.category = category;
        this.user = user;
    }

    public Transaction(int amount, Date date, String note, TransactionType type, Wallet wallet, Category category, int userId) {
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.type = type;
        this.wallet = wallet;
        this.category = category;
        this.user.setUserId(userId);
    }

    public Transaction(int amount, Date date, String note, TransactionType type, int walletId, int categoryId, User user) {
		this.amount = amount;
		this.date = date;
		this.note = note;
		this.type = type;
		this.wallet.setWalletId(walletId);
		this.category.setCategoryId(categoryId);
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
