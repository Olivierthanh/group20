package com.quanlychitieu.dao;

import com.quanlychitieu.entity.PasswordResetToken;
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
public class PasswordResetTokenDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        System.out.println(sessionFactory);
        return sessionFactory.getCurrentSession();
    }

    public boolean savePasswordResetToken(PasswordResetToken passwordResetToken) {
        boolean isSaved;
        try {
            getSession().saveOrUpdate(passwordResetToken);
            isSaved = true;
        }
        catch (HibernateException ex) {
            isSaved = false;
        }
        return isSaved;
    }

    @SuppressWarnings("unchecked")
    public PasswordResetToken getPasswordResetTokenByToken(String token) {
        PasswordResetToken passwordResetToken = null;
        Criteria criteria = getSession().createCriteria(PasswordResetToken.class);
        criteria.add(Restrictions.eq("token", token));
        List<PasswordResetToken> tokens = (List<PasswordResetToken>) criteria.list();
        if (!tokens.isEmpty()) {
            passwordResetToken = tokens.get(0);
        }
        return passwordResetToken;
    }

    public boolean deletePasswordResetToken(PasswordResetToken token) {
        boolean flag;
        try {
            getSession().delete(token);
            flag = true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
