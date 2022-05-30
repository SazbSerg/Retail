package com.retail.Retail.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class AddInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String textFirstPage;
    private String descriptionFirstPage;
    private String textSecondPage;
    private String descriptionAboutUS;
    private int phone1;
    private int phone2;
    private String policies;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddInfo(String textFirstPage, String descriptionFirstPage, String textSecondPage, String descriptionAboutUS, int phone1, int phone2, String policies) {
        this.textFirstPage = textFirstPage;
        this.descriptionFirstPage = descriptionFirstPage;
        this.textSecondPage = textSecondPage;
        this.descriptionAboutUS = descriptionAboutUS;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.policies = policies;
    }
}
