package main.java.com.carpooling.user.model;

import main.java.com.carpooling.car.model.CarInterface;

import java.util.Map;

public interface UserInterface {
    Integer getUserId();
    String getEmail();
    String getUsername();
    String getPassword();

    Map<String, CarInterface> getCars();
    boolean addCar(CarInterface car);
    void removeCar(String plate);
}
