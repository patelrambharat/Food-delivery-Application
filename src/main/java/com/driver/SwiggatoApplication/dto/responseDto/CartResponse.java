package com.driver.SwiggatoApplication.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    double cartTotal;

    List<MenuResponse> foodItems;
}