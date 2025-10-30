package fr.mmp.rebu.ride.service;

import fr.mmp.rebu.domain.AbstractService;
import fr.mmp.rebu.ride.dao.RideDAO;
import fr.mmp.rebu.ride.model.Ride;
import fr.mmp.rebu.ride.model.RideInterface;

import java.util.List;

public class RideService extends AbstractService implements RideServiceInterface {
    private final RideDAO rideRepository;

    public RideService(RideDAO rideRepository) {
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

    public void addPassengerToRide(int rideId, int passengerId) {
        if (rideRepository.findById(rideId) == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        if (rideRepository.findById(passengerId) != null) {
            throw new IllegalArgumentException("Passenger with ID " + passengerId + " already exist");
        }

        rideRepository.addPassenger(rideId, passengerId);
    }

    public void removePassengerFromRide(int rideId, int passengerId) {
        var ride = rideRepository.findById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        var passenger = rideRepository.findById(passengerId);
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger with ID " + passengerId + " does not exist");
        }

        // Assuming RideRepository has a method to remove a passenger
        rideRepository.removePassenger(rideId, passengerId);
    }

    public void deleteRide(int rideId) {
        if (rideRepository.findById(rideId) == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        rideRepository.deleteById(rideId);
    }

    public List<RideInterface> getRides() {
        return rideRepository.findAll();
    }

    public List<RideInterface> getRidesByPassenger(int passengerId) {
        return rideRepository.findByPassengerId(passengerId);
    }

}
