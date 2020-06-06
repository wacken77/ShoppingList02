package com.javaguru.shoppingList.ui.consoleMenu;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.service.CategoryService;
import com.javaguru.shoppingList.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindCategoryUIAction {

    private CategoryService categoryService;

    @Autowired
    public FindCategoryUIAction(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;

    }
    public void execute() {
        System.out.println(categoryService.findCategoryByName(categoryNameInputByUser()));
    }
    public void execute2() {
        System.out.println(categoryService.findCategoryById(categoryIdInputByUser()));
    }

    public String categoryNameInputByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category name: ");
        String categoryName = scanner.nextLine();
        return categoryName;
    }

    public Long categoryIdInputByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category ID: ");
        Long id = scanner.nextLong();
        return id;
    }

    public void printOutFoundCategory(Category newCategory) {
        System.out.println("Result: " + newCategory.toString() + "with id " + newCategory.getId() + " has been found.");
    }
}
