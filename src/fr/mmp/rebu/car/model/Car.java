package fr.mmp.rebu.car.model;

import fr.mmp.rebu.user.model.UserInterface;

public class Car implements CarInterface{
    private String plate;
    private int passengersNumber;
    private final UserInterface owner;

    public Car(String licensePlate, int passengersNumber, UserInterface owner) {
        this.setPlate(licensePlate);
        this.setPassengersNumber(passengersNumber);
        this.owner = owner;
    }

    private void setPlate(String plate) {
        this.plate = plate.toUpperCase();
    }

    private void setPassengersNumber(int passengersNumber) {
        if (passengersNumber < 1) {
            throw new IllegalArgumentException("Le nombre de passagers doit être au moins 1");
        }
        this.passengersNumber = passengersNumber;
    }
    
    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public int getPassengersNumber() {
        return passengersNumber;
    }

    @Override
    public UserInterface getOwner() {
        return this.owner;
    }
}
