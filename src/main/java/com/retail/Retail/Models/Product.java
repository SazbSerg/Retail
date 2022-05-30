package com.retail.Retail.Models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private Long vendorCode;
    private Double price;
    private Double existence;
    private Double discount;
    private String details;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    public Product(String name, Long vendorCode, Double price, Double existence, Double discount, String details, String image1, String image2, String image3, String image4, String image5) {
        this.name = name;
        this.vendorCode = vendorCode;
        this.price = price;
        this.existence = existence;
        this.discount = discount;
        this.details = details;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
    }
}
