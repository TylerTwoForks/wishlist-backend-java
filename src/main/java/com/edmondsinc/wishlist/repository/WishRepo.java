package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepo extends JpaRepository<Wish, Long> {

    List<Wish> findAllByWishlistIdOrderBySortOrderAsc(Long id);

    Wish findFirstByWishlistIdOrderBySortOrderDesc(Long id);

}
