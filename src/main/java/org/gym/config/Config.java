package org.gym.config;

import org.gym.dao.TrainerDAO;
import org.gym.service.TrainerService;
import org.gym.storage.TrainerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;

import java.security.SecureRandom;

@Configuration
@PropertySource( "classpath:application.properties" )
public class Config {
    public final static String ID_CANT_BE_NEGATIVE = "id can't be negative";

    @Bean
    public SecureRandom secureRandomBean() {
        return new SecureRandom();
    }

    public static String TRAINERS_FILE_TO_READ_JSONS;
    public static String TRAINEES_FILE_TO_READ_JSONS;
    public static String TRAININGS_FILE_TO_READ_JSONS;


    @Value("${trainers.json}")
    private void setTrainersFileToReadDataJson(String file) {
        TRAINERS_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainees.json}")
    private void setTraineesFileToReadData(String file) {
        TRAINEES_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainings.json}")
    private void setTrainingsFileToReadData(String file) {
        TRAININGS_FILE_TO_READ_JSONS = file;
    }

    @Configuration
    public static class TestConfig {

        @Autowired
        private TrainerStorage trainerStorage;
        @Autowired
        private TrainerDAO trainerDAO;

        @Bean
        public SecureRandom secureRandomBean() {
            return new SecureRandom();
        }

        @Bean
        public TrainerStorage trainerStorage() {
            return new TrainerStorage();
        }

        @Bean
        public TrainerDAO trainerDAO() {
            return new TrainerDAO(trainerStorage);
        }
        @Bean
        public TrainerService trainerService() {
            return new TrainerService(trainerDAO, secureRandomBean());
        }
    }
}
