package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.WishBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishBankRepo extends JpaRepository<WishBank, Long> {

    List<WishBank> findAllByUserId(Long userId);
}
