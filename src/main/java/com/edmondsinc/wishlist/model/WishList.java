package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.config.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class WishList extends AbstractEntity {
    //WishList is a collection of Wishes.  You can have multiple WishLists to hold different types of wishes in order to categorize them.

    @ManyToOne(optional = false)
    @JoinColumn()
    User user;

    String wishListName;


    public WishList() {
    }

    public WishList(User user, String wishListName) {
        this.user = user;
        this.wishListName = wishListName;
    }

    public WishList(User user, String wishListName, List<Wish> wishes) {
        this.user = user;
        this.wishListName = wishListName;
//        this.wishes = wishes;
    }

    public WishList(Long id, LocalDateTime createdAt, UUID guid, User user, String wishListName, List<Wish> wishes) {
        super(id, createdAt, guid);
        this.user = user;
        this.wishListName = wishListName;
//        this.wishes = wishes;
    }

    public static class Builder {

        private User user;
        private String wishListName;
        private List<Wish> wishes;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setWishListName(String wishListName) {
            this.wishListName = wishListName;
            return this;
        }

        public Builder setWishes(List<Wish> wishes) {
            this.wishes = wishes;
            return this;
        }

        public WishList build() {
            if (wishes != null) return new WishList(user, wishListName, wishes);
            else return new WishList(user, wishListName);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

//    public List<Wish> getWishes() {
//        return wishes;
//    }
//
//    public void setWishes(List<Wish> wishes) {
//        this.wishes = wishes;
//    }
}
