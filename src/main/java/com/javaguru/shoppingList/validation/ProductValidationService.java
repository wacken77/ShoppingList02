package com.javaguru.shoppingList.validation;

import com.javaguru.shoppingList.domain.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class ProductValidationService {
    private Set<ProductValidationRule> validationRules = new HashSet<>();

    public ProductValidationService() {
        validationRules.add(new ProductNameValidationRule());
    }
    public void validate(Product product) {
        validationRules.forEach((s -> s.validate(product)));
    }
}
