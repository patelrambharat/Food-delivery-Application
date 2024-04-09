package com.driver.SwiggatoApplication.repository;

import com.driver.SwiggatoApplication.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryPartnerRepo extends JpaRepository<DeliveryPartner,Integer> {
//    String findRandomPartner = "select p from DeliveryPartner p order by RAND() LIMIT 1";
//
//    @Query(value = findRandomPartner)
//    DeliveryPartner findRandomDeliveryPartner();
}
