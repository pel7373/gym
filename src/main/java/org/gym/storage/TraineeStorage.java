package org.gym.storage;

import org.gym.domain.Trainee;
import org.gym.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TraineeStorage {
    private final HashMap<Long, Trainee> traineeStorage = new HashMap<>();
    private long storageNextId;
    
}
