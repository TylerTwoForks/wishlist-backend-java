package com.edmondsinc.wishlist.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PersonDto {
    String name;
    String email;           //intention to use this to email the person their matches directly.
    @JsonIgnore
    int matched;
    PersonDto forbiddenOne;

    public PersonDto(String name){
        this.name = name;
    }

    public PersonDto(String name, String email, int matched, PersonDto forbiddenOne) {
        this.name = name;
        this.email = email;
        this.matched = matched;
        this.forbiddenOne = forbiddenOne;
    }

    public PersonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatched() {
        return matched;
    }

    public void setMatched(int matched) {
        this.matched = matched;
    }

    public PersonDto getForbiddenOne() {
        return forbiddenOne;
    }

    public void setForbiddenOne(PersonDto forbiddenOne) {
        this.forbiddenOne = forbiddenOne;
    }
}
