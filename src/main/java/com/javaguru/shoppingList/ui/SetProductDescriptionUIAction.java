package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.service.ProductService;
import com.javaguru.shoppingList.service.SetDescriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SetProductDescriptionUIAction {
    private ProductService productService;

    @Autowired
    public SetProductDescriptionUIAction(ProductService productService) {
        this.productService = productService;
    }

    public long getProductIdFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void execute() {
        Product product = productService.findProductById(getProductIdFromUser());
        SetDescriptionResponse response = productService.setDescription(getProductDescriptionFromUser(), product);
        operationResultMessage(response);
    }

    public void operationResultMessage(SetDescriptionResponse response) {
        if(response.isSuccess()) {
            System.out.println("The new description has been successfully recorded");
        } else {
            System.out.println("The operation has failed");
            System.out.println("Error message: " + response.getErrorMessage());
        }
    }

    public String getProductDescriptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the description for the product: ");
        String description = scanner.nextLine();
        return description;
    }
}
