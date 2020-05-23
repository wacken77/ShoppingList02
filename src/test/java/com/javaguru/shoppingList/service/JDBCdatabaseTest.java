package com.javaguru.shoppingList.service;

import com.javaguru.shoppingList.ApplicationConfiguration;
import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.repository.Database;
import com.javaguru.shoppingList.ui.consoleMenu.ConsoleUI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JDBCdatabaseTest {
    private Database database;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
         database = applicationContext.getBean(Database.class);
         DatabaseCleaner databaseCleaner = applicationContext.getBean(DatabaseCleaner.class);
         databaseCleaner.cleanDatabase();
    }

    @Test
    public void createProductTest() {
        Product product = new Product();
        product.setName("Good product");
        product.setPrice(BigDecimal.valueOf(10));
        product.setReducedPrice(BigDecimal.valueOf(10));
        Category category = new Category("Default");
        product.setCategory(category);
        product.setDiscount(0);
        product.setDescription("Good product");
        assertEquals(product.getId(), null);
        database.insert(product);
        assertNotNull(product.getId());
    }

    @Test
    public void updateProductTest() {
        Product product = new Product();
        product.setName("Good product");
        product.setPrice(BigDecimal.valueOf(10));
        product.setReducedPrice(BigDecimal.valueOf(10));
        Category category = new Category("Default");
        product.setCategory(category);
        product.setDiscount(0);
        product.setDescription("Good product");
        database.insert(product);

        product.setPrice(BigDecimal.valueOf(20));
        product.setReducedPrice(BigDecimal.valueOf(10));
        product.setDescription("An updated product");
        product.setDiscount(50);
        database.update(product, product.getId());

        Product updatedProduct = database.findProductById(product.getId());
        assertEquals(updatedProduct.getPrice().stripTrailingZeros(), BigDecimal.valueOf(20).stripTrailingZeros());
        assertEquals(updatedProduct.getReducedPrice().stripTrailingZeros(), BigDecimal.valueOf(10).stripTrailingZeros());
        assertEquals(product.getDiscount(), 50, 0.01);
    }

    @Test
    public void findProductByIdTest() {
        Product product = new Product();
        product.setName("Good product");
        product.setPrice(BigDecimal.valueOf(10));
        product.setReducedPrice(BigDecimal.valueOf(10));
        Category category = new Category("Default");
        product.setCategory(category);
        product.setDiscount(0);
        product.setDescription("Good product");
        database.insert(product);

        Product searchedProduct = database.findProductById(product.getId());
        assertEquals(searchedProduct.getDescription(), product.getDescription());
        assertEquals(searchedProduct.getPrice().stripTrailingZeros(), product.getPrice().stripTrailingZeros());
        assertEquals(searchedProduct.getName(), product.getName());
    }

    @Test
    public void createCategoryTest() {
        Category testCategory = new Category("Test category");
        assertEquals(testCategory.getId(), null);
        database.insertCategory(testCategory);
        assertNotNull(testCategory.getId());
    }

    @Test
    public void findCategoryTest() {
        Category testCategory = new Category("Test category");
        database.insertCategory(testCategory);
        Category searchedCategory = database.findCategory("Test category");
        assertNotNull(searchedCategory.getId());
    }
}
