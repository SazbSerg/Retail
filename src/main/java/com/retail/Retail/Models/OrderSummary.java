package com.retail.Retail.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class OrderSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JsonManagedReference
    private Customer customer;

    @ManyToOne
    private DeliveryAdress deliveryAdress;

    @OneToOne
    private Cart cart;

    private Date date;
    private Time orderTime;
    private Double totalAmountOfOrder;

    @ElementCollection(targetClass = OrderStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_summery_order_status", joinColumns = @JoinColumn(name = "order_summary_id"))
    @Enumerated(EnumType.STRING)
    private Set<OrderStatus> orderStatus = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "reason_of_cancellation_id")
    private ReasonOfCancellation reasonOfCancellation;
}
