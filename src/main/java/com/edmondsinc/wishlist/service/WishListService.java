package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.User;
import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.WishList;
import com.edmondsinc.wishlist.model.dto.WishListCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishListResponseDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.UserRepo;
import com.edmondsinc.wishlist.repository.WishListRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    WishRepo wishRepo;
    WishListRepo wishListRepo;
    UserRepo userRepo;

    public WishListService(WishRepo wishRepo, WishListRepo wishListRepo, UserRepo userRepo) {
        this.wishRepo = wishRepo;
        this.wishListRepo = wishListRepo;
        this.userRepo = userRepo;
    }

    public WishListResponseDto getWishList(Long id){
        Optional<WishList> o = wishListRepo.findById(id);
        if(o.isPresent()){
            return new WishListResponseDto(o.get());
        }else{
            throw new NoResultException("No user found for id ["+id+"]");
        }
    }


    public List<WishListResponseDto> getWishListsForUser(Long userId){
        User user = userRepo.getReferenceById(userId);
        List<WishList> wbList = wishListRepo.findAllByUserId(userId);
        if(wbList != null && !wbList.isEmpty()){
            List<WishListResponseDto> responseList = new ArrayList<>();
            wbList.forEach(wb -> {
                responseList.add(new WishListResponseDto(wb));
            });
            return responseList;
        }else{
            throw new NoResultException("No wish_bank found for userId ["+userId+"]");
        }
    }




    public WishListResponseDto getWishesForWishList(Long wishBankId){
        WishList wb = wishListRepo.getReferenceById(wishBankId);
        List<Wish> wishList = wishRepo.findByWishList(wb);
        WishListResponseDto response = new WishListResponseDto(wb);
        response.setWishList(WishResponseDto.wishListToResponseList(wishList));
        return response;
    }


    public WishListResponseDto createWishList(WishListCreateDto dto){
        WishList wb = new WishList.Builder()
                .setListName(dto.getName())
                .setUser(userRepo.getReferenceById(dto.getUserId()))
                .build();
        wishListRepo.save(wb);
        return new WishListResponseDto(wb);
    }

}
