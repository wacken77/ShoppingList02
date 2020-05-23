package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.service.CategoryService;
import com.javaguru.shoppingList.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ShowContentsUIAction {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ShowContentsUIAction(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    public void showAvailableCategories() {
        System.out.println("Here's the list of all categories: ");
        Iterator<Category> i = categoryService.getCategoriesFromDatabase().iterator();
        while (i.hasNext())
            System.out.println(i.next());
    }

    public void showAvailableProducts() {
        productService.showAvailableProducts();
    }
}
