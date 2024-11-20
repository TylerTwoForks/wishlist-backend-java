package com.edmondsinc.wishlist.model.dto.response;


import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public class ResponseBaseDto {
    private Integer httpCode;
    private HttpStatus httpStatus;
    private String message;

    public ResponseBaseDto(){
        this(200, HttpStatus.OK, "OK");
    }

    public ResponseBaseDto(Integer httpCode, HttpStatus httpStatus, String message) {
        this.httpCode = httpCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public void testMethodResponseBase(){
        System.out.println("Response Base Method");
    }
}
