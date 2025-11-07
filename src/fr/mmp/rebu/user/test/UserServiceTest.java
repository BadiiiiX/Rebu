package fr.mmp.rebu.user.test;

import fr.mmp.rebu.test.ITest;
import fr.mmp.rebu.user.dao.UserInDatabaseDAO;
import fr.mmp.rebu.user.factory.UserFactory;
import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.user.service.UserServiceImpl;

public class UserServiceTest implements ITest {

    @Override
    public String getName() {
        return "UserService";
    }

    @Override
    public void run() {
        var dao = new UserInDatabaseDAO();
        var service = new UserServiceImpl(dao);

        createUserTest(service);
        readUserTest(service);
        updateUserTest(service);
        authUserTest(service);
        deleteUserTest(service);
    }

    private UserInterface createdUser;

    private void createUserTest(UserServiceImpl service) {
        System.out.println("\t▶️ Test CREATE...");
        var user = UserFactory.build("service@test.com", "ServiceUser", "password123");
        createdUser = service.createUser(user);
        System.out.println(createdUser != null ? "\t\t✅ Création réussie" : "\t\t❌ Échec création");
        System.out.println();
    }

    private void readUserTest(UserServiceImpl service) {
        System.out.println("\t▶️ Test READ...");
        var found = service.findUserById(createdUser.getUserId());
        System.out.println(found != null ? "\t\t✅ Lecture OK" : "\t\t❌ Lecture échouée");
        System.out.println();
    }

    private void updateUserTest(UserServiceImpl service) {
        System.out.println("\t▶️ Test UPDATE...");
        createdUser = UserFactory.build(createdUser.getUserId(), createdUser.getEmail(), "UpdatedServiceUser", createdUser.getPassword());
        service.updateUser(createdUser);
        var updated = service.findUserById(createdUser.getUserId());
        System.out.println("\t\t✅ Nouveau nom : " + updated.getUsername());
        System.out.println();
    }

    private void authUserTest(UserServiceImpl service) {
        System.out.println("\t▶️ Test AUTH...");
        var auth = service.verifyUserAuthentication(createdUser.getEmail(), createdUser.getPassword());
        System.out.println(auth != null ? "\t\t✅ Auth OK" : "\t\t❌ Auth échouée");
        System.out.println();
    }

    private void deleteUserTest(UserServiceImpl service) {
        System.out.println("\t▶️ Test DELETE...");
        service.deleteUser(createdUser.getUserId());
        var deleted = service.findUserById(createdUser.getUserId());
        System.out.println(deleted == null ? "\t\t✅ Suppression OK" : "\t\t❌ Suppression échouée");
        System.out.println();
    }
}
