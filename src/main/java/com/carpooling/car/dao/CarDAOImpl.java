package main.java.com.carpooling.car.dao;

import main.java.com.carpooling.car.exception.CarNotFoundException;
import main.java.com.carpooling.car.exception.DatabaseException;
import main.java.com.carpooling.car.exception.DuplicateCarException;
import main.java.com.carpooling.car.model.Car;
import main.java.com.carpooling.domain.AbstractDAO;
import main.java.com.carpooling.domain.AbstractModel;
import main.java.com.carpooling.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDAOImpl extends AbstractDAO implements CarDAO {

    private static final String INSERT_CAR =
            "INSERT INTO cars (license_plate, passengers_number) VALUES (?, ?) RETURNING id";

    private static final String SELECT_BY_ID =
            "SELECT * FROM cars WHERE id = ?";

    private static final String SELECT_BY_LICENSE_PLATE =
            "SELECT * FROM cars WHERE license_plate = ?";

    private static final String SELECT_ALL =
            "SELECT * FROM cars ORDER BY id DESC";

    private static final String UPDATE_CAR =
            "UPDATE cars SET license_plate = ?, passengers_number = ? WHERE id = ?";

    private static final String HARD_DELETE =
            "DELETE FROM cars WHERE id = ?";

    private static final String EXISTS_BY_LICENSE_PLATE =
            "SELECT COUNT(*) FROM cars WHERE license_plate = ?";

    private static final String COUNT_ALL =
            "SELECT COUNT(*) FROM cars";


    @Override
    public Car create(Car car) throws DuplicateCarException, DatabaseException {
        if (existsByLicensePlate(car.getPlate())) {
            throw new DuplicateCarException(car.getPlate());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CAR)) {

            stmt.setString(1, car.getPlate());
            stmt.setInt(2, car.getPassengersNumber());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                car.setId(rs.getLong("id"));
            }

            return car;

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la création de la voiture", e);
        }
    }

    @Override
    public Optional<Car> findById(Long id) throws DatabaseException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToCar(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la recherche de la voiture par ID", e);
        }
    }

    @Override
    public Optional<Car> findByLicensePlate(String licensePlate) throws DatabaseException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_LICENSE_PLATE)) {

            stmt.setString(1, licensePlate);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToCar(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la recherche de la voiture par plaque", e);
        }
    }

    @Override
    public List<Car> findAll() throws DatabaseException {
        List<Car> cars = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {

            while (rs.next()) {
                cars.add(mapResultSetToCar(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la récupération de toutes les voitures", e);
        }

        return cars;
    }

    @Override
    public Car update(Car car) throws CarNotFoundException, DatabaseException {
        if (car.getId() == null || findById(car.getId()).isEmpty()) {
            throw new CarNotFoundException(car.getId());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CAR)) {

            stmt.setString(1, car.getPlate());
            stmt.setInt(2, car.getPassengersNumber());
            stmt.setLong(3, car.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new CarNotFoundException(car.getId());
            }

            return car;

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la mise à jour de la voiture", e);
        }
    }

    @Override
    public boolean hardDelete(Long id) throws CarNotFoundException, DatabaseException {
        if (findById(id).isEmpty()) {
            throw new CarNotFoundException(id);
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(HARD_DELETE)) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la suppression définitive de la voiture", e);
        }
    }

    @Override
    public boolean existsByLicensePlate(String licensePlate) throws DatabaseException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(EXISTS_BY_LICENSE_PLATE)) {

            stmt.setString(1, licensePlate);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la vérification de l'existence de la plaque", e);
        }
    }

    @Override
    public int count() throws DatabaseException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(COUNT_ALL)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors du comptage des voitures", e);
        }
    }

    private Car mapResultSetToCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong("id"));
        car.setPlate(rs.getString("license_plate"));
        car.setPassengersNumber(rs.getInt("passengers_number"));
        return car;
    }
}
