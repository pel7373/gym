package org.gym.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource( "classpath:application.properties" )
public class Config {
    public final static String ID_CANT_BE_NEGATIVE = "id can't be negative";


    public static String USERS_FILE_TO_READ_JSONS;
    public static String TRAINERS_FILE_TO_READ_CSV;

    public static String TRAINERS_FILE_TO_READ_JSONS;

    public static String TRAINEES_FILE_TO_READ_JSONS;
    public static String TRAININGS_FILE_TO_READ_JSONS;

    @Value("${users.json}")
    private void setUsersFileToReadData(String file) {
        USERS_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainers.json}")
    private void setTrainersFileToReadDataJson(String file) {
        TRAINERS_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainers.csv}")
    private void setTrainersFileToReadData(String file) {
        TRAINERS_FILE_TO_READ_CSV = file;
    }

    @Value("${trainees.json}")
    private void setTraineesFileToReadData(String file) {
        TRAINEES_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainings.json}")
    private void setTrainingsFileToReadData(String file) {
        TRAININGS_FILE_TO_READ_JSONS = file;
    }

}
