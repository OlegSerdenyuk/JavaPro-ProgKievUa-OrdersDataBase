package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    final static String url = "jdbc:mysql://localhost:3306/OrdersDataBase?useLegacyDatetimeCode=false&serverTimezone=Europe/Kiev" ;
    final static String login="root";
    final static String password="MySQL164897";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
