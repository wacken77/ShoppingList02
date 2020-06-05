package com.javaguru.shoppingList.repository;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
@Component

public class HibernateCategoryRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateCategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Category insertCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
        return category;
    }

    public Category findCategoryById(Long id) {
        Category category = (Category) sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        if (category == null) {
            System.out.println("Category with id = '" + id + "' was not found in database.");
        }
        return category;
    }

    public Category findCategory(String name) {
        Category category = (Category) sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .add(Restrictions.eq("categoryName", name))
                .uniqueResult();
        if (category == null) {
            System.out.println("Category with id = '" + name + "' was not found in database.");
        }
        System.out.println("HibernateRepository has found this " + category);
        return category;
    }

    public List<Category> showAllCategories() {
        return sessionFactory.getCurrentSession().createCriteria(Category.class)
                .list();
    }
}
