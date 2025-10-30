package main.java.com.carpooling.ride.model;

import main.java.com.carpooling.car.model.CarInterface;
import main.java.com.carpooling.user.model.UserInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ride implements RideInterface {
    private final int rideId;
    private final CarInterface vehicle;
    private final UserInterface driver;
    private final List<main.java.com.carpooling.user.model.UserInterface> passengers;
    private final String origin;
    private final String destination;
    private final Date startDate;

    public Ride(int rideId, CarInterface vehicle, UserInterface driver, String origin, String destination, Date startDate) {
        this.rideId = rideId;
        this.vehicle = vehicle;
        this.driver = driver;
        this.origin = origin;
        this.destination = destination;
        this.startDate = startDate;
        passengers = new ArrayList<>();
    }

    @Override
    public int getRideId() {
        return rideId;
    }

    @Override
    public CarInterface getVehicle() {
        return vehicle;
    }

    @Override
    public UserInterface getDriver() {
        return driver;
    }

    @Override
    public List<UserInterface> getPassengers() {
        return passengers;
    }

    @Override
    public boolean addPassenger(UserInterface passenger) {
        if (passengers.contains(passenger)) {
            return false;
        }
        return passengers.add(passenger);
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }
}
