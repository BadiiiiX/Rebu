package main.java.com.carpooling.car.service;

import main.java.com.carpooling.car.dao.CarDAO;
import main.java.com.carpooling.car.dao.CarDAOImpl;
import main.java.com.carpooling.car.exception.CarNotFoundException;
import main.java.com.carpooling.car.exception.DatabaseException;
import main.java.com.carpooling.car.exception.DuplicateCarException;
import main.java.com.carpooling.car.exception.InvalidCarDataException;
import main.java.com.carpooling.car.model.Car;
import main.java.com.carpooling.car.model.CarInterface;
import main.java.com.carpooling.domain.AbstractDAO;
import main.java.com.carpooling.domain.AbstractService;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CarServiceImpl extends AbstractService implements CarService {

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
    public Car register(Car car) throws InvalidCarDataException, DuplicateCarException {
        validateCar(car);
        return carDAO.create(car);
    }


    @Override
    public List<CarInterface> getAll() {
        return carDAO.findAll();
    }

    @Override
    public List<Car> getCarsByOwner(Long ownerId) {
        throw new UnsupportedOperationException("La gestion des propriétaires n'est pas encore implémentée.");
    }

    @Override
    public Car update(Car car) throws InvalidCarDataException, CarNotFoundException {
        validateCar(car);
        if (car.getId() == null) {
            throw new InvalidCarDataException("ID de la voiture manquant pour la mise à jour");
        }
        car.setPlate(car.getPlate().toUpperCase());
        return carDAO.update(car);
    }

    @Override
    public void delete(String plate) throws CarNotFoundException {
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
        if (car.getPlate() == null || car.getPlate().trim().isEmpty()) {
            throw new InvalidCarDataException("La plaque d'immatriculation est obligatoire");
        }

        String plate = car.getPlate().toUpperCase();
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

}
