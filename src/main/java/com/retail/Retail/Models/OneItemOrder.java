package com.retail.Retail.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class OneItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

   @OneToOne(cascade = CascadeType.ALL)
   private Product product;

   @ManyToOne(cascade = CascadeType.ALL)
   @JsonBackReference
   private Cart cart;

   private int item;
}
