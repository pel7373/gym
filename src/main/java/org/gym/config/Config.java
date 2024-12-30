package org.gym.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;


import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import org.gym.domain.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class Config {
    public final static String ID_CANT_BE_NEGATIVE = "id can't be negative";



    //private final ObjectMapper mapper = new ObjectMapper();

}
