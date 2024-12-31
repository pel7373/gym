package org.gym.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean isActive;

    private boolean isUserNameApproved = false;

    private static int serialNumber;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = firstName + "." + lastName;
        this.password = password;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(!isUserNameApproved) {
            this.userName = firstName + "." + lastName;
        }
    }

    public void setApprovedUserName(String userName) {
        this.userName = userName;
    }

    public void approveUserName() {
        isUserNameApproved = true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public static int getSerialNumber() {
        return serialNumber;
    }

    public static void incrementSerialNumber() {
        serialNumber++;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
