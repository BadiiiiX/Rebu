package fr.mmp.rebu.event;

public interface IEventDispatcher {
    IEventDispatcher register(IEventListener e);

    void fire(IEvent e);
}
