package com.driver.SwiggatoApplication.transformer;

import com.driver.SwiggatoApplication.dto.requestDto.MenuRequest;
import com.driver.SwiggatoApplication.dto.responseDto.MenuResponse;
import com.driver.SwiggatoApplication.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemTransformer {
    public static MenuResponse FoodItemToFoodResponse(MenuItem menuItem){
        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .price(menuItem.getPrice())
                .foodCategory(menuItem.getCategory())
                .veg(menuItem.isVeg())
                .build();
    }
    public static List<MenuResponse> FoodItemListToFoodResponseList(List<MenuItem> menuItemList) {
        if(menuItemList == null || menuItemList.size()==0){
            return  new ArrayList<MenuResponse>();
        }
        List<MenuResponse> menuResponseList = new ArrayList<>();
        for (MenuItem menuItem : menuItemList) {
            MenuResponse menuResponse = MenuItemTransformer.FoodItemToFoodResponse(menuItem);
            menuResponseList.add(menuResponse);
        }
        return menuResponseList;
    }

    public static MenuItem FoodRequestToFoodItem(MenuRequest menuRequest) {
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .category(menuRequest.getCategory())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .build();
    }
}
