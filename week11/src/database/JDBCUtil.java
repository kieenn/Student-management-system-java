package database;

import javax.swing.*;
import java.sql.*;

import static view.view.getTableModel;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        Connection c;
        com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
        String Url = "jdbc:mySQL://localhost:3306/students";
        String username = "root";
        String password = "";
        c = DriverManager.getConnection(Url, username, password);

        return c;
    }

    public static void closeConnection(Connection c) throws SQLException {
        if (c != null) {
            c.close();
        }
    }

}
