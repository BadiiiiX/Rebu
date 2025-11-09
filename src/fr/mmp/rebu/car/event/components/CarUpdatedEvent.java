package fr.mmp.rebu.car.event.components;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.event.AbstractEvent;

public class CarUpdatedEvent extends AbstractEvent {
    public CarUpdatedEvent(CarInterface source) {
        super(source);
    }
}
