package fr.mmp.rebu.ride.service;

import fr.mmp.rebu.domain.AbstractService;
import fr.mmp.rebu.ride.factory.RideFactory;
import fr.mmp.rebu.ride.dao.RideDAO;
import fr.mmp.rebu.ride.model.RideInterface;

import java.util.List;

import static fr.mmp.rebu.user.factory.UserFactory.TEMPORARY_ID;

public class RideServiceImpl extends AbstractService implements RideService {
    private final RideDAO rideRepository;

    public RideServiceImpl(RideDAO rideRepository) {
        if (rideRepository == null) {
            throw new IllegalArgumentException("RideRepository cannot be null");
        }
        this.rideRepository = rideRepository;
    }

    public RideInterface createRide(RideInterface ride) {
        if (ride.getRideId() != TEMPORARY_ID) {
            throw new IllegalArgumentException("Cannot add an already existing ride (ID defined)");
        }

        var rideId = rideRepository.save(ride);

        return RideFactory.build(rideId, ride);
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

        var passenger = rideRepository.findPassengerById(passengerId);
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger with ID " + passengerId + " does not exist");
        }

        rideRepository.removePassenger(rideId, passengerId);
    }

    public void deleteRide(int rideId) {
        if (rideRepository.findById(rideId) == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        rideRepository.deleteById(rideId);
    }

    public List<RideInterface> findAll() {
        return rideRepository.findAll();
    }

    @Override
    public RideInterface findById(int rideId) {
        return rideRepository.findById(rideId);
    }

    public List<RideInterface> findByPassenger(int passengerId) {
        return rideRepository.findByPassengerId(passengerId);
    }

    @Override
    public List<RideInterface> findByDriver(int userId) {
        return rideRepository.findByDriverId(userId);
    }

}
