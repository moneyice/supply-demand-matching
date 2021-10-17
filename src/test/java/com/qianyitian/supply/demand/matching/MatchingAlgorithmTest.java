package com.qianyitian.supply.demand.matching;

import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        SupplyInput supplyInput = DataLoader.loadSupply("supply-1.data");
        DemandInput demandInput = DataLoader.loadDemand("demand-1.data");

        MatchingOutput runningResult = matchingAlgorithm.run(supplyInput, demandInput);

        MatchingOutput expectedResult = DataLoader.loadResult("output-1.data");

        compare(runningResult.getResult(), expectedResult.getResult());

    }

    private void compare(List<MatchingResult> result1, List<MatchingResult> result2) {
        assertEquals(result1.size(), result2.size());
        for (int i = 0; i < result1.size(); i++) {
            assertEquals(result1.get(i), result2.get(i));
        }
    }


}