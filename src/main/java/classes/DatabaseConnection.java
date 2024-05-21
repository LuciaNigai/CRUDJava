package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection connection;

    private DatabaseConnection() {
        try {
            // Load the JDBC driver (optional step depending on your JDBC driver and environment)
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unifun_project", "root", "HelloAdmin23");
        } catch (ClassNotFoundException e) {
            // Handle the error when the driver class is not found
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle any SQL errors that occur during connection creation
            e.printStackTrace();
        }
    }

    private static volatile DatabaseConnection instance;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
