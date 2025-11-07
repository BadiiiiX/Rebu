package fr.mmp.rebu.ride.dto;

import fr.mmp.rebu.ride.model.RideInterface;
import fr.mmp.rebu.user.model.UserInterface;

public record RidePassengerEventDto(RideInterface ride, UserInterface passenger) {
}
