package com.driver.SwiggatoApplication.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {
    int requiredQuantity;
    String customerMobile;
    int menuItemId;
}
