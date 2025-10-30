package main.java.com.carpooling.car.model;

import java.util.Objects;

public class Car implements CarInterface{
    private String plate;
    private int passengersNumber;

    public Car(String licensePlate, int passengersNumber) {
        this.setPlate(licensePlate);
        this.setPassengersNumber(passengersNumber);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getPlate(), car.getPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate);
    }

    @Override
    public String toString() {
        return "Car{licensePlate='" + plate + '\'' +
                ", passengersNumber=" + passengersNumber +
                '}';
    }
}
