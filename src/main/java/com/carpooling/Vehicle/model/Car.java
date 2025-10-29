package main.java.com.carpooling.Vehicle.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Car {
    private Long id;
    private String licensePlate;
    private int passengersNumber;

    public Car() {
    }

    public Car(String licensePlate, int passengersNumber) {
        this.licensePlate = licensePlate;
        this.passengersNumber = passengersNumber;
    }
    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPassengersNumber(int passengersNumber) {
        if (passengersNumber < 1) {
            throw new IllegalArgumentException("Le nombre de passagers doit être au moins 1");
        }
        this.passengersNumber = passengersNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && 
               Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", passengersNumber=" + passengersNumber +
                '}';
    }
}
