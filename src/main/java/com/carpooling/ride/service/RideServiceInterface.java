package main.java.com.carpooling.ride.service;

import main.java.com.carpooling.ride.model.Ride;
import main.java.com.carpooling.ride.model.RideInterface;

import java.util.List;

public interface RideServiceInterface {
    void createRide(Ride ride);
    void addPassengerToRide(int rideId, int passengerId);
    void removePassengerFromRide(int rideId, int passengerId);
    void deleteRide(int rideId);
    List<RideInterface> getRides();
    List<RideInterface> getRidesByPassenger(int passengerId);
}
