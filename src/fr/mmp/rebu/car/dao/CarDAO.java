package fr.mmp.rebu.car.dao;

import fr.mmp.rebu.car.model.CarInterface;

import java.util.List;

public interface CarDAO {

    void save(CarInterface car);

    CarInterface update(CarInterface car);

    CarInterface findByPlate(String plate);

    List<CarInterface> findAll();

    List<CarInterface> findByOwner(int ownerId);

    boolean delete(String plate);
}
