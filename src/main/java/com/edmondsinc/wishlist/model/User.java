package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.config.AbstractEntity;
import com.edmondsinc.wishlist.model.dto.CreateUserDto;
import com.edmondsinc.wishlist.model.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_account")
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractEntity {
    //AbstractAuditable<User, Long>

    String firstName;
    String lastName;
    String hashedPw;
    boolean active;
    @Column(nullable = false)
    String userName; //probably going to be email
    LocalDateTime lastLogin;

    public User(CreateUserDto u){
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.active = true; //when creating a user, we are defaulting the value to true.
        this.userName = u.getUserName();
    }

    public User(String firstName, String lastName, String hashedPw, boolean active, String userName, LocalDateTime lastLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPw = hashedPw;
        this.active = active;
        this.userName = userName;
        this.lastLogin = lastLogin;
    }

    public UserDto toUserDto(){
        UserDto userDto = new UserDto();
        userDto.setFirstName(this.firstName);
        userDto.setLastName(this.lastName);
        userDto.setActive(this.active);
        userDto.setUserName(this.userName);
        userDto.setLastLogin(this.getLastLogin());
        userDto.setUserGuid(this.getGuid().toString());
        return userDto;
    }

    public User() {}

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

    public String getHashedPw() {
        return hashedPw;
    }

    public void setHashedPw(String hashedPw) {
        this.hashedPw = hashedPw;
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

    public static class Builder{

        private String firstName;
        private String lastName;
        private String hashedPw;
        private boolean active;
        private String userName;
        private LocalDateTime lastLogin;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setHashedPw(String hashedPw) {
            this.hashedPw = hashedPw;
            return this;
        }

        public Builder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setLastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public User build() {
            //String firstName, String lastName, String hashedPw, boolean active, String userName, LocalDateTime lastLogin
            return new User(this.firstName, this.lastName, this.hashedPw, this.active, this.userName, this.lastLogin);
        }
    }

}
