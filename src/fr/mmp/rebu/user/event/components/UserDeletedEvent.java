package fr.mmp.rebu.user.event.components;

import fr.mmp.rebu.event.AbstractEvent;

public class UserDeletedEvent extends AbstractEvent {
    public UserDeletedEvent(int userId) {
        super(userId);
    }
}
