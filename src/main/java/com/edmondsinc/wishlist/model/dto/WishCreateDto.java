package com.edmondsinc.wishlist.model.dto;

public class WishCreateDto {
    Long wishlistId;
    String externalUrl;
    String notes;
    int qtyRequested;
    boolean purchased;
    boolean active;
    Integer sortOrder;

    public WishCreateDto() {}

    public WishCreateDto(Long wishlistId, String externalUrl, String notes, int qtyRequested, boolean purchased, boolean active, Integer sortOrder) {
        this.wishlistId = wishlistId;
        this.externalUrl = externalUrl;
        this.notes = notes;
        this.qtyRequested = qtyRequested;
        this.purchased = purchased;
        this.active = active;
        this.sortOrder = sortOrder;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
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

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "WishCreateDto{" +
                "wishListId=" + wishlistId +
                ", externalUrl='" + externalUrl + '\'' +
                ", notes='" + notes + '\'' +
                ", qtyRequested=" + qtyRequested +
                ", purchased=" + purchased +
                ", active=" + active +
                ", sortOrder=" + sortOrder +
                '}';
    }
}

