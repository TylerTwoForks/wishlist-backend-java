package com.edmondsinc.wishlist.service;

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
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Integer newSortPosition = wishCreateDto.getSortOrder() != null ? wishCreateDto.getSortOrder() : 0;
        List<Wish> wishesToUpsert = updateSortOrder(wish, newSortPosition, false);

        wishRepo.saveAll(wishesToUpsert);
        return new WishResponseDto(wish);
    }

    /**
     * @param wish - base wish we've already built.
     * @param newSortOrder - the dto being used to create
     */
    private List<Wish> updateSortOrder(Wish wish, Integer newSortOrder, Boolean delete){
        List<Wish> wishesToUpsert = new ArrayList<>();

        List<Wish> wishes = wishRepo.findAllByWishlistIdOrderBySortOrderAsc(wish.getWishlist().getId());
        if(wishes == null || wishes.isEmpty()) {
            wishesToUpsert.add(wish);
            return wishesToUpsert;
        }

        if(newSortOrder >= wishes.size() || newSortOrder == 0){
            wish.setSortOrder(wishes.getLast().getSortOrder()+1);
        } else {
            wishesToUpsert.addAll(
                    wishes.stream().filter(w -> w.getSortOrder() >= newSortOrder && !Objects.equals(w.getId(), wish.getId()))
                            .peek(w -> {
                                if(delete) w.setSortOrder(w.getSortOrder() - 1);
                                else w.setSortOrder(w.getSortOrder() + 1);
                            }).toList()
            );
        }
        if(!delete) wishesToUpsert.add(wish);
        return wishesToUpsert;
    }

    public Boolean deleteWish(Long wishId) {
        Wish wish = wishRepo.getWishById(wishId);
        List<Wish> wishesToUpdate = updateSortOrder(wish, wish.getSortOrder(), true);

        try {
            wishRepo.delete(wish);
            wishRepo.saveAll(wishesToUpdate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
