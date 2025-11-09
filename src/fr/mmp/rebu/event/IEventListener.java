package fr.mmp.rebu.event;

/**
 * Interface représentant un écouteur d'événements.
 */
public interface IEventListener {

    /**
     * Méthode appelée lorsqu'un événement est déclenché.
     *
     * @param e l'événement reçu
     */
    void onListen(IEvent e);
}