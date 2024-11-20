package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.dto.MagicHatDto;
import com.edmondsinc.wishlist.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MagicHatResponseDto extends ResponseBaseDto {
    List<MagicHatDto> magicHatDtoList;

    public MagicHatResponseDto() {}

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

    public MagicHatResponseDto ok(){
        this.setHttpCode(200);
        this.setHttpStatus(HttpStatus.OK);

        return this;
    }


}
