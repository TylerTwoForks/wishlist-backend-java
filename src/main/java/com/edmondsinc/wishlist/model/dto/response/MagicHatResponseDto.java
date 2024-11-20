package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.dto.MagicHatDto;
import org.springframework.http.HttpStatus;

import java.util.List;


public class MagicHatResponseDto extends ResponseBaseDto {
    List<MagicHatDto> magicHatDtoList;

    public MagicHatResponseDto(Integer httpCode, HttpStatus httpStatus, String message){
        super(httpCode, httpStatus, message);
    }

    public MagicHatResponseDto(List<MagicHatDto> magicHatDtoList) {
        this.magicHatDtoList = magicHatDtoList;
    }

    public MagicHatResponseDto(Integer httpCode, HttpStatus httpStatus, String message, List<MagicHatDto> magicHatDtoList) {
        super(httpCode, httpStatus, message);
        this.magicHatDtoList = magicHatDtoList;
    }


    public void testMethodMHResponseDto1(){
        this.testMethodResponseBase(); //calling this from parent
    }

    public void testMethodMHResponseDto2(){
        //method only here in MH Response
        System.out.println("doing my own thing");
    }


}
