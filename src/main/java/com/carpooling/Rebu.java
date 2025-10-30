package main.java.com.carpooling;

import main.java.com.carpooling.car.model.CarInterface;
import main.java.com.carpooling.car.service.CarService;
import main.java.com.carpooling.car.service.CarServiceImpl;
import main.java.com.carpooling.domain.AbstractService;
import main.java.com.carpooling.ride.dao.RideInDatabaseDAO;
import main.java.com.carpooling.ride.model.RideInterface;
import main.java.com.carpooling.ride.service.RideService;

import java.util.HashMap;
import java.util.Map;

public class Rebu {
    public static Map<String, AbstractService> dictionary = new HashMap<>();

    static {
        var carService = new CarServiceImpl();
        Object userService = null;

        var rideDAO = new RideInDatabaseDAO();
        var rideService = new RideService(rideDAO);

        dictionary.put(CarInterface.class.getSimpleName(), carService);
        dictionary.put(RideInterface.class.getSimpleName(), rideService);
    }

    public static AbstractService getService(String serviceName) {
        return dictionary.get(serviceName);
    }

    public static CarService getCarService() {
        return (CarService) Rebu.getService(CarInterface.class.getSimpleName());
    }

    public static AbstractService getRideService() {
        return Rebu.getService(RideInterface.class.getSimpleName());
    }
}
