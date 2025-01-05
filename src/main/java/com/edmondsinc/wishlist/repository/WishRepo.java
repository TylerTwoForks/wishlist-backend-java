package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepo extends JpaRepository<Wish, Long> {

    List<Wish> findByWishList(Wishlist wl);

    List<Wish> findByWishListId(Long wishlistId);

    List<Wish> findAllByWishList(Wishlist wl);


}
