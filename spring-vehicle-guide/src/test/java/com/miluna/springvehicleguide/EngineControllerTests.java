package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.controllers.EngineController;
import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.services.EngineService;
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

public class EngineControllerTests implements CrudTests {

    private Engine mock = mock(Engine.class);
    private EngineService service = mock(EngineService.class);

    @InjectMocks
    private EngineController controller;

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
        ResponseEntity<Engine> res = controller.getOne(1L);
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void getOneFail() {
        ResponseEntity<Engine> res = controller.getOne(null);
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Override
    public void getAllTest() {
        ResponseEntity<List<Engine>> res = controller.getAll();
        List<Engine> resBody = res.getBody();

        assertNotNull(resBody);
        assertTrue(resBody.contains(mock));
    }

    @Test
    @Override
    public void createOneTest() {
        ResponseEntity<Engine> res = controller.createOne(new Engine());
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void updateOneTest() {
        ResponseEntity<Engine> res = controller.updateOne(1L, new Engine());
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void updateOneFail() {
        ResponseEntity<Engine> res = controller.updateOne(null, new Engine());
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Override
    public void deleteOneTest() {
        ResponseEntity<HttpStatus> res = controller.deleteOne(1L);
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.OK);
    }
}
