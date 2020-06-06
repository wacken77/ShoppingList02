package com.javaguru.shoppingList.repository;

import com.javaguru.shoppingList.domain.Category;
import com.javaguru.shoppingList.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

//@Component
//@Profile("local")
public class JDBCDatabase  {

    @Value( "${jdbc.url}" )
    private String jdbcUrl;

    @Value( "${driverClass}" )
    private String driverClass;

    //@Override
    public List<Product> findAll() {
        return null;
    }

    @Value( "${database.user.name}" )
    private String userName;

    @Value( "${database.user.password}" )
    private String password;

    protected Connection getConnection() {
        try{
            return DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to database");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception while closing connection to database");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

   // @Override
    public Product insert(Product product) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into PRODUCTS(product_id, product_name, product_price, product_reduced_price, product_category, product_discount, product_description) values(default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setBigDecimal(3, product.getReducedPrice());
            preparedStatement.setString(4, product.getCategory().getCategoryName());
            preparedStatement.setDouble(5, product.getDiscount());
            preparedStatement.setString(6, product.getDescription());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                product.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute addProduct");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return product;
    }

    //@Override
    public Product update(Product product) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update PRODUCTS set product_price=?, product_name=?, product_reduced_price=?, product_discount=?, product_description=?, product_category=? where product_id=?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, product.getPrice());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setBigDecimal(3, product.getReducedPrice());
            preparedStatement.setDouble(4, product.getDiscount());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getCategory().getCategoryName());
            //preparedStatement.setLong(7, id);
            preparedStatement.setLong(7, product.getId());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                product.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute addProduct");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return product;
    }

    //@Override
    public Product findProductById(Long id) {
        Product product = new Product();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from PRODUCTS where product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getBigDecimal("product_price"));
                product.setReducedPrice(resultSet.getBigDecimal("product_reduced_price"));
                product.setDiscount(resultSet.getDouble("product_discount"));
                product.setDescription(resultSet.getString("product_description"));

                String categoryStr = resultSet.getString("product_category").toUpperCase();
                //Category category = new Category(categoryStr);
                Category category = new Category();
                product.setCategory(category);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting product by id");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return product;
    }

//    @Override
//    public Category insertCategory(Category category) {
//        Connection connection = null;
//        try {
//            connection = getConnection();
//            String sql = "insert into CATEGORIES(category_id, category_name) values(default, ?)";
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, category.getCategoryName());
//            preparedStatement.executeUpdate();
//            ResultSet rs = preparedStatement.getGeneratedKeys();
//            if (rs.next()){
//                category.setId(rs.getLong(1));
//            }
//        } catch (Throwable e) {
//            System.out.println("Exception while execute addProduct");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            closeConnection(connection);
//        }
//        return category;
//    }

//@Override
//    public Category findCategory(String name) {
//    //Category category = new Category(null);
//    Category category = new Category();
//        Connection connection = null;
//        try {
//            connection = getConnection();
//            String sql = "select * from CATEGORIES where category_name = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                category.setId(resultSet.getLong("category_id"));
//                category.setCategoryName(resultSet.getString("category_name"));
//
//            }
//
//        } catch (Throwable e) {
//            System.out.println("Exception while getting product by id");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            closeConnection(connection);
//        }
//        return category;
//
//    }

//    @Override
//    public Set<Category> getCategories() {
//        Set<Category> categories = new HashSet<>();
//        //List<Category> categories = new ArrayList<>();
//        Connection connection = null;
//        try {
//            connection = getConnection();
//            String sql = "select * from CATEGORIES";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//
//                String categoryStr = "default";
//                //Category category = new Category(categoryStr);
//                Category category = new Category();
//                category.setId(resultSet.getLong("category_id"));
//                category.setCategoryName(resultSet.getString("category_name"));
//                categories.add(category);
//            }
//        } catch (Throwable e) {
//            System.out.println("Exception while getting products - getProducts");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            closeConnection(connection);
//        }
//        return categories;
//    }

    //@Override
    public Map<Long, Product> getProducts() {
        Map<Long, Product> products = new HashMap();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from PRODUCTS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getBigDecimal("product_price"));
                product.setReducedPrice(resultSet.getBigDecimal("product_reduced_price"));
                product.setDiscount(resultSet.getDouble("product_discount"));
                product.setDescription(resultSet.getString("product_description"));
                String categoryStr = resultSet.getString("product_category").toUpperCase();
                //Category category = new Category(categoryStr);
                Category category = new Category();
                product.setCategory(category);
                products.put(resultSet.getLong("product_id"), product);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting products - getProducts");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return products;
    }
}
