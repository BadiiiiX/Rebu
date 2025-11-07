package fr.mmp.rebu.ride.event.components;

import fr.mmp.rebu.event.AbstractEvent;
import fr.mmp.rebu.ride.model.RideInterface;

public class RideCreatedEvent extends AbstractEvent {
    public RideCreatedEvent(RideInterface source) {
        super(source);
    }
}