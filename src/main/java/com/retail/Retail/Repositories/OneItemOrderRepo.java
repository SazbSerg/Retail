package com.retail.Retail.Repositories;

import com.retail.Retail.Models.OneItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneItemOrderRepo extends JpaRepository<OneItemOrder, Long> {
}
