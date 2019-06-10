package com.demo.searchrecords.model;

import java.util.List;

public class RecordApi {
    List<Record> recordList;
    int total;

    public RecordApi(int total, List<Record> recordList) {
        this.total = total;
        this.recordList = recordList;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
