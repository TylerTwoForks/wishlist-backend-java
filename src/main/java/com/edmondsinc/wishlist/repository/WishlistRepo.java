package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findAllByUserId(Long userId);
}
