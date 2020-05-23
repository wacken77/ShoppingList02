package com.javaguru.shoppingList.domain;

    public class Category {
        private String categoryName;
        private Long id;

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public Category(String categoryName) {
            this.categoryName = categoryName;
        }

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
