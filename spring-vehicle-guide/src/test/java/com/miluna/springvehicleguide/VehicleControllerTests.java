package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.controllers.VehicleController;
import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.services.VehicleService;
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

public class VehicleControllerTests implements CrudTests {

    private Vehicle mock = mock(Vehicle.class);
    private VehicleService service = mock(VehicleService.class);

    @InjectMocks
    private VehicleController controller;

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
        ResponseEntity<Vehicle> res = controller.getOne(1L);
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void getOneFail() {
        ResponseEntity<Vehicle> res = controller.getOne(null);
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Override
    public void getAllTest() {
        ResponseEntity<List<Vehicle>> res = controller.getAll();
        List<Vehicle> resBody = res.getBody();

        assertNotNull(resBody);
        assertTrue(resBody.contains(mock));
    }

    @Test
    @Override
    public void createOneTest() {
        ResponseEntity<Vehicle> res = controller.createOne(new Vehicle());
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void updateOneTest() {
        ResponseEntity<Vehicle> res = controller.updateOne(1L, new Vehicle());
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void updateOneFail() {
        ResponseEntity<Vehicle> res = controller.updateOne(null, new Vehicle());
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
