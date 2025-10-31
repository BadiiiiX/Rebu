package fr.mmp.rebu.shared.view;

import fr.mmp.rebu.cli.CliUtils;

public class ChooseActionView {

    public static void choose() {

        var choice = CliUtils.askChoice("Que souhaîtez-vous faire ?",
                "Gérer mes voitures",
                "Gérer mes trajets",
                "Voir les trajets disponibles");


        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }



    }

}
