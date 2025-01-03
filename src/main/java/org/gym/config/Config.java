package org.gym.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

@Configuration
@PropertySource( "classpath:application.properties" )
@Order(1)
public class Config {
    public final static String ID_CANT_BE_NEGATIVE = "id can't be negative";

    public static String TRAINERS_FILE_TO_READ_JSONS;
    public static String TRAINERS_FILE_TO_WRITE_JSONS;
    public static String TRAINEES_FILE_TO_READ_JSONS;
    public static String TRAININGS_FILE_TO_READ_JSONS;

    @Value("${trainers-in.json}")
    private void setTrainersFileToReadDataJson(String file) {
        TRAINERS_FILE_TO_READ_JSONS = file;
    }
    @Value("${trainers-out.json}")
    private void setTrainersFileToWriteDataJson(String file) {
        TRAINERS_FILE_TO_WRITE_JSONS = file;
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
