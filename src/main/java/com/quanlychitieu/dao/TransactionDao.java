package com.quanlychitieu.dao;

import com.quanlychitieu.entity.Transaction;
import com.quanlychitieu.entity.TransactionType;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TransactionDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        System.out.println(sessionFactory);
        return sessionFactory.getCurrentSession();
    }

    public boolean deleteTransaction(Transaction transaction) {
        boolean flag;
        try {
            getSession().delete(transaction);
            flag = true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }
    
    public boolean addTransaction(Transaction transaction) {
    	 boolean flag;
         try {
             getSession().save(transaction);
             flag = true;
         }
         catch (HibernateException ex) {
             ex.printStackTrace();
             flag = false;
         }
         return flag;
    }

    public boolean updateTransaction(Transaction transaction) {
        boolean flag;
        try {
            getSession().update(transaction);
            flag = true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }
    
  
    public List<Transaction> getTransactionByTransactionType(int walletId , TransactionType type) {
    	String sql = "FROM transaction where walletId ="+walletId+" and type ='"+type+"'";
    	@SuppressWarnings("unchecked")
		List<Transaction> list = (List<Transaction>) getSession().createQuery(sql).list();
		List<Transaction> result = list;
    	return result;
    }
    
    public List<Transaction> getTransactionByWalletId(int walletId){
    	String sql = "FROM transaction where walletId ="+walletId+"";
    	@SuppressWarnings("unchecked")
		List<Transaction> list = (List<Transaction>) getSession().createQuery(sql).list();
		List<Transaction> result = list;
    	return result;
    }

    @SuppressWarnings("unchecked")
    public Transaction getTransactionByTransactionId(int transactionId) {
        Transaction transaction = null;
        Criteria criteria = getSession().createCriteria(Transaction.class);
        criteria.add(Restrictions.eq("transactionId", transactionId));
        List<Transaction> transactions = (List<Transaction>) criteria.list();
        if (!transactions.isEmpty())  {
            transaction = transactions.get(0);
        }
        return transaction;
    }
  
    public int deleteTransactionByWalletId(int walletId) {
    	String hql = "delete FROM transaction where walletId = :walletId";
    	Query query = getSession().createQuery(hql);
    	query.setParameter("walletId", walletId);
    	int result = query.executeUpdate();
    	return result;
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> getListTransactionByDateList(int walletId, List<Date> dateList) {
        Criteria criteria = getSession().createCriteria(Transaction.class);
        criteria.add(Restrictions.eq("wallet.walletId", walletId));
        criteria.add(Restrictions.in("date", dateList));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Transaction>) criteria.list();
    }
}
