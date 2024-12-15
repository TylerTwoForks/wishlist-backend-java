package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.WishCreateDto;
import com.edmondsinc.wishlist.service.WishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wish")
public class WishController {

    WishService ws;

    public WishController(WishService ws) {
        this.ws = ws;
    }

    @PostMapping
    public ResponseEntity<?> createWishItem(@RequestBody WishCreateDto wishCreateDto){
        return ResponseEntity.ok(ws.addWish(wishCreateDto));
    }

}
