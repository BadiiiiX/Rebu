package fr.mmp.rebu.car.factory;

import fr.mmp.rebu.car.model.Car;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.user.model.UserInterface;

public class CarFactory {

    public static CarInterface build(String plate, int passengersNumber, UserInterface owner) {
        return new Car(plate, passengersNumber, owner);
    }

}
