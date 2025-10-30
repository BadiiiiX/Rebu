package fr.mmp.rebu.ride.model;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.Date;
import java.util.List;

public interface RideInterface {
    int getRideId();
    String getOrigin();
    String getDestination();
    Date getStartDate();
    UserInterface getDriver();
    List<UserInterface> getPassengers();
    boolean addPassenger(UserInterface passenger);
    CarInterface getVehicle();
}
