package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.Wish;

import java.util.ArrayList;
import java.util.List;


public class WishResponseDto {
    Long wishId;
    Long wishListId;
    String externalUrl;
    String notes;
    int qtyRequested;
    boolean purchased;
    boolean active;

    public WishResponseDto(Wish wish){
        this.wishId = wish.getId();
        this.wishListId = wish.getWishList().getId();
        this.externalUrl = wish.getExternalUrl();
        this.notes = wish.getNotes();
        this.qtyRequested = wish.getQtyRequested();
        this.purchased = wish.isPurchased();
        this.active = wish.isActive();
    }

    public static List<WishResponseDto> wishListToResponseList(List<Wish> wishList){
        List<WishResponseDto> response = new ArrayList<>();
        wishList.forEach(w -> {
            response.add(new WishResponseDto(w));
        });
        return response;
    }

    public Long getWishId() {
        return wishId;
    }

    public void setWishId(Long wishId) {
        this.wishId = wishId;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
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
        return "WishResponseDto{" +
                "wishId=" + wishId +
                ", wishListId=" + wishListId +
                ", externalUrl='" + externalUrl + '\'' +
                ", notes='" + notes + '\'' +
                ", qtyRequested=" + qtyRequested +
                ", purchased=" + purchased +
                ", active=" + active +
                '}';
    }
}
