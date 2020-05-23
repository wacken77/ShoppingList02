package com.javaguru.shoppingList.service;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.repository.Database;
import com.javaguru.shoppingList.validation.CategoryValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class        CategoryService {
    private Database dataBase;
    @Autowired
    public CategoryService(Database dataBase) {
        this.dataBase = dataBase;
    }

    public Long addCategory(Category category) {
          validate(category);
          dataBase.insertCategory(category);
          return category.getId();

    }
    public Category findCategoryByName(String categoryName) {
        return dataBase.findCategory(categoryName);
    }

    private void validate(Category category) {
        if(category.getCategoryName() == null || category.getCategoryName().equals("")) {
            throw new CategoryValidationException("The category must not be empty");
        }
        if(dataBase.findCategory(category.getCategoryName()).getCategoryName() != null) {
            throw new CategoryValidationException("The category name must be unique");
        }
    }

    private void validateName(Category category) {
        if(category.getCategoryName() == null || category.getCategoryName().equals("")) {
            throw new CategoryValidationException("The category must not be empty");
        }
    }

    public SetCategoryResponse setCategory(Product product) {
        System.out.println(product);
        dataBase.update(product, product.getId());
        SetCategoryResponse response = new SetCategoryResponse(true, null);
        return response;
    }

    public Set<Category> getCategoriesFromDatabase() {
        return dataBase.getCategories();
    }

}
