package fr.mmp.rebu.ride.dao;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.domain.AbstractDAO;
import fr.mmp.rebu.ride.mapper.RideMapper;
import fr.mmp.rebu.ride.model.Ride;
import fr.mmp.rebu.ride.model.RideInterface;
import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RideInDatabaseDAO extends AbstractDAO implements RideDAO {

    private final Connection connection;

    public RideInDatabaseDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données PostgreSQL : " + e.getMessage());
        }
    }

    @Override
    public List<RideInterface> findAll() {
        List<RideInterface> rides = new ArrayList<>();
        String sql = "SELECT ride_id, car_plate, driver_id, ride_origin, ride_destination, ride_start_date FROM rides";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RideInterface ride = RideMapper.databaseToRide(rs);
                ride.getPassengers().addAll(findPassengers(ride.getRideId()));
                rides.add(ride);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors du chargement des trajets : " + e.getMessage());
        }

        return rides;
    }

    @Override
    public RideInterface findById(int id) {
        String sql = "SELECT * FROM rides WHERE ride_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                RideInterface ride = RideMapper.databaseToRide(rs);
                ride.getPassengers().addAll(findPassengers(ride.getRideId()));
                return ride;
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du trajet ID " + id + " : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void addPassenger(int rideId, int passengerId) {
        String sql = "INSERT INTO ride_passengers (ride_id, passenger_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rideId);
            stmt.setInt(2, passengerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l’ajout d’un passager au trajet : " + e.getMessage());
        }
    }

    @Override
    public int save(RideInterface ride) {
        int newRideId = -1;
        String sql = """
                INSERT INTO rides (car_plate, driver_id, ride_origin, ride_destination, ride_start_date)
                VALUES (?, ?, ?, ?, ?)
                RETURNING ride_id
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ride.getVehicle().getPlate());
            stmt.setInt(2, ride.getDriver().getUserId());
            stmt.setString(3, ride.getOrigin());
            stmt.setString(4, ride.getDestination());
            stmt.setTimestamp(5, new Timestamp(ride.getStartDate().getTime()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                newRideId = rs.getInt("ride_id");
                for (UserInterface passenger : ride.getPassengers()) {
                    addPassenger(newRideId, passenger.getUserId());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l’enregistrement du trajet : " + e.getMessage());
        }

        if (newRideId == -1) {
            throw new RuntimeException("Failed to save ride to the database.");
        }

        return newRideId;
    }

    @Override
    public void deleteById(int rideId) {
        String deletePassengers = "DELETE FROM ride_passengers WHERE ride_id = ?";
        String deleteRide = "DELETE FROM rides WHERE ride_id = ?";

        try (PreparedStatement ps1 = connection.prepareStatement(deletePassengers);
             PreparedStatement ps2 = connection.prepareStatement(deleteRide)) {

            ps1.setInt(1, rideId);
            ps1.executeUpdate();

            ps2.setInt(1, rideId);
            ps2.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du trajet ID " + rideId + " : " + e.getMessage());
        }
    }

    @Override
    public List<UserInterface> findPassengers(int rideId) {
        List<UserInterface> passengers = new ArrayList<>();
        String sql = """
                SELECT passenger_id
                FROM ride_passengers
                WHERE ride_id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rideId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserInterface passengerId = Rebu.getUserService().findUserById(rs.getInt("passenger_id"));
                passengers.add(passengerId);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des passagers du trajet ID " + rideId + " : " + e.getMessage());
        }
        return passengers;
    }

    @Override
    public List<RideInterface> findByPassengerId(int passengerId) {
        List<RideInterface> rides = new ArrayList<>();
        String sql = """
                SELECT r.ride_id, r.car_plate, r.driver_id, r.ride_origin, r.ride_destination, r.ride_start_date
                FROM rides r
                JOIN ride_passengers rp ON r.ride_id = rp.ride_id
                WHERE rp.passenger_id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, passengerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RideInterface ride = RideMapper.databaseToRide(rs);
                ride.getPassengers().addAll(findPassengers(ride.getRideId()));
                rides.add(ride);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rides;
    }

    @Override
    public void removePassenger(int rideId, int passengerId) {
        String sql = "DELETE FROM ride_passengers WHERE ride_id = ? AND passenger_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rideId);
            stmt.setInt(2, passengerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du passager du trajet : " + e.getMessage());
        }
    }
}
