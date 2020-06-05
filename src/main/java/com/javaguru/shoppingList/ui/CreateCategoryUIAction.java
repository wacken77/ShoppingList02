package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.service.CategoryService;
import com.javaguru.shoppingList.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateCategoryUIAction {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public CreateCategoryUIAction(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    public void execute() {
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryNameInputByUser());
        categoryService.addCategory(newCategory);
        printOutCreatedCategory(newCategory);
    }

    public String categoryNameInputByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category name: ");
        String categoryName = scanner.nextLine();
        return categoryName;
    }

    public void printOutCreatedCategory(Category newCategory) {
        System.out.println("Result: " + newCategory.toString() + "with id " + newCategory.getId() + " has been created.");
    }

    public void  categorizeProduct() {
        long id = getProductIdFromUser();
        Product existingProduct = productService.findProductById(id);
        addCategoryToProduct(existingProduct);
    }

    public void addCategoryToProduct(Product product) {
        if (product.getId() == null) {
            System.out.println("The id doesn't exist, please try again ");
        } else {
            attachCategoryToProduct(product);
        }
    }

    public void attachCategoryToProduct(Product product) {
        String categoryName = getCategoryNameFromUser();
        Category existingCategory = categoryService.findCategoryByName(categoryName);
        if (existingCategory.getId() == null) {
            System.out.println("Wrong category name, please try again ");
        } else {
            categoryService.setCategory(product, existingCategory);
        }
    }

    public String getCategoryNameFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category name");
        return scanner.nextLine();
    }

    public long getProductIdFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        return Long.parseLong(scanner.nextLine());
    }
}
