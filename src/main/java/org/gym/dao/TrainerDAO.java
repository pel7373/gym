package org.gym.dao;

import org.gym.domain.Trainer;
import org.gym.exception.EntityNotFoundException;
import org.gym.storage.TrainerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class TrainerDAO implements CrudDAO<Trainer, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final TrainerStorage trainerStorage;

    @Autowired
    public TrainerDAO(TrainerStorage trainerStorage) {
        this.trainerStorage = trainerStorage;
    }

    @Override
    public List<Trainer> findAll() {
        LOGGER.info("findAll has worked");
        return trainerStorage.findAll();
    }

    @Override
    public Trainer findById(Long id) throws EntityNotFoundException {
        LOGGER.info(String.format("findById is finding trainer with id %d", id));
        return trainerStorage.findById(id);
    }

    @Override
    public void save(Trainer trainer) {
        LOGGER.info(String.format("save is saving trainer %s", trainer));
        trainerStorage.save(trainer);
    }

    @Override
    public void update(Long id, Trainer trainer) {
        LOGGER.info(String.format("update is updating trainer with id %d", id));
        trainerStorage.update(id, trainer);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info(String.format("deleteById is deleting trainer with id %d", id));
        trainerStorage.deleteById(id);
    }
}
