package org.gym.dto;

import lombok.ToString;
import lombok.Value;
import org.gym.domain.TrainingType;

@Value
@ToString
public class TrainerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Boolean isActive;
    private TrainingType specialization;
}
