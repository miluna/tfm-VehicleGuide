package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.controllers.UserController;
import com.miluna.springvehicleguide.models.User;
import com.miluna.springvehicleguide.services.UserService;
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

public class UserControllerTests implements CrudTests {

    private User mock = mock(User.class);
    private UserService service = mock(UserService.class);

    @InjectMocks
    private UserController controller;

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
        ResponseEntity<User> res = controller.getOne(1L);
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void getOneFail() {
        ResponseEntity<User> res = controller.getOne(null);
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Override
    public void getAllTest() {
        ResponseEntity<List<User>> res = controller.getAll();
        List<User> resBody = res.getBody();

        assertNotNull(resBody);
        assertTrue(resBody.contains(mock));
    }

    @Test
    @Override
    public void createOneTest() {
        User u = new User();
        u.setPassword("abc");
        u.setPassword2("abc");
        ResponseEntity<User> res = controller.createOne(u);
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    public void createOneFail() {
        User u = new User();
        u.setPassword("abc");
        u.setPassword2("asdjasdas");
        ResponseEntity<User> res = controller.createOne(u);
        Object resBody = res.getBody();

        assertNull(resBody);
        assertSame(res.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @Override
    public void updateOneTest() {
        ResponseEntity<User> res = controller.updateOne(1L, new User());
        Object resBody = res.getBody();

        assertNotNull(resBody);
        assertEquals(resBody, mock);
    }

    @Test
    @Override
    public void updateOneFail() {
        ResponseEntity<User> res = controller.updateOne(null, new User());
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
