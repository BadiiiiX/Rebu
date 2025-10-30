package main.java.com.carpooling.Ride.service;

import main.java.com.carpooling.Ride.dao.RideRepository;
import main.java.com.carpooling.Ride.model.Ride;

import java.util.List;

public class RideService {
    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        if (rideRepository == null) {
            throw new IllegalArgumentException("RideRepository cannot be null");
        }
        this.rideRepository = rideRepository;
    }

    public void createRide(Ride ride) {
        if (rideRepository.findById(ride.getRideId()) != null) {
            throw new IllegalArgumentException("Ride with ID " + ride.getRideId() + " already exists");
        }

        rideRepository.save(ride);
    }

    public void addPassengerToRide(long rideId, long passengerId) {
        if (rideRepository.findById(rideId) == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        if (rideRepository.findById(passengerId) != null) {
            throw new IllegalArgumentException("Passenger with ID " + passengerId + " already exist");
        }

        rideRepository.addPassengerToRide(rideId, passengerId);
    }

    public void removePassengerFromRide(long rideId, long passengerId) {
        var ride = rideRepository.findById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        var passenger = rideRepository.findById(passengerId);
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger with ID " + passengerId + " does not exist");
        }

        // Assuming RideRepository has a method to remove a passenger
        rideRepository.removePassengerFromRide(rideId, passengerId);
    }

    public void deleteRide(long rideId) {
        if (rideRepository.findById(rideId) == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        rideRepository.deleteById(rideId);
    }

    public List<Ride> getRides() {
        return rideRepository.findAll();
    }

    public List<Ride> getRidesByPassenger(long passengerId) {
        return rideRepository.findRidesByPassengerId(passengerId);
    }

}
