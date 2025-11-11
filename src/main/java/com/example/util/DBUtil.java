package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // --- UPDATE THESE DETAILS ---
    private static final String URL = "jdbc:mysql://localhost:3306/course_registration";
    private static final String USER = "root"; // Or your MySQL username
    private static final String PASSWORD = "@Ravi181910"; // Your MySQL password
    // ----------------------------

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC Driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}