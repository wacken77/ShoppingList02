package com.javaguru.shoppingList.service;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.repository.Database;
import com.javaguru.shoppingList.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductService {
    private Database repository;
    private ProductValidationService validationService;
@Autowired
    public ProductService(Database repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Transactional
    public Long createProduct(Product product) {
        validationService.validate(product);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
      Product productToBeFound = repository.findProductById(id);
      return productToBeFound;
    }

    public List<Product> showAvailableProducts() {
        return repository.findAll();
    }

    @Transactional
    public SetPriceResponse setPrice(BigDecimal price, Product product) {
        if(price.compareTo(BigDecimal.valueOf(0)) <= 0) {
            SetPriceResponse response = new SetPriceResponse(false, "Cannot be lower than 0 ");
            return response;
        } else {
            SetPriceResponse response = new SetPriceResponse(true, null);
            product.setPrice(price);
            product.setDiscount(0);
            product.setReducedPrice(price);
            return response;
        }
    }

    @Transactional
    public SetPriceResponse updatePrice(BigDecimal price, Product product) {
        if(price.compareTo(BigDecimal.valueOf(0)) <= 0) {
            SetPriceResponse response = new SetPriceResponse(false, "Cannot be lower than 0 ");
            return response;
        } else {
            SetPriceResponse response = new SetPriceResponse(true, null);
            product.setPrice(price);
            product.setDiscount(0);
            product.setReducedPrice(price);
            repository.update(product);
            return response;
        }
    }

    public SetDiscountResponse setDiscount(double discount, Product product) {

        if(discount >= 100) {
            SetDiscountResponse setDiscountResponse = new SetDiscountResponse(false, "Discount cannot be over 100%" );
            return setDiscountResponse;
        } else {
            BigDecimal newPrice = product.getPrice()
                    .subtract(product.getPrice()
                            .divide(BigDecimal.valueOf(100))
                            .multiply(BigDecimal.valueOf(discount)));
            product.setReducedPrice(newPrice);
            product.setDiscount(discount);
            repository.update(product);
            SetDiscountResponse setDiscountResponse = new SetDiscountResponse(true, null);
            return setDiscountResponse;
        }
    }

    public SetDescriptionResponse setDescription(String description, Product product) {
        if (description.length() < 3 || description.length() > 32) {
            SetDescriptionResponse response = new SetDescriptionResponse(false,
                    "The description cannot exceed 32 characters and be less than 3. ");
            return response;
        } else {
            product.setDescription(description);
            System.out.println(product);
            repository.update(product);
            SetDescriptionResponse response = new SetDescriptionResponse(true,
                    null);
            return response;
        }
    }

    //    public void showAvailableProducts2() {
//        for (Long name : repository.getProducts().keySet()) {
//            String key = name.toString();
//            String value = repository.getProducts().get(name).toString();
//            System.out.println(key + " " + value);
//        }
//    }
}

