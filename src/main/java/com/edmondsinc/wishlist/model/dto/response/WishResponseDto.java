package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.Wish;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class WishResponseDto {
    @JsonIgnore //hard coded base Affiliate link for All Things Possible.
    private static final String BASE_URL = "https://www.amazon.com/dp/%s/ref=nosim?tag=%s";
    @JsonIgnore
    private static final String ASSOCIATE_ID = "allthingspo0c-20";

    Long id;
    Long wishListId;
    String externalUrl;
    String notes;
    int qtyRequested;
    boolean purchased;
    boolean active;

    public WishResponseDto(Wish wish){
        this.id = wish.getId();
        this.wishListId = wish.getWishlist().getId();
        this.externalUrl = buildAmazonAffiliateLink(wish.getExternalUrl());
        this.notes = wish.getNotes();
        this.qtyRequested = wish.getQtyRequested();
        this.purchased = wish.isPurchased();
        this.active = wish.isActive();
    }

    private String buildAmazonAffiliateLink(String url){
        if(url.contains("dp/")){
            //for Amazon "full" urls, the string between the "db/" and the next "/" is the ASIN.
            //TODO - will probably need to find a way to get the ASIN of a product from a shortened URL as well.
            // Probably from the PAAPI that Amazon provides.
            String asin = StringUtils.substringBetween(url,"dp/", "/");
            return String.format(BASE_URL, asin, ASSOCIATE_ID);
        }

        return url;
    }

    public static List<WishResponseDto> wishListToResponseList(List<Wish> wishes){
        List<WishResponseDto> response = new ArrayList<>();
        wishes.forEach(w -> {
            WishResponseDto dto = new WishResponseDto(w);
            response.add(dto);
        });
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWishlistId() {
        return wishListId;
    }

    public void setWishlistId(Long wishListId) {
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
                "wishId=" + id +
                ", wishListId=" + wishListId +
                ", externalUrl='" + externalUrl + '\'' +
                ", notes='" + notes + '\'' +
                ", qtyRequested=" + qtyRequested +
                ", purchased=" + purchased +
                ", active=" + active +
                '}';
    }
}
