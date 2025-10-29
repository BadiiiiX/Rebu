package main.java.com.carpooling;

import main.java.com.carpooling.util.DatabaseConnection;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test de connexion à la bdd");
        if (DatabaseConnection.testConnection()) {
            System.out.println("Connexion réussie");

            DatabaseConnection.closeConnection();
        } else {
            System.err.println("Échec de la connexion à la bdd");
        }
    }
}
