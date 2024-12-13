package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.dto.WishCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.WishListRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@GraphQLApi
public class WishService {

    WishRepo wishRepo;
    WishListRepo wishBankRepo;

    public WishService(WishRepo wishRepo, WishListRepo wishBankRepo) {
        this.wishRepo = wishRepo;
        this.wishBankRepo = wishBankRepo;
    }

    @GraphQLQuery
    public WishResponseDto getWishById(Long id){
        Optional<Wish> o = wishRepo.findById(id);
        if(o.isPresent()){
            return new WishResponseDto(o.get());
        }else{
            throw new NoResultException("No user found for id ["+id+"]");
        }
    }


    @GraphQLMutation
    public WishResponseDto addWish(WishCreateDto wishCreateDto){
        Wish wish = new Wish.Builder()
                .setWishList(wishBankRepo.getReferenceById(wishCreateDto.getWishListId()))
                .setActive(true)
                .setExternalUrl(wishCreateDto.getExternalUrl())
                .setNotes(wishCreateDto.getNotes())
                .setPurchased(false)
                .setQtyRequested(wishCreateDto.getQtyRequested())
                .build();
        wishRepo.save(wish);
        return new WishResponseDto(wish);
    }
}
