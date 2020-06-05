package com.javaguru.shoppingList.service;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.repository.Database;
import com.javaguru.shoppingList.repository.HibernateCategoryRepository;
import com.javaguru.shoppingList.validation.CategoryValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class     CategoryService {
    private Database dataBase;
    private HibernateCategoryRepository categoryRepository;

    @Autowired
    public CategoryService(HibernateCategoryRepository categoryRepository, Database dataBase) {
        this.dataBase = dataBase;
        this.categoryRepository = categoryRepository;
    }

    public Long addCategory(Category category) {
          validate(category);
          categoryRepository.insertCategory(category);
          return category.getId();

    }
    @Transactional
    public Category findCategoryByName(String categoryName) {
        Category foundCategory = categoryRepository.findCategory(categoryName);
        System.out.println("Category service got this: " + foundCategory);
        return categoryRepository.findCategory(categoryName);
    }

    public Category findCategoryById(Long id) {
        //return dataBase.findCategory(categoryName);
        return categoryRepository.findCategoryById(id);
    }

    private void validate(Category category) {
        if(category.getCategoryName() == null || category.getCategoryName().equals("")) {
            throw new CategoryValidationException("The category must not be empty");
        }
        if(categoryRepository.findCategory(category.getCategoryName()) != null) {
            throw new CategoryValidationException("The category name must be unique");
        }
    }

    private void validateName(Category category) {
        if(category.getCategoryName() == null || category.getCategoryName().equals("")) {
            throw new CategoryValidationException("The category must not be empty");
        }
    }

    @Transactional
    public SetCategoryResponse setCategory(Product product, Category category) {
        System.out.println(product);
        product.setCategory(category);
        System.out.println(product);
        dataBase.update(product);
        return new SetCategoryResponse(true, null);
    }

    public List<Category> getCategoriesFromDatabase() {
        return categoryRepository.showAllCategories();
    }

}
