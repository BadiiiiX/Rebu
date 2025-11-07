package fr.mmp.rebu.ride.test;

import fr.mmp.rebu.car.dao.CarInDatabaseDAO;
import fr.mmp.rebu.car.factory.CarFactory;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.car.service.CarServiceImpl;
import fr.mmp.rebu.ride.dao.RideInDatabaseDAO;
import fr.mmp.rebu.ride.factory.RideFactory;
import fr.mmp.rebu.ride.model.RideInterface;
import fr.mmp.rebu.ride.service.RideServiceImpl;
import fr.mmp.rebu.test.ITest;
import fr.mmp.rebu.user.dao.UserInDatabaseDAO;
import fr.mmp.rebu.user.factory.UserFactory;
import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.user.service.UserServiceImpl;

import java.util.Date;

public class RideServiceTest implements ITest {

    @Override
    public String getName() {
        return "RideService";
    }

    @Override
    public void run() {
        var dao = new RideInDatabaseDAO();
        var service = new RideServiceImpl(dao);

        createRideTest(service);
        readRideTest(service);
        addPassengerToRideTest(service);
        removePassengerFromRideTest(service);
        deleteRideTest(service);
    }

    private UserInterface createdPassenger;
    private UserInterface createdDriver;
    private CarInterface createdCar;
    private RideInterface createdRide;

    private void createRideTest(RideServiceImpl service) {
        System.out.println("\t▶️ Test CREATE...");

        var driver =  UserFactory.build("serviced@test.com", "ServiceDriver", "password123");
        createdDriver = new UserServiceImpl(new UserInDatabaseDAO()).createUser(driver);

        var car = CarFactory.build("ABC-123", 4, createdDriver);
        createdCar = new CarServiceImpl(new CarInDatabaseDAO()).createCar(car);

        var ride = RideFactory.build(createdCar, createdDriver, "CityA", "CityB",  new Date());
        createdRide = service.createRide(ride);

        if  (createdRide.getRideId() == -1 ) {
            throw new RuntimeException("Ride creation failed, ID not set.");
        }

        System.out.println(createdRide != null ? "\t\t✅ Création réussie" : "\t\t❌ Échec création");
        System.out.println();
    }

    public void readRideTest(RideServiceImpl service) {
        System.out.println("\t▶️ Test READ...");
        var found = service.findById(createdRide.getRideId());
        System.out.println(found != null ? "\t\t✅ Lecture OK" : "\t\t❌ Lecture échouée");
        System.out.println();
    }

    private void addPassengerToRideTest(RideServiceImpl service) {
        System.out.println("\t▶️ Test ADD PASSENGER...");
        var passenger =  UserFactory.build("servicep@test.com", "ServicePassenger", "password123");
        createdPassenger = new UserServiceImpl(new UserInDatabaseDAO()).createUser(passenger);

        service.addPassengerToRide(createdRide.getRideId(), createdPassenger.getUserId());

        System.out.println("\t\t✅ Passenger added to ride");
        System.out.println();
    }

    private void removePassengerFromRideTest(RideServiceImpl service) {
        System.out.println("\t▶️ Test REMOVE PASSENGER...");
        service.removePassengerFromRide(createdRide.getRideId(), createdPassenger.getUserId());
        System.out.println("\t\t✅ Passenger removed from ride");
        System.out.println();
    }

    private void deleteRideTest(RideServiceImpl service) {
        System.out.println("\t▶️ Test DELETE...");
        service.deleteRide(createdRide.getRideId());
        var deletedRide = service.findById(createdRide.getRideId());

        new CarServiceImpl(new CarInDatabaseDAO()).deleteCar(createdCar.getPlate());
        new UserServiceImpl(new UserInDatabaseDAO()).deleteUser(createdPassenger.getUserId());
        new UserServiceImpl(new UserInDatabaseDAO()).deleteUser(createdDriver.getUserId());

        System.out.println(deletedRide == null ? "\t\t✅ Suppression OK" : "\t\t❌ Suppression échouée");
        System.out.println();
    }
}
