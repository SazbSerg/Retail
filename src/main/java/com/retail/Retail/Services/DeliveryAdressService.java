package com.retail.Retail.Services;

import com.retail.Retail.Models.DeliveryAdress;
import com.retail.Retail.Repositories.DeliveryAdressRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryAdressService {

    private final DeliveryAdressRepo deliveryAdressRepo;


    public List<DeliveryAdress> getGlobalListOfDeliveryAddresses() {
        return deliveryAdressRepo.findAll();
    }




}
