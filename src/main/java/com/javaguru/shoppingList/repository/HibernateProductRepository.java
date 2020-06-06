package com.javaguru.shoppingList.repository;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
@Component

public class HibernateProductRepository implements Database {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Product insert(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        System.out.println("Updating database");
        sessionFactory.getCurrentSession().update(product);
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession()
                .createCriteria(Product.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        if (product == null) {
            System.out.println("Product with id = '" + id + "' was not found in database.");
        }
        return product;
    }

    @Override
    public boolean deleteProduct(Long id) {
       Product product = (Product) sessionFactory.getCurrentSession()
               .createCriteria(Product.class)
               .add(Restrictions.eq("id", id))
               .uniqueResult();
       if(product == null) {
           System.out.println("The product with id " + id + " is not found");
           return false;
       } else {
           sessionFactory.getCurrentSession()
                   .delete(product);
           return true;
       }
    }

    @Override
    public Map<Long, Product> getProducts() {
        return null;
    }

    public List<Product> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class)
                .list();
    }
}
