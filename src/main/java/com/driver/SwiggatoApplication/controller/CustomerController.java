package com.driver.SwiggatoApplication.controller;


import com.driver.SwiggatoApplication.dto.requestDto.CustomerRequest;
import com.driver.SwiggatoApplication.dto.responseDto.CustomerResponse;
import com.driver.SwiggatoApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;  // field injection

    /*
    Consturctor Injection ----> Always use in enterprise applications
     */
//    final CustomerService customerService;
//
//    @Autowired
//    public CustomerController(CustomerService customerService){
//        this.customerService = customerService;
//    }
    @PostMapping("/add-customer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }
    @GetMapping("/get-customer-by-mobile/{mobile}")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobile){
        try {
            CustomerResponse customerResponse = customerService.getCustomerByMobile(mobile);
            return new ResponseEntity<>(customerResponse, HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
