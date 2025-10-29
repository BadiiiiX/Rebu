package main.java.com.carpooling.Vehicle.controller;

import main.java.com.carpooling.Vehicle.exception.CarNotFoundException;
import main.java.com.carpooling.Vehicle.exception.DatabaseException;
import main.java.com.carpooling.Vehicle.exception.DuplicateCarException;
import main.java.com.carpooling.Vehicle.exception.InvalidCarDataException;
import main.java.com.carpooling.Vehicle.model.Car;
import main.java.com.carpooling.Vehicle.service.CarService;
import main.java.com.carpooling.Vehicle.service.CarServiceImpl;

import java.util.List;

public class CarController {

    private final CarService carService;

    public CarController() {
        this.carService = new CarServiceImpl();
    }

    public CarController(CarService carService) {
        this.carService = carService;
    }

    public Car registerCar(String licensePlate, int passengersNumber) {
        try {
            Car car = new Car();
            car.setLicensePlate(licensePlate);
            car.setPassengersNumber(passengersNumber);

            Car registeredCar = carService.registerCar(car);
            System.out.println("Voiture enregistrée avec succès : " + registeredCar.getLicensePlate());
            return registeredCar;

        } catch (InvalidCarDataException e) {
            System.err.println("Données invalides : " + e.getMessage());
            return null;
        } catch (DuplicateCarException e) {
            System.err.println("Erreur : " + e.getMessage());
            return null;
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return null;
        }
    }

    public Car findCarById(Long id) {
        try {
            return carService.getCarById(id);
        } catch (CarNotFoundException e) {
            System.err.println("Voiture non trouvée : " + e.getMessage());
            return null;
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return null;
        }
    }

    public Car findCarByPlate(String licensePlate) {
        try {
            return carService.getCarByLicensePlate(licensePlate);
        } catch (CarNotFoundException e) {
            System.err.println("Voiture non trouvée : " + e.getMessage());
            return null;
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return null;
        }
    }

    public List<Car> getAllCars() {
        try {
            return carService.getAllCars();
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return null;
        }
    }

    public List<Car> getCarsByOwner(Long ownerId) {
        try {
            return carService.getCarsByOwner(ownerId);
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return null;
        }
    }

    public Car updateCar(Car car) {
        try {
            Car updatedCar = carService.updateCar(car);
            System.out.println("Voiture mise à jour avec succès : " + updatedCar.getLicensePlate());
            return updatedCar;
        } catch (InvalidCarDataException e) {
            System.err.println("Données invalides : " + e.getMessage());
            return null;
        } catch (CarNotFoundException e) {
            System.err.println("Voiture non trouvée : " + e.getMessage());
            return null;
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return null;
        }
    }

    public boolean deleteCar(Long id) {
        try {
            carService.deleteCar(id);
            System.out.println("Voiture supprimée avec succès (ID : " + id + ")");
            return true;
        } catch (CarNotFoundException e) {
            System.err.println("Voiture non trouvée : " + e.getMessage());
            return false;
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return false;
        }
    }

    public int getTotalCars() {
        try {
            return carService.getTotalCars();
        } catch (DatabaseException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            return -1;
        }
    }
}
