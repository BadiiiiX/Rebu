package fr.mmp.rebu.User;

import fr.mmp.rebu.Vehicle.VehicleInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements UserInterface {

    private final String mail;
    private final String username;
    private final Map<String, VehicleInterface> vehicles = new HashMap<>();

    public User(String mail, String username) {
        //TODO Validators
        if (mail == null || mail.isBlank() || !mail.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        this.mail = mail.toLowerCase().trim();
        this.username = username.trim();
    }

    @Override
    public String getMail() {
        return this.mail;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public List<VehicleInterface> getVehicles() {
        return new ArrayList<>(vehicles.values());
    }

    @Override
    public void addVehicle(VehicleInterface vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        if (vehicles.containsKey(vehicle.getPlate())) {
            throw new IllegalArgumentException(
                    "Vehicle with plate " + vehicle.getPlate() + " already exists"
            );
        }

        vehicles.put(vehicle.getPlate(), vehicle);
    }

    @Override
    public void removeVehicle(String plate) {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Plate cannot be blank");
        }

        if (!this.hasVehicle(plate)) {
            throw new IllegalArgumentException(
                    "Vehicle with plate " + plate + " not found"
            );
        }

        vehicles.remove(plate);
    }

    @Override
    public VehicleInterface getVehicle(String plate) {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Plate cannot be blank");
        }

        if(!this.hasVehicle(plate)) {
            throw new IllegalArgumentException("Vehicle with plate " + plate + " not found");
        }

        return vehicles.get(plate);
    }

    @Override
    public boolean hasVehicle(String plate) {
        return vehicles.containsKey(plate);
    }

}
