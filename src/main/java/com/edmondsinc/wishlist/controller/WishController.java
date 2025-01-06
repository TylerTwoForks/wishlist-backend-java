package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.WishCreateDto;
import com.edmondsinc.wishlist.service.WishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wish")
public class WishController {
    Logger logger = LoggerFactory.getLogger(WishController.class);

    WishService ws;
    private Long wishId;

    public WishController(WishService ws) {
        this.ws = ws;
    }

    @PostMapping
    public ResponseEntity<?> createWishItem(@RequestBody WishCreateDto wishCreateDto){
        logger.error("createWish:: {}", wishCreateDto.toString());

        return ResponseEntity.ok(ws.addWish(wishCreateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWish(@PathVariable Long id) {
        if(ws.deleteWish(id)) return ResponseEntity.ok("success");
        else return ResponseEntity.internalServerError().body("failed to delete");
    }

}
