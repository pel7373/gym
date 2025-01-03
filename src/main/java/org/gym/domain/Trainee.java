package org.gym.domain;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Data
public class Trainee extends User {
    private LocalDate dateOfBirth;
    private String address;

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", isActive=" + getIsActive() +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
