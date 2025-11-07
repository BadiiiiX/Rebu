package fr.mmp.rebu.user.event;

import fr.mmp.rebu.event.IEvent;
import fr.mmp.rebu.event.IEventListener;
import fr.mmp.rebu.user.event.components.*;
import fr.mmp.rebu.user.model.UserInterface;

public class UserEventListener implements IEventListener {
    @Override
    public void onListen(IEvent e) {
        switch (e) {
            case UserCreatedEvent userCreatedEvent -> onUserCreated((UserInterface) userCreatedEvent.getSource());
            case UserUpdatedEvent userUpdatedEvent -> onUserUpdated((UserInterface) userUpdatedEvent.getSource());
            case UserDeletedEvent userDeletedEvent -> onUserDeleted((Integer) userDeletedEvent.getSource());
            case UserLoggedEvent userLoggedEvent -> onUserLogged((UserInterface) userLoggedEvent.getSource());
            case UserLogoutEvent logoutEvent -> onUserLogout((UserInterface) logoutEvent.getSource());
            case UserFailedAuthEvent _ -> onUserFailedAuth();
            case null, default -> {
            }
        }
    }

    private void onUserCreated(UserInterface user) {
        System.out.printf("🎉 Nouvel utilisateur créé : %s (%s)%n",
                user.getUsername(), user.getEmail());
    }

    private void onUserUpdated(UserInterface user) {
        System.out.printf("✏️  Profil mis à jour pour : %s (%s)%n",
                user.getUsername(), user.getEmail());
    }

    private void onUserDeleted(int userId) {
        System.out.printf("🗑️  Utilisateur %s supprimé %n",
                userId);
    }

    private void onUserLogged(UserInterface user) {
        System.out.printf("🔑 Connexion réussie : bienvenue %s !%n", user.getUsername());
    }

    private void onUserFailedAuth() {
        System.out.println("❌ Tentative de connexion échouée. Veuillez réessayer.");
    }

    private void onUserLogout(UserInterface user) {
        System.out.printf("🚪 Déconnexion : à bientôt %s 👋%n",
                user.getUsername());
    }

}
