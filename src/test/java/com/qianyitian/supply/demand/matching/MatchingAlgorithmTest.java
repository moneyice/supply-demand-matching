package com.qianyitian.supply.demand.matching;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchingAlgorithmTest {
    MatchingAlgorithm matchingAlgorithm = null;

    @BeforeEach
    void setUp() {
        matchingAlgorithm = new MatchingAlgorithm();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() throws IOException {
        //加载数据
        SupplyInput supplyInput = DataLoader.loadSupply("supply-1.data");
        DemandInput demandInput = DataLoader.loadDemand("demand-1.data");

        //实际运行结果
        MatchingOutput runningResult = matchingAlgorithm.run(supplyInput, demandInput);

        //期望结果
        MatchingOutput expectedResult = DataLoader.loadResult("output-1.data");

        //实际结果和期望结果比较
        compare(runningResult.getResult(), expectedResult.getResult());

    }

    private void compare(List<MatchingResult> result1, List<MatchingResult> result2) {
        assertEquals(result1.size(), result2.size());
        for (int i = 0; i < result1.size(); i++) {
            assertEquals(result1.get(i), result2.get(i));
        }
    }
}