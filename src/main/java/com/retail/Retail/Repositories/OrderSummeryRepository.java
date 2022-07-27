package com.retail.Retail.Repositories;

import com.retail.Retail.Models.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderSummeryRepository extends JpaRepository<OrderSummary, Long> {
        Optional<OrderSummary> findByCartIsNotNull();

}
