package main.java.com.carpooling.Ride.dao;

import main.java.com.carpooling.Ride.model.Ride;

import java.util.List;

public interface RideRepository  {
    List<Ride> findAll();
    Ride findById(long rideId);
    void addPassengerToRide(long rideId, long passengerId);
    void save(Ride ride);
    void deleteById(long rideId);
    List<Ride> findRidesByPassengerId(long passengerId);
    void removePassengerFromRide(long rideId, long passengerId);
}