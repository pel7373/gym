package org.gym.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gym.dao.TrainingDAO;
import org.gym.domain.Training;
import org.gym.dto.TrainingDto;
import org.gym.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.gym.config.Config.ID_CANT_BE_NEGATIVE;

@Service
public class TrainingService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final TrainingDAO trainingDAO;
    private final ObjectMapper objectMapper;

    @Autowired
    public TrainingService(TrainingDAO trainingDAO, ObjectMapper objectMapper) {
        this.trainingDAO = trainingDAO;
        this.objectMapper = objectMapper;
    }

    public List<TrainingDto> getAll() {
        LOGGER.info("getAll is working");
        List<TrainingDto> trainingDtoList = new ArrayList<>();
        trainingDAO.findAll().forEach(t ->
                trainingDtoList.add(
                        objectMapper.convertValue((t), TrainingDto.class)));
        LOGGER.info("getAll has worked successfully");
        return trainingDtoList;
    }


    public TrainingDto getById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        LOGGER.info(String.format("getById is finding training with id %d", id));
        return objectMapper.convertValue(trainingDAO.findById(id), TrainingDto.class);
    }

    public void save(Training training) {
        LOGGER.info(String.format("save is saving training %s", training));
        trainingDAO.save(training);
    }

    public void update(Long id, TrainingDto trainingDto) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }

        Training training = objectMapper.convertValue(trainingDto, Training.class);
        LOGGER.info(String.format("update is updating training with id %d", id));
        trainingDAO.update(id, training);
    }

    public void deleteById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }

        LOGGER.info(String.format("deleteById is deleting training with id %d", id));
        trainingDAO.deleteById(id);
    }
}