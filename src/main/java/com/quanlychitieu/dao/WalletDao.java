package com.quanlychitieu.dao;

import com.quanlychitieu.entity.PasswordResetToken;
import com.quanlychitieu.entity.Wallet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WalletDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        System.out.println(sessionFactory);
        return sessionFactory.getCurrentSession();
    }

    public Wallet getWalletByWalletId(int walletId) {
        Wallet wallet = null;
        Criteria criteria = getSession().createCriteria(Wallet.class);
        criteria.add(Restrictions.eq("walletId", walletId));
        List<Wallet> result = criteria.list();
        if (!result.isEmpty()) {
            wallet = result.get(0);
        }
        return wallet;
    }
    public boolean deleteWallet(Wallet wallet) {
    	boolean flag;
        try {
            getSession().delete(wallet);
            
            flag = true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
        
    }
    
 
    
}
