package org.gym.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gym.dao.TraineeDAO;
import org.gym.dao.TrainerDAO;
import org.gym.domain.Trainee;
import org.gym.domain.Trainer;
import org.gym.dto.TraineeDto;
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
public class TraineeService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final SecureRandom secureRandom;
    private TraineeDAO traineeDAO;
    private int serialNumberForUserName;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public TraineeService(TraineeDAO traineeDAO, SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        this.traineeDAO = traineeDAO;
    }

    public List<TraineeDto> getAll() {
        LOGGER.info("getAll is working");
        List<TraineeDto> traineeDtoList = new ArrayList<>();
        traineeDAO.findAll().forEach(t ->
                traineeDtoList.add(
                        objectMapper.convertValue((t), TraineeDto.class)));
        LOGGER.info("getAll has worked successfully");
        return traineeDtoList;
    }


    public TraineeDto getById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }
        LOGGER.info(String.format("getById is finding trainee with id %d", id));
        TraineeDto traineeDto = objectMapper.convertValue(traineeDAO.findById(id), TraineeDto.class);
        return traineeDto;
    }

    public void save(Trainee trainee) {
        trainee.setUserName(createUserNameForTrainee(trainee, traineeDAO.findAll()));
        trainee.setPassword(createSecurePassword());
        LOGGER.info(String.format("save is saving trainee %s", trainee));
        traineeDAO.save(trainee);
    }

    public void update(Long id, TraineeDto traineeDto) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }

        Trainee oldTrainee = traineeDAO.findById(id);
        Trainee trainee = objectMapper.convertValue(traineeDto, Trainee.class);
        trainee.setPassword(oldTrainee.getPassword());
        LOGGER.info(String.format("update is updating trainee with id %d", id));
        traineeDAO.update(id, trainee);
    }

    public void deleteById(Long id) {
        if(id < 0) {
            throw new InvalidIdException(ID_CANT_BE_NEGATIVE);
        }

        LOGGER.info(String.format("deleteById is deleting trainee with id %d", id));
        traineeDAO.deleteById(id);
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

    private String createUserNameForTrainee(Trainee trainee, List<Trainee> traineeList) {
        String userName = trainee.getFirstName() + "." + trainee.getLastName();
        String temporaryUserName = userName;
        while(isUserNameExist(temporaryUserName, traineeList)) {
            int suffix = serialNumberForUserName++;
            temporaryUserName = userName + suffix;
        }
        return temporaryUserName;
    }

    private boolean isUserNameExist(String userName, List<Trainee> traineeList) {
        return traineeList.stream()
                .anyMatch(t -> (t.getUserName() == null) ?
                        userName == null : t.getUserName().equals(userName));
    }
}
