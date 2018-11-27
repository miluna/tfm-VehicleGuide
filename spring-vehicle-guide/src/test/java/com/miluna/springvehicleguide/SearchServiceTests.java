package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.services.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SearchServiceTests {

    private Vehicle mock = mock(Vehicle.class);
    private List mockedResults = Arrays.asList(mock(Map.class));
    private JdbcTemplate template = mock(JdbcTemplate.class);

    @InjectMocks
    private SearchService service;

    @Before
    public void setup() {
        when(template.queryForList(anyString())).thenReturn(mockedResults);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSearchNullTest() {
        List results = service.doSearch(null, null, null, null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchNameTest() {
        List results = service.doSearch("pepe", null, null, null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchTypeTest() {
        List results = service.doSearch(null, "pepe", null, null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchBrandTest() {
        List results = service.doSearch(null, null, "pepe", null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchPriceTest() {
        List results = service.doSearch(null, null, null, "10000", "15000", null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }
}
