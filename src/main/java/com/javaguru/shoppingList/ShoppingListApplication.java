package com.javaguru.shoppingList;

import com.javaguru.shoppingList.ui.consoleMenu.ConsoleUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ShoppingListApplication {
    public static void main(String[] args) {
//for Spring config
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


        ConsoleUI consoleUI = applicationContext.getBean(ConsoleUI.class);
        consoleUI.execute();
    }
}
