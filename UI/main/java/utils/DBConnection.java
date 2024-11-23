package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/sqe_test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "3722";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static String getTestData(String query) throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getString(1);
        }
        rs.close();
        stmt.close();
        conn.close();
        return null;
    }
}
