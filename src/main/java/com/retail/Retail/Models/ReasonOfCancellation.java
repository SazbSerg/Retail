package com.retail.Retail.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ReasonOfCancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String reason;

    public ReasonOfCancellation(String reason) {
        this.reason = reason;
    }
}
