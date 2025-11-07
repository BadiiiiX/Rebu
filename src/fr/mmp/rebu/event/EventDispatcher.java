package fr.mmp.rebu.event;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher implements IEventDispatcher {

    private final List<IEventListener> listeners = new ArrayList<>();

    public IEventDispatcher register(IEventListener e) {
        this.listeners.add(e);

        return this;
    }

    public void fire(IEvent e) {
        for (IEventListener listener : this.listeners) {
            listener.onListen(e);
        }
    }

}