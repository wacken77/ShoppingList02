package com.javaguru.shoppingList.repository;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

//@Component
@Profile({"old"})
public class InMemoryDatabase implements Database {
    private Map<Long, Product> products = new HashMap<>();
    private Long productIdSequence = 0L;
    private Long categoryIdSequence = 0L;
    private Set<Category> categories = new HashSet<>();

    public Map<Long, Product> getProducts() {
        return products;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Product insert(Product product) {
        product.setId(productIdSequence);
        products.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    @Override
    public Product update(Product product, Long id) {
        Product product1 = new Product();
        return product1 ;
    }

    public Product findProductById(Long id) {
        return products.get(id);
    }

    @Override
    public Category insertCategory(Category category) {
        category.setId(categoryIdSequence);
        categoryIdSequence++;
        categories.add(category);
        return category;
    }

    @Override
    public Category findCategory(String name) {
        return null;
    }

    public Category findCategoryByCategory(String categoryName) {
        Category category = null;
        for (Category ct : categories) {
            if (ct.getCategoryName().equals(categoryName)) {
                category = ct;
            }
        }
        return category;
    }
}


