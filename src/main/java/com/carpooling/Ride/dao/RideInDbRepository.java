package main.java.com.carpooling.Ride.dao;

import main.java.com.carpooling.Ride.model.Ride;
import main.java.com.carpooling.Vehicle.exception.CarNotFoundException;
import main.java.com.carpooling.Vehicle.exception.DatabaseException;
import main.java.com.carpooling.Vehicle.model.Car;
import main.java.com.carpooling.Vehicle.service.CarServiceImpl;
import main.java.com.carpooling.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RideInDbRepository implements RideRepository {

    private final Connection connection;

    public RideInDbRepository() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données PostgreSQL : " + e.getMessage());
        }
    }

    @Override
    public List<Ride> findAll() {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT ride_id, vehicle_id, driver_id, origin, destination, start_date FROM rides";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int rideId = rs.getInt("ride_id");
                Car vehicle = new CarServiceImpl().getCarById(rs.getLong("vehicle_id"));
                UserInterface driver = findUserById(rs.getInt("driver_id"));
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                Date startDate = new Date(rs.getTimestamp("start_date").getTime());

                Ride ride = new Ride(rideId, vehicle, driver, origin, destination, startDate);
                ride.getPassengers().addAll(findPassengersByRideId(rideId));

                rides.add(ride);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors du chargement des trajets : " + e.getMessage());
        } catch (CarNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        return rides;
    }

    @Override
    public Ride findById(long id) {
        String sql = "SELECT * FROM rides WHERE ride_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                VehicleInterface vehicle = findVehicleById(rs.getInt("vehicle_id"));
                UserInterface driver = findUserById(rs.getInt("driver_id"));
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                Date startDate = new Date(rs.getTimestamp("start_date").getTime());

                Ride ride = new Ride((int) id, vehicle, driver, origin, destination, startDate);
                ride.getPassengers().addAll(findPassengersByRideId(id));
                return ride;
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du trajet ID " + id + " : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void addPassengerToRide(long rideId, long passengerId) {
        String sql = "INSERT INTO ride_passengers (ride_id, passenger_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, rideId);
            stmt.setLong(2, passengerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l’ajout d’un passager au trajet : " + e.getMessage());
        }
    }

    @Override
    public void save(Ride ride) {
        String sql = """
                INSERT INTO rides (vehicle_id, driver_id, origin, destination, start_date)
                VALUES (?, ?, ?, ?, ?)
                RETURNING ride_id
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ride.getVehicle().getVehicleId());
            stmt.setInt(2, ride.getDriver().getUserId());
            stmt.setString(3, ride.getOrigin());
            stmt.setString(4, ride.getDestination());
            stmt.setTimestamp(5, new Timestamp(ride.getStartDate().getTime()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int newRideId = rs.getInt("ride_id");
                for (UserInterface passenger : ride.getPassengers()) {
                    addPassengerToRide(newRideId, passenger.getUserId());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l’enregistrement du trajet : " + e.getMessage());
        }
    }

    @Override
    public void deleteById(long rideId) {
        String deletePassengers = "DELETE FROM ride_passengers WHERE ride_id = ?";
        String deleteRide = "DELETE FROM rides WHERE ride_id = ?";

        try (PreparedStatement ps1 = connection.prepareStatement(deletePassengers);
             PreparedStatement ps2 = connection.prepareStatement(deleteRide)) {

            ps1.setLong(1, rideId);
            ps1.executeUpdate();

            ps2.setLong(1, rideId);
            ps2.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du trajet ID " + rideId + " : " + e.getMessage());
        }
    }

    @Override
    public List<Ride> findRidesByPassengerId(long passengerId) {
        List<Ride> rides = new ArrayList<>();
        String sql = """
                SELECT r.ride_id, r.vehicle_id, r.driver_id, r.origin, r.destination, r.start_date
                FROM rides r
                JOIN ride_passengers rp ON r.ride_id = rp.ride_id
                WHERE rp.passenger_id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, passengerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int rideId = rs.getInt("ride_id");
                VehicleInterface vehicle = findVehicleById(rs.getInt("vehicle_id"));
                UserInterface driver = findUserById(rs.getInt("driver_id"));
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                Date startDate = new Date(rs.getTimestamp("start_date").getTime());
                Ride ride = new Ride(rideId, vehicle, driver, origin, destination, startDate);
                ride.getPassengers().addAll(findPassengersByRideId(rideId));
                rides.add(ride);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des trajets pour le passager ID " + passengerId + " : " + e.getMessage());
        }
        return rides;
    }

    @Override
    public void removePassengerFromRide(long rideId, long passengerId) {
        String sql = "DELETE FROM ride_passengers WHERE ride_id = ? AND passenger_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, rideId);
            stmt.setLong(2, passengerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du passager du trajet : " + e.getMessage());
        }
    }
}
