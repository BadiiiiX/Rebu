package main.java.com.carpooling.user.model;

import main.java.com.carpooling.car.model.CarInterface;
import main.java.com.carpooling.domain.AbstractModel;

import java.util.HashMap;
import java.util.Map;

public class User extends AbstractModel implements UserInterface{

    private final int userId;
    private final String email;
    private final String username;
    private final String password;
    private final Map<String, CarInterface> cars;

    private User(int userId, String email, String username, String password) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        cars = new HashMap<>();
    }

    @Override
    public Integer getUserId() {
        return this.userId;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Map<String, CarInterface> getCars() {
        return this.cars;
    }

    @Override
    public boolean addCar(CarInterface car) {
        //todo verif
        return this.cars.put(car.getPlate(), car) != null;
    }

    @Override
    public void removeCar(String plate) {
        var car = this.cars.get(plate);
        if (car != null) {
            this.cars.remove(plate);
        }

        //Todo error
    }
}
