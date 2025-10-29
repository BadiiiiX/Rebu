package fr.mmp.rebu.Ride;

import fr.mmp.rebu.User.UserInterface;
import fr.mmp.rebu.Vehicle.VehicleInterface;

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
    VehicleInterface getVehicle();
}
