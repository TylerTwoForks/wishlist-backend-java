package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.PersonDto;
import com.edmondsinc.wishlist.model.dto.response.ResponseBaseDto;
import com.edmondsinc.wishlist.service.MagicHatService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/magicHat")
public class MagicHatController {

    private final MagicHatService mhs;

    @JsonValue
    ResponseBaseDto res;

    public MagicHatController(MagicHatService mhs) {
        this.mhs = mhs;
    }

    /**
     *
     * @param magicHat test
     * @param giftingTo test
     * @return test
     */
    @PostMapping(path="/pullNames")
    public ResponseBaseDto pullNames(
            @RequestBody List<PersonDto> magicHat,
            @RequestParam int giftingTo
    )
    {
        if(magicHat.size() < 4){
            return new ResponseBaseDto(411, HttpStatus.LENGTH_REQUIRED, "MagicHat must contain at least 4 entries to sort");
        }else{
            return mhs.pullNames(magicHat, giftingTo);
        }
    }
}
