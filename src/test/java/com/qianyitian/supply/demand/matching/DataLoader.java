package com.qianyitian.supply.demand.matching;

import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static DemandInput loadDemand(String dataFileName) throws IOException {
        DemandInput demandInput = new DemandInput();
        demandInput.setDataList(load(dataFileName));
        return demandInput;
    }

    public static SupplyInput loadSupply(String dataFileName) throws IOException {
        SupplyInput supplyInput = new SupplyInput();
        supplyInput.setDataList(load(dataFileName));
        return supplyInput;
    }

    private static List<Item> load(String dataFileName) throws IOException {
        List<Item> itemList = new ArrayList<>();
        Files.asCharSource(new File("./testdata", dataFileName), Charset.defaultCharset())
                .readLines(new LineProcessor<String>() {
                    public boolean processLine(String line) throws IOException {
                        String[] array = line.split(",");
                        LocalDate date = LocalDate.parse(array[0], DateTimeFormatter.ISO_DATE);
                        Integer amount = Integer.valueOf(array[1]);
                        {
                            Item supplyItem = new Item();
                            supplyItem.setAmount(amount);
                            supplyItem.setDate(date);
                            itemList.add(supplyItem);
                        }
                        return true;
                    }

                    @Override
                    public String getResult() {
                        return null;
                    }
                });
        return itemList;
    }

    public static MatchingOutput loadResult(String dataFileName) throws IOException {
        MatchingOutput output = new MatchingOutput();
        List<MatchingResult> itemList = new ArrayList<>();
        output.setResult(itemList);

        Files.asCharSource(new File("./testdata", dataFileName), Charset.defaultCharset())
                .readLines(new LineProcessor<String>() {
                    public boolean processLine(String line) throws IOException {
                        String[] array = line.split(",");
                        LocalDate supplyDate = LocalDate.parse(array[0], DateTimeFormatter.ISO_DATE);
                        LocalDate demandDate = LocalDate.parse(array[1], DateTimeFormatter.ISO_DATE);
                        Integer amount = Integer.valueOf(array[2]);
                        {
                            MatchingResult result = new MatchingResult();
                            result.setSupplyDate(supplyDate);
                            result.setDemandDate(demandDate);
                            result.setAmount(amount);
                            itemList.add(result);
                        }
                        return true;
                    }

                    @Override
                    public String getResult() {
                        return null;
                    }
                });
        return output;
    }
}
