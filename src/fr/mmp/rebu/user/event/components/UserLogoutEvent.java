package fr.mmp.rebu.user.event.components;

import fr.mmp.rebu.event.AbstractEvent;
import fr.mmp.rebu.user.model.UserInterface;

public class UserLogoutEvent extends AbstractEvent {
    public UserLogoutEvent(UserInterface source) {
        super(source);
    }
}
