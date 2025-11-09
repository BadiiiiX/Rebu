package fr.mmp.rebu.car.event.components;

import fr.mmp.rebu.event.AbstractEvent;

public class CarDeletedEvent extends AbstractEvent {
    public CarDeletedEvent(String plate) {
        super(plate);
    }
}
