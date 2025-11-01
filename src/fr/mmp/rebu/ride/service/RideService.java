package fr.mmp.rebu.ride.service;

import fr.mmp.rebu.ride.model.RideInterface;

import java.util.List;

public interface RideService {
    RideInterface createRide(RideInterface ride);
    void addPassengerToRide(int rideId, int passengerId);
    void removePassengerFromRide(int rideId, int passengerId);
    void deleteRide(int rideId);
    List<RideInterface> findAll();
    RideInterface findById(int rideId);
    List<RideInterface> findByPassenger(int passengerId);
    List<RideInterface> findByDriver(int userId);
}
