package fr.mmp.rebu.User;

import fr.mmp.rebu.Vehicle.VehicleInterface;

import java.util.List;

public interface UserInterface {
    String getMail();
    String getUsername();
    List<VehicleInterface> getVehicles();
    VehicleInterface getVehicle(String plate);
    void addVehicle(VehicleInterface vehicle);
    void removeVehicle(String plate);
    boolean hasVehicle(String plate);
}
