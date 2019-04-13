package com.quanlychitieu.dao;

import com.quanlychitieu.entity.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
