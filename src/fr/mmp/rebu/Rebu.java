package fr.mmp.rebu;

import fr.mmp.rebu.domain.AbstractService;

import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.ride.model.RideInterface;

import fr.mmp.rebu.user.service.UserService;
import fr.mmp.rebu.car.service.CarService;

import fr.mmp.rebu.car.dao.CarInDatabaseDAO;
import fr.mmp.rebu.car.service.CarServiceImpl;

import fr.mmp.rebu.ride.dao.RideInDatabaseDAO;
import fr.mmp.rebu.ride.service.RideService;

import fr.mmp.rebu.user.dao.UserInDatabaseDAO;
import fr.mmp.rebu.user.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Rebu {
    public static Map<String, AbstractService> services = new HashMap<>();

    static {
        var carDAO = new CarInDatabaseDAO();
        var carService = new CarServiceImpl(carDAO);

        var userDAO = new UserInDatabaseDAO();
        var userService = new UserServiceImpl(userDAO);

        var rideDAO = new RideInDatabaseDAO();
        var rideService = new RideService(rideDAO);

        services.put(CarInterface.class.getSimpleName(), carService);
        services.put(RideInterface.class.getSimpleName(), rideService);
        services.put(UserInterface.class.getSimpleName(), userService);
    }

    public static AbstractService getService(String serviceName) {
        return services.get(serviceName);
    }

    public static CarService getCarService() {
        return (CarService) Rebu.getService(CarInterface.class.getSimpleName());
    }

    public static RideInterface getRideService() {
        return (RideInterface) Rebu.getService(RideInterface.class.getSimpleName());
    }

    public static UserService getUserService() {
        return (UserService) Rebu.getService(UserInterface.class.getSimpleName());
    }
}
