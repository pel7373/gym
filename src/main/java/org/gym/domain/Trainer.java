package org.gym.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Data
public class Trainer extends User {
    @JsonProperty("specialization")
    private TrainingType specialization;

//    public Trainer(String firstName, String lastName, String userName, String password, boolean isActive, TrainingType specialization) {
//        super(firstName, lastName, userName, password, isActive);
//        this.specialization = specialization;
//    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", isActive=" + getIsActive() +
                ", specialization=" + specialization +
                '}';
    }
}
