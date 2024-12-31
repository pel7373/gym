package org.gym.storage;

import org.gym.domain.Trainee;
import org.gym.domain.User;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@Component
public class TraineeStorage {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final HashMap<Long, Trainee> traineeStorage = new HashMap<>();
    private long storageNextId;
    
}
