package com.retail.Retail.Repositories;


import com.retail.Retail.Models.DeliveryAdress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAdressRepo extends JpaRepository<DeliveryAdress, Long> {
        DeliveryAdress findDeliveryAdressByCity(String city);
}
