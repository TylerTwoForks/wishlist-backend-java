package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.config.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity()
@Table(name = "wish_list")
public class Wishlist extends AbstractEntity {
    //Wishlist is a collection of Wishes.  You can have multiple Wishlists to hold different types of wishes in order to categorize them.

    @ManyToOne(optional = false)
    @JoinColumn()
    User user;

    String wishListName;


    public Wishlist() {
    }

    public Wishlist(User user, String wishListName) {
        this.user = user;
        this.wishListName = wishListName;
    }

    public User getUser() {
        return user;
    }

    public String getWishlistName() {
        return wishListName;
    }


    public static class Builder {

        private User user;
        private String wishListName;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setWishlistName(String wishListName) {
            this.wishListName = wishListName;
            return this;
        }

        public Wishlist build() {
            return new Wishlist(user, wishListName);
        }
    }


}
