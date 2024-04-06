package com.driver.SwiggatoApplication.transformer;

import com.driver.SwiggatoApplication.dto.responseDto.CartResponse;
import com.driver.SwiggatoApplication.dto.responseDto.FoodResponse;
import com.driver.SwiggatoApplication.model.Cart;
import com.driver.SwiggatoApplication.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

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
