package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.WishBank;
import org.springframework.http.HttpStatus;

import java.util.List;


public class WishBankResponseDto extends ResponseBaseDto {
    Long id;
    String name;
    Long userId;
    List<WishResponseDto> wishList;

    public WishBankResponseDto(WishBank wb){
        this.id = wb.getId();
        this.name = wb.getBankName();
        this.userId = wb.getUser().getId();
    }

    public WishBankResponseDto(Integer httpCode, HttpStatus httpStatus, String message){
        super(httpCode, httpStatus, message);
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

    public List<WishResponseDto> getWishList() {
        return wishList;
    }

    public void setWishList(List<WishResponseDto> wishList) {
        this.wishList = wishList;
    }
}
