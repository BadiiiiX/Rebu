package main.java.com.carpooling.car.controller;

import main.java.com.carpooling.car.exception.CarNotFoundException;
import main.java.com.carpooling.car.exception.DatabaseException;
import main.java.com.carpooling.car.exception.DuplicateCarException;
import main.java.com.carpooling.car.exception.InvalidCarDataException;
import main.java.com.carpooling.car.model.Car;
import main.java.com.carpooling.car.service.CarService;
import main.java.com.carpooling.car.service.CarServiceImpl;

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
            Car car = new Car(licensePlate, passengersNumber);

            Car registeredCar = carService.register(car);
            System.out.println("Voiture enregistrée avec succès : " + registeredCar.getPlate());
            return registeredCar;

        } catch (InvalidCarDataException e) {
            System.err.println("Données invalides : " + e.getMessage());
            return null;
        } catch (DuplicateCarException e) {
            System.err.println("Erreur : " + e.getMessage());
            return null;
        }
    }

    public Car findCarById(Long id) {
        try {
            return carService.getByPlate(id);
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
            return carService.getAll();
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
            Car updatedCar = carService.update(car);
            System.out.println("Voiture mise à jour avec succès : " + updatedCar.getPlate());
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
            carService.delete(id);
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
