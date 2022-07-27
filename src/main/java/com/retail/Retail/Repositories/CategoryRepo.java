package com.retail.Retail.Repositories;


import com.retail.Retail.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
