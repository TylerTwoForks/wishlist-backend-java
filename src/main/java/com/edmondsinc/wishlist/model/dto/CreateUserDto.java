package com.edmondsinc.wishlist.model.dto;


import org.springframework.stereotype.Component;

@Component
public class CreateUserDto {
    String firstName;
    String lastName;
    String userName; //probably going to be email
    String unhashedPw;

    public CreateUserDto(String firstName, String lastName, String userName, String unhashedPw) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.unhashedPw = unhashedPw;
    }

    public CreateUserDto() {}

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
        this.userName = userName;
    }

    public String getUnhashedPw() {
        return unhashedPw;
    }

    public void setUnhashedPw(String unhashedPw) {
        this.unhashedPw = unhashedPw;
    }
}

