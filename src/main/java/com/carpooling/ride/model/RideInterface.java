package main.java.com.carpooling.ride.model;

import main.java.com.carpooling.car.model.CarInterface;
import main.java.com.carpooling.user.model.UserInterface;

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
