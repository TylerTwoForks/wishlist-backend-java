package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.WishList;
import com.edmondsinc.wishlist.model.dto.WishListCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishListResponseDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.UserRepo;
import com.edmondsinc.wishlist.repository.WishListRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    private final WishService wishService;
    WishRepo wishRepo;
    WishListRepo wishListRepo;
    UserRepo userRepo;

    public WishListService(WishRepo wishRepo, WishListRepo wishListRepo, UserRepo userRepo, WishService wishService) {
        this.wishRepo = wishRepo;
        this.wishListRepo = wishListRepo;
        this.userRepo = userRepo;
        this.wishService = wishService;
    }

    /*****************************************************
     * GET
     *****************************************************/
    public WishListResponseDto getWishList(Long id){
        WishList wl = wishListRepo.findById(id).orElse(null);

        if(wl != null) return buildResDtoFromWishLists(new ArrayList<>(List.of(wl))).getFirst();
        else throw new NoResultException("No WishList found for id ["+id+"]");
    }

    public List<WishListResponseDto> getAllWishLists(){
        return buildResDtoFromWishLists(wishListRepo.findAll());
    }


    public List<WishListResponseDto> getWishListsForUser(Long userId){
        return buildResDtoFromWishLists(wishListRepo.findAllByUserId(userId));
    }

    /*****************************************************
     * CREATE Methods
     *****************************************************/
    public void createWishList(WishListCreateDto dto){
        WishList wl = new WishList.Builder()
                .setWishListName(dto.getName())
                .setUser(userRepo.getReferenceById(dto.getUserId()))
                .build();
        wishListRepo.save(wl);
        new WishListResponseDto(wl);
    }

    /*****************************************************
     * DELETE Methods
     *****************************************************/
    public void deleteWishList(Long wishListId){
        wishListRepo.deleteById(wishListId);
    }


    /*****************************************************
     * Private Methods
     *****************************************************/
    private List<WishListResponseDto> buildResDtoFromWishLists(List<WishList> foundWishLists){
        List<WishListResponseDto> res = new ArrayList<>();
        if(foundWishLists != null && !foundWishLists.isEmpty()){
            foundWishLists.forEach(wl -> {
                List<WishResponseDto> wishResponseDtoList = wishService.getWishesByWishList(wl);
                WishListResponseDto wlRes = new WishListResponseDto(wl);
                wlRes.setWishResDtoList(wishResponseDtoList);
                res.add(wlRes);
            });
        }
        return res;
    }

}
