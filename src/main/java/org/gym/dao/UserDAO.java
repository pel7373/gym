package org.gym.dao;

import org.gym.domain.User;
import org.gym.storage.UserStorage;

public class UserDAO {
    UserStorage userStorage;

    public User findById(long id) {
        return  userStorage.findById(id);
    }

    public void save(User user) {
        userStorage.save(user);
    }

    public void update(long id, User user) {
        userStorage.update(id, user);
    }

    public void deleteById(long id) {
        userStorage.deleteById(id);
    }
}
