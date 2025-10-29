package fr.mmp.rebu.Ride;

import fr.mmp.rebu.User.UserInterface;
import fr.mmp.rebu.Vehicle.VehicleInterface;

import java.util.Date;
import java.util.List;

public class Ride implements RideInterface {
    private int rideId;
    private VehicleInterface vehicle;
    private UserInterface driver;
    private List<UserInterface> passengers;
    private String origin;
    private String destination;
    private Date startDate;

    public Ride(int rideId, VehicleInterface vehicle, UserInterface driver, String origin, String destination, Date startDate) {
        this.rideId = rideId;
        this.vehicle = vehicle;
        this.driver = driver;
        this.origin = origin;
        this.destination = destination;
        this.startDate = startDate;
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
