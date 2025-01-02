package org.gym.service;

import org.gym.config.TestConfig;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.mockito.Mockito;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.security.SecureRandom;



@SpringJUnitConfig
@ExtendWith({org.springframework.test.context.junit.jupiter.SpringExtension.class})
@ContextConfiguration(classes = TestConfig.class)
public class TrainerServiceTest {

    @Autowired
    ApplicationContext webApplicationContext;


//    @Autowired
//    TrainerService trainerService;
//
//    @Test
//    void getAll() {
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
    void createSecurePassword() {

//
        //TestContext testContext = TestContext

        String testPassword = "ABCDEFGHIJ";
        SecureRandom mockSecureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(mockSecureRandom.ints(10, 33, 127)).thenReturn(testPassword.chars());
        //secureRandomBean.ints(10, 33, 127)
//        TrainerService trainerService = .getBean(TrainerService.class);
//        String password = trainerService.createSecurePassword();
//        assertEquals(testPassword, password);


    }
}