package com.driver.SwiggatoApplication.repository;

import com.driver.SwiggatoApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByMobileNo(String mobile);
}
