package com.javaguru.shoppingList.ui.consoleMenu;

import com.javaguru.shoppingList.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ProductMenuUI {
    boolean exitMenu;
    private CreateProductUIAction createProductUIAction;
    private FindProductUIAction findProductUIAction;
    private SetProductDiscountUIAction setProductDiscountUIAction;
    private SetProductDescriptionUIAction setProductDescriptionUIAction;
    private SetProductPriceUIAction setProductPriceUIAction;

    @Autowired
    public ProductMenuUI(CreateProductUIAction createProductUIAction,
                         FindProductUIAction findProductUIAction,
                         SetProductDiscountUIAction setProductDiscountUIAction,
                         SetProductDescriptionUIAction setProductDescriptionUIAction,
                                     SetProductPriceUIAction setProductPriceUIAction) {

        this.createProductUIAction = createProductUIAction;
        this.findProductUIAction = findProductUIAction;
        this.setProductDiscountUIAction = setProductDiscountUIAction;
        this.setProductDescriptionUIAction = setProductDescriptionUIAction;
        this.setProductPriceUIAction = setProductPriceUIAction;
    }

    public void execute() {
        exitMenu = false;
        while (!exitMenu) {
            Scanner scanner = new Scanner(System.in);
            try {
                printMenu();
                Integer userInput = Integer.valueOf(scanner.nextLine());
                productMenuSwitch(userInput);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("Error! Please try again.");
            }
        }
    }

    public void printMenu() {
        System.out.println("1. Create product");
        System.out.println("2. Find product by id");
        System.out.println("3. Set a discount");
        System.out.println("4. Describe the product");
        System.out.println("5. Change price of the product");
        System.out.println("0. Exit to main menu");
    }

    public void productMenuSwitch(Integer userInput) {
        switch (userInput) {
            case 1:
                createProductUIAction.execute();
                break;
            case 2:
                findProductUIAction.execute();
                break;
            case 3:
                setProductDiscountUIAction.execute();
                break;
            case 4:
                setProductDescriptionUIAction.execute();
                break;
            case 5:
                setProductPriceUIAction.execute();
                break;
            case 0:
                exitMenu = true;
        }
    }
}
