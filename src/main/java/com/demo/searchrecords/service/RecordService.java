package com.demo.searchrecords.service;


import com.demo.searchrecords.model.Record;
import com.demo.searchrecords.model.RecordApi;
import com.demo.searchrecords.util.CsvDataLoader;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private static int NUMBER_PER_PAGE = 10;
    private CsvDataLoader  csvDataLoader;

    public RecordService(CsvDataLoader csvDataLoader) {
        this.csvDataLoader = csvDataLoader;
    }

    public RecordApi find(int page, String keyword) {
        List<Record> recordList = csvDataLoader.
                loadObjectList(Record.class, "/static/datafullstack.csv");
        int total = this.getTotalRecords(recordList, keyword);

        return new RecordApi(
                total,
                total > 0 ? this.findRecordsByKeywords(recordList, keyword, page, total): Arrays.asList());
    }

    private List<Record> findRecordsByKeywords(List<Record> recordList, String keyword, int page, int total) {
        int start = (page-1) * NUMBER_PER_PAGE;
        int end = page * NUMBER_PER_PAGE;
        end = end > total ? total : end;

        if (end <= start) {
            return Arrays.asList();
        }

        return recordList
                .stream()
                .filter(item -> item.getText().contains(keyword))
                .collect(Collectors.toList()).subList(
                        start,
                        end);
    }

    private int getTotalRecords(List<Record> recordList, String keyword) {
        return recordList
                .stream()
                .filter(item -> item.getText().contains(keyword))
                .collect(Collectors.reducing(0, e -> 1, Integer::sum));
    }
}
