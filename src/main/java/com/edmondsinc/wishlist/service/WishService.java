package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.WishList;
import com.edmondsinc.wishlist.model.dto.WishCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.WishListRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class WishService {

    WishRepo wishRepo;
    WishListRepo wishListRepo;

    public WishService(WishRepo wishRepo, WishListRepo wishListRepo) {
        this.wishRepo = wishRepo;
        this.wishListRepo = wishListRepo;
    }

    public WishService(){}

    @Autowired
    public void setWishRepo(WishRepo wishRepo) {
        this.wishRepo = wishRepo;
    }

    @Autowired
    public void setWishListRepo(WishListRepo wishListRepo) {
        this.wishListRepo = wishListRepo;
    }




    public WishResponseDto getWishById(Long id){
        Optional<Wish> o = wishRepo.findById(id);
        if(o.isPresent()){
            return new WishResponseDto(o.get());
        }else{
            throw new NoResultException("No user found for id ["+id+"]");
        }
    }

    public List<WishResponseDto> getWishesByWishList(WishList wl){
        return WishResponseDto.wishListToResponseList(wishRepo.findAllByWishList(wl));
    }

    public WishResponseDto addWish(WishCreateDto wishCreateDto){
        Wish wish = new Wish.Builder()
                .setWishList(wishListRepo.findById(wishCreateDto.getWishListId()).orElse(null))
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
