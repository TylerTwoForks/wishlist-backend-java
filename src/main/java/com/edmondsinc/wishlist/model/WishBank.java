package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.config.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.ConnectionBuilder;

@Entity
public class WishBank extends AbstractEntity {
    //WishBank is a collection of Wishes.  You can have multiple Banks to hold different types of wishes in order to categorize them.

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
    String bankName;

    public WishBank(){}

    public WishBank(User user, String bankName) {
        this.user = user;
        this.bankName = bankName;
    }

    public static class Builder{

        private User user;
        private String bankName;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setBankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        public WishBank build() {
            return new WishBank(user, bankName);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


}
