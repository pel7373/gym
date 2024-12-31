package org.gym.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.gym.dao.TrainerDAO;
import org.gym.domain.Trainer;
import org.gym.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.gym.config.Config.ID_CANT_BE_NEGATIVE;

@Service
public class TrainerService implements CrudService<Trainer, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    ObjectMapper objectMapper = new ObjectMapper();
    private TrainerDAO trainerDAO;

    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    public TrainerService(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    @Override
    public List<Trainer> getAll() {
        return  trainerDAO.findAll();
    }

    @Override
    public Trainer getById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        return  trainerDAO.findById(id);
    }

    @Override
    public void save(Trainer trainer) {
        trainerDAO.save(trainer);
    }

    @Override
    public void update(Long id, Trainer trainer) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        trainerDAO.update(id, trainer);
    }

    @Override
    public void deleteById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        trainerDAO.deleteById(id);
    }
}
