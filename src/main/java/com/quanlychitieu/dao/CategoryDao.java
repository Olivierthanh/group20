package com.quanlychitieu.dao;

import com.quanlychitieu.entity.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        System.out.println(sessionFactory);
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Category> getAllCategory() {
        Criteria criteria = getSession().createCriteria(Category.class);
        return criteria.list();
    }
}
