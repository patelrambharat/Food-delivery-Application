package com.driver.SwiggatoApplication.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    String name ;
    String mobileNo;
    String address;
    CartResponse cartResponse;
}