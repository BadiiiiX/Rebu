package fr.mmp.rebu.car.event;

import fr.mmp.rebu.car.event.components.CarCreatedEvent;
import fr.mmp.rebu.car.event.components.CarDeletedEvent;
import fr.mmp.rebu.car.event.components.CarUpdatedEvent;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.event.IEvent;
import fr.mmp.rebu.event.IEventListener;

public class CarEventListener implements IEventListener {
    @Override
    public void onListen(IEvent e) {
        switch (e) {
            case CarCreatedEvent created -> onCarCreated((CarInterface) created.getSource());
            case CarUpdatedEvent updated -> onCarUpdated((CarInterface) updated.getSource());
            case CarDeletedEvent deleted -> onCarDeleted((String) deleted.getSource());
            case null, default -> {
            }
        }
    }

    private void onCarCreated(CarInterface car) {
        System.out.printf("🚗 Nouvelle voiture enregistrée : %s (places : %d, propriétaire #%d)%n",
                car.getPlate(), car.getPassengersNumber(), car.getOwner().getUserId());
    }

    private void onCarUpdated(CarInterface car) {
        System.out.printf("✏️  Voiture mise à jour : %s (%d places)%n",
                car.getPlate(), car.getPassengersNumber());
    }

    private void onCarDeleted(String plate) {
        System.out.printf("🗑️  Voiture supprimée : %s%n", plate);
    }

}
