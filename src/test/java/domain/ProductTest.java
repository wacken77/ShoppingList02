package domain;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @org.junit.jupiter.api.Test
    void getReducedPrice() throws Exception {
        Product testProduct = new Product();
        testProduct.setPrice(BigDecimal.valueOf(100));
        testProduct.setDiscount(15);
    }
}