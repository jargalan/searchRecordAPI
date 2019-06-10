package com.demo.searchrecords.service;
import com.demo.searchrecords.model.Record;
import com.demo.searchrecords.model.RecordApi;
import com.demo.searchrecords.util.CsvDataLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecordServiceTest {

    @Mock
    private CsvDataLoader csvDataLoader;
    @InjectMocks
    private RecordService service;


    @Test
    public void shouldReturnZeroTotal(){
        when(csvDataLoader.loadObjectList(any(), any())).thenReturn(Arrays.asList(
                new Record("1", "text 1"),
                new Record("2", "text 2"),
                new Record("3", "text 3")));
        int expected = 0;
        int actual = service.find(1, "content").getTotal();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRecordsMatching(){
        when(csvDataLoader.loadObjectList(any(), any())).thenReturn(Arrays.asList(
                new Record("1", "text 1"),
                new Record("2", "content 2"),
                new Record("3", "text 3")));
        int expected = 2;
        int actual = service.find(1, "text").getTotal();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSecondPage(){
        when(csvDataLoader.loadObjectList(any(), any())).thenReturn(Arrays.asList(
                new Record("1", "text 1"),
                new Record("2", "text 2"),
                new Record("3", "text 3"),
                new Record("4", "text 1"),
                new Record("5", "text 2"),
                new Record("6", "text 3"),
                new Record("7", "text 1"),
                new Record("8", "text 2"),
                new Record("9", "text 3"),
                new Record("10", "text 1"),
                new Record("11", "text 2"),
                new Record("12", "text 3")));
        RecordApi recordApi = service.find(2, "text");

        int expected = 12;
        int actual = recordApi.getTotal();
        Assert.assertEquals(expected, actual);

        expected = 2;
        actual = recordApi.getRecordList().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterRecords(){
        when(csvDataLoader.loadObjectList(any(), any())).thenReturn(Arrays.asList(
                new Record("1", "title 1"),
                new Record("2", "text 2"),
                new Record("3", "text 3"),
                new Record("4", "title 1"),
                new Record("5", "text 2"),
                new Record("6", "text 3"),
                new Record("7", "title 1"),
                new Record("8", "content 2"),
                new Record("9", "text 3"),
                new Record("10", "title 1"),
                new Record("11", "text 2"),
                new Record("12", "text 3")));
        RecordApi recordApi = service.find(2, "text");

        int expected = 7;
        int actual = recordApi.getTotal();

        expected = 0;
        actual = recordApi.getRecordList().size();
        Assert.assertEquals(expected, actual);
    }
}