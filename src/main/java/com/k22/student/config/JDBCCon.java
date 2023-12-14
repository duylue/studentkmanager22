package com.k22.student.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCCon {
    public final static String dbName = "/ktsql";
    public final static String username = "root";
    public final static String password = "123456";
    public final static String url = "jdbc:mysql://localhost:3306" + dbName;

    public static Connection connection() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
