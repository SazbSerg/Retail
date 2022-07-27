package com.retail.Retail.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String image;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<Product> products;


    public Category(String name, String image, List<Product> products) {
        this.name = name;
        this.image = image;
        this.products = products;
    }

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
