package com.driver.SwiggatoApplication.service;

import com.driver.SwiggatoApplication.dto.requestDto.DeliveryPartnerRequest;
import com.driver.SwiggatoApplication.model.DeliveryPartner;
import com.driver.SwiggatoApplication.repository.DeliveryPartnerRepo;
import com.driver.SwiggatoApplication.transformer.PartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService {

    final DeliveryPartnerRepo deliveryPartnerRepo;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepo deliveryPartnerRepo) {
        this.deliveryPartnerRepo = deliveryPartnerRepo;
    }

    public String addPartner(DeliveryPartnerRequest deliveryPartnerRequest) {

        //dto -> entity
        DeliveryPartner deliveryPartner = PartnerTransformer.PartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        // save
        DeliveryPartner savedPartner = deliveryPartnerRepo.save(deliveryPartner);

        return "You have been successfully regsitered!!!";

    }
}
