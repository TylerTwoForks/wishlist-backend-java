package com.edmondsinc.wishlist.model.dto;

public class WishCreateDto {
    Long wishListId;
    String externalUrl;
    String notes;
    int qtyRequested;
    boolean purchased;
    boolean active;

    public WishCreateDto() {}

    public WishCreateDto(Long wishListId, String externalUrl, String notes, int qtyRequested, boolean purchased, boolean active) {
        this.wishListId = wishListId;
        this.externalUrl = externalUrl;
        this.notes = notes;
        this.qtyRequested = qtyRequested;
        this.purchased = purchased;
        this.active = active;
    }

    public Long getWishlistId() {
        return wishListId;
    }

    //this setter needs to be lowercase `wish` for some reason. All the other setters work fine.
    public void setwishListId(Long wishListId) {
        this.wishListId = wishListId;
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

    @Override
    public String toString() {
        return "WishCreateDto{" +
                "wishListId=" + wishListId +
                ", externalUrl='" + externalUrl + '\'' +
                ", notes='" + notes + '\'' +
                ", qtyRequested=" + qtyRequested +
                ", purchased=" + purchased +
                ", active=" + active +
                '}';
    }
}

