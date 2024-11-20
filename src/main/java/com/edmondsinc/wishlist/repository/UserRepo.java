package com.edmondsinc.wishlist.repository;

import com.edmondsinc.wishlist.model.User;
import org.jetbrains.annotations.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @NotNull
    Optional<User> findById(@NotNull Long id);

    User findByGuid(UUID userGuid);

}
