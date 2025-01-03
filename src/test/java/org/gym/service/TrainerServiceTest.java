package org.gym.service;

import org.gym.config.TestConfig;

import org.gym.dto.TrainerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestPropertySource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.mockito.Mockito;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.SecureRandom;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainerServiceTest {

//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Autowired
//    TrainerService trainerService;
//
//    @Test
//    void getAll() {
//        //TrainerService trainerService = applicationContext.getBean(TrainerService.class);
//        List<TrainerDto> trainerDtoList = trainerService.getAll();
//        assertNotNull(trainerDtoList);
//    }
//
//    @Test
//    void getById() {
//    }
//
//    @Test
//    void save() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void deleteById() {
//    }

    @Test
    void createSecurePassword(ApplicationContext applicationContext) {

        String testPassword = "ABCDEFGHIJ";
        SecureRandom mockSecureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(mockSecureRandom.ints(10, 33, 127)).thenReturn(testPassword.chars());
        TrainerService trainerService = applicationContext.getBean(TrainerService.class);
        String password = trainerService.createSecurePassword();
        assertEquals(testPassword, password);


    }
}