package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.WishBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepo extends JpaRepository<Wish, Long> {

    List<Wish> findByWishBank(WishBank wb);
}
