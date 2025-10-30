package main.java.com.carpooling.Ride.Factory;

import main.java.com.carpooling.Ride.model.RideInterface;
import fr.mmp.rebu.User.UserInterface;
import fr.mmp.rebu.Vehicle.VehicleInterface;
import main.java.com.carpooling.Ride.model.Ride;

import java.util.Date;

public class RideFactory {
    private static int rideCounter = 0;

    public static RideInterface createRide(VehicleInterface vehicle, UserInterface driver, String origin, String destination, Date startDate) {
        rideCounter++;
        return new Ride(rideCounter, vehicle, driver, origin, destination, startDate);
    }
}