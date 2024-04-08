package com.driver.SwiggatoApplication.repository;

import com.driver.SwiggatoApplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
