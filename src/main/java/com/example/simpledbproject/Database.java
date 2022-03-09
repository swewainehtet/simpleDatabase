package com.example.simpledbproject;

import java.sql.*;

public class Database {
    private final String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=&serverTimezone=UTC";

    public ResultSet getResult(String cmd) {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(conn_url);
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void DBTest() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url);
            ResultSet rs = null;
            Statement stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM employees");
            while (rs != null && rs.next()) {
                System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
