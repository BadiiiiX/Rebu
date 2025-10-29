package fr.mmp.rebu.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserInMemoryRepository implements UserRepository {

    private final Map<String, UserInterface> storage;

    public UserInMemoryRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<UserInterface> findByMail(String mail) {
        if (mail == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(storage.get(mail.toLowerCase()));
    }

    @Override
    public boolean exists(String mail) {
        if (mail == null) {
            return false;
        }
        return storage.containsKey(mail.toLowerCase());
    }

    @Override
    public void save(UserInterface user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        storage.put(user.getMail(), user);
    }

    @Override
    public void delete(String mail) {
        if (mail == null) {
            return;
        }
        storage.remove(mail.toLowerCase());
    }

    @Override
    public List<UserInterface> findAll() {
        return new ArrayList<>(storage.values());
    }
}