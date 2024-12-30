package org.gym.storage;

import org.gym.domain.Training;
import org.gym.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TrainingStorage {
    private final HashMap<Long, Training> trainingStorage = new HashMap<>();
    private long storageNextId;
}
