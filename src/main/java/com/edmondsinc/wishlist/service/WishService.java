package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.Wishlist;
import com.edmondsinc.wishlist.model.dto.WishCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.WishlistRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class WishService {

    WishRepo wishRepo;
    WishlistRepo wishlistRepo;

    public WishService(WishRepo wishRepo, WishlistRepo wishlistRepo) {
        this.wishRepo = wishRepo;
        this.wishlistRepo = wishlistRepo;
    }

    public WishService(){}

    @Autowired
    public void setWishRepo(WishRepo wishRepo) {
        this.wishRepo = wishRepo;
    }

    @Autowired
    public void setWishlistRepo(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }




    public WishResponseDto getWishById(Long id){
        Optional<Wish> o = wishRepo.findById(id);
        if(o.isPresent()){
            return new WishResponseDto(o.get());
        }else{
            throw new NoResultException("No user found for id ["+id+"]");
        }
    }

    public List<WishResponseDto> getWishesByWishlist(Wishlist wl){
        List<WishResponseDto> res = WishResponseDto.wishListToResponseList(wishRepo.findAllByWishList(wl));
        return WishResponseDto.wishListToResponseList(wishRepo.findAllByWishList(wl));
    }

    public WishResponseDto addWish(WishCreateDto wishCreateDto){
        //todo - in here, we need to build the URL for the Amazon Affiliate links
        // Info on this here: https://affiliate-program.amazon.com/help/node/topic/GP38PJ6EUR6PFBEC
        // Means we need to expand the Wish model with an affiliate URL as well.
        Wish wish = new Wish.Builder()
                .setWishlist(wishlistRepo.findById(wishCreateDto.getWishlistId()).orElse(null))
                .setActive(wishCreateDto.isActive())
                .setExternalUrl(wishCreateDto.getExternalUrl())
                .setNotes(wishCreateDto.getNotes())
                .setPurchased(wishCreateDto.isPurchased())
                .setQtyRequested(wishCreateDto.getQtyRequested())
                .build();
        wishRepo.save(wish);
        return new WishResponseDto(wish);
    }
}
