package com.retail.Retail.Controllers;

import com.retail.Retail.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/clients-table")
    public String getAllCustomersTable(Model model){
        customerService.findAllCustomers(model);
        return "/clients/clients-table";
    }


    @GetMapping("/clients-table-status-change/{id}")
    public String updateCustomerAccountCustomerStatus(@PathVariable long id){
        customerService.changeAccountStatusOfCustomerById(id);
        return "redirect:/clients-table";
    }
}
