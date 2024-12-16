package com.edmondsinc.wishlist.model.dto;

import com.edmondsinc.wishlist.model.User;

import java.time.LocalDateTime;
import java.util.UUID;


public class UserDto {
    String firstName;
    String lastName;
    boolean active;
    String userName; //probably going to be email
    LocalDateTime lastLogin;
    String userGuid;
    Long id;

    public UserDto(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.active = user.isActive();
        this.userName = user.getUserName();
        this.lastLogin = user.getLastLogin();
        this.userGuid = user.getGuid().toString();
        this.id = user.getId();
    }

    public UserDto() {}

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
