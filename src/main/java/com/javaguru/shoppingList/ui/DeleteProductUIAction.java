package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteProductUIAction {

    private ProductService productService;

@Autowired
    public DeleteProductUIAction(ProductService productService) {
        this.productService = productService;
    }

    public void execute() {
    productService.deleteProduct(getProductIdFromUser());
    }


    public long getProductIdFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        return Long.parseLong(scanner.nextLine());
    }
}
