package com.javaguru.shoppingList.domain;
import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price = BigDecimal.valueOf(0);
    private BigDecimal reducedPrice;
    private Category category;
    private double discount;
    private String description = "empty";


    public void setDescription(String description) {
        this.description = description;
    }

        public String getDescription() {
        return description;
    }

    public BigDecimal getReducedPrice() {
        return reducedPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setReducedPrice(BigDecimal reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public double getDiscount() {
        return discount;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", price with discount=" + reducedPrice +
                ", category=" + category.getCategoryName() +
                ", discount=" + discount + "%" +
                ", description='" + description + '\'' +
                '}';
    }
}
