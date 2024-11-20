package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.dto.MagicHatDto;
import com.edmondsinc.wishlist.model.dto.PersonDto;
import com.edmondsinc.wishlist.model.dto.response.MagicHatResponseDto;
import com.edmondsinc.wishlist.model.dto.response.ResponseBaseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class MagicHatService {
    public MagicHatService() {}

    public ResponseBaseDto pullNames(List<PersonDto> magicHat, int giftingTo){
        System.out.println("magicHat::" + magicHat);

        HashMap<PersonDto, List<PersonDto>> matchedUp = matchUp(magicHat, giftingTo);

        if(!matchedUp.isEmpty()){
            List<MagicHatDto> response = new ArrayList<>();

            matchedUp.forEach((pdto, lpdto) -> {
                response.add(new MagicHatDto(pdto, lpdto));
            });

            for(MagicHatDto mhdto : response){
                System.out.println("res:: "+mhdto.toString());
            }
            return new MagicHatResponseDto(response).ok();
        }else{
            return new ResponseBaseDto(204, HttpStatus.NO_CONTENT, "Magic hat go boom!");
        }
    }

    public HashMap<PersonDto, List<PersonDto>> matchUp(List<PersonDto> masterBucket, int giftingTo) {
        //giftingTo is used to determine how many people you will gift to.  Logically, this indicates how many times you can be "gifted to" as well.

        Collections.shuffle(masterBucket); //shuffle up the objects so they are in an unknown order.

        HashMap<PersonDto, List<PersonDto>> matchedMap = new HashMap<>(); //Person is giftingTo List<Person>

        for (int x = 0; x < masterBucket.size(); x++) {

            matchedMap.putIfAbsent(masterBucket.get(x), new ArrayList<>());

            int i = 1;
            int left = 0;
            while (i <= giftingTo) {
                PersonDto giftee;
                if (x <= masterBucket.size()) {
                    try {
                        giftee = masterBucket.get(i + x);
                    } catch (IndexOutOfBoundsException oob) {
                        giftee = masterBucket.get(left);
                        left++;
                    }
                    matchedMap.get(masterBucket.get(x)).add(giftee);
                    i++;
                }
            }
        }
        return matchedMap;
    }

    private void separateForbidden(List<PersonDto> masterBucket){
        //Reminder of problem:  Need to implement a way to prevent gifting to a forbiddenOne (spouse, person you don't like, etc...)

    }

}
