package com.retail.Retail.Controllers;

import com.retail.Retail.Repositories.AddInfoRepo;
import com.retail.Retail.Services.AddInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@RequiredArgsConstructor
@Controller
public class AddInfoAndSettingsController {

    private final AddInfoRepo addInfoRepo;
    private final AddInfoService addInfoService;


    @GetMapping("/add-info-admin")
    public String showAddInfo(Model model){
          addInfoService.getAllAdditionalInfo(model);
        return "/add-info-template/add-info-admin";
    }


    @GetMapping("/add-info-edit-admin/{id}")
    public String showEditAddInfo(@PathVariable(value = "id") long id, Model model){
        if (!addInfoRepo.existsById(id)) {
            return "redirect:/add-info-template/add-info-edit-admin";
        }
        addInfoService.getAdditionalInfoById(id, model);
        return "/add-info-template/add-info-edit-admin";
    }


   @PostMapping("/add-info-admin")
   public String saveAddInfo(@RequestParam String textFirstPage,
                             @RequestParam String descriptionFirstPage,
                             @RequestParam String textSecondPage,
                             @RequestParam String descriptionAboutUS,
                             @RequestParam String phone1,
                             @RequestParam String phone2,
                             @RequestParam String policies,
                             Principal principal) {
       addInfoService.createAdditionalInfo(textFirstPage, descriptionFirstPage, textSecondPage, descriptionAboutUS, phone1, phone2, policies, principal);
       return "redirect:/add-info-admin";
   }


   @PostMapping("/add-info-edit-admin/{id}")
   String saveEditAddInfo(@PathVariable(value = "id") long id, @RequestParam String textFirstPage,
                          @RequestParam String descriptionFirstPage,
                          @RequestParam String textSecondPage,
                          @RequestParam String descriptionAboutUS,
                          @RequestParam String phone1,
                          @RequestParam String phone2,
                          @RequestParam String policies,
                          Principal principal){
       addInfoService.updateAdditionalInfoById(id, textFirstPage, descriptionFirstPage, textSecondPage, descriptionAboutUS, phone1, phone2, policies, principal);
       return "redirect:/add-info-admin";
   }


   @GetMapping("/add-settings")
    public String showAllReasons(Model model) {
        addInfoService.getReasonsOfCancellation(model);
        return "/add-info-template/add-settings";
   }


   @PostMapping("/add-settings")
    public String saveNewReasonOfCancellation(@RequestParam String reason){
       addInfoService.createReasonOfCancellation(reason);
       return "redirect:/add-settings";
   }


    @GetMapping("/add-settings/delete/{id}")
    public String removeReasonOfCancellation(@PathVariable long id, Principal principal){
        addInfoService.getReasonOfCancellationById(id, principal);
        return  "redirect:/add-settings";
    }
}
