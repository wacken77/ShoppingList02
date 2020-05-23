package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.service.ProductService;
import com.javaguru.shoppingList.service.SetPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
@Component
public class SetProductPriceUIAction {
    private ProductService productService;
    @Autowired
    public SetProductPriceUIAction(ProductService productService) {
        this.productService = productService;
    }

    public long getProductIdFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        return Long.parseLong(scanner.nextLine());
    }

    public BigDecimal productPriceInputByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        return price;
    }

    public void execute() {
        Product product = productService.findProductById(getProductIdFromUser());
        SetPriceResponse response = productService.setPrice(productPriceInputByUser(),product);
        operationResultMessage(response);
    }

    public void operationResultMessage(SetPriceResponse response) {
        if(response.isSuccess()) {
            System.out.println("The new price is successfully set. ");
        } else {
            System.out.println("The operation has failed");
            System.out.println("Error message: " + response.getErrorMessage());
        }
    }
}
