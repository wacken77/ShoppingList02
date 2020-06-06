package com.javaguru.shoppingList.service;

import com.javaguru.shoppingList.ApplicationConfiguration;
import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import com.javaguru.shoppingList.repository.Database;
import com.javaguru.shoppingList.repository.HibernateCategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class JDBCdatabaseTest {
    private Database database;
    private HibernateCategoryRepository categoryRepository;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
         database = applicationContext.getBean(Database.class);
         categoryRepository = applicationContext.getBean(HibernateCategoryRepository.class);
         DatabaseCleaner databaseCleaner = applicationContext.getBean(DatabaseCleaner.class);
         databaseCleaner.cleanDatabase();
    }

    @Test
    public void createProductTest() {
        Product product = new Product();
        product.setName("Good product");
        product.setPrice(BigDecimal.valueOf(10));
        product.setReducedPrice(BigDecimal.valueOf(10));
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
        product.setDiscount(0);
        product.setDescription("Good product");
        database.insert(product);

        product.setPrice(BigDecimal.valueOf(20));
        product.setReducedPrice(BigDecimal.valueOf(10));
        product.setDescription("An updated product");
        product.setDiscount(50);
        database.update(product);

        Product updatedProduct = database.findProductById(product.getId());
        assertTrue(updatedProduct.getPrice().compareTo(BigDecimal.valueOf(20)) == 0);
        assertTrue(updatedProduct.getReducedPrice().compareTo(BigDecimal.valueOf(10)) == 0);
        assertEquals(product.getDiscount(), 50, 0.01);
    }

    @Test
    public void findProductByIdTest() {
        Product product = new Product();
        product.setName("Good product");
        product.setPrice(BigDecimal.valueOf(10));
        product.setReducedPrice(BigDecimal.valueOf(10));
        product.setDiscount(0);
        product.setDescription("Good product");
        database.insert(product);

        Product searchedProduct = database.findProductById(product.getId());
        assertEquals(searchedProduct.getDescription(), product.getDescription());
        assertTrue(searchedProduct.getPrice().compareTo(product.getPrice()) == 0);
        //assertEquals(searchedProduct.getPrice().stripTrailingZeros(), product.getPrice().stripTrailingZeros());
        assertEquals(searchedProduct.getName(), product.getName());
    }

    @Test
    public void createCategoryTest() {
        Category testCategory = new Category();
        testCategory.setCategoryName("test category");
        assertEquals(testCategory.getId(), null);
        categoryRepository.insertCategory(testCategory);
        assertNotNull(testCategory.getId());
    }

    @Test
    public void findCategoryTest() {
        Category testCategory = new Category();
        testCategory.setCategoryName("test category");
        categoryRepository.insertCategory(testCategory);
        Category searchedCategory = categoryRepository.findCategory("test category");
        assertNotNull(searchedCategory.getId());
    }

    @Test
    public void categorizeProductTest() {
        Category testCategory = new Category();
        testCategory.setCategoryName("test category");
        categoryRepository.insertCategory(testCategory);

        Product product = new Product();
        product.setName("Good product");
        product.setPrice(BigDecimal.valueOf(10));
        product.setReducedPrice(BigDecimal.valueOf(10));
        product.setDiscount(0);
        product.setDescription("Good product");
        database.insert(product);

        Product toBeUpdated = database.findProductById(product.getId());
        toBeUpdated.setCategory(categoryRepository.findCategory("test category"));
        database.update(toBeUpdated);
    }
    @Test
    public void deleteProductTest() {
        Product product = new Product();
        product.setName("Bad product");
        product.setPrice(BigDecimal.valueOf(100));
        product.setReducedPrice(BigDecimal.valueOf(50));
        product.setDiscount(50);
        product.setDescription("Some awful product");
        database.insert(product);
        assertNotNull(database.findProductById(product.getId()));
        database.deleteProduct(product.getId());
        assertEquals(database.findProductById(product.getId()), null);
    }
}
