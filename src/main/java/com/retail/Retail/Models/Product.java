package com.retail.Retail.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode
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

    @JsonManagedReference
    @ManyToOne
    private Category category;
}