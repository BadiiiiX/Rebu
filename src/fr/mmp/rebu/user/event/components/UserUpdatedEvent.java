package fr.mmp.rebu.user.event.components;

import fr.mmp.rebu.event.AbstractEvent;
import fr.mmp.rebu.user.model.UserInterface;

public class UserUpdatedEvent extends AbstractEvent {
    public UserUpdatedEvent(UserInterface source) {
        super(source);
    }
}
