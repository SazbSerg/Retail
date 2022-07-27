package com.retail.Retail.Services;

import com.retail.Retail.Models.AddInfo;
import com.retail.Retail.Models.ReasonOfCancellation;
import com.retail.Retail.Repositories.AddInfoRepo;
import com.retail.Retail.Repositories.ReasonOfCancellationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AddInfoService {

    private final AddInfoRepo addInfoRepo;
    private final ReasonOfCancellationRepo reasonOfCancellationRepo;


    public List<AddInfo> getInformation() {
        return addInfoRepo.findAll();

    }

    public ArrayList<String> getFirstPageInfo() {
        AddInfo addInfo = addInfoRepo.findById(1L).get();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(addInfo.getTextFirstPage());
        arrayList.add(addInfo.getDescriptionFirstPage());
        return arrayList;
    }

    public String getSecondPageInfo() {
        AddInfo addInfo = addInfoRepo.findById(1L).get();
        return addInfo.getTextSecondPage();
    }

    public ArrayList<String> getSideMenuInfo() {
        AddInfo addInfo = addInfoRepo.findById(1L).get();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(addInfo.getDescriptionAboutUS());
        arrayList.add(addInfo.getPolicies());
        arrayList.add(addInfo.getPhone1());
        arrayList.add(addInfo.getPhone2());
        return arrayList;
    }

    public void getAllAdditionalInfo(Model model) {
        Iterable<AddInfo> addInfos = addInfoRepo.findAll();
        model.addAttribute("addInfoss", addInfos);
    }

    public void getAdditionalInfoById(long id, Model model) {
        Optional<AddInfo> addInfo = addInfoRepo.findById(id);
        ArrayList<AddInfo> res = new ArrayList<>();
        addInfo.ifPresent(res::add);
        model.addAttribute("addInfoss", res);
    }

    public void createAdditionalInfo(String textFirstPage, String descriptionFirstPage, String textSecondPage, String descriptionAboutUS, String phone1, String phone2, String policies, Principal principal) {
        AddInfo addInfo = new AddInfo(textFirstPage, descriptionFirstPage, textSecondPage, descriptionAboutUS, phone1, phone2, policies);
        addInfoRepo.save(addInfo);
        log.info("Дополнительная информация была отредактирована пользователем {}", principal.getName());
    }

    public void updateAdditionalInfoById(long id, String textFirstPage, String descriptionFirstPage, String textSecondPage, String descriptionAboutUS, String phone1, String phone2, String policies, Principal principal) {
        AddInfo addInfo = addInfoRepo.findById(id).orElseThrow();
        addInfo.setTextFirstPage(textFirstPage);
        addInfo.setDescriptionFirstPage(descriptionFirstPage);
        addInfo.setTextSecondPage(textSecondPage);
        addInfo.setDescriptionAboutUS(descriptionAboutUS);
        addInfo.setPhone1(phone1);
        addInfo.setPhone2(phone2);
        addInfo.setPolicies(policies);
        addInfoRepo.save(addInfo);
        log.info("Дополнительная информация была отредактирована пользователем {}", principal.getName());
    }

    public void getReasonsOfCancellation(Model model) {
        List<ReasonOfCancellation> reasonOfCancellationList = reasonOfCancellationRepo.findAll();
        model.addAttribute("reasonList", reasonOfCancellationList);
    }

    public void createReasonOfCancellation(String reason) {
        ReasonOfCancellation reasonOfCancellation = new ReasonOfCancellation(reason);
        reasonOfCancellationRepo.save(reasonOfCancellation);
    }

    public void getReasonOfCancellationById(long id, Principal principal) {
        ReasonOfCancellation reasonOfCancellation = reasonOfCancellationRepo.findById(id).orElseThrow();
        reasonOfCancellationRepo.delete(reasonOfCancellation);
        log.info("В разделе 'ПРИЧИНА ОТМЕНЫ ЗАКАЗА' удалён раздел пользователем {}", principal.getName());
    }
}
