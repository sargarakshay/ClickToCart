package dao.utildb;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBConnection {
    private static Connection connection;

    public MySQLDBConnection() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/clicktocart";
        String username = "root";
        String password = "Akshay@1710290";
        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
