package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.dto.MagicHatDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class RandomResponseDTO extends MagicHatResponseDto{
    public RandomResponseDTO(Integer httpCode, HttpStatus httpStatus, String message) {
        super(httpCode, httpStatus, message);
    }

    public RandomResponseDTO(List<MagicHatDto> magicHatDtoList) {
        super(magicHatDtoList);
    }

    public RandomResponseDTO(Integer httpCode, HttpStatus httpStatus, String message, List<MagicHatDto> magicHatDtoList) {
        super(httpCode, httpStatus, message, magicHatDtoList);
    }

    public void testMethod1(){
        this.testMethodResponseBase(); //from response base dto
        this.testMethodMHResponseDto1(); //gets from MagicHatResponseDto that is getting it from response base dto
        this.testMethodMHResponseDto2(); //this is only in MH Response DTO

    }
}
