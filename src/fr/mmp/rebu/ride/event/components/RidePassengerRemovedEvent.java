package fr.mmp.rebu.ride.event.components;

import fr.mmp.rebu.event.AbstractEvent;
import fr.mmp.rebu.ride.dto.RidePassengerEventDto;

public class RidePassengerRemovedEvent extends AbstractEvent {
    public RidePassengerRemovedEvent(RidePassengerEventDto source) {
        super(source);
    }
}