package console;

import domain.Product;
import service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {
    private ProductService productService = new ProductService();
    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }
    public void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.createProduct(product);
        System.out.println("Result: " + product.toString() + "with id " + product.getId() + " has been created.");
    }
    public void findProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        long id = scanner.nextLong();
        Product findProductResult = productService.getRepository().findProductById(id);
        System.out.println(findProductResult);
    }
}
