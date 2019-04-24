package com.quanlychitieu.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transactionId")
//    private Set<Transaction> listTransaction;


    public Category() {

    }

    public Category(String categoryName, TransactionType type) {
        this.categoryName = categoryName;
        this.type = type;
    }

    //    public Category(int categoryId, String categoryName, TransactionType type, Set<Transaction> listTransaction) {
//        super();
//        this.categoryId = categoryId;
//        this.categoryName = categoryName;
//        this.type = type;
//        this.listTransaction = listTransaction;
//    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

//    public Set<Transaction> getListTransaction() {
//        return listTransaction;
//    }

//    public void setListTransaction(Set<Transaction> listTransaction) {
//        this.listTransaction = listTransaction;
//    }


}
