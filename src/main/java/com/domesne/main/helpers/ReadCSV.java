package com.domesne.main.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ReadCSV {
    public static ArrayList<TreeMap<String,String>> readCSVFile(String aPathToCSVFile){
        ArrayList<TreeMap<String,String>> csvArrayList = new ArrayList<>();
        try{
            Reader in = new FileReader(aPathToCSVFile);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);
            for (CSVRecord record : records) {
                TreeMap<String, String> newRecord = new TreeMap<>();

                for (String headerLabel : headers) {
                    newRecord.put(headerLabel, record.get(headerLabel));
                }
                csvArrayList.add(newRecord);
            }
        } catch (IOException e) {
            System.out.println("Failed to load job data");
            e.printStackTrace();
        }
        return csvArrayList;
    }
}
