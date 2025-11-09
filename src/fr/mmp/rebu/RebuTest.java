package fr.mmp.rebu;

import fr.mmp.rebu.car.test.CarServiceTest;
import fr.mmp.rebu.ride.test.RideServiceTest;
import fr.mmp.rebu.test.ITest;
import fr.mmp.rebu.user.test.UserServiceTest;

import java.util.List;

public class RebuTest {

    public static void main(String[] args) {

        List<ITest> tests = List.of(
                new UserServiceTest(),
                new RideServiceTest(),
                new CarServiceTest()
        );

        System.out.println("===== 🚀 DÉMARRAGE DES TESTS =====\n");


        for (ITest test : tests) {
            try {
                System.out.println("🔹🔹 Lancement du test " + test.getName() + " 🔹🔹\n");
                test.run();
                System.out.println("🔹🔹 Fin du test " + test.getName() + " 🔹🔹\n");
            } catch (Exception e) {
                System.out.println("❌ Erreur pendant " + test.getName() + " : " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("===== ✅ TOUS LES TESTS TERMINÉS =====");
    }
}
