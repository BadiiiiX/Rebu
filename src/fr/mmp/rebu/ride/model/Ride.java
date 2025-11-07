package fr.mmp.rebu.ride.model;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.user.model.UserInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ride implements RideInterface {
    private final int rideId;
    private final CarInterface vehicle;
    private final UserInterface driver;
    private final List<UserInterface> passengers;
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
    public CarInterface getCar() {
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
    public boolean removePassenger(UserInterface passenger) {
        if  (!passengers.contains(passenger)) {
            return false;
        }
        return passengers.remove(passenger);
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

    @Override
    public String toString() {
        return "Trajet n°" + rideId +
                "\nConducteur : " + driver.getUsername() +
                "\nVéhicule : " + vehicle.getPlate() + " avec " + vehicle.getPassengersNumber() + " places" +
                "\nDépart : " + origin +
                "\nDestination : " + destination +
                "\nDate de départ : " + startDate +
                "\nPassagers : " + (passengers.isEmpty() ? "Aucun" : passengers.stream().map(UserInterface::getUsername).reduce((a, b) -> a + ", " + b).orElse(""));
    }
}
