package service;

import domain.Product;
import repository.ProductInMemoryRepository;
import validation.ProductValidationService;

public class ProductService {
    private ProductInMemoryRepository repository = new ProductInMemoryRepository(); // Implement Dependency Injection through constructor.

    public ProductInMemoryRepository getRepository() {  // After Dependency Injection implementation remove this method
        return repository;
    }

    public void setRepository(ProductInMemoryRepository repository) {  // Method not used! 
        this.repository = repository;   // Do not insetr in your code not used peaces of code.
    }

    private ProductValidationService validationService = new ProductValidationService();

    public Long createProduct(Product product) {
        validationService.validate(product);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

}
