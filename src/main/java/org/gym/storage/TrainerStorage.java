package org.gym.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.gym.domain.Trainer;
import org.gym.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public void save(Trainer trainer) {
        trainer.setId(storageNextId);
        trainerStorage.put(storageNextId++, trainer);
        LOGGER.info(String.format("save saved trainer with id %d", trainer.getId()));
    }

    @Override
    public void update(Long id, Trainer trainer) {
        trainerStorage.put(id, trainer);
        LOGGER.info(String.format("update updated trainer with id %d", trainer.getId()));
    }

    @Override
    public void deleteById(Long id) {
        if(trainerStorage.containsKey(id)) {
            LOGGER.info(String.format("deleteById deleted trainer with id %d", id));
            trainerStorage.remove(id);
        } else {
            LOGGER.info(String.format("deleteById didn't delete trainer with id %d, because trainerStorage doesn't contain it", id));
        }
    }
}