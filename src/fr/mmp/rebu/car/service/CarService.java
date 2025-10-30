package fr.mmp.rebu.car.service;

import fr.mmp.rebu.car.model.CarInterface;

import java.util.List;

public interface CarService {

    CarInterface createUser(CarInterface car);

    void updateUser(CarInterface car);

    void deleteUser(String plate);

    CarInterface findCarByPlate(String plate);

    List<CarInterface> findAllCars() ;

    List<CarInterface> findCarsByOwner(int ownerId);


}
