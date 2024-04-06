package com.driver.SwiggatoApplication.service;

import com.driver.SwiggatoApplication.dto.requestDto.CustomerRequest;
import com.driver.SwiggatoApplication.dto.responseDto.CustomerResponse;
import com.driver.SwiggatoApplication.exception.CustomerNotFoundException;
import com.driver.SwiggatoApplication.model.Cart;
import com.driver.SwiggatoApplication.model.Customer;
import com.driver.SwiggatoApplication.repository.CustomerRepository;
import com.driver.SwiggatoApplication.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
                //change //dto -> model

        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);
        //allocate a cart
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)   //set the customer for cart
                .build();


        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer); // saved both customer and cart

        //prepare response dto
        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse getCustomerByMobile(String mobile) {
        Customer customer = customerRepository.findByMobileNo(mobile);
        if(customer==null){
            throw new CustomerNotFoundException("Customer Not Found!!!!\nInvalid Mobile Number");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
