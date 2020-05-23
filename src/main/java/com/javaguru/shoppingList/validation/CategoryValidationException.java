package com.javaguru.shoppingList.validation;

public class CategoryValidationException extends IllegalArgumentException {
    public CategoryValidationException(String message) {
        super(message);
    }
}
