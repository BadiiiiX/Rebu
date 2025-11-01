package fr.mmp.rebu.cli;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.Scanner;

public class CliUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String askString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static Date askDate(String prompt) {
        System.out.print(prompt);
        Date date = null;
        while (date == null) {
            String input = scanner.nextLine();
            try {
                date = new Date(Date.parse(input));
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Format de date invalide. Veuillez réessayer (ex: Mon Jan 01 00:00:00 GMT 2024).");
            }
        }

        return date;
    }

    public static int askInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }

    public static int askIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = askInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("❌ Le nombre doit être entre " + min + " et " + max + ".");
        }
    }

    public static double askDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrée invalide. Veuillez entrer un nombre décimal.");
            }
        }
    }

    public static boolean askYesNo(String prompt) {
        while (true) {
            String response = askString(prompt + " (o/n): ").toLowerCase().trim();
            if (response.equals("o") || response.equals("oui")) {
                return true;
            } else if (response.equals("n") || response.equals("non")) {
                return false;
            }
            System.out.println("❌ Répondez par 'o' (oui) ou 'n' (non).");
        }
    }

    public static int askChoice(String prompt, String... options) {
        printList(prompt, options);
        return askIntInRange("Votre choix: ", 1, options.length);
    }

    public static void printList(String prompt, String... options) {
        System.out.println(prompt);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public static void success(String message) {
        System.out.println("✅ " + message);
    }

    public static void error(String message) {
        System.out.println("❌ " + message);
    }

    public static void info(String message) {
        System.out.println("ℹ️  " + message);
    }

    public static void printTitle(String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title);
        System.out.println("=".repeat(50));
    }

    public static void pause() {
        System.out.print("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(String prompt) {
        System.out.println(prompt);
    }

    public static void printLine() {
        System.out.println();
    }
}