package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.config.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wish extends AbstractEntity {
    //Wish is an actual object you're wishing for.  Something you want and you can write down what that is.

    @ManyToOne(optional = false)
    @JoinColumn(name = "wish_bank_id")
    WishBank wishBank;
    String externalUrl;
    String notes;
    int qtyRequested;
    boolean purchased;
    boolean active;

    public Wish(){}

    public Wish(WishBank wishBank, String externalUrl, String notes, int qtyRequested, boolean purchased, boolean active) {
        this.wishBank = wishBank;
        this.externalUrl = externalUrl;
        this.notes = notes;
        this.qtyRequested = qtyRequested;
        this.purchased = purchased;
        this.active = active;
    }

    public WishBank getWishBank() {
        return wishBank;
    }

    public void setWishBank(WishBank wishBank) {
        this.wishBank = wishBank;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getQtyRequested() {
        return qtyRequested;
    }

    public void setQtyRequested(int qtyRequested) {
        this.qtyRequested = qtyRequested;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static class Builder{

        private WishBank wishBank;
        private String externalUrl;
        private String notes;
        private int qtyRequested;
        private boolean purchased;
        private boolean active;

        public Builder setWishBank(WishBank wishBank) {
            this.wishBank = wishBank;
            return this;
        }

        public Builder setExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setQtyRequested(int qtyRequested) {
            this.qtyRequested = qtyRequested;
            return this;
        }

        public Builder setPurchased(boolean purchased) {
            this.purchased = purchased;
            return this;
        }

        public Builder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Wish build() {
            return new Wish(wishBank, externalUrl, notes, qtyRequested, purchased, active);
        }
    }
}
