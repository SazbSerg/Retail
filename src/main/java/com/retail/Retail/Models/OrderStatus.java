package com.retail.Retail.Models;

public enum OrderStatus {
    ORDER_PLACED("Заказ в обработке.", 1),
    ORDER_SHIPPED("Заказ упакован и готов к отправке.", 2),
    ORDER_OUT_OF_DELIVERY("Заказ в пути к месту доставки.", 3),
    ORDER_DELIVERED("Заказ доставлен.", 4),
    ORDER_CANCELED("Заказ отменён.", 5);

    String description;
    int valueOrderStatus;

    OrderStatus(String description, int valueOrderStatus){
        this.description = description;
        this.valueOrderStatus = valueOrderStatus;
    }

    public String getDescription() {
        return description;
    }

    public int getValueOrderStatus() {
        return valueOrderStatus;
    }
}
