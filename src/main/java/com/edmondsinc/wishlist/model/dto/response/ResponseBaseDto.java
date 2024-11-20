package com.edmondsinc.wishlist.model.dto.response;


import org.springframework.http.HttpStatus;

public class ResponseBaseDto {
    private Integer httpCode;
    private HttpStatus httpStatus;
    private String message;

    public ResponseBaseDto(){
        new ResponseBaseDto(200, HttpStatus.OK, "OK");
    }

    public ResponseBaseDto(Integer httpCode, HttpStatus httpStatus, String message) {
        this.httpCode = httpCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
