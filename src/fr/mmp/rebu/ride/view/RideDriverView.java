package fr.mmp.rebu.ride.view;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.cli.CliApp;
import fr.mmp.rebu.cli.CliUtils;
import fr.mmp.rebu.ride.factory.RideFactory;
import fr.mmp.rebu.view.ChooseActionView;

public class RideDriverView {
    public static void choose() {
        CliUtils.clearScreen();

        if (!CliApp.getIsDriver()) {
            RidePassengerView.choose();
        }

        var choice = CliUtils.askChoice("[RIDE] Que souhaîtez-vous faire ?",
                "Ajouter un trajet",
                "Supprimer un trajet",
                "Afficher mes trajets",
                "Retour au menu principal");

        switch (choice) {
            case 1:
                create();
                break;
            case 2:
                delete();
                break;
            case 3:
                show();
                break;
            case 4:
                ChooseActionView.choose();
        }
    }

    private static void create() {
        var plate = CliUtils.askString("[RIDE] Quelle est la plaque d'immatriculation du véhicule pour ce trajet ?");
        var car = Rebu.getCarService().findCarByPlate(plate);
        var origin = CliUtils.askString("[RIDE] Quelle est l'origine de votre trajet ?");
        var destination = CliUtils.askString("[RIDE] Quelle est la destination de votre trajet ?");
        var startDate = CliUtils.askDate("[RIDE] Quelle est la date de départ de votre trajet ?");

        var ride = RideFactory.build(car, CliApp.getUserLogged(), origin, destination, startDate);
        ride = Rebu.getRideService().createRide(ride);

        CliUtils.success("Trajet créé !");
        CliUtils.pause();

        RideDriverView.choose();
    }

    private static void delete() {
        var rideId = CliUtils.askInt("Quel est l'ID du trajet que vous souhaitez supprimer ?");

        var ride = Rebu.getRideService().findById(rideId);

        if (ride == null) {
            CliUtils.error("Trajet inexistant, retour au menu...");
            CliUtils.pause();

            RideDriverView.choose();

            return;
        }

        Rebu.getRideService().deleteRide(ride.getRideId());

        CliUtils.success("Trajet supprimé !");
        CliUtils.pause();

        RideDriverView.choose();
    }

    private static void show() {
        var rides = Rebu.getRideService().findByDriver(CliApp.getUserLogged().getUserId());

        if (rides.isEmpty()) {
            CliUtils.info("Vous n'avez aucun trajet, retour au menu...");
            CliUtils.pause();

            RideDriverView.choose();

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

            for (var passenger : ride.getPassengers()) {
                CliUtils.print(" - Passager : " + passenger.getUsername());
            }

            CliUtils.printLine();
        }

        CliUtils.pause();

        RideDriverView.choose();
    }
}
