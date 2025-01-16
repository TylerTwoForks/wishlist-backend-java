package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.controller.WishController;
import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.Wishlist;
import com.edmondsinc.wishlist.model.dto.WishCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishResponseDto;
import com.edmondsinc.wishlist.repository.WishlistRepo;
import com.edmondsinc.wishlist.repository.WishRepo;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class WishService {
    Logger logger = LoggerFactory.getLogger(WishService.class);


    WishRepo wishRepo;
    WishlistRepo wishlistRepo;

    public WishService(WishRepo wishRepo, WishlistRepo wishlistRepo) {
        this.wishRepo = wishRepo;
        this.wishlistRepo = wishlistRepo;
    }

    public WishService() {
    }

    @Autowired
    public void setWishRepo(WishRepo wishRepo) {
        this.wishRepo = wishRepo;
    }

    @Autowired
    public void setWishlistRepo(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }


    public WishResponseDto getWishById(Long id) {
        Optional<Wish> o = wishRepo.findById(id);
        if (o.isPresent()) {
            return new WishResponseDto(o.get());
        } else {
            throw new NoResultException("No user found for id [" + id + "]");
        }
    }

    public List<WishResponseDto> getWishesByWishlist(Wishlist wl) {
        return WishResponseDto.wishListToResponseList(wishRepo.findAllByWishlistIdOrderBySortOrderAsc(wl.getId()));
    }

    public WishResponseDto addWish(WishCreateDto wishCreateDto) {
        //todo - in here, we need to build the URL for the Amazon Affiliate links
        // Info on this here: https://affiliate-program.amazon.com/help/node/topic/GP38PJ6EUR6PFBEC
        // Means we need to expand the Wish model with an affiliate URL as well.

        List<Wish> wishesToUpsert = new ArrayList<>();

        Wishlist wl = wishlistRepo.findById(wishCreateDto.getWishlistId()).orElse(null);
        if(wl != null) logger.warn(wl.toString());
        else logger.error("no wishlist found");

        Wish wish = new Wish.Builder()
                .setWishlist(wl)
                .setActive(wishCreateDto.isActive())
                .setExternalUrl(wishCreateDto.getExternalUrl())
                .setNotes(wishCreateDto.getNotes())
                .setPurchased(wishCreateDto.isPurchased())
                .setQtyRequested(wishCreateDto.getQtyRequested())
                .build();

        placeWishInProperSortOrder(wish, wishCreateDto, wishesToUpsert);

        wishRepo.saveAll(wishesToUpsert);
        return new WishResponseDto(wish);
    }

    /**
     * @param wish - base wish we've already built.
     * @param wishCreateDto - the dto being used to create
     * @param wishesToUpsert - list we're updating with the wish placed in the proper sort order.
     */
    private void placeWishInProperSortOrder(Wish wish, WishCreateDto wishCreateDto, List<Wish> wishesToUpsert){
        Wish lastWish = wishRepo.findFirstByWishlistIdOrderBySortOrderDesc(wishCreateDto.getWishlistId());
        if (wishCreateDto.getSortOrder() == null || (lastWish != null && wishCreateDto.getSortOrder() > lastWish.getSortOrder())) {
            wish.setSortOrder(lastWish.getSortOrder() + 1);
        } else {
            List<Wish> wishes = wishRepo.findAllByWishlistIdOrderBySortOrderAsc(wishCreateDto.getWishlistId());
            wishesToUpsert.addAll(
                    wishes.stream()
                            .filter(w -> w.getSortOrder() >= wishCreateDto.getSortOrder())
                            .peek(w -> w.setSortOrder(w.getSortOrder() + 1)) //intermediate opp. this operates on each item as it's being consumed.
                            .toList() //this is terminal so it ends our stream here.
            );
        }
        wishesToUpsert.add(wish);
    }

    public Boolean deleteWish(Long wishId) {
        try {
            wishRepo.deleteById(wishId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
