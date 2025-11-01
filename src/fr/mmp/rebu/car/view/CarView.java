package fr.mmp.rebu.car.view;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.car.factory.CarFactory;
import fr.mmp.rebu.cli.CliApp;
import fr.mmp.rebu.cli.CliUtils;

public class CarView {


    public static void choose() {

        CliUtils.clearScreen();

        var choice = CliUtils.askChoice("[CAR] Que souhaîtez-vous faire ?",
                "Ajouter une voiture",
                "Supprimer une voiture",
                "Afficher mes voitures");

        switch (choice) {
            case 1:
                CarView.create();
                break;
            case 2:
                CarView.delete();
                break;
            case 3:
                CarView.show();
                break;
        }

    }

    public static void create(){
        var plate =  CliUtils.askString("[CAR] Quelle est la plaque d'immatriculation de votre voiture ?");
        var nbOfPlaces =  CliUtils.askInt("[CAR] Combien de place dispose votre voiture ?");

        var car = CarFactory.build(plate, nbOfPlaces, CliApp.getUserLogged());

        car = Rebu.getCarService().createCar(car);

        CliApp.setIsDriver(true);
        CliUtils.success("Voiture créé !");
        CliUtils.pause();

        CarView.choose();
    }

    public static void delete() {
        var plate = CliUtils.askString("Quelle est la plaque d'immatriculation de la voiture que vous souhaitez supprimer ?");

        var car = Rebu.getCarService().findCarByPlate(plate);

        if(car == null){
            CliUtils.error("Plaque inexistante, retour au menu...");
            CliUtils.pause();

            CarView.choose();

            return;
        }

        Rebu.getCarService().deleteCar(car.getPlate());
        CliUtils.success("Voiture supprimée !");
        CliUtils.pause();

        CarView.choose();
    }

    public static void show(){

        var cars = Rebu.getCarService().findCarsByOwner(CliApp.getUserLogged().getUserId());

        if(cars.isEmpty()){
            CliUtils.error("Vous n'avez aucune voiture.");
            CliUtils.pause();

            CarView.choose();

            return;
        }

        CliUtils.printList("Voici la liste de vos véhicules", String.valueOf(cars));

        CliUtils.pause();

        CarView.choose();
    }


}
