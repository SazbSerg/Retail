package com.retail.Retail.Repositories;

import com.retail.Retail.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
