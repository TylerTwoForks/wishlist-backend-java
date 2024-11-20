package com.edmondsinc.wishlist.model.dto;

import java.util.List;

public class MagicHatDto {
    PersonDto gifter;
    List<PersonDto> giftees;

    public MagicHatDto() {
    }

    public MagicHatDto(PersonDto gifter, List<PersonDto> giftees) {
        this.gifter = gifter;
        this.giftees = giftees;
    }

    public PersonDto getGifter() {
        return gifter;
    }

    public void setGifter(PersonDto gifter) {
        this.gifter = gifter;
    }

    public List<PersonDto> getGiftees() {
        return giftees;
    }

    public void setGiftees(List<PersonDto> giftees) {
        this.giftees = giftees;
    }
}
