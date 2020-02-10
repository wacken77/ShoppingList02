package domain;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    // Write tests for each class like:  ProductNameValidationRule, ProductValidationService, ProductInMemoryRepository
    
    
    // What this test check??  Where assertions? Please write proper test case:
    // 1. prepare test data
    // 2. invoke production code
    // 3. assert results
    
    @org.junit.jupiter.api.Test
    void getReducedPrice() throws Exception {   
        Product testProduct = new Product();
        testProduct.setPrice(BigDecimal.valueOf(100));
        testProduct.setDiscount(15);
    }
}
