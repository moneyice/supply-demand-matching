package com.qianyitian.supply.demand.matching;

import java.util.ArrayList;
import java.util.List;

public class MatchingAlgorithm {


    public MatchingOutput run(SupplyInput supplyInput, DemandInput demandInput) {
        List<MatchingResult> outputList = new ArrayList<>();

        //预处理
        pre(supplyInput, demandInput);

        List<Item> supplyList = supplyInput.getDataList();
        List<Item> demandList = demandInput.getDataList();

        //
        int i = 0;
        int j = 0;

        while (true) {
            if (i == supplyList.size() || j == demandList.size()) {
                //一方耗尽，即退出条件
                break;
            }
            Item supplyItem = supplyList.get(i);
            Item demandItem = demandList.get(j);

            //获取两者小的值
            Integer minValue = Math.min(supplyItem.getAmount(), demandItem.getAmount());

            //必定有一方归零
            supplyItem.setAmount(supplyItem.getAmount() - minValue);
            demandItem.setAmount(demandItem.getAmount() - minValue);

            MatchingResult result = getMatchingResult(supplyItem, demandItem, minValue);
            outputList.add(result);

            log(result, supplyItem, demandItem);

            if (supplyItem.getAmount() == 0) {
                //如果供应量为0，找下一个
                i++;
            }
            if (demandItem.getAmount() == 0) {
                //如果需求量为0，找下一个
                j++;
            }
        }

        //善后处理
        post(outputList, supplyList, demandList);

        //返回结果
        MatchingOutput matchingOutput = new MatchingOutput();
        matchingOutput.setResult(outputList);
        return matchingOutput;
    }

    private MatchingResult getMatchingResult(Item supplyItem, Item demandItem, Integer minValue) {
        MatchingResult result = new MatchingResult();
        result.setAmount(minValue);
        result.setSupplyDate(supplyItem.getDate());
        result.setDemandDate(demandItem.getDate());
        return result;
    }

    private void post(List<MatchingResult> outputList, List<Item> supplyList, List<Item> demandList) {
    }

    private void pre(SupplyInput supplyInput, DemandInput demandInput) {
    }

    private void log(MatchingResult result, Item supplyItem, Item demandItem) {
    }
}
