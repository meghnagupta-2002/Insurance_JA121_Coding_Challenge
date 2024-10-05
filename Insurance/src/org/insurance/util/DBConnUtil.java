package org.insurance.util;

import java.sql.Connection;
import java.sql.*;

public class DBConnUtil {
    
    // Static variable to store connection
    private static Connection connection;

    private DBConnUtil() {
        super();
    }

    // Establish connection; if error occurs, throw SQLException 
    public static Connection getConnection() throws SQLException {
        // Check if connection is null or closed
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Extract connection string from PropertyUtil (assuming this is where the connection string is defined)
                String connectionString = PropertyUtil.getPropertyString(); 
                connection = DriverManager.getConnection(connectionString);
                System.out.println("Database connection established.");
            } catch (SQLException err) {
                err.printStackTrace();
                throw new SQLException("Failed to establish Connection.", err);
            } catch (ClassNotFoundException err) {
                err.printStackTrace();
                throw new RuntimeException("Driver loading failed.", err); 
            }
        }
        return connection;
    }
}
