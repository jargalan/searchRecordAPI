package com.demo.searchrecords.controller;

import com.demo.searchrecords.model.RecordApi;
import com.demo.searchrecords.service.RecordService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RecordController {
    private RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records/{page}/{keyword}")
    public RecordApi getRecords(@PathVariable int page, @PathVariable String keyword) {
        return recordService.find(page, keyword);
    }

}
