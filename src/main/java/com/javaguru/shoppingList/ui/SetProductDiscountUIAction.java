package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.service.ProductService;
import com.javaguru.shoppingList.service.SetDiscountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SetProductDiscountUIAction {
    private ProductService productService;

    @Autowired
    public SetProductDiscountUIAction(ProductService productService) {
        this.productService = productService;
    }

    public long getProductIdFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void execute() {
        Product product = productService.findProductById(getProductIdFromUser());
        SetDiscountResponse response = productService.setDiscount(getDiscountFromUser(), product);
        operationResultMessage(response);
    }

    public void operationResultMessage(SetDiscountResponse response) {
        if (response.isSuccess()) {
            System.out.println("The operation is successful");
        } else {
            System.out.println("Operation has failed");
            System.out.println("Error message = " + response.getErrorMessage());
        }
    }

    public double getDiscountFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the discount in percent: ");
        double discount = scanner.nextDouble();
        return discount;
    }
}
