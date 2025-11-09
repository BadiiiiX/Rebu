package fr.mmp.rebu.event;

/**
 * Interface responsable de la gestion et de la diffusion des événements.
 */
public interface IEventDispatcher {

    /**
     * Enregistre un écouteur d'événements.
     *
     * @param e l'écouteur à enregistrer
     * @return l'instance du dispatcher (pour chaînage)
     */
    IEventDispatcher register(IEventListener e);

    /**
     * Déclenche un événement et le transmet à tous les écouteurs enregistrés.
     *
     * @param e l'événement à diffuser
     */
    void fire(IEvent e);
}