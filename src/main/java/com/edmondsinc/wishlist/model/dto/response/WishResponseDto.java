package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.Wish;
import io.leangen.graphql.annotations.GraphQLNonNull;

import java.util.ArrayList;
import java.util.List;


public class WishResponseDto {
    Long wishId;
    Long wishBankId;
    String externalUrl;
    String notes;
    int qtyRequested;
    @GraphQLNonNull
    boolean purchased;
    boolean active;

    public WishResponseDto(Wish wish){
        this.wishId = wish.getId();
        this.wishBankId = wish.getWishBank().getId();
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

}
