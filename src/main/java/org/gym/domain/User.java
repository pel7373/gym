package org.gym.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//import java.security.SecureRandom;
import java.security.SecureRandom;
import java.util.stream.IntStream;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Data
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean isActive;

}
