package fr.mmp.rebu.User;

import fr.mmp.rebu.Vehicle.VehicleInterface;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("UserRepository cannot be null");
        }
        this.userRepository = userRepository;
    }

    public void registerUser(UserCreateDTO request) {
        //TODO DTO & that
    }

    public void addVehicle(String userMail, UserVehicleAddDTO request) {
        if (userMail == null || userMail.isBlank()) {
            throw new IllegalArgumentException("User email is required");
        }
        if (request == null) {
            throw new IllegalArgumentException("AddVehicleRequest cannot be null");
        }

        var user = userRepository.findByMail(userMail)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found: " + userMail
                ));

        VehicleInterface vehicle = null; //factory

        user.addVehicle(vehicle);
        userRepository.save(user);
    }

    public void removeVehicle(String userMail, String plate) {
        if (userMail == null || userMail.isBlank()) {
            throw new IllegalArgumentException("User email is required");
        }
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Plate is required");
        }

        var user = userRepository.findByMail(userMail)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found: " + userMail
                ));

        user.removeVehicle(plate);
        userRepository.save(user);
    }

    public UserInterface getUser(String mail) {
        if (mail == null || mail.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        return userRepository.findByMail(mail)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found: " + mail
                ));
    }

    public List<UserInterface> getAllUsers() {
        return userRepository.findAll().stream().toList();
    }

    public boolean userExists(String mail) {
        if (mail == null || mail.isBlank()) {
            return false;
        }
        return userRepository.exists(mail);
    }

    public void deleteUser(String mail) {
        if (mail == null || mail.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (!userRepository.exists(mail)) {
            throw new IllegalArgumentException("User not found: " + mail);
        }

        userRepository.delete(mail);
    }
}