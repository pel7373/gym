package org.gym.service;


import jakarta.annotation.PostConstruct;
import org.gym.dao.UserDAO;
import org.gym.domain.User;
import org.gym.exception.InvalidIdException;
import org.springframework.stereotype.Service;

import static org.gym.config.Config.ID_CANT_BE_NEGATIVE;

@Service
public class UserService {
    private UserDAO userDAO;


    public User getById(long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        return  userDAO.findById(id);
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public void update(long id, User user) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        userDAO.update(id, user);
    }

    public void deleteById(long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        userDAO.deleteById(id);
    }
}
