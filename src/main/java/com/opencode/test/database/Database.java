package com.opencode.test.database;

import java.sql.*;

public class Database {

    private static Database instance;

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    private Connection conn;

    private Database() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gamedb?serverTimezone=Europe/Samara", "root", "root"
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() {
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
    }
}