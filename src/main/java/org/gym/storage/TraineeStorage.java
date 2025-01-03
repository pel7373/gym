package org.gym.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.gym.domain.Trainee;
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

import static org.gym.config.Config.TRAINEES_FILE_TO_READ_JSONS;
import static org.gym.config.Config.TRAINEES_FILE_TO_WRITE_JSONS;

@Component
public class TraineeStorage implements CrudStorage<Trainee, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final HashMap<Long, Trainee> traineeMap = new HashMap<>();
    private long storageNextId;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<Trainee> findAll() {
        LOGGER.info("findAll works successfully");
        return new ArrayList<>(traineeMap.values());
    }

    @Override
    public Trainee findById(Long id) throws EntityNotFoundException {
        Trainee trainee = Optional.ofNullable(traineeMap.get(id))
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Trainee with id %d wasn't found!", id)));

        LOGGER.info(String.format("findById found trainer with id %d", id));
        return trainee;
    }

    @Override
    public void save(Trainee trainee) {
        trainee.setId(storageNextId);
        traineeMap.put(storageNextId++, trainee);
        LOGGER.info(String.format("save saved trainer with id %d", trainee.getId()));
    }

    @Override
    public void update(Long id, Trainee trainee) {
        traineeMap.put(id, trainee);
        LOGGER.info(String.format("update updated trainer with id %d", trainee.getId()));
    }

    @Override
    public void deleteById(Long id) {
        if(traineeMap.containsKey(id)) {
            LOGGER.info(String.format("deleteById deleted trainee with id %d", id));
            traineeMap.remove(id);
        } else {
            LOGGER.info(String.format("deleteById didn't delete trainee with id %d, because traineeStorage doesn't contain it", id));
        }
    }

    @PostConstruct
    private void restoreDataFromFileToStorage() throws IOException {
        try {
            List<Trainee> traineeList = objectMapper.readValue(new File(TRAINEES_FILE_TO_READ_JSONS), new TypeReference<List<Trainee>>(){});
            traineeList.forEach(t -> {
                traineeMap.put(t.getId(), t);
            });

            storageNextId = traineeList.stream()
                    .mapToLong(Trainee::getId)
                    .max().getAsLong()
                    + 1;
        } catch (IOException e) {
            String errorMessage = String.format("restoreDataFromFileToDb couldn't read file %s", TRAINEES_FILE_TO_READ_JSONS);
            LOGGER.info(errorMessage);
            throw new IOException(errorMessage);
        }
        LOGGER.info(String.format("restoreDataFromFileToDb restored storage with data from file %s", TRAINEES_FILE_TO_READ_JSONS));
    }

    @PreDestroy
    public void backupDataFromStorageToFile() throws IOException {
        try {
            List<Trainee> traineeList = new ArrayList<>(traineeMap.values());
            objectMapper.writeValue(new File(TRAINEES_FILE_TO_WRITE_JSONS), traineeList);
        } catch (IOException e) {
            String errorMessage = String.format("backupDataFromStorageToFile couldn't write to file %s", TRAINEES_FILE_TO_WRITE_JSONS);
            LOGGER.info(errorMessage);
            throw new IOException(errorMessage);
        }
        LOGGER.info(String.format("backupDataFromStorageToFile saved data from storage to file %s", TRAINEES_FILE_TO_WRITE_JSONS));
    }
}
