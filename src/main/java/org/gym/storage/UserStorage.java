package org.gym.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.gym.domain.User;
import org.gym.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class UserStorage {
    private final HashMap<Long, User> userStorage = new HashMap<>();
    private long storageNextId;

    public User findByUserName(String userName) {
        return userStorage.values().stream()
                        .filter(un -> un.getUserName().equals(userName))
                        .findFirst()
                        .orElseThrow(() -> new UserNotFoundException(
                                String.format("User with userName=%s was not found", userName)));
    }

    public User findById(long id) {
        return  Optional.ofNullable(userStorage.get(id))
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with id %d wasn't found!", id)));
    }

    public void save(User user) {
        userStorage.put(storageNextId++, user);
    }

    public void update(long id, User user) {
        userStorage.put(id, user);
    }

    public void deleteById(long id) {
        userStorage.remove(id);
    }

    @PostConstruct
    private void populateDB() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //@SuppressWarnings("unchecked")
            List<User> list2 = mapper.readValue(new File("Users.json"), new TypeReference<List<User>>(){});
            list2.forEach(this::save);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
