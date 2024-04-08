package com.driver.SwiggatoApplication.repository;

import com.driver.SwiggatoApplication.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem,Integer> {
}
