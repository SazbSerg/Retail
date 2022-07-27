package com.retail.Retail.Services;

import com.retail.Retail.Models.*;
import com.retail.Retail.Repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.Optional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

   private final OrderSummeryRepository orderSummeryRepository;
   private final CustomerRepo customerRepo;
   private final OneItemOrderRepo oneItemOrderRepo;
   private final ProductRepo productRepo;
   private final CartRepository cartRepository;
   private final ReasonOfCancellationRepo reasonOfCancellationRepo;


   public void showInfoFromAllOrders(Model model) {
      Optional<OrderSummary> summaryList = orderSummeryRepository.findByCartIsNotNull();
      ArrayList<OrderSummary> orderSummaryList = new ArrayList<>();
      summaryList.ifPresent(orderSummaryList::add);

      List<OrderSummary> actualOrderSummary = new ArrayList<>();
      for (OrderSummary orderSummary : orderSummaryList) {
         if (!(orderSummary.getOrderStatus().equals(OrderStatus.ORDER_CANCELED))) {
            actualOrderSummary.add(orderSummary);
         }
         model.addAttribute("ordersList", actualOrderSummary);

         double totalPrice = 0;
         double totalDiscount = 0;
         double totalAmount = 0;
         for (OrderSummary list : actualOrderSummary) {
            if (list.getCart() == null) {
               List<OneItemOrder> oneItemOrderList = new ArrayList<>();
               for (OneItemOrder itemOrder: oneItemOrderList) {
                  totalPrice += itemOrder.getProduct().getPrice() * itemOrder.getItem();
                  totalDiscount += itemOrder.getProduct().getPrice() * itemOrder.getProduct().getDiscount() * itemOrder.getItem()/100;
               }
               totalAmount = totalPrice - totalDiscount;
               model.addAttribute("chek", totalAmount);
            } else {
               Cart cart = list.getCart();
               List<OneItemOrder> oneItemOrderList = cart.getOneItemOrders();
               for (OneItemOrder itemOrder: oneItemOrderList) {
                  totalPrice += itemOrder.getProduct().getPrice() * itemOrder.getItem();
                  totalDiscount += itemOrder.getProduct().getPrice() * itemOrder.getProduct().getDiscount() * itemOrder.getItem()/100;
               }
               totalAmount = totalPrice - totalDiscount;
               model.addAttribute("chek", totalAmount);
            }
         }
      }
   }



   public void showInfoFromOrderByCustomerId(Model model, long id_customer) {
      Customer customer = customerRepo.findById(id_customer).orElseThrow();
      model.addAttribute("currentCustomer", customer);
      if (!((customer.getOrderSummary().getReasonOfCancellation()) == null) || ((customer.getOrderSummary() == null) )) {
         model.addAttribute("reasonOfCancelM", customer.getOrderSummary().getReasonOfCancellation().getReason());
      }
      List<OneItemOrder> itemOrderList = oneItemOrderRepo.findAll();
      List<OneItemOrder> requiredItemOrderList = new ArrayList<>();
      for (OneItemOrder el: itemOrderList) {
         if (el.getCart().getId() == customer.getCart().getId()) {
            requiredItemOrderList.add(el);
         }
      }

      model.addAttribute("oneItemOrderList1", requiredItemOrderList);

      OrderSummary orderSummary = customer.getOrderSummary();
      Set<OrderStatus> orderStatusSet = orderSummary.getOrderStatus();
      model.addAttribute("orderSummaryM", orderSummary);
      model.addAttribute("orderStatusM", orderStatusSet);
      model.addAttribute("notificationAndStatus", customer.getNotification());

      Cart cart = customer.getCart();
      List<OneItemOrder> oneItemOrderList = cart.getOneItemOrders();

      double totalAmount = 0;
      totalAmount = getTotalAmount(oneItemOrderList);
      model.addAttribute("chek", totalAmount);
     // model.addAttribute("total-price", totalPrice);
     // model.addAttribute("total-discount", totalDiscount);
   }


   //Метод для вычисления общего чека заказа конкретного клиента.
   public Double getTotalAmount(List<OneItemOrder> oneItemOrderList){
      double totalPrice = 0;
      double totalDiscount = 0;
      double totalAmount = 0;
      for (OneItemOrder itemOrder: oneItemOrderList) {
         totalPrice += itemOrder.getProduct().getPrice() * itemOrder.getItem();
         totalDiscount += itemOrder.getProduct().getPrice() * itemOrder.getProduct().getDiscount() * itemOrder.getItem()/100;
      }
      totalAmount = totalPrice - totalDiscount;
      return totalAmount;
   }



   public void changeOrderStatusByCustomerId(OrderStatus status, long id) {
      Customer customer = customerRepo.findById(id).orElseThrow();
      OrderSummary orderSummary = customer.getOrderSummary();
     if (status==OrderStatus.ORDER_DELIVERED) {

        orderSummary.setTotalAmountOfOrder(getTotalAmount(orderSummary.getCart().getOneItemOrders()));

         orderSummary.getOrderStatus().clear();
         orderSummary.getOrderStatus().add(status);
         orderSummary.setReasonOfCancellation(null);
         orderSummeryRepository.save(orderSummary);

         long cartId = customer.getCart().getId();
        customer.setOrderSummary(null);
        customer.setCart(null);
        customer.setNotification(OrderStatus.ORDER_DELIVERED.getDescription());
        customerRepo.save(customer);
        orderSummary.setCart(null);
        orderSummeryRepository.save(orderSummary);
        cartRepository.deleteById(cartId);
      }
     if (status==OrderStatus.ORDER_CANCELED) {
        List<OneItemOrder> oneItemOrderList = orderSummary.getCart().getOneItemOrders();
        for (OneItemOrder currentOrder: oneItemOrderList) {
           Product product = productRepo.findById(currentOrder.getProduct().getId()).orElseThrow();
           product.setExistence(product.getExistence() + currentOrder.getItem());
           productRepo.save(product);
        }
        long cartId = customer.getCart().getId();
       // ReasonOfCancellation reasonOfCancellation = new ReasonOfCancellation();
        customer.getOrderSummary().setReasonOfCancellation(null);
        customer.setOrderSummary(null);
        customer.setCart(null);
        customer.setNotification("Заказ отменён администратором.");
        customerRepo.save(customer);
        orderSummary.setCart(null);
        orderSummeryRepository.save(orderSummary);
        cartRepository.deleteById(cartId);
     }


     // if(orderSummary.getOrderStatus().contains(OrderStatus.ORDER_PLACED)){
        // orderSummary.setReasonOfCancellation(reasonOfCancellationRepo.findById(id).orElseThrow());
         orderSummary.getOrderStatus().clear();
         orderSummary.getOrderStatus().add(status);
         orderSummeryRepository.save(orderSummary);
         customer.setNotification(status.getDescription());
         customerRepo.save(customer);


      //      orderSummary.setReasonOfCancellation(new ReasonOfCancellation());
  //   orderSummary.getReasonOfCancellation().setReason("Заказ актуален.");
  //   orderSummeryRepository.save(orderSummary);
  //   if (!(status==OrderStatus.ORDER_CANCELED)) {
  //      orderSummary.getOrderStatus().clear();
  //      orderSummary.getOrderStatus().add(status);
  //      if (!(orderSummary.getReasonOfCancellation().getReason() == null)) {
  //         orderSummary.getReasonOfCancellation().setReason("Заказ актуален.");
  //      }
  //      orderSummeryRepository.save(orderSummary);
  //      customer.setNotification(status.getDescription());
  //   } else {
  //      orderSummary.getOrderStatus().clear();
  //      orderSummary.getOrderStatus().add(status);
  //      orderSummary.getReasonOfCancellation().setReason("Заказ отменён администратором");
  //   }
  //   if (orderSummary.getOrderStatus().contains(OrderStatus.ORDER_DELIVERED) || orderSummary.getOrderStatus().contains(OrderStatus.ORDER_CANCELED)){
  //      customer.getOrderSummaryListHistory().add(orderSummary);
  //   }
  //   customerRepo.save(customer);
  }
}
