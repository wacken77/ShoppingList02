package com.javaguru.shoppingList.ui.consoleMenu;

import com.javaguru.shoppingList.ui.ShowContentsUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class ConsoleUI {

    boolean exitMainMenu;
    private ProductMenuUI productMenuUI;
    private CategoryMenuUI categoryMenuUI;
    private ShowContentsUIAction showContentsUIAction;

    @Autowired
    public ConsoleUI( ShowContentsUIAction showContentsUIAction,
                     ProductMenuUI productMenuUI,
                     CategoryMenuUI categoryMenuUI) {

        this.showContentsUIAction = showContentsUIAction;
        this.productMenuUI = productMenuUI;
        this.categoryMenuUI = categoryMenuUI;
    }

    public void execute() {
        while (!exitMainMenu) {
            Scanner scanner = new Scanner(System.in);
            try {
                printMenu();
                Integer userInput = Integer.valueOf(scanner.nextLine());
                mainMenuSwitch(userInput);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error! Please try again.");
            }
        }
    }

    public void mainMenuSwitch(Integer userInput) {
        switch (userInput) {
            case 1:
                productMenuUI.execute();
                break;
            case 2:
                categoryMenuUI.execute();
                break;
            case 3:
                showContentsUIAction.showAvailableProducts();
                break;
            case 0:
                exitMainMenu = true;
        }
    }

    public void printMenu() {
        System.out.println("1. Product menu");
        System.out.println("2. Category menu");
        System.out.println("3. Show contents");
        System.out.println("0. Exit");
    }
}