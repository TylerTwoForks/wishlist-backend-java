package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.User;
import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.WishBank;
import com.edmondsinc.wishlist.model.dto.WishBankCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishBankResponseDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.UserRepo;
import com.edmondsinc.wishlist.repository.WishBankRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishBankService {

    WishRepo wishRepo;
    WishBankRepo wishBankRepo;
    UserRepo userRepo;


    public WishBankResponseDto getWishBank(Long id){
        Optional<WishBank> o = wishBankRepo.findById(id);
        if(o.isPresent()){
            return new WishBankResponseDto(o.get());
        }else{
//            log.error("No wish_bank found for id ["+id+"]");
            throw new NoResultException("No user found for id ["+id+"]");
        }
    }


    public List<WishBankResponseDto> getWishBanksForUser(Long userId){
        User user = userRepo.getReferenceById(userId);
        List<WishBank> wbList = wishBankRepo.findAllByUserId(userId);
        if(wbList != null && !wbList.isEmpty()){
            List<WishBankResponseDto> responseList = new ArrayList<>();
            wbList.forEach(wb -> {
                responseList.add(new WishBankResponseDto(wb));
            });
            return responseList;
        }else{
//            log.error("No wish_bank found for userId ["+userId+"]");
            throw new NoResultException("No wish_bank found for userId ["+userId+"]");
        }
    }




    public WishBankResponseDto getWishesForWishBank(Long wishBankId){
        WishBank wb = wishBankRepo.getReferenceById(wishBankId);
        List<Wish> wishList = wishRepo.findByWishBank(wb);
        WishBankResponseDto response = new WishBankResponseDto(wb);
        response.setWishList(WishResponseDto.wishListToResponseList(wishList));
        return response;
    }


    public WishBankResponseDto createWishBank(WishBankCreateDto dto){
        WishBank wb = new WishBank.Builder()
                .setBankName(dto.getName())
                .setUser(userRepo.getReferenceById(dto.getUserId()))
                .build();
        wishBankRepo.save(wb);
        return new WishBankResponseDto(wb);
    }

}
