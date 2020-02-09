package repository;

import domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductInMemoryRepository {
    Map<Long, Product> products = new HashMap<>();
    Long productIdSequence = 0L;

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
