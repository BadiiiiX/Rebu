package fr.mmp.rebu.event;


/**
 * Représente un événement dans le système.
 */
public interface IEvent {

    /**
     * Retourne la source de l'événement.
     *
     * @return l'objet à l'origine de l'événement
     */
    Object getSource();
}