package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.WishlistCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishlistResponseDto;
import com.edmondsinc.wishlist.service.WishlistService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/wish-list")
public class WishlistController {

    private final WishlistService wls;

    public WishlistController(WishlistService wls) {
        this.wls = wls;
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllWishlists(){
        try{
            List<WishlistResponseDto> wlRes = wls.getAllWishlists();
            return buildResponse(wlRes);
        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?>  getWishlistsForUser(@PathVariable Long userId){
        try{
            List<WishlistResponseDto> wlRes = wls.getWishlistsForUser(userId);
            return buildResponse(wlRes);
        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createWishlist(@RequestBody WishlistCreateDto createWishlist) {
        try{
            wls.createWishlist(createWishlist);
            return ResponseEntity.ok(HttpEntity.EMPTY);
        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }

    @DeleteMapping("/{wishListId}")
    public ResponseEntity<?> deleteWishlistById(
            @PathVariable Long wishListId
    ){
        try{
            wls.deleteWishlist(wishListId);
            return ResponseEntity.ok(HttpEntity.EMPTY);

        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }




    /*****************************************************
     * Private Methods
     *****************************************************/
    private ResponseEntity<?> buildResponse(List<WishlistResponseDto> dtoResponse){
        if(dtoResponse != null && !dtoResponse.isEmpty()){
            return ResponseEntity.ok(dtoResponse);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<?> serverErrorResponse(Exception e){
        return ResponseEntity.internalServerError().body(e.getMessage() + " "+ Arrays.toString(e.getStackTrace()));
    }

}
