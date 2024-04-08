package com.driver.SwiggatoApplication.transformer;

import com.driver.SwiggatoApplication.dto.responseDto.CartResponse;
import com.driver.SwiggatoApplication.model.Cart;

import java.util.ArrayList;

public class CartTransformer {
    public static CartResponse CartToCartResponse(Cart cart){
//        List<FoodItem> foodItemList = cart.getFoodItems();
//        //converting FoodItem to FoodResponse
//        List<FoodResponse> foodResponseList = FoodItemTransformer.FoodItemListToFoodResponseList(foodItemList);
          return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItems(new ArrayList<>())
                .build();
    }
}
