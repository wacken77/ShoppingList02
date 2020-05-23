package com.javaguru.shoppingList.ui.consoleMenu;

import com.javaguru.shoppingList.ui.CreateCategoryUIAction;
import com.javaguru.shoppingList.ui.ShowContentsUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CategoryMenuUI {
    boolean exitMenu;
    private CreateCategoryUIAction createCategoryUIAction;
    private ShowContentsUIAction showContentsUIAction;
    private FindCategoryUIAction findCategoryUIAction;

    @Autowired
    public CategoryMenuUI(CreateCategoryUIAction createCategoryUIAction,
                          ShowContentsUIAction showContentsUIAction,
                          FindCategoryUIAction findCategoryUIAction) {
        this.createCategoryUIAction = createCategoryUIAction;
        this.showContentsUIAction = showContentsUIAction;
        this.findCategoryUIAction = findCategoryUIAction;
    }

    public void execute() {
        exitMenu = false;
        while (!exitMenu) {
            Scanner scanner = new Scanner(System.in);
            try {
                printMenu();
                Integer userInput = Integer.valueOf(scanner.nextLine());
                categoryMenuSwitch(userInput);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error! Please try again.");
            }
        }
    }

    public void printMenu() {
        System.out.println("1. Create category");
        System.out.println("2. View categories");
        System.out.println("3. Categorize product");
        System.out.println("4. View contents");
        System.out.println("5. Find category");
        System.out.println("0. Exit to main menu");
    }

    public void categoryMenuSwitch(Integer userInput) {
        switch (userInput) {
            case 1:
                createCategoryUIAction.execute();
                break;
            case 2:
                showContentsUIAction.showAvailableCategories();
                break;
            case 3:
                createCategoryUIAction.categorizeProduct();
                break;
            case 4:
                showContentsUIAction.showAvailableProducts();
                break;
            case 5:
                findCategoryUIAction.execute();
                break;
            case 0:
                exitMenu = true;
        }
    }
}
