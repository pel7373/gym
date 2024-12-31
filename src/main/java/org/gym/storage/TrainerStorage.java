package org.gym.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gym.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.gym.domain.Trainer;
import org.gym.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.gym.config.Config.TRAINERS_FILE_TO_READ_JSONS;

@Component
public class TrainerStorage implements CrudStorage<Trainer, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final HashMap<Long, Trainer> trainerStorage = new HashMap<>();
    private long storageNextId;

    @Override
    public List<Trainer> findAll() {
        LOGGER.info("findAll works successfully");
        return new ArrayList<>(trainerStorage.values());
    }

    @Override
    public Trainer findById(Long id) throws EntityNotFoundException {
        Trainer trainer = Optional.ofNullable(trainerStorage.get(id))
                .orElseThrow(() ->
                    new EntityNotFoundException(String.format("Trainer with id %d wasn't found!", id)));

        LOGGER.info(String.format("findById found trainer with id %d", id));
        return trainer;
    }

    @Override
    public Trainer save(Trainer trainer) {
        checkAndUpdateUserNameInTrainerIfNeeded(trainer);
        trainer.approveUserName();
        trainer.setId(storageNextId);
        trainerStorage.put(storageNextId++, trainer);
        return trainer;
    }

    @Override
    public void update(Long id, Trainer trainer) {
        checkAndUpdateUserNameInTrainerIfNeeded(trainer);
        trainer.setId(id);
        trainerStorage.put(id, trainer);
    }

    @Override
    public void deleteById(Long id) {
        trainerStorage.remove(id);
    }

    @PostConstruct
    private void populateDB() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            @SuppressWarnings("unchecked")
            List<Trainer> list = mapper.readValue(new File(TRAINERS_FILE_TO_READ_JSONS), new TypeReference<List<Trainer>>(){});
            list.forEach(t -> {
                t.setUserName("");
                save(t);
            });
        } catch (IOException e) {
            System.out.println(TRAINERS_FILE_TO_READ_JSONS);
            throw new IOException(e);
        }
    }

    private void checkAndUpdateUserNameInTrainerIfNeeded(Trainer trainer) {
        String userName = trainer.getUserName();
        String temporaryUserName = userName;
        while(isUserName(temporaryUserName)) {
            int suffix = User.getSerialNumber();
            temporaryUserName = userName + suffix;
            User.incrementSerialNumber();
        }
        trainer.setApprovedUserName(temporaryUserName);
    }

    public boolean isUserName(String userName) {
        return trainerStorage.values().stream()
                .filter(t ->
                        (t.getUserName() == null) ?
                                (userName == null ? true : false) :
                                t.getUserName().equals(userName))
                .findAny()
                .isPresent();
    }
}
