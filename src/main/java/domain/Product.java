package domain;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price = BigDecimal.valueOf(0);
    private BigDecimal reducedPrice;
    private Category category;
    private double discount;
    private String description;

    public BigDecimal getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(BigDecimal reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        try {
            if(price.compareTo(BigDecimal.valueOf(0)) <= 0) {
                throw new NumberFormatException();
            } else {
                this.price = price;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Price cannot be 0 or less");
        }
    }

    public void setDiscount(double discount) {
        try {
            if(discount >= 100) {
                throw new NumberFormatException();
            } else {
                reducedPrice = price.subtract(price.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(discount)));
                this.discount = discount;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Discount cannot be over 100%");
        }
    }

    public void setDescription(String description) {
        try {
            if(description.length() < 3 || description.length() > 32) {
                throw new RuntimeException();
            } else {
                this.description = description;
            }
        }
        catch (RuntimeException e) {
            System.out.println("Can't be less than 3 and more than 32 characters");
        }
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", price with discount=" + reducedPrice +
                ", category=" + category +
                ", discount=" + discount + "%" +
                ", description='" + description + '\'' +
                '}';
    }
}
