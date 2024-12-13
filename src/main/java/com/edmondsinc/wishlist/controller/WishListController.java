package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.CreateUserDto;
import com.edmondsinc.wishlist.model.dto.WishListCreateDto;
import com.edmondsinc.wishlist.service.WishListService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/wishBank")
public class WishListController {

    private final WishListService wls;

    public WishListController(WishListService wls) {
        this.wls = wls;
    }

    @PostMapping
    public ResponseEntity<?> createWishList(@RequestBody WishListCreateDto createWishList){
        wls.createWishList(createWishList);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
