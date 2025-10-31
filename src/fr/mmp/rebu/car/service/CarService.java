package fr.mmp.rebu.car.service;

import fr.mmp.rebu.car.model.CarInterface;

import java.util.List;

public interface CarService {

    CarInterface createCar(CarInterface car);

    void updateCar(CarInterface car);

    void deleteCar(String plate);

    CarInterface findCarByPlate(String plate);

    List<CarInterface> findAllCars() ;

    List<CarInterface> findCarsByOwner(int ownerId);


}
