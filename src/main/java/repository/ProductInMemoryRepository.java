package repository;

import domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductInMemoryRepository {  // Where interface for this class? Like ProductRepository or Database?  
    Map<Long, Product> products = new HashMap<>();  // fields mus be private
    Long productIdSequence = 0L;   // fields mus be private

    public Product insert(Product product) {
        product.setId(productIdSequence);
        products.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }
    public Product findProductById(Long id) {
        return products.get(id);
    }
}
