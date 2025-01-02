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

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.gym.config.Config.ID_CANT_BE_NEGATIVE;
import static org.gym.config.Config.TRAINERS_FILE_TO_READ_JSONS;

@Service
public class TrainerService implements CrudService<Trainer, TrainerDto, Long> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    ObjectMapper objectMapper = new ObjectMapper();
    private final SecureRandom secureRandomBean;

    private TrainerDAO trainerDAO;

    private int serialNumberForUserName;

    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    public TrainerService(TrainerDAO trainerDAO, SecureRandom secureRandomBean) {
        this.trainerDAO = trainerDAO;
        this.secureRandomBean = secureRandomBean;
    }

    @Override
    public List<TrainerDto> getAll() {
        List<Trainer> trainerList = trainerDAO.findAll();
        List<TrainerDto> trainerDtoList = new ArrayList<>();
        trainerList.forEach(t -> trainerDtoList.add(objectMapper.convertValue((t), TrainerDto.class)));
        return trainerDtoList;
    }

    @Override
    public TrainerDto getById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        return  objectMapper.convertValue(trainerDAO.findById(id), TrainerDto.class);
    }

    @Override
    public void save(Trainer trainer) {
        trainer.setUserName(createUserNameForTrainer(trainer, trainerDAO.findAll()));
        trainer.setPassword(createSecurePassword());
        trainerDAO.save(trainer);
    }

    @Override
    public void update(Long id, TrainerDto trainerDto) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }

        Trainer oldTrainer = trainerDAO.findById(id);
        Trainer trainer = objectMapper.convertValue(trainerDto, Trainer.class);
        trainer.setPassword(oldTrainer.getPassword());
        trainerDAO.update(id, trainer);
    }

    @Override
    public void deleteById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        trainerDAO.deleteById(id);
    }


    public String createSecurePassword() {
        IntStream is = secureRandomBean.ints(10, 33, 127);
        String securePassword = is.collect(
                StringBuilder::new,
                (sb, i) -> sb.append((char)i),
                StringBuilder::append
        ).toString();
        return securePassword;
    }


    @PostConstruct
    private void fillDbWithInitialData() throws IOException {
        try {
            @SuppressWarnings("unchecked")
            List<Trainer> trainerList = objectMapper.readValue(new File(TRAINERS_FILE_TO_READ_JSONS), new TypeReference<List<Trainer>>(){});
            trainerList.forEach(t -> {
                trainerDAO.save(t);
            });
        } catch (IOException e) {
            String errorMessage = String.format("fillDBwithInitialData couldn't read file %s", TRAINERS_FILE_TO_READ_JSONS);
            LOGGER.info(errorMessage);
            throw new IOException(errorMessage);
        }
        LOGGER.info(String.format("fillDBwithInitialData populated storage with data from file %s", TRAINERS_FILE_TO_READ_JSONS));
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