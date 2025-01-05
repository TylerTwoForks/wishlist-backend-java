package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.Wishlist;
import com.edmondsinc.wishlist.model.dto.WishlistCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishlistResponseDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.UserRepo;
import com.edmondsinc.wishlist.repository.WishlistRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final WishService wishService;
    WishRepo wishRepo;
    WishlistRepo wishListRepo;
    UserRepo userRepo;

    public WishlistService(WishRepo wishRepo, WishlistRepo wishListRepo, UserRepo userRepo, WishService wishService) {
        this.wishRepo = wishRepo;
        this.wishListRepo = wishListRepo;
        this.userRepo = userRepo;
        this.wishService = wishService;
    }

    /*****************************************************
     * GET
     *****************************************************/
    public WishlistResponseDto getWishlist(Long id){
        Wishlist wl = wishListRepo.findById(id).orElse(null);

        if(wl != null) return buildResDtoFromWishlists(new ArrayList<>(List.of(wl))).getFirst();
        else throw new NoResultException("No Wishlist found for id ["+id+"]");
    }

    public List<WishlistResponseDto> getAllWishlists(){
        return buildResDtoFromWishlists(wishListRepo.findAll());
    }


    public List<WishlistResponseDto> getWishlistsForUser(Long userId){
        return buildResDtoFromWishlists(wishListRepo.findAllByUserId(userId));
    }

    /*****************************************************
     * CREATE Methods
     *****************************************************/
    public void createWishlist(WishlistCreateDto dto){
        Wishlist wl = new Wishlist.Builder()
                .setWishlistName(dto.getName())
                .setUser(userRepo.getReferenceById(dto.getUserId()))
                .build();
        wishListRepo.save(wl);
        new WishlistResponseDto(wl);
    }

    /*****************************************************
     * DELETE Methods
     *****************************************************/
    public void deleteWishlist(Long wishListId){
        wishListRepo.deleteById(wishListId);
    }


    /*****************************************************
     * Private Methods
     *****************************************************/
    private List<WishlistResponseDto> buildResDtoFromWishlists(List<Wishlist> foundWishlists){
        List<WishlistResponseDto> res = new ArrayList<>();
        if(foundWishlists != null && !foundWishlists.isEmpty()){
            foundWishlists.forEach(wl -> {
                List<WishResponseDto> wishResponseDtoList = wishService.getWishesByWishlist(wl);
                WishlistResponseDto wlRes = new WishlistResponseDto(wl);
                wlRes.setWishResDtoList(wishResponseDtoList);
                res.add(wlRes);
            });
        }
        return res;
    }

}
