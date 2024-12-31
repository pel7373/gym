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
        return trainerStorage.findAll();
    }

    @Override
    public Trainer findById(Long id) throws EntityNotFoundException {
        return trainerStorage.findById(id);
    }

    @Override
    public Trainer save(Trainer trainer) {
        return trainerStorage.save(trainer);
    }

    @Override
    public void update(Long id, Trainer trainer) {
        trainerStorage.update(id, trainer);
    }

    @Override
    public void deleteById(Long id) {
        trainerStorage.deleteById(id);
    }
}
