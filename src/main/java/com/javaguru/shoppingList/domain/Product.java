package com.javaguru.shoppingList.domain;
import java.math.BigDecimal;
import javax.persistence.*;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_price", precision = 19, scale = 4)
    private BigDecimal price = BigDecimal.valueOf(0);

    @Column(name = "product_reduced_price", precision = 19, scale = 4)
    private BigDecimal reducedPrice;

    @ManyToOne
    @JoinColumn(name = "product_category")
    private Category category;

    @Column(name = "product_discount", precision = 19, scale = 4)
    private double discount;

    @Column(name = "product_description", nullable = false)
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
                ", discount=" + discount + "%" +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
