package fr.mmp.rebu.ride.event;

import fr.mmp.rebu.event.IEvent;
import fr.mmp.rebu.event.IEventListener;
import fr.mmp.rebu.ride.dto.RidePassengerEventDto;
import fr.mmp.rebu.ride.event.components.RideCreatedEvent;
import fr.mmp.rebu.ride.event.components.RideDeletedEvent;
import fr.mmp.rebu.ride.event.components.RidePassengerAddedEvent;
import fr.mmp.rebu.ride.event.components.RidePassengerRemovedEvent;
import fr.mmp.rebu.ride.model.RideInterface;

public class RideEventListener implements IEventListener {

    @Override
    public void onListen(IEvent e) {
        switch (e) {
            case RideCreatedEvent created -> onRideCreated((RideInterface) created.getSource());
            case RideDeletedEvent deleted -> onRideDeleted((Integer) deleted.getSource());
            case RidePassengerAddedEvent added -> onPassengerAdded((RidePassengerEventDto) added.getSource());
            case RidePassengerRemovedEvent removed -> onPassengerRemoved((RidePassengerEventDto) removed.getSource());
            case null, default -> {
            }
        }
    }

    private void onRideCreated(RideInterface ride) {
        System.out.printf("🚗 Nouveau trajet créé : #%d %s → %s (%s)%n",
                ride.getRideId(), ride.getOrigin(), ride.getDestination(), ride.getCar().getPlate());
    }

    private void onRideDeleted(int rideId) {
        System.out.printf("🗑️ Trajet supprimé : #%d%n", rideId);
    }

    private void onPassengerAdded(RidePassengerEventDto data) {
        System.out.printf("👥 Passager ajouté : %s dans le trajet #%d (%s → %s)%n",
                data.passenger().getUsername(), data.ride().getRideId(), data.ride().getOrigin(), data.ride().getDestination());
    }

    private void onPassengerRemoved(RidePassengerEventDto data) {
        System.out.printf("🚪 Passager retiré : %s du trajet #%d (%s → %s)%n",
                data.passenger().getUsername(), data.ride().getRideId(), data.ride().getOrigin(), data.ride().getDestination());
    }
}