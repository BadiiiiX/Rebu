package main.java.com.carpooling.ride.dao;

import main.java.com.carpooling.ride.model.Ride;
import main.java.com.carpooling.ride.model.RideInterface;
import main.java.com.carpooling.user.model.UserInterface;

import java.util.List;

public interface RideDAO {
    List<RideInterface> findAll();
    Ride findById(int rideId);
    void addPassenger(int rideId, int passengerId);
    int save(RideInterface ride);
    void deleteById(int rideId);
    List<UserInterface> findPassengers(int rideId);
    List<RideInterface> findByPassengerId(int passengerId);
    void removePassenger(int rideId, int passengerId);
}