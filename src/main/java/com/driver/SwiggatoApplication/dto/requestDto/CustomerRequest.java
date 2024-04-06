package com.driver.SwiggatoApplication.dto.requestDto;

import com.driver.SwiggatoApplication.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    String name ;
    Gender gender;
    String email;
    String mobileNo;
    String address;


}
