package fr.mmp.rebu.ride.service;

import fr.mmp.rebu.ride.model.Ride;
import fr.mmp.rebu.ride.model.RideInterface;

import java.util.List;

public interface RideServiceInterface {
    void createRide(Ride ride);
    void addPassengerToRide(int rideId, int passengerId);
    void removePassengerFromRide(int rideId, int passengerId);
    void deleteRide(int rideId);
    List<RideInterface> getRides();
    List<RideInterface> getRidesByPassenger(int passengerId);
}
