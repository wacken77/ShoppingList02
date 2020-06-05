package com.javaguru.shoppingList.repository;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Database {

    Product insert(Product product);
    //Product update(Product product, Long id);
    Product update(Product product);
    Product findProductById(Long id);
    //Category insertCategory(Category category);
    //Category findCategory(String name);
    //Set<Category> getCategories();
    //List<Category> getCategories();
    Map<Long, Product> getProducts();
    List<Product> findAll();

}
