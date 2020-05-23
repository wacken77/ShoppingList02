package com.javaguru.shoppingList.validation;

public class ProductValidationException extends IllegalArgumentException {
    public ProductValidationException(String message) {
        super(message);
    }
}
