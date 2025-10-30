package main.java.com.carpooling.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final String DRIVER;

    private static final String PROPERTIES_FILE = "database.properties";
    
    private static Connection connection = null;

    static {
        Properties props = new Properties();
        String urlTemp = null;
        String userTemp = null;
        String passwordTemp = null;
        String driverTemp = null;

        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IOException("Fichier de configuration introuvable : " + PROPERTIES_FILE);
            }

            props.load(input);

            urlTemp = props.getProperty("db.url");
            userTemp = props.getProperty("db.user");
            passwordTemp = props.getProperty("db.password");
            driverTemp = props.getProperty("db.driver");

            if (urlTemp == null || userTemp == null || passwordTemp == null || driverTemp == null) {
                throw new IllegalArgumentException("Paramètres manquants dans " + PROPERTIES_FILE);
            }

        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier " + PROPERTIES_FILE + " : " + e.getMessage());
        }

        URL = urlTemp;
        USER = userTemp;
        PASSWORD = passwordTemp;
        DRIVER = driverTemp;
    }

    private DatabaseConnection() {}
    
    /**
     * Obtenir une connexion à la base de données
     * @return Connection l'objet de connexion
     * @throws SQLException si une erreur survient lors de la connexion
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(DRIVER);
                
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
