package com.javaguru.shoppingList.service;

import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.repository.Database;
import com.javaguru.shoppingList.validation.ProductValidationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class ProductServiceMethodsTest {

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
    public void shouldSetTheDiscount() {
        Product victim = new Product();
        victim.setPrice(BigDecimal.valueOf(10));
        productService.setDiscount(20, victim);
        int expectedResult = BigDecimal.valueOf(8).intValue();
        int reducedPrice = victim.getReducedPrice().intValue();
        assertEquals(expectedResult, reducedPrice);
    }

    @Test
    public void shouldSetTheDescription() {
        Product victim = new Product();
        productService.setDescription("a nice product", victim);
        String expectedResult = "a nice product";
        String actualDescription = victim.getDescription();
        assertEquals(expectedResult, actualDescription);
    }

    @Test
    public void shouldReturnFailedResponseWhenDiscountIsBiggerThan100()  {
        Product victim = new Product();
        SetDiscountResponse response = productService.setDiscount(101, victim);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrorMessage(), "Discount cannot be over 100%");
    }

    @Test
    public void shouldReturnFailedResponseWhenDescriptionIsShorterThan3() {
        Product victim = new Product();
        SetDescriptionResponse response = productService.setDescription("oh", victim);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrorMessage(), "The description cannot exceed 32 characters and be less than 3. " );
    }

    @Test
    public void shouldReturnFailedResponseWhenDescriptionIsLongerThan32() {
        Product victim = new Product();
        SetDescriptionResponse response = productService.setDescription(
                "Peter Piper picked a peck of pickled peppers", victim);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrorMessage(), "The description cannot exceed 32 characters and be less than 3. " );
    }

    @Test
    public void shouldReturnFailedResonseWhenPriceIsBelowZero() {
        Product victim = new Product();
        SetPriceResponse response = productService.setPrice(BigDecimal.valueOf(-1), victim);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrorMessage(), "Cannot be lower than 0 ");
    }
}