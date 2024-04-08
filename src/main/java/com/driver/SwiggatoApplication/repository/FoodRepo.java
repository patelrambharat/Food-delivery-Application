package com.driver.SwiggatoApplication.repository;

import com.driver.SwiggatoApplication.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepo extends JpaRepository<FoodItem,Integer> {

}
