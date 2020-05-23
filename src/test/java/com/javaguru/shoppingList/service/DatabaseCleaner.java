package com.javaguru.shoppingList.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseCleaner {
    @Value( "${jdbc.url}" )
    private String jdbcUrl;

    @Value( "${driverClass}" )
    private String driverClass;

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

    public void cleanDatabase() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from PRODUCTS";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (Throwable e) {
            System.out.println("Exception while execute addProduct");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }

    }
}
