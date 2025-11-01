package fr.mmp.rebu.ride.view;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.cli.CliApp;
import fr.mmp.rebu.cli.CliUtils;
import fr.mmp.rebu.ride.Factory.RideFactory;
import fr.mmp.rebu.shared.view.ChooseActionView;

public class RidePassengerView {
    public static void choose() {
        CliUtils.clearScreen();

        var choice = CliUtils.askChoice("[RIDE] Que souhaîtez-vous faire ?",
                "Supprimer un trajet",
                "Afficher mes trajets",
                "Retour au menu principal");

        switch (choice) {
            case 1:
                delete();
                break;
            case 2:
                show();
                break;
            case 3:
                ChooseActionView.choose();
                break;
        }
    }

    private static void delete() {
        var rideId = CliUtils.askInt("Quel est l'ID du trajet que vous souhaitez supprimer ?");

        var ride = Rebu.getRideService().findById(rideId);

        if (ride == null) {
            CliUtils.error("Trajet inexistant, retour au menu...");
            CliUtils.pause();

            RidePassengerView.choose();

            return;
        }

        Rebu.getRideService().deleteRide(ride.getRideId());

        CliUtils.success("Trajet supprimé !");
        CliUtils.pause();

        RidePassengerView.choose();
    }

    private static void show() {
        var rides = Rebu.getRideService().findByPassenger(CliApp.getUserLogged().getUserId());

        if (rides.isEmpty()) {
            CliUtils.info("Vous n'avez aucun trajet, retour au menu...");
            CliUtils.pause();

            RidePassengerView.choose();

            return;
        }

        CliUtils.clearScreen();
        CliUtils.printTitle("Vos trajets");

        for (var ride : rides) {
            CliUtils.print("Trajet ID #" + ride.getRideId());
            CliUtils.print("Véhicule : " + ride.getCar().getPlate());
            CliUtils.print("Origine : " + ride.getOrigin());
            CliUtils.print("Destination : " + ride.getDestination());
            CliUtils.print("Date de départ : " + ride.getStartDate());
            CliUtils.printLine();
        }

        CliUtils.pause();

        RidePassengerView.choose();
    }
}
