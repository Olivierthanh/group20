package com.quanlychitieu.service;

import com.quanlychitieu.dao.CategoryDao;
import com.quanlychitieu.entity.Category;
import com.quanlychitieu.entity.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getCategoryListByType(List<Category> categoryList, TransactionType type) {
        List<Category> result = new ArrayList<Category>();
        for (Category category: categoryList) {
            if (category.getType() == type) {
                result.add(category);
            }
        }
        return result;
    }

    public List<Category> getAllCategoryList() {
        return categoryDao.getAllCategory();
    }
}
