package org.gym.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Data
public class Training {
    private long id;
    private long traineeId;
    private long trainerId;
    private String trainingName;
    @JsonProperty("trainingType")
    private TrainingType trainingType;
    private LocalDate trainingDate;
    private long trainingDuration;
}
