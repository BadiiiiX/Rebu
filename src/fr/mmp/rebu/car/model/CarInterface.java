package fr.mmp.rebu.car.model;

import fr.mmp.rebu.user.model.UserInterface;

public interface CarInterface {
    String getPlate();
    int getPassengersNumber();
    UserInterface getOwner();
}
