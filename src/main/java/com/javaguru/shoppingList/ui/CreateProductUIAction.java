package com.javaguru.shoppingList.ui;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
@Component
public class CreateProductUIAction {
    private ProductService productService;

    @Autowired
    public CreateProductUIAction(ProductService productService) {
        this.productService = productService;
    }

    public void execute() {
        Product product = new Product();
        product.setName(productNameInputByUser());
        productService.setPrice(productPriceInputByUser(), product);
        productService.createProduct(product);
        printOutCreatedProduct(product);
    }

    public String productNameInputByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        return name;
    }
    public BigDecimal productPriceInputByUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        return price;
    }
    public void printOutCreatedProduct(Product product) {
        System.out.println("Result: " + product.toString() + "with id " + product.getId() + " has been created.");
    }

}


