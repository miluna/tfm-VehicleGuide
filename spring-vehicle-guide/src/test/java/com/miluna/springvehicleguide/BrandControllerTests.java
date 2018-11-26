package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.controllers.BrandController;
import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.services.BrandService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BrandControllerTests implements CrudTests {

    private Brand mock = mock(Brand.class);
    private BrandService service = mock(BrandService.class);

    @InjectMocks
    private BrandController controller;

    @Before
    public void setup() {
        when(service.findOne(anyLong())).thenReturn(mock);
        when(service.createOne(any())).thenReturn(mock);
        when(service.deleteOne(any())).thenReturn(true);
        when(service.findAll()).thenReturn(Arrays.asList(mock));
        when(service.updateOne(anyLong(), any())).thenReturn(mock);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Override
    public void getOneTest() {
        ResponseEntity res = controller.getOne(1L);
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void getOneFail() {
        ResponseEntity res = controller.getOne(null);
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Override
    public void getAllTest() {
        ResponseEntity res = controller.getAll();
        List resBody = (List) res.getBody();

        assertNotNull(resBody);
        assertTrue(resBody.contains(mock));
    }

    @Test
    @Override
    public void createOneTest() {
        ResponseEntity res = controller.createOne(new Brand());
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void updateOneTest() {
        ResponseEntity res = controller.updateOne(1L, new Brand());
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void updateOneFail() {
        ResponseEntity res = controller.updateOne(null, new Brand());
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Override
    public void deleteOneTest() {
        ResponseEntity res = controller.deleteOne(1L);
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.OK);
    }
}
