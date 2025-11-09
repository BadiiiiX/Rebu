package fr.mmp.rebu.car.test;

import fr.mmp.rebu.car.dao.CarInDatabaseDAO;
import fr.mmp.rebu.car.factory.CarFactory;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.car.service.CarServiceImpl;
import fr.mmp.rebu.test.ITest;
import fr.mmp.rebu.user.dao.UserInDatabaseDAO;
import fr.mmp.rebu.user.factory.UserFactory;
import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.user.service.UserServiceImpl;

import java.util.List;

public class CarServiceTest implements ITest {

    @Override
    public String getName() {
        return "CarService";
    }

    @Override
    public void run() {
        var dao = new CarInDatabaseDAO();
        var service = new CarServiceImpl(dao);

        var driver = UserFactory.build("serviced@test.com", "ServiceDriver", "password123");
        owner = new UserServiceImpl(new UserInDatabaseDAO()).createUser(driver);

        createCarTest(service);
        readCarTest(service);
        updateCarTest(service);
        listCarsTest(service);
        deleteCarTest(service);
    }

    private CarInterface createdCar;
    private UserInterface owner;

    private void createCarTest(CarServiceImpl service) {
        System.out.println("\t▶️ Test CREATE...");

        var car = CarFactory.build("AA-123-BB", 4, owner);
        createdCar = service.createCar(car);

        System.out.println(createdCar != null ? "\t\t✅ Voiture créée : " + createdCar.getPlate()
                : "\t\t❌ Échec création");
        System.out.println();
    }

    private void readCarTest(CarServiceImpl service) {
        System.out.println("\t▶️ Test READ...");

        var found = service.findCarByPlate(createdCar.getPlate());
        System.out.println(found != null ? "\t\t✅ Lecture OK : " + found.getPlate()
                : "\t\t❌ Lecture échouée");
        System.out.println();
    }

    private void updateCarTest(CarServiceImpl service) {
        System.out.println("\t▶️ Test UPDATE...");

        var car = CarFactory.build(createdCar.getPlate(), 5, owner);
        service.updateCar(car);

        var updated = service.findCarByPlate(car.getPlate());
        if (updated != null && updated.getPassengersNumber() == 5) {
            System.out.println("\t\t✅ Mise à jour réussie : " + updated.getPassengersNumber() + " places");
        } else {
            System.out.println("\t\t❌ Échec mise à jour");
        }
        System.out.println();
    }

    private void listCarsTest(CarServiceImpl service) {
        System.out.println("\t▶️ Test FIND ALL...");

        List<CarInterface> cars = service.findAllCars();
        if (cars != null && !cars.isEmpty()) {
            System.out.println("\t\t✅ " + cars.size() + " voiture(s) trouvée(s)");
        } else {
            System.out.println("\t\t❌ Aucune voiture trouvée");
        }
        System.out.println();
    }

    private void deleteCarTest(CarServiceImpl service) {
        System.out.println("\t▶️ Test DELETE...");

        service.deleteCar(createdCar.getPlate());
        var deleted = service.findCarByPlate(createdCar.getPlate());

        new UserServiceImpl(new UserInDatabaseDAO()).deleteUser(owner.getUserId());

        System.out.println(deleted == null ? "\t\t✅ Suppression OK"
                : "\t\t❌ Suppression échouée");
        System.out.println();
    }
}
