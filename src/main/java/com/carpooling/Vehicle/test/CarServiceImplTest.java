package main.java.com.carpooling.Vehicle.test;

import main.java.com.carpooling.Vehicle.model.Car;
import main.java.com.carpooling.Vehicle.service.CarService;
import main.java.com.carpooling.Vehicle.service.CarServiceImpl;

public class CarServiceImplTest {
    public static void main(String[] args) {
        testRegisterCar();
        testGetCarById();
        testGetCarByLicensePlate();
        testGetAllCars();
        testGetCarsByOwner();
        testUpdateCar();
        testDeleteCar();
        testValidateCar();
        testGetTotalCars();
    }

    public static void testRegisterCar() {
        Car car = new Car("AB-123-CD", 4);
        CarService carService = new CarServiceImpl();
        try {
            Car registeredCar = carService.registerCar(car);
            System.out.println("testRegisterCar: SUCCESS - " + registeredCar);
        } catch (Exception e) {
            System.err.println("testRegisterCar: FAILED - " + e.getMessage());
        }
    }

    public static void testGetCarById() {
        CarService carService = new CarServiceImpl();
        try {
            Car car = carService.getCarById(1L);
            System.out.println("testGetCarById: SUCCESS - " + car);
        } catch (Exception e) {
            System.err.println("testGetCarById: FAILED - " + e.getMessage());
        }
    }

    public static void testGetCarByLicensePlate() {
        CarService carService = new CarServiceImpl();
        try {
            Car car = carService.getCarByLicensePlate("AB-123-CD");
            System.out.println("testGetCarByLicensePlate: SUCCESS - " + car);
        } catch (Exception e) {
            System.err.println("testGetCarByLicensePlate: FAILED - " + e.getMessage());
        }
    }

    public static void testGetAllCars() {
        CarService carService = new CarServiceImpl();
        try {
            var cars = carService.getAllCars();
            System.out.println("testGetAllCars: SUCCESS - Found " + cars.size() + " cars");
        } catch (Exception e) {
            System.err.println("testGetAllCars: FAILED - " + e.getMessage());
        }
    }

    public static void testGetCarsByOwner() {
        CarService carService = new CarServiceImpl();
        try {
            var cars = carService.getCarsByOwner(1L);
            System.out.println("testGetCarsByOwner: SUCCESS - Found " + cars.size() + " cars");
        } catch (Exception e) {
            System.err.println("testGetCarsByOwner: FAILED - " + e.getMessage());
        }
    }

    public static void testUpdateCar() {
        CarService carService = new CarServiceImpl();
        try {
            Car car = new Car(1L, "XY-999-ZZ", 5);
            Car updatedCar = carService.updateCar(car);
            System.out.println("testUpdateCar: SUCCESS - " + updatedCar);
        } catch (Exception e) {
            System.err.println("testUpdateCar: FAILED - " + e.getMessage());
        }
    }

    public static void testDeleteCar() {
        CarService carService = new CarServiceImpl();
        try {
            carService.deleteCar(1L);
            System.out.println("testDeleteCar: SUCCESS");
        } catch (Exception e) {
            System.err.println("testDeleteCar: FAILED - " + e.getMessage());
        }
    }

    public static void testValidateCar() {
        CarService carService = new CarServiceImpl();
        try {
            Car car = new Car("AB-123-CD", 4);
            carService.validateCar(car);
            System.out.println("testValidateCar: SUCCESS");
        } catch (Exception e) {
            System.err.println("testValidateCar: FAILED - " + e.getMessage());
        }
    }

    public static void testGetTotalCars() {
        CarService carService = new CarServiceImpl();
        try {
            int total = carService.getTotalCars();
            System.out.println("testGetTotalCars: SUCCESS - Total: " + total);
        } catch (Exception e) {
            System.err.println("testGetTotalCars: FAILED - " + e.getMessage());
        }
    }
}