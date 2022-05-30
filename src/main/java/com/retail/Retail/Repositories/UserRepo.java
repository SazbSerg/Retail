package com.retail.Retail.Repositories;

import com.retail.Retail.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);
}
