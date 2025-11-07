package fr.mmp.rebu.user.event.components;

import fr.mmp.rebu.event.AbstractEvent;
import fr.mmp.rebu.user.model.UserInterface;

public class UserLoggedEvent extends AbstractEvent {
    public UserLoggedEvent(UserInterface source) {
        super(source);
    }
}
