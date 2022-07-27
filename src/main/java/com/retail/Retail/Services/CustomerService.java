package com.retail.Retail.Services;

import com.retail.Retail.Models.Customer;
import com.retail.Retail.Repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerService {

    private final CustomerRepo customerRepo;


    public void findAllCustomers(Model model) {
        Iterable<Customer> customers = customerRepo.findAll();
        model.addAttribute("customer", customers);
    }


    public void changeAccountStatusOfCustomerById(long id) {
        Customer customer = customerRepo.findById(id).orElseThrow();
        if(customer.isDeleted()){
            customer.setDeleted(false);
            customer.setAccountNonLocked(true);
            customer.setActive(true);
            customerRepo.save(customer);
        } else {
            customer.setDeleted(true);
            customer.setAccountNonLocked(false);
            customer.setActive(false);
            customerRepo.save(customer);
        }
    }
}
