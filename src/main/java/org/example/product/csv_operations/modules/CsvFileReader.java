package org.example.product.csv_operations.modules;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode
@ToString
@Component
public class CsvFileReader {
    private final List<String> rowRecords = new ArrayList<>();


    public List<String> read(File filePath) {
        rowRecords.clear();
        Reader in;
        try {
            in = new FileReader(filePath.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(Arrays.toString(Headers.values()))
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records;
        try {
            records = csvFormat.parse(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (CSVRecord record : records) {
            parseToList(record);
        }
        return rowRecords;
    }

    private void parseToList(CSVRecord record) {
        for (String value : record) {
            rowRecords.add(value);
        }
    }
}
