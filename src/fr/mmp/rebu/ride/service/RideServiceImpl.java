package fr.mmp.rebu.ride.service;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.domain.AbstractService;
import fr.mmp.rebu.ride.dao.RideDAO;
import fr.mmp.rebu.ride.dto.RidePassengerEventDto;
import fr.mmp.rebu.ride.event.components.RideCreatedEvent;
import fr.mmp.rebu.ride.event.components.RideDeletedEvent;
import fr.mmp.rebu.ride.event.components.RidePassengerAddedEvent;
import fr.mmp.rebu.ride.event.components.RidePassengerRemovedEvent;
import fr.mmp.rebu.ride.factory.RideFactory;
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

        var createdRide = RideFactory.build(rideId, ride);

        var createdEvent = new RideCreatedEvent(createdRide);
        Rebu.getEventDispatcher().fire(createdEvent);

        return createdRide;
    }

    public void addPassengerToRide(int rideId, int passengerId) {

        var ride = rideRepository.findById(rideId);

        if (ride == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        if (rideRepository.findPassengerById(rideId, passengerId) != null) {
            throw new IllegalArgumentException("Passenger with ID " + passengerId + " already exist");
        }

        rideRepository.addPassenger(rideId, passengerId);

        var user = Rebu.getUserService().findUserById(passengerId);
        var passengerAddedDto = new RidePassengerEventDto(ride, user);

        var passengerAddedEvent = new RidePassengerAddedEvent(passengerAddedDto);
        Rebu.getEventDispatcher().fire(passengerAddedEvent);
    }

    public void removePassengerFromRide(int rideId, int passengerId) {
        var ride = rideRepository.findById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        var passenger = rideRepository.findPassengerById(rideId, passengerId);
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger with ID " + passengerId + " does not exist");
        }

        rideRepository.removePassenger(rideId, passengerId);

        var passengerRemovedDto = new RidePassengerEventDto(ride, passenger);
        var passengerRemovedEvent = new RidePassengerRemovedEvent(passengerRemovedDto);
        Rebu.getEventDispatcher().fire(passengerRemovedEvent);
    }

    public void deleteRide(int rideId) {
        if (rideRepository.findById(rideId) == null) {
            throw new IllegalArgumentException("Ride with ID " + rideId + " does not exist");
        }

        rideRepository.deleteById(rideId);

        var rideDeletedEvent = new RideDeletedEvent(rideId);
        Rebu.getEventDispatcher().fire(rideDeletedEvent);
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
