package main.java.com.carpooling.ride.Factory;

import main.java.com.carpooling.car.model.CarInterface;
import main.java.com.carpooling.ride.model.RideInterface;
import main.java.com.carpooling.ride.model.Ride;
import main.java.com.carpooling.user.model.UserInterface;

import java.util.Date;

public class RideFactory {
    private static int TEMPORARY_ID = -1;

    public static RideInterface createRide(int rideId, CarInterface vehicle, UserInterface driver, String origin, String destination, Date startDate) {
        return new Ride(rideId, vehicle, driver, origin, destination, startDate);
    }

    public static RideInterface createRide(CarInterface vehicle, UserInterface driver, String origin, String destination, Date startDate) {
        return new Ride(TEMPORARY_ID, vehicle, driver, origin, destination, startDate);
    }
}