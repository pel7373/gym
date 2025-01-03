package org.gym.dao;

import org.gym.domain.Trainee;
import org.gym.exception.EntityNotFoundException;
import org.gym.storage.TraineeStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class TraineeDAO implements CrudDAO<Trainee, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final TraineeStorage traineeStorage;

    @Autowired
    public TraineeDAO(TraineeStorage traineeStorage) {
        this.traineeStorage = traineeStorage;
    }

    @Override
    public List<Trainee> findAll() {
        LOGGER.info("findAll has worked");
        return traineeStorage.findAll();
    }

    @Override
    public Trainee findById(Long id) throws EntityNotFoundException {
        LOGGER.info(String.format("findById is finding trainee with id %d", id));
        return traineeStorage.findById(id);
    }

    @Override
    public void save(Trainee trainee) {
        LOGGER.info(String.format("save is saving trainee %s", trainee));
        traineeStorage.save(trainee);
    }

    @Override
    public void update(Long id, Trainee trainee) {
        LOGGER.info(String.format("update is updating trainee with id %d", id));
        traineeStorage.update(id, trainee);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info(String.format("deleteById is deleting trainee with id %d", id));
        traineeStorage.deleteById(id);
    }


}
