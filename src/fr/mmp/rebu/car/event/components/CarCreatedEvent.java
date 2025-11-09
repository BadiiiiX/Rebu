package fr.mmp.rebu.car.event.components;

import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.event.AbstractEvent;

public class CarCreatedEvent extends AbstractEvent {
    public CarCreatedEvent(CarInterface source) {
        super(source);
    }
}
