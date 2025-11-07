package fr.mmp.rebu.ride.dao;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.domain.AbstractDAO;
import fr.mmp.rebu.ride.mapper.RideMapper;
import fr.mmp.rebu.ride.model.RideInterface;
import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RideInDatabaseDAO extends AbstractDAO implements RideDAO {

    @Override
    public List<RideInterface> findAll() {
        List<RideInterface> rides = new ArrayList<>();
        String sql = "SELECT ride_id, car_plate, driver_id, ride_origin, ride_destination, ride_start_date FROM rides";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RideInterface ride = RideMapper.databaseToRide(rs);
                ride.getPassengers().addAll(findPassengers(ride.getRideId()));
                rides.add(ride);
            }

        } catch (SQLException e) {
            System.err.println("Error loading rides : " + e.getMessage());
        }

        return rides;
    }

    @Override
    public RideInterface findById(int id) {
        String sql = "SELECT * FROM rides WHERE ride_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                RideInterface ride = RideMapper.databaseToRide(rs);
                ride.getPassengers().addAll(findPassengers(ride.getRideId()));
                return ride;
            }

        } catch (SQLException e) {
            System.err.println("Error searching for ride ID " + id + " : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void addPassenger(int rideId, int passengerId) {
        String sql = "INSERT INTO ride_passengers (ride_id, passenger_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rideId);
            stmt.setInt(2, passengerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding a passenger to the ride : " + e.getMessage());
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

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ride.getCar().getPlate());
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
            System.err.println("Error while creating the ride : " + e.getMessage());
        }

        return newRideId;
    }

    @Override
    public void deleteById(int rideId) {
        String deletePassengers = "DELETE FROM ride_passengers WHERE ride_id = ?";
        String deleteRide = "DELETE FROM rides WHERE ride_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps1 = conn.prepareStatement(deletePassengers);
             PreparedStatement ps2 = conn.prepareStatement(deleteRide)) {

            ps1.setInt(1, rideId);
            ps1.executeUpdate();

            ps2.setInt(1, rideId);
            ps2.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting ride ID " + rideId + " : " + e.getMessage());
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

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rideId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserInterface passengerId = Rebu.getUserService().findUserById(rs.getInt("passenger_id"));
                passengers.add(passengerId);
            }
        } catch (SQLException e) {
            System.err.println("Error searching for passengers on ride ID " + rideId + " : " + e.getMessage());
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

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rideId);
            stmt.setInt(2, passengerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting passenger from ride : " + e.getMessage());
        }
    }

    @Override
    public List<RideInterface> findByDriverId(int userId) {
        List<RideInterface> rides = new ArrayList<>();
        String sql = "SELECT ride_id, car_plate, driver_id, ride_origin, ride_destination, ride_start_date FROM rides WHERE driver_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RideInterface ride = RideMapper.databaseToRide(rs);
                ride.getPassengers().addAll(findPassengers(ride.getRideId()));
                rides.add(ride);
            }

        } catch (SQLException e) {
            System.err.println("Error loading rides for driver ID " + userId + " : " + e.getMessage());
        }

        return rides;
    }

    @Override
    public UserInterface findPassengerById(int passengerId) {
        String sql = "SELECT passenger_id FROM ride_passengers WHERE passenger_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, passengerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Rebu.getUserService().findUserById(rs.getInt("passenger_id"));
            }

        } catch (SQLException e) {
            System.err.println("Error adding a passenger to the ride : " + e.getMessage());
        }
        return null;
    }
}
