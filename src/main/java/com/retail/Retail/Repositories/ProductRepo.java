package com.retail.Retail.Repositories;

import com.retail.Retail.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p order by p.name ASC")
    List<Product> findAllAndOrderByName();

    List<Product> getProductsByCategoryId(Long id);
}
