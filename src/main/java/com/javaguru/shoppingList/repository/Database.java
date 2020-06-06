package com.javaguru.shoppingList.repository;
import com.javaguru.shoppingList.domain.Product;
import java.util.List;
import java.util.Map;


public interface Database {

    Product insert(Product product);
    //Product update(Product product, Long id);
    Product update(Product product);
    Product findProductById(Long id);
    boolean deleteProduct(Long id);
    Map<Long, Product> getProducts();
    List<Product> findAll();

}
