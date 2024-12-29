package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.WishListCreateDto;
import com.edmondsinc.wishlist.model.dto.response.WishListResponseDto;
import com.edmondsinc.wishlist.service.WishListService;
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
public class WishListController {

    private final WishListService wls;

    public WishListController(WishListService wls) {
        this.wls = wls;
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllWishLists(){
        try{
            List<WishListResponseDto> wlRes = wls.getAllWishLists();
            return buildResponse(wlRes);
        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?>  getWishListsForUser(@PathVariable Long userId){
        try{
            List<WishListResponseDto> wlRes = wls.getWishListsForUser(userId);
            System.out.println("wlRes:: "+wlRes.toString());
            return buildResponse(wlRes);
        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createWishList(@RequestBody WishListCreateDto createWishList) {
        try{
            wls.createWishList(createWishList);
            return ResponseEntity.ok(HttpEntity.EMPTY);
        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }

    @DeleteMapping("/{wishListId}")
    public ResponseEntity<?> deleteWishListById(@PathVariable Long wishListId){
        try{
            wls.deleteWishList(wishListId);
            return ResponseEntity.ok(HttpEntity.EMPTY);

        }catch (Exception e){
            return serverErrorResponse(e);
        }
    }




    /*****************************************************
     * Private Methods
     *****************************************************/
    private ResponseEntity<?> buildResponse(List<WishListResponseDto> dtoResponse){
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
