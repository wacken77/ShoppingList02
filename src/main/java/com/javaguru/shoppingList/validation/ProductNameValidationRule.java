package com.javaguru.shoppingList.validation;

import com.javaguru.shoppingList.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getName() == null) {
            throw new ProductValidationException("Product name must be not null.");
        }
        if (product.getName().length() < 3 || product.getName().length() > 32) {
            throw new ProductValidationException("The name is too long or too short");
        }
        if (product.getDiscount() >= 100) {
            throw new NumberFormatException();
        }
    }
}
