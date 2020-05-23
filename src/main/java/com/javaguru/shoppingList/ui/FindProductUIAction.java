package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindProductUIAction {
    private ProductService productService;

    @Autowired
    public FindProductUIAction(ProductService productService) {
        this.productService = productService;
    }

    public void execute() {
        Product findProductResult = productService.findProductById(getProductIdFromUser());
        System.out.println("The following product is found: ");
        System.out.println(findProductResult);
    }

    public long getProductIdFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        return Long.parseLong(scanner.nextLine());
    }
}
