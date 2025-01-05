package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.config.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Wish extends AbstractEntity {
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "wishlist_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Wishlist wishList;

    String externalUrl;
    String notes;
    int qtyRequested;
    boolean purchased;
    boolean active;

    public Wish() {
    }

    public Wish(Wishlist wishList, String externalUrl, String notes, int qtyRequested, boolean purchased, boolean active) {
        this.wishList = wishList;
        this.externalUrl = externalUrl;
        this.notes = notes;
        this.qtyRequested = qtyRequested;
        this.purchased = purchased;
        this.active = active;
    }

    public Wishlist getWishlist() {
        return wishList;
    }

    public void setWishlist(Wishlist wishList) {
        this.wishList = wishList;
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

    public static class Builder {

        private Wishlist wishList;
        private String externalUrl;
        private String notes;
        private int qtyRequested;
        private boolean purchased;
        private boolean active;

        public Builder setWishlist(Wishlist wishList) {
            this.wishList = wishList;
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
            return new Wish(wishList, externalUrl, notes, qtyRequested, purchased, active);
        }
    }
}
