package database.expensesharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExpenseDatabaseUtil {

    public static Connection databaseConnnection;
    private static final String URL = "jdbc:mysql://localhost:3306/expense_sharing";
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "12345678@123"; // replace with your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getExpenseTableConnection() throws SQLException {
        if (databaseConnnection == null) {
            databaseConnnection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return databaseConnnection;
    }
}
