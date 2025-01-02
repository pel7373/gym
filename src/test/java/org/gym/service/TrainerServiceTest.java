package org.gym.service;

import org.gym.config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.Mockito;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.TestConfig.class})
class TrainerServiceTest {

    @Autowired
    private TrainerService trainerService;

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

//    @Test
//    void createSecurePassword() {
//        String testPassword = "ABCDEFGHIJ";
//        SecureRandom mockSecureRandom = Mockito.mock(SecureRandom.class);
//        Mockito.when(mockSecureRandom.ints(10, 33, 127)).thenReturn(testPassword.chars());
//        //secureRandomBean.ints(10, 33, 127)
//        String password = trainerService.createSecurePassword();
//        assertEquals(testPassword, password);
//
//    }
}