package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.services.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SearchServiceTests {

    private Vehicle mock = mock(Vehicle.class);
    private List<Vehicle> mockedResults = Arrays.asList(mock);
    private JdbcTemplate template = mock(JdbcTemplate.class);

    @InjectMocks
    private SearchService service;

    @Before
    public void setup() {
        when(template.query(anyString(), (RowMapper) any())).thenReturn(mockedResults);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSearchNullTest() {
        List<Vehicle> results = service.doSearch(null, null, null, null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchNameTest() {
        List<Vehicle> results = service.doSearch("pepe", null, null, null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchTypeTest() {
        List<Vehicle> results = service.doSearch(null, "pepe", null, null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchBrandTest() {
        List<Vehicle> results = service.doSearch(null, null, "pepe", null, null, null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }

    @Test
    public void doSearchPriceTest() {
        List<Vehicle> results = service.doSearch(null, null, null, "10000", "15000", null, null);

        assertNotNull(results);
        assertEquals(results, mockedResults);
    }
}
