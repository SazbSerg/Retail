package com.retail.Retail.Repositories;


import com.retail.Retail.Models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepo extends JpaRepository<WishList, Long> {
}
