package com.javaguru.shoppingList.repository;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;

import java.util.Map;
import java.util.Set;

public interface Database {

    Product insert(Product product);
    Product update(Product product, Long id);
    Product findProductById(Long id);
    Category insertCategory(Category category);
    Category findCategory(String name);
    Set<Category> getCategories();
    Map<Long, Product> getProducts();

}
