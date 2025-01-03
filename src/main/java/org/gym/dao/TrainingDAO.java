package org.gym.dao;

import org.gym.domain.Training;
import org.gym.storage.TrainingStorage;
import org.gym.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

@Component
public class TrainingDAO  implements CrudDAO<Training, Long>{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final TrainingStorage trainingStorage;

    @Autowired
    public TrainingDAO(TrainingStorage trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Override
    public List<Training> findAll() {
        LOGGER.info("findAll has worked");
        return trainingStorage.findAll();
    }

    @Override
    public Training findById(Long id) throws EntityNotFoundException {
        LOGGER.info(String.format("findById is finding training with id %d", id));
        return trainingStorage.findById(id);
    }

    @Override
    public void save(Training training) {
        LOGGER.info(String.format("save is saving training %s", training));
        trainingStorage.save(training);
    }

    @Override
    public void update(Long id, Training training) {
        LOGGER.info(String.format("update is updating training with id %d", id));
        trainingStorage.update(id, training);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info(String.format("deleteById is deleting training with id %d", id));
        trainingStorage.deleteById(id);
    }
}
