package fr.mmp.rebu.user.event.components;

import fr.mmp.rebu.event.AbstractEvent;
import fr.mmp.rebu.user.model.UserInterface;

public class UserCreatedEvent extends AbstractEvent {
    public UserCreatedEvent(UserInterface source) {
        super(source);
    }
}
