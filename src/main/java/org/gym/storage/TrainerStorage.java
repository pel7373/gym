package org.gym.storage;

import org.gym.domain.Trainer;
import org.gym.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TrainerStorage {
    private final HashMap<Long, Trainer> trainerStorage = new HashMap<>();
    private long storageNextId;
}
