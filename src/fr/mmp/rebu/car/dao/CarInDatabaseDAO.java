package fr.mmp.rebu.car.dao;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.car.mapper.CarMapper;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.database.DatabaseConnection;
import fr.mmp.rebu.domain.AbstractDAO;
import fr.mmp.rebu.exception.MappingException;
import fr.mmp.rebu.user.model.UserInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarInDatabaseDAO extends AbstractDAO implements CarDAO {

    @Override
    public void save(CarInterface car) {
        final String INSERT_CAR = """
            INSERT INTO cars (car_plate, car_passengers_number, user_id)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CAR)) {

            stmt.setString(1, car.getPlate());
            stmt.setInt(2, car.getPassengersNumber());
            stmt.setInt(3, car.getOwner().getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement de la voiture");
        }

    }

    @Override
    public CarInterface update(CarInterface car) {
        final String UPDATE_CAR = """
            UPDATE cars
            SET car_passengers_number = ?, user_id = ?
            WHERE car_plate = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CAR)) {

            stmt.setInt(1, car.getPassengersNumber());
            stmt.setInt(2, car.getOwner().getUserId());
            stmt.setString(3, car.getPlate());

            stmt.executeUpdate();
            return car;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la voiture");
        }

        return null;
    }

    @Override
    public CarInterface findByPlate(String plate) {
        final String FIND_CAR = "SELECT * FROM cars WHERE car_plate = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_CAR)) {

            stmt.setString(1, plate);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    UserInterface owner = Rebu.getUserService().findUserById(rs.getInt("user_id"));

                    return CarMapper.databaseToCar(rs, owner);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la voiture par plaque");
        } catch (MappingException me) {
            System.out.println("Erreur lors du mapping de la voiture");
        }

        return null;
    }

    @Override
    public List<CarInterface> findAll() {
        final String FIND_ALL_CARS = "SELECT * FROM cars";
        List<CarInterface> cars = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL_CARS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                try {
                    UserInterface owner = Rebu.getUserService().findUserById(rs.getInt("user_id"));

                    cars.add(CarMapper.databaseToCar(rs, owner));
                } catch (MappingException me) {
                    System.out.println("Erreur lors du mapping d'une voiture (plaque : " + rs.getString("car_plate") + ")");
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de toutes les voitures");
        }

        return cars;
    }

    @Override
    public List<CarInterface> findByOwner(int ownerId) {
        final String FIND_BY_OWNER = "SELECT * FROM cars WHERE user_id = ?";
        List<CarInterface> cars = new ArrayList<>();
        UserInterface owner = Rebu.getUserService().findUserById(ownerId);


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_OWNER)) {

            stmt.setInt(1, ownerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    try {
                        cars.add(CarMapper.databaseToCar(rs, owner));
                    } catch (MappingException me) {
                        System.out.println("Erreur lors du mapping d'une voiture appartenant à l'utilisateur " + ownerId);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des voitures d'un utilisateur");
        }

        return cars;
    }

    @Override
    public boolean delete(String plate) {
        final String DELETE_CAR = "DELETE FROM cars WHERE car_plate = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CAR)) {

            stmt.setString(1, plate);
            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la voiture");
        }

        return false;
    }
}