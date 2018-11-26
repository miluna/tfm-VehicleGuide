package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.controllers.SearchController;
import com.miluna.springvehicleguide.services.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SearchControllerTests {

    private List mock = Arrays.asList(mock(Map .class));
    private SearchService service = mock(SearchService.class);

    @InjectMocks
    private SearchController controller;

    @Before
    public void setup() {
        when(service.doSearch(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(mock);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSearchNullTest() {
        ResponseEntity res = controller.doSearch(null, null, null, null, null, null, null);
        Object resBody = res.getBody();

        assertNotNull(res);
        assertNotNull(resBody);
        assertSame(mock, resBody);
    }

    @Test
    public void doSearchNameTest() {
        ResponseEntity res = controller.doSearch("pepe", null, null, null, null, null, null);
        Object resBody = res.getBody();

        assertNotNull(res);
        assertNotNull(resBody);
        assertSame(mock, resBody);
    }

    @Test
    public void doSearchBrandTest() {
        ResponseEntity res = controller.doSearch(null, null, "pepe", null, null, null, null);
        Object resBody = res.getBody();

        assertNotNull(res);
        assertNotNull(resBody);
        assertSame(mock, resBody);
    }

    @Test
    public void doSearchPriceTest() {
        ResponseEntity res = controller.doSearch(null, null, null, "10000", "15000", null, null);
        Object resBody = res.getBody();

        assertNotNull(res);
        assertNotNull(resBody);
        assertSame(mock, resBody);
    }
}
