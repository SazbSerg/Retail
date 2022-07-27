package com.retail.Retail.Repositories;

import com.retail.Retail.Models.Customer;
import com.retail.Retail.Models.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    boolean existsByEmail(String email);

    Customer findByPassword(String password);

    boolean existsCustomerByPassword(String password);

}
