package com.quanlychitieu.dao;

import com.quanlychitieu.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        System.out.println(sessionFactory);
        return sessionFactory.getCurrentSession();
    }

    public boolean saveUser(User user) {
        boolean flag;
        if (!isUserExisted(user)) {
            getSession().save(user);
            flag = true;
        }
        else flag = false;
        return flag;
    }

    @SuppressWarnings("unchecked")
    public User getUserByEmail(String email) {
        User user = null;
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        List<User> result = (List<User>) criteria.list();
        if (!result.isEmpty()){
            user = result.get(0);
        }
        return user;
    }

    public boolean isUserExisted(User user) {
        User foundUser = getUserByEmail(user.getEmail());
        return foundUser != null;
    }
}
