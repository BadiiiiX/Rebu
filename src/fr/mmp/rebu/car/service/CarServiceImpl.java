package fr.mmp.rebu.car.service;

import fr.mmp.rebu.car.dao.CarDAO;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.domain.AbstractService;

import java.util.List;

public class CarServiceImpl extends AbstractService implements CarService {

    private final CarDAO carDAO;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public CarInterface createCar(CarInterface car) {
        this.carDAO.save(car);

        return car;
    }

    @Override
    public void updateCar(CarInterface car) {
        this.carDAO.save(car);
    }

    @Override
    public void deleteCar(String plate) {
        this.carDAO.delete(plate);
    }

    @Override
    public CarInterface findCarByPlate(String plate) {
        return this.carDAO.findByPlate(plate);
    }

    @Override
    public List<CarInterface> findAllCars() {
        return this.carDAO.findAll();
    }

    @Override
    public List<CarInterface> findCarsByOwner(int ownerId) {
        return this.carDAO.findByOwner(ownerId);
    }
}
