package fr.mmp.rebu.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<UserInterface> findByMail(String mail);

    boolean exists(String mail);

    void save(UserInterface user);

    void delete(String mail);

    List<UserInterface> findAll();
}