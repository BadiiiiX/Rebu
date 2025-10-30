package main.java.com.carpooling.Ride.model;

import fr.mmp.rebu.User.UserInterface;
import fr.mmp.rebu.Vehicle.VehicleInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ride implements RideInterface {
    private final int rideId;
    private final VehicleInterface vehicle;
    private final UserInterface driver;
    private final List<UserInterface> passengers;
    private final String origin;
    private final String destination;
    private final Date startDate;

    public Ride(int rideId, VehicleInterface vehicle, UserInterface driver, String origin, String destination, Date startDate) {
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
    public VehicleInterface getVehicle() {
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
