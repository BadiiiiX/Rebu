package main.java.com.carpooling.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/carpooling_db";
    private static final String USER = "carpooling_user";
    private static final String PASSWORD = "carpooling_password";
    
    private static Connection connection = null;
    
    private DatabaseConnection() {
    }
    
    /**
     * Obtenir une connexion à la base de données
     * @return Connection l'objet de connexion
     * @throws SQLException si une erreur survient lors de la connexion
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                
                Properties properties = new Properties();
                properties.setProperty("user", USER);
                properties.setProperty("password", PASSWORD);
                properties.setProperty("ssl", "false");
                
                connection = DriverManager.getConnection(URL, properties);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver PostgreSQL non trouvé", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
    public static boolean testConnection() {
        try {
            Connection connection = getConnection();
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Erreur lors du test de connexion : " + e.getMessage());
            return false;
        }
    }
}
