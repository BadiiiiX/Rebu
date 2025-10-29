package fr.mmp.rebu.Vehicle;

public class CarController {
    public static VehicleInterface create(String licensePlate, int passengersNumber) {
        Car car = new Car(licensePlate, passengersNumber);
        return car;
    }

    public static void remove(String licensePlate) {
    }

    public static VehicleInterface get(String licensePlate) {
        return null;
    }
}