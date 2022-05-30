package com.retail.Retail.Controllers;

import com.retail.Retail.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StatisticsController {
    private UserRepo userRepo;

    @GetMapping("/statistics")
    public String showStatistics(Model model){
      // long countOfUsers =  userRepo.count();
      // model.addAttribute("countOfCustomers", countOfUsers);
       return "/statistics";
    }
}
