package fr.mmp.rebu.ride.event.components;

import fr.mmp.rebu.event.AbstractEvent;

public class RideDeletedEvent extends AbstractEvent {
    public RideDeletedEvent(int rideId) {
        super(rideId);
    }
}