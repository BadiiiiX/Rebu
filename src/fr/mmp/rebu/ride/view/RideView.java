package fr.mmp.rebu.ride.view;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.cli.CliApp;
import fr.mmp.rebu.cli.CliUtils;
import fr.mmp.rebu.view.ChooseActionView;

public class RideView {
    public static void choose() {
        CliUtils.clearScreen();

        var choice = CliUtils.askChoice("[RIDE] Que souhaîtez-vous faire ?",
                "Afficher les trajets disponibles",
                "Reserver un trajet",
                "Retour au menu principal");

        switch (choice) {
            case 1:
                show();
                break;
            case 2:
                reserve();
                break;
            case 3:
                ChooseActionView.choose();
        }
    }

    private static void show() {
        CliUtils.clearScreen();
        CliUtils.print("[RIDE] Trajets disponibles :");
        System.out.println("--------------------------------------------------");
        Rebu.getRideService().findAll().forEach((ride) -> {
            CliUtils.print(ride.toString());
            System.out.println("--------------------------------------------------");
        });

        choose();
    }

    private static void reserve() {
        CliUtils.clearScreen();

        var rideId = CliUtils.askInt("[RIDE] Entrez le numéro du trajet que vous souhaitez réserver : ");

        var ride = Rebu.getRideService().findById(rideId);
        if (ride == null) {
            CliUtils.error("[RIDE] Trajet introuvable.");
            choose();
            return;
        }

        Rebu.getRideService().addPassengerToRide(rideId, CliApp.getUserLogged().getUserId());

        CliUtils.success("[RIDE] Trajet réservé avec succès !");
        choose();
    }
}
