package org.gym.dao;

import org.gym.storage.TraineeStorage;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TraineeDAO {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private TraineeStorage traineeStorage;

}
