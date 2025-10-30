package fr.mmp.rebu.ride.Factory;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.ride.model.RideInterface;
import fr.mmp.rebu.ride.model.Ride;
import fr.mmp.rebu.user.model.UserInterface;

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