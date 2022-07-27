package com.retail.Retail.Controllers;

import com.retail.Retail.Models.*;
import com.retail.Retail.Repositories.CustomerRepo;
import com.retail.Retail.Repositories.OrderSummeryRepository;
import com.retail.Retail.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;


@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerRepo customerRepo;

    //отправить сообщение из админки пользователю
    @PostMapping("/send-message-to-customer/{id}")
    public String sendMessage(@PathVariable long id, @RequestParam String notificationFromArea) {
        Customer customer = customerRepo.findById(id).orElseThrow();
        customer.setNotification(notificationFromArea);
        customerRepo.save(customer);
        return "redirect:/order-summary/"+id;
    }
    //выводит таблицу с заказами в админку
    @GetMapping("/orders-table")
    public String getOrders(Model model){
        List<Customer> customers = customerRepo.findAll();
        List<OrderSummary> orderSummaries = new ArrayList<>();
        for (Customer customer : customers) {
            if (!(customer.getOrderSummary()==null)) {
                orderSummaries.add(customer.getOrderSummary());
            }
        }

        Map<Double, OrderSummary> summaryDoubleMap = new HashMap<>();
            for (OrderSummary list : orderSummaries) {
                double totalPrice = 0;
                double totalDiscount = 0;
                double totalAmount = 0;
                    List<OneItemOrder> oneItemOrderList = list.getCart().getOneItemOrders();
                    for (OneItemOrder itemOrder: oneItemOrderList) {
                        totalPrice += itemOrder.getProduct().getPrice() * itemOrder.getItem();
                        totalDiscount += itemOrder.getProduct().getPrice() * itemOrder.getProduct().getDiscount() * itemOrder.getItem()/100;
                    }
                    totalAmount = totalPrice - totalDiscount;
                    summaryDoubleMap.put(totalAmount, list);
            }
        model.addAttribute("mapa", summaryDoubleMap);
        //   orderService.showInfoFromAllOrders(model);
        return "/order handling/orders-table";
    }



    @GetMapping("/order-summary/{id_customer}")
    public String getOrder (Model model, @PathVariable long id_customer) {
        Customer customer = customerRepo.findById(id_customer).orElseThrow();
        if (customer.getOrderSummary() == null) {
            return "redirect:/orders-table";
        }
        orderService.showInfoFromOrderByCustomerId(model, id_customer);
        return "/order handling/order-summary";
    }


    @PostMapping("/order-summary/{id}")
    public String postStatus(@RequestParam OrderStatus status, @PathVariable long id) {
        orderService.changeOrderStatusByCustomerId(status, id);
        return "redirect:/order-summary/{id}";
    }
}
