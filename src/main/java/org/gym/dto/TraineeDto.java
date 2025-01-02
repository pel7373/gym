package org.gym.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class TraineeDto {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Boolean isActive;
    private LocalDate dateOfBirth;
    private String address;
}
