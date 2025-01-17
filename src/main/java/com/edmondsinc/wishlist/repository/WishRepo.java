package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.Wish;
import com.edmondsinc.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishRepo extends JpaRepository<Wish, Long> {

    Wish getWishById(Long id);

    List<Wish> findAllByWishlistIdOrderBySortOrderAsc(Long id);

    Wish findFirstByWishlistIdOrderBySortOrderDesc(Long id);

    @Query("SELECT w FROM Wish w WHERE w.wishlist.id = (SELECT w2.wishlist.id FROM Wish w2 WHERE w2.id = :wishId) ORDER BY w.sortOrder ASC ")
    List<Wish> findAllWishesOnWishlistByWishIdASC(@Param("wishId") Long wishId);

    @Query("SELECT w FROM Wish w WHERE w.wishlist.id = (SELECT w2.wishlist.id FROM Wish w2 WHERE w2.id = :wishId) ORDER BY w.sortOrder DESC")
    List<Wish> findAllWishesOnWishlistByWishIdDESC(@Param("wishId") Long wishId);
}
