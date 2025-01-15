package com.edmondsinc.wishlist.model;

import com.edmondsinc.wishlist.config.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wish extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "wishlist_id")
    Wishlist wishlist;

    String externalUrl;
    String notes;
    int qtyRequested;
    boolean purchased;
    boolean active;
    int sortOrder;


    public Wish() {
    }

    public Wish(Wishlist wishlist, String externalUrl, String notes, int qtyRequested, boolean purchased, boolean active, int sortOrder) {
        this.wishlist = wishlist;
        this.externalUrl = externalUrl;
        this.notes = notes;
        this.qtyRequested = qtyRequested;
        this.purchased = purchased;
        this.active = active;
        this.sortOrder = sortOrder;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishList) {
        this.wishlist = wishList;
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

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public static class Builder {

        private Wishlist wishList;
        private String externalUrl;
        private String notes;
        private int qtyRequested;
        private boolean purchased;
        private boolean active;
        private int sortOrder;

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

        public Builder setSortOrder(int sortOrder){
            this.sortOrder = sortOrder;
            return this;
        }

        public Wish build() {
            return new Wish(wishList, externalUrl, notes, qtyRequested, purchased, active, sortOrder);
        }
    }
}
