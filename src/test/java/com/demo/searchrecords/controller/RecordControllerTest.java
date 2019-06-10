package com.demo.searchrecords.controller;

import com.demo.searchrecords.model.Record;
import com.demo.searchrecords.model.RecordApi;
import com.demo.searchrecords.service.RecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class RecordControllerTest {

    @Mock
    private RecordService service;
    @InjectMocks
    private RecordController controller;

    @Test
    public void shouldReturnRecords(){
        when(service.find(1, "text")).thenReturn(new RecordApi(3, Arrays.asList(
                new Record("1", "text 1"),
                new Record("2", "text 2"),
                new Record("3", "text 3"))));
        int expected = 3;
        int actual = controller.getRecords(1, "text").getTotal();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnBlankArray(){
        when(service.find(1, "text")).thenReturn(new RecordApi(0, Arrays.asList()));
        int expected = 0;
        RecordApi recordApi = controller.getRecords(1, "text");
        int actual = recordApi.getTotal();
        Assert.assertEquals(expected, actual);;

        actual = recordApi.getRecordList().size();
        Assert.assertEquals(expected, actual);
    }
}
