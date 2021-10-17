package com.qianyitian.supply.demand.matching;

import java.time.LocalDate;

public class Item {
    LocalDate date;
    Integer amount;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
