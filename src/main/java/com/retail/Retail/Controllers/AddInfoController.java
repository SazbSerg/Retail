package com.retail.Retail.Controllers;

import com.retail.Retail.Models.AddInfo;
import com.retail.Retail.Models.Category;
import com.retail.Retail.Repositories.AddInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AddInfoController {

    @Autowired
    AddInfoRepo addInfoRepo;

    @GetMapping("/add-info-admin")
    public String showAddInfo(Model model){
          Iterable<AddInfo> addInfos = addInfoRepo.findAll();
          model.addAttribute("addInfoss", addInfos);
        return "/add-info-template/add-info-admin";
    }

    @GetMapping("/add-info-edit-admin/{id}")
    public String showEditAddInfo(@PathVariable(value = "id") long id, Model model){
        if (!addInfoRepo.existsById(id)) {
            return "redirect:/add-info-template/add-info-edit-admin";
        }
        Optional<AddInfo> addInfo = addInfoRepo.findById(id);
        ArrayList<AddInfo> res = new ArrayList<>();
        addInfo.ifPresent(res::add);
        model.addAttribute("addInfoss", res);
        return "/add-info-template/add-info-edit-admin";
    }

   @PostMapping("/add-info-admin")
   public String saveAddInfo(@RequestParam String textFirstPage,
                             @RequestParam String descriptionFirstPage,
                             @RequestParam String textSecondPage,
                             @RequestParam String descriptionAboutUS,
                             @RequestParam int phone1,
                             @RequestParam int phone2,
                             @RequestParam String policies) {
       AddInfo addInfo = new AddInfo(textFirstPage, descriptionFirstPage, textSecondPage, descriptionAboutUS, phone1, phone2, policies);
       addInfoRepo.save(addInfo);
       return "redirect:/add-info-admin";
   }

   @PostMapping("/add-info-edit-admin/{id}")
   String saveEditAddInfo(@PathVariable(value = "id") long id, @RequestParam String textFirstPage,
                          @RequestParam String descriptionFirstPage,
                          @RequestParam String textSecondPage,
                          @RequestParam String descriptionAboutUS,
                          @RequestParam int phone1,
                          @RequestParam int phone2,
                          @RequestParam String policies){
       AddInfo addInfo = addInfoRepo.findById(id).orElseThrow();
       addInfo.setTextFirstPage(textFirstPage);
       addInfo.setDescriptionFirstPage(descriptionFirstPage);
       addInfo.setTextSecondPage(textSecondPage);
       addInfo.setDescriptionAboutUS(descriptionAboutUS);
       addInfo.setPhone1(phone1);
       addInfo.setPhone2(phone2);
       addInfo.setPolicies(policies);
       addInfoRepo.save(addInfo);
       return "redirect:/add-info-admin";
   }
}
