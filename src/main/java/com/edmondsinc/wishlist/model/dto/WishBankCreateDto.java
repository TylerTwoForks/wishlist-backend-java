package com.edmondsinc.wishlist.model.dto;

public class WishBankCreateDto {
    String name;
    Long userId;

    public WishBankCreateDto() {
    }

    public WishBankCreateDto(String name, Long userId) {
        this.name = name;
        this.userId = userId;
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
}
