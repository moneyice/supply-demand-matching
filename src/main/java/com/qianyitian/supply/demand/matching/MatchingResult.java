package com.qianyitian.supply.demand.matching;

import java.time.LocalDate;
import java.util.Objects;

public class MatchingResult {
    LocalDate supplyDate;
    LocalDate demandDate;
    Integer amount;

    public LocalDate getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(LocalDate supplyDate) {
        this.supplyDate = supplyDate;
    }

    public LocalDate getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(LocalDate demandDate) {
        this.demandDate = demandDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchingResult result = (MatchingResult) o;
        return Objects.equals(supplyDate, result.supplyDate) && Objects.equals(demandDate, result.demandDate) && Objects.equals(amount, result.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplyDate, demandDate, amount);
    }
}
