package org.gym.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
@PropertySource( "classpath:application.properties" )
@Order(1)
public class Config {
    public final static String ID_CANT_BE_NEGATIVE = "id can't be negative";

    public static String TRAINERS_FILE_TO_READ_JSONS;
    public static String TRAINERS_FILE_TO_WRITE_JSONS;
    public static String TRAINEES_FILE_TO_READ_JSONS;
    public static String TRAINEES_FILE_TO_WRITE_JSONS;
    public static String TRAININGS_FILE_TO_READ_JSONS;
    public static String TRAININGS_FILE_TO_WRITE_JSONS;

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        return objectMapper;
    }

    @Value("${trainers-in.json}")
    private void setTrainersFileToReadDataJson(String file) {
        TRAINERS_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainers-out.json}")
    private void setTrainersFileToWriteDataJson(String file) {
        TRAINERS_FILE_TO_WRITE_JSONS = file;
    }

    @Value("${trainees-in.json}")
    private void setTraineesFileToReadData(String file) {
        TRAINEES_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainees-out.json}")
    private void setTraineesFileToWriteDataJson(String file) {
        TRAINEES_FILE_TO_WRITE_JSONS = file;
    }

    @Value("${trainings-in.json}")
    private void setTrainingsFileToReadData(String file) {
        TRAININGS_FILE_TO_READ_JSONS = file;
    }

    @Value("${trainings-out.json}")
    private void setTrainingsFileToWriteDataJson(String file) {
        TRAININGS_FILE_TO_WRITE_JSONS = file;
    }
}
