package fr.mmp.rebu.ride.event.components;

import fr.mmp.rebu.event.AbstractEvent;
import fr.mmp.rebu.ride.dto.RidePassengerEventDto;

public class RidePassengerAddedEvent extends AbstractEvent {
    public RidePassengerAddedEvent(RidePassengerEventDto source) {
        super(source);
    }
}