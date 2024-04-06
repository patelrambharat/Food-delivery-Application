package com.driver.SwiggatoApplication.transformer;

import com.driver.SwiggatoApplication.dto.requestDto.CustomerRequest;
import com.driver.SwiggatoApplication.dto.responseDto.CartResponse;
import com.driver.SwiggatoApplication.dto.responseDto.CustomerResponse;
import com.driver.SwiggatoApplication.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .mobileNo(customerRequest.getMobileNo())
                .address(customerRequest.getAddress())
                .gender(customerRequest.getGender())
                .build();

    }
    public static CustomerResponse CustomerToCustomerResponse(Customer customer){
        //  cart to cart response
        CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());

        //adding cartResponse to customerResponse
        return CustomerResponse.builder()
                .name(customer.getName())
                .mobileNo(customer.getMobileNo())
                .address(customer.getAddress())
                .cartResponse(cartResponse)
                .build();
    }
}
