package org.gym.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.gym.domain.Training;
import org.gym.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.gym.config.Config.TRAININGS_FILE_TO_READ_JSONS;
import static org.gym.config.Config.TRAININGS_FILE_TO_WRITE_JSONS;

@Component
public class TrainingStorage implements CrudStorage<Training, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final HashMap<Long, Training> trainingMap = new HashMap<>();
    private long storageNextId;
    ObjectMapper objectMapper;

    @Autowired
    public TrainingStorage(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Training> findAll() {
        LOGGER.info("findAll works successfully");
        return new ArrayList<>(trainingMap.values());
    }

    @Override
    public Training findById(Long id) throws EntityNotFoundException {
        Training Training = Optional.ofNullable(trainingMap.get(id))
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("The training with id %d wasn't found!", id)));

        LOGGER.info(String.format("findById found the training with id %d", id));
        return Training;
    }

    @Override
    public void save(Training Training) {
        Training.setId(storageNextId);
        trainingMap.put(storageNextId++, Training);
        LOGGER.info(String.format("save saved the training with id %d", Training.getId()));
    }

    @Override
    public void update(Long id, Training Training) {
        trainingMap.put(id, Training);
        LOGGER.info(String.format("update updated the training with id %d", Training.getId()));
    }

    @Override
    public void deleteById(Long id) {
        if(trainingMap.containsKey(id)) {
            LOGGER.info(String.format("deleteById deleted the training with id %d", id));
            trainingMap.remove(id);
        } else {
            LOGGER.info(String.format("deleteById didn't delete the training with id %d, because trainingStorage doesn't contain it", id));
        }
    }

    @PostConstruct
    private void restoreDataFromFileToStorage() throws IOException {
        List<Training> trainingList;
        try {
            trainingList = objectMapper.readValue(new File(TRAININGS_FILE_TO_READ_JSONS), new TypeReference<List<Training>>(){});
        } catch (IOException e) {
            String errorMessage = String.format("restoreDataFromFileToDb couldn't read file %s", TRAININGS_FILE_TO_READ_JSONS);
            LOGGER.warn(errorMessage);
            return;
            //throw new IOException(errorMessage);
        }

        if(trainingList != null && !trainingList.isEmpty()) {
            trainingList.forEach(t -> {
                trainingMap.put(t.getId(), t);
            });

            storageNextId = trainingList.stream()
                    .mapToLong(Training::getId)
                    .max().getAsLong()
                    + 1;
            LOGGER.info(String.format("restoreDataFromFileToDb restored storage with data from file %s", TRAININGS_FILE_TO_READ_JSONS));
        } else {
            LOGGER.info(String.format("restoreDataFromFileToDb has read the file %s, but can't get data from it", TRAININGS_FILE_TO_READ_JSONS));
        }
    }

    @PreDestroy
    public void backupDataFromStorageToFile() {
        try {
            List<Training> TrainingList = new ArrayList<>(trainingMap.values());
            objectMapper.writeValue(new File(TRAININGS_FILE_TO_WRITE_JSONS), TrainingList);
        } catch (IOException e) {
            String errorMessage = String.format("backupDataFromStorageToFile couldn't write to file %s", TRAININGS_FILE_TO_WRITE_JSONS);
            LOGGER.warn(errorMessage);
            return;
            //throw new IOException(errorMessage);
        }
        LOGGER.info(String.format("backupDataFromStorageToFile saved data from storage to file %s", TRAININGS_FILE_TO_WRITE_JSONS));
    }


}
