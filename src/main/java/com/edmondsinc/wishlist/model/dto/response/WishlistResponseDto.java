package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.Wishlist;

import java.util.List;


public class WishlistResponseDto {

    Long id;
    String name;
    Long userId;
    List<WishResponseDto> wishResDtoList;

    public WishlistResponseDto(Wishlist wl){
        this.id = wl.getId();
        this.name = wl.getWishlistName();
        this.userId = wl.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<WishResponseDto> getWishResDtoList() {
        return wishResDtoList;
    }

    public void setWishResDtoList(List<WishResponseDto> wishResDtoList) {
        this.wishResDtoList = wishResDtoList;
    }

    @Override
    public String toString() {
        return "WishlistResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", wishResDtoList=" + wishResDtoList +
                '}';
    }
}
