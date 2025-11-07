package fr.mmp.rebu.event;

public abstract class AbstractEvent implements IEvent {

    private final Object source;

    public AbstractEvent(Object source) {
        this.source = source;
    }

    @Override
    public Object getSource() {
        return this.source;
    }

}
