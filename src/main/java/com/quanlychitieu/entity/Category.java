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

enum Type {
	expense, income
}

@Entity(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;
	private String categoryName;

	@Enumerated(EnumType.STRING)
	private Type type;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="incomeId")
	private Set<Income> listIncome ;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="expenseId")
	private Set<Expense> listExpense ;
	

	public Category() {
		
	}

	public Category(int categoryId, String categoryName, Type type, Set<Income> listIncome, Set<Expense> listExpense) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.type = type;
		this.listIncome = listIncome;
		this.listExpense = listExpense;
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Set<Income> getListIncome() {
		return listIncome;
	}

	public void setListIncome(Set<Income> listIncome) {
		this.listIncome = listIncome;
	}

	public Set<Expense> getListExpense() {
		return listExpense;
	}

	public void setListExpense(Set<Expense> listExpense) {
		this.listExpense = listExpense;
	}
	
	
}
