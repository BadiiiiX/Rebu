package fr.mmp.rebu.ride.dao;

import fr.mmp.rebu.ride.model.Ride;
import fr.mmp.rebu.ride.model.RideInterface;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.List;

public interface RideDAO {
    List<RideInterface> findAll();
    RideInterface findById(int rideId);
    void addPassenger(int rideId, int passengerId);
    int save(RideInterface ride);
    void deleteById(int rideId);
    List<UserInterface> findPassengers(int rideId);
    List<RideInterface> findByPassengerId(int passengerId);
    void removePassenger(int rideId, int passengerId);
    List<RideInterface> findByDriverId(int userId);
    UserInterface findPassengerById(int rideId, int passengerId);
}