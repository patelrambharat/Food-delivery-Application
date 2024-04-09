package com.driver.SwiggatoApplication.repository;

import com.driver.SwiggatoApplication.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepo extends JpaRepository<OrderEntity,Integer> {

}