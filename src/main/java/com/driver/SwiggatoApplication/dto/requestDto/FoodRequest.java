package com.driver.SwiggatoApplication.dto.requestDto;

import com.driver.SwiggatoApplication.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {

    FoodCategory foodCategory;
    boolean veg;
    boolean available;
}
