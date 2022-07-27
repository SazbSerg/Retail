package com.retail.Retail.Models;

import lombok.Data;

import java.util.Date;

@Data
public class StatisticResponseData {
    private long id;
    private Date date;
    private double totatSumm;
}
