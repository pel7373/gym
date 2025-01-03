package org.gym.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.gym.domain.Trainer;
import org.gym.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.gym.config.Config.TRAINERS_FILE_TO_READ_JSONS;
import static org.gym.config.Config.TRAINERS_FILE_TO_WRITE_JSONS;

@Component
public class TrainerStorage implements CrudStorage<Trainer, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final HashMap<Long, Trainer> trainerMap = new HashMap<>();
    private long storageNextId;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<Trainer> findAll() {
        LOGGER.info("findAll works successfully");
        return new ArrayList<>(trainerMap.values());
    }

    @Override
    public Trainer findById(Long id) throws EntityNotFoundException {
        Trainer trainer = Optional.ofNullable(trainerMap.get(id))
                .orElseThrow(() ->
                    new EntityNotFoundException(String.format("Trainer with id %d wasn't found!", id)));

        LOGGER.info(String.format("findById found trainer with id %d", id));
        return trainer;
    }

    @Override
    public void save(Trainer trainer) {
        trainer.setId(storageNextId);
        trainerMap.put(storageNextId++, trainer);
        LOGGER.info(String.format("save saved trainer with id %d", trainer.getId()));
    }

    @Override
    public void update(Long id, Trainer trainer) {
        trainerMap.put(id, trainer);
        LOGGER.info(String.format("update updated trainer with id %d", trainer.getId()));
    }

    @Override
    public void deleteById(Long id) {
        if(trainerMap.containsKey(id)) {
            LOGGER.info(String.format("deleteById deleted trainer with id %d", id));
            trainerMap.remove(id);
        } else {
            LOGGER.info(String.format("deleteById didn't delete trainer with id %d, because trainerStorage doesn't contain it", id));
        }
    }

    @PostConstruct
    private void restoreDataFromFileToStorage() throws IOException {
        try {
            List<Trainer> trainerList = objectMapper.readValue(new File(TRAINERS_FILE_TO_READ_JSONS), new TypeReference<List<Trainer>>(){});
            trainerList.forEach(t -> {
                trainerMap.put(t.getId(), t);
            });

            storageNextId = trainerList.stream()
                    .mapToLong(Trainer::getId)
                    .max().getAsLong()
            + 1;
        } catch (IOException e) {
            String errorMessage = String.format("restoreDataFromFileToDb couldn't read file %s", TRAINERS_FILE_TO_READ_JSONS);
            LOGGER.info(errorMessage);
            throw new IOException(errorMessage);
        }
        LOGGER.info(String.format("restoreDataFromFileToDb restored storage with data from file %s", TRAINERS_FILE_TO_READ_JSONS));
    }

    @PreDestroy
    public void backupDataFromStorageToFile() throws IOException {
        try {
            List<Trainer> trainerList = new ArrayList<>(trainerMap.values());
            objectMapper.writeValue(new File(TRAINERS_FILE_TO_WRITE_JSONS), trainerList);
        } catch (IOException e) {
            String errorMessage = String.format("backupDataFromStorageToFile couldn't write to file %s", TRAINERS_FILE_TO_WRITE_JSONS);
            LOGGER.info(errorMessage);
            throw new IOException(errorMessage);
        }
        LOGGER.info(String.format("backupDataFromStorageToFile saved data from storage to file %s", TRAINERS_FILE_TO_WRITE_JSONS));
    }
}