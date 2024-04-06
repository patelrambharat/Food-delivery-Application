package com.driver.SwiggatoApplication.transformer;

import com.driver.SwiggatoApplication.dto.responseDto.FoodResponse;
import com.driver.SwiggatoApplication.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodItemTransformer {
    public static FoodResponse FoodItemToFoodResponse(FoodItem foodItem){
        return FoodResponse.builder()
                .dishName(foodItem.getDishName())
                .price(foodItem.getPrice())
                .foodCategory(foodItem.getFoodCategory())
                .veg(foodItem.isVeg())
                .build();
    }
    public static List<FoodResponse> FoodItemListToFoodResponseList(List<FoodItem>foodItemList) {
        if(foodItemList == null || foodItemList.size()==0){
            return  new ArrayList<FoodResponse>();
        }
        List<FoodResponse> foodResponseList = new ArrayList<>();
        for (FoodItem foodItem : foodItemList) {
            FoodResponse foodResponse = FoodItemTransformer.FoodItemToFoodResponse(foodItem);
            foodResponseList.add(foodResponse);
        }
        return foodResponseList;
    }
}
