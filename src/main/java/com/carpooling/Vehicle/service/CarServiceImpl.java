package main.java.com.carpooling.Vehicle.service;

import main.java.com.carpooling.Vehicle.dao.CarDAO;
import main.java.com.carpooling.Vehicle.dao.CarDAOImpl;
import main.java.com.carpooling.Vehicle.exception.CarNotFoundException;
import main.java.com.carpooling.Vehicle.exception.DatabaseException;
import main.java.com.carpooling.Vehicle.exception.DuplicateCarException;
import main.java.com.carpooling.Vehicle.exception.InvalidCarDataException;
import main.java.com.carpooling.Vehicle.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CarServiceImpl implements CarService {

    private final CarDAO carDAO;

    private static final Pattern LICENSE_PLATE_PATTERN = Pattern.compile("^[A-Z]{2}-\\d{3}-[A-Z]{2}$");
    private static final int MIN_PASSENGERS = 1;
    private static final int MAX_PASSENGERS = 9;

    public CarServiceImpl() {
        this.carDAO = new CarDAOImpl();
    }

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public Car registerCar(Car car) throws InvalidCarDataException, DuplicateCarException, DatabaseException {
        validateCar(car);
        car.setLicensePlate(car.getLicensePlate().toUpperCase());
        return carDAO.create(car);
    }

    @Override
    public Car getCarById(Long id) throws CarNotFoundException, DatabaseException {
        if (id == null || id <= 0) {
            throw new CarNotFoundException("ID invalide : " + id);
        }
        Optional<Car> car = carDAO.findById(id);
        return car.orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car getCarByLicensePlate(String licensePlate) throws CarNotFoundException, DatabaseException {
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new CarNotFoundException("Plaque d'immatriculation invalide");
        }
        Optional<Car> car = carDAO.findByLicensePlate(licensePlate.toUpperCase());
        return car.orElseThrow(() -> new CarNotFoundException(licensePlate, true));
    }

    @Override
    public List<Car> getAllCars() throws DatabaseException {
        return carDAO.findAll();
    }

    @Override
    public List<Car> getCarsByOwner(Long ownerId) throws DatabaseException {
        throw new UnsupportedOperationException("La gestion des propriétaires n'est pas encore implémentée.");
    }

    @Override
    public Car updateCar(Car car) throws InvalidCarDataException, CarNotFoundException, DatabaseException {
        validateCar(car);
        if (car.getId() == null) {
            throw new InvalidCarDataException("ID de la voiture manquant pour la mise à jour");
        }
        car.setLicensePlate(car.getLicensePlate().toUpperCase());
        return carDAO.update(car);
    }

    @Override
    public void deleteCar(Long id) throws CarNotFoundException, DatabaseException {
        if (id == null || id <= 0) {
            throw new CarNotFoundException("ID invalide : " + id);
        }
        carDAO.hardDelete(id);
    }

    @Override
    public void validateCar(Car car) throws InvalidCarDataException {
        if (car == null) {
            throw new InvalidCarDataException("La voiture ne peut pas être null");
        }
        if (car.getLicensePlate() == null || car.getLicensePlate().trim().isEmpty()) {
            throw new InvalidCarDataException("La plaque d'immatriculation est obligatoire");
        }

        String plate = car.getLicensePlate().toUpperCase();
        if (!LICENSE_PLATE_PATTERN.matcher(plate).matches()) {
            throw new InvalidCarDataException("Format de plaque invalide. Format attendu : XX-123-XX (ex: AB-123-CD)");
        }

        int passengers = car.getPassengersNumber();
        if (passengers < MIN_PASSENGERS || passengers > MAX_PASSENGERS) {
            throw new InvalidCarDataException(
                    String.format("Le nombre de passagers doit être entre %d et %d", MIN_PASSENGERS, MAX_PASSENGERS)
            );
        }
    }

    @Override
    public int getTotalCars() throws DatabaseException {
        return carDAO.count();
    }
}
