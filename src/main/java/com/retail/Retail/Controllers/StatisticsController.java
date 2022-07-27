package com.retail.Retail.Controllers;

import com.retail.Retail.Models.OrderStatus;
import com.retail.Retail.Models.OrderSummary;
import com.retail.Retail.Models.StatisticResponseData;
import com.retail.Retail.Repositories.CustomerRepo;
import com.retail.Retail.Repositories.OrderSummeryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class StatisticsController {

    private final CustomerRepo customerRepo;
    private final OrderSummeryRepository orderSummeryRepository;


    @GetMapping("/statistics")
    public String showStatistics(Model model){
        List<OrderSummary> orderSummaryList = orderSummeryRepository.findAll();
        List<StatisticResponseData> finalList = new ArrayList<>();

        for (OrderSummary orderSummary : orderSummaryList) {
            if (!(orderSummary.getTotalAmountOfOrder()==null) && (orderSummary.getOrderStatus().contains(OrderStatus.ORDER_DELIVERED))) {// && (!(orderSummary.getCustomer()==null)
                StatisticResponseData statisticResponseData = new StatisticResponseData();
                statisticResponseData.setDate(orderSummary.getDate());
                statisticResponseData.setTotatSumm(orderSummary.getTotalAmountOfOrder());
                finalList.add(statisticResponseData);
            }
        }

        double currentSumm= 0;
        for (StatisticResponseData statisticResponseData : finalList){
            int currentDate = statisticResponseData.getDate().getDate();
            if (statisticResponseData.getDate().getDate() == currentDate){
                currentSumm += statisticResponseData.getTotatSumm();
            }
        }


        model.addAttribute("countOfCustomersAttr", customerRepo.count());
        model.addAttribute("listOfCustomersAttr", customerRepo.findAll());
        model.addAttribute("finalList", finalList);
       return "/statistics";
    }
}
  //  @RequestMapping(value="/admin-mailing-user-choice", method= RequestMethod.POST, produces = "application/json", consumes="application/json")
  //  @ResponseBody
  //  public void postTestRequest(@RequestBody Long json) {
  //      User user = userRepository.findById(json).orElseThrow();
  //      if (user.isCheckForMail()){
  //          user.setCheckForMail(false);
  //      } else {
  //          user.setCheckForMail(true);
  //      }
  //      userRepository.save(user);
  //  }
//  @RequestMapping(value="/admin-mailing", method= RequestMethod.POST, produces = "application/json", consumes="application/json")
//  @ResponseBody
//  public SmsModel postTestRequest(@RequestBody String json) {
//      return smsRepository.save(new SmsModel(json));
//  }