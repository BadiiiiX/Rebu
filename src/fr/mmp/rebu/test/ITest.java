package fr.mmp.rebu.test;

/**
 * Interface représentant un test exécutable dans le système.
 */
public interface ITest {

    /**
     * Retourne le nom du test.
     *
     * @return le nom du test
     */
    String getName();

    /**
     * Exécute le test.
     */
    void run();
}