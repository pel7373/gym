package org.gym.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import jakarta.annotation.PostConstruct;
import org.gym.dao.TrainerDAO;
import org.gym.domain.Trainer;
import org.gym.dto.TrainerDto;
import org.gym.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.gym.config.Config.ID_CANT_BE_NEGATIVE;

@Service
public class TrainerService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final SecureRandom secureRandom;
    private TrainerDAO trainerDAO;
    private int serialNumberForUserName;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public TrainerService(TrainerDAO trainerDAO, SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        this.trainerDAO = trainerDAO;

    }

    public List<TrainerDto> getAll() {
        LOGGER.info("getAll is working");
        List<TrainerDto> trainerDtoList = new ArrayList<>();
        trainerDAO.findAll().forEach(t ->
                trainerDtoList.add(
                        objectMapper.convertValue((t), TrainerDto.class)));
        LOGGER.info("getAll has worked successfully");
        return trainerDtoList;
    }


    public TrainerDto getById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        LOGGER.info(String.format("getById is finding trainer with id %d", id));
        TrainerDto trainerDto = objectMapper.convertValue(trainerDAO.findById(id), TrainerDto.class);
        return trainerDto;
    }

    public void save(Trainer trainer) {
        trainer.setUserName(createUserNameForTrainer(trainer, trainerDAO.findAll()));
        trainer.setPassword(createSecurePassword());
        LOGGER.info(String.format("save is saving trainer %s", trainer));
        trainerDAO.save(trainer);
    }

    public void update(Long id, TrainerDto trainerDto) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }

        Trainer oldTrainer = trainerDAO.findById(id);
        Trainer trainer = objectMapper.convertValue(trainerDto, Trainer.class);
        trainer.setPassword(oldTrainer.getPassword());
        LOGGER.info(String.format("update is updating trainer with id %d", id));
        trainerDAO.update(id, trainer);
    }

    public void deleteById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }

        LOGGER.info(String.format("deleteById is deleting trainer with id %d", id));
        trainerDAO.deleteById(id);
    }

    public String createSecurePassword() {
        IntStream is = secureRandom.ints(10, 33, 127);
        String securePassword = is.collect(
                StringBuilder::new,
                (sb, i) -> sb.append((char)i),
                StringBuilder::append
        ).toString();
        return securePassword;
    }

    private String createUserNameForTrainer(Trainer trainer, List<Trainer> trainerList) {
        String userName = trainer.getFirstName() + "." + trainer.getLastName();
        String temporaryUserName = userName;
        while(isUserNameExist(temporaryUserName, trainerList)) {
            int suffix = serialNumberForUserName++;
            temporaryUserName = userName + suffix;
        }
        return temporaryUserName;
    }

    private boolean isUserNameExist(String userName, List<Trainer> trainerList) {
        return trainerList.stream()
                .anyMatch(t -> (t.getUserName() == null) ?
                        userName == null : t.getUserName().equals(userName));
    }
}