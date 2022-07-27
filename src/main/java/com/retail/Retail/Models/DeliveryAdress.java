package com.retail.Retail.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String city;
    private String district;
    private String street;
    private String houseNumber;
    private String frontDoor;
    private String floor;
    private String apartmentNumber;
    private String description;
    private boolean current;
}
