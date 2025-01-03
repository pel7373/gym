package org.gym.config;

import org.gym.dao.TrainerDAO;
import org.gym.service.TrainerService;
import org.gym.storage.TrainerStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.security.SecureRandom;

@Configuration
public class TestConfig {
    @Bean
    public TrainerStorage trainerStorage() {
        return new TrainerStorage();
    }
    @Bean
    public TrainerDAO trainerDAO() {
        return new TrainerDAO(trainerStorage());
    }

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    public TrainerService getTrainerService() {
        return new TrainerService(trainerDAO(), secureRandom());
    }
}
