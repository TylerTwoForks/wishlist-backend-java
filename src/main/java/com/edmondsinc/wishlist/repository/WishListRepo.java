package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserId(Long userId);
}
