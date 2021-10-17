package com.qianyitian.supply.demand.matching;

import java.util.List;

public class DemandInput {
    public List<Item> getDataList() {
        return dataList;
    }

    public void setDataList(List<Item> dataList) {
        this.dataList = dataList;
    }

    List<Item> dataList = null;
}
