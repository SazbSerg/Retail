package com.retail.Retail.Repositories;

import com.retail.Retail.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
