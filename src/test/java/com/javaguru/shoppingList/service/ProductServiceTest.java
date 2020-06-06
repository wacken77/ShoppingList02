package com.javaguru.shoppingList.service;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.repository.Database;
import com.javaguru.shoppingList.validation.ProductValidationException;
import com.javaguru.shoppingList.validation.ProductValidationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

public class ProductServiceTest {

    private Database repository;
    private ProductValidationService validationService;

    private ProductService productService;

    @Before
    public void setup() {
        repository = Mockito.mock(Database.class);
        validationService = Mockito.mock(ProductValidationService.class);
        productService = new ProductService(repository, validationService);
    }

    @Test
    public void shouldAddProductToDatabase() {
        Product product = new Product();
        Product createdProduct = new Product();
        createdProduct.setId(1L);
        Mockito.when(repository.insert(product)).thenReturn(createdProduct);
        long productId = productService.createProduct(product);
        assertEquals(productId, 1L);
        Mockito.verify(validationService).validate(product);
    }

    @Test
    public void shouldFindProductByID() {
        Product testProduct = new Product();
        Mockito.when(repository.findProductById(0L)).thenReturn(testProduct);
        assertEquals(productService.findProductById(0L), testProduct);
    }

//    @Test
//    public void shouldTrueException() {
//        Product product = new Product();
//        doThrow(new ProductValidationException("Error")).when(validationService).validate(product);
//        productService.createProduct(product);
//    }
}