package com.driver.SwiggatoApplication.dto.responseDto;

import com.driver.SwiggatoApplication.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponse {

    String dishName;

    double price;

    FoodCategory foodCategory;

    boolean veg;
}