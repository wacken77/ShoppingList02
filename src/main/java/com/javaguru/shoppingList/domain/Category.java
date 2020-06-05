package com.javaguru.shoppingList.domain;

import javax.persistence.*;

@Entity
@Table(name = "categories")
    public class Category {

    @Column(name = "category_name")
        private String categoryName;

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

//        public Category(String categoryName) {
//            this.categoryName = categoryName;
//        }

        public String getCategoryName() {
            return categoryName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Category {\'" +
                    categoryName + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
