package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.entities.UserEntity;
import com.miluna.springvehicleguide.models.User;
import com.miluna.springvehicleguide.repositories.UserRepository;
import com.miluna.springvehicleguide.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UserServiceTests implements CrudTests {

    private UserEntity mock = mock(UserEntity.class);
    private UserRepository repository = mock(UserRepository.class);

    @InjectMocks
    private UserService service;

    @Before
    public void setup() {
        when(repository.findById(any())).thenReturn(Optional.of(mock));
        when(repository.findAll()).thenReturn(Arrays.asList(mock));
        when(repository.save(any())).thenReturn(mock);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Override
    public void getOneTest() {
        Object result = service.findOne(1L);

        assertNotNull(result);
        // assert not same -> output should be model, not entity
        assertNotSame(result, mock);
    }

    @Test
    @Override
    public void getOneFail() {
        Object result = service.findOne(null);

        assertNull(result);
    }

    @Test
    @Override
    public void getAllTest() {
        List result = service.findAll();

        assertNotNull(result);
        // assert false -> should contain model not entity
        assertFalse(result.contains(mock));
        result.forEach(e -> assertTrue(e instanceof User));
    }

    @Test
    @Override
    public void createOneTest() {
        Object result = service.createOne(
                new User(null, "pepe@gmail.com", "abc", "abc", "ADMIN"));

        assertNotNull(result);
        // assert not same -> output should be model, not entity
        assertNotSame(result, mock);
    }

    @Test
    @Override
    public void updateOneTest() {
        Object result = service.updateOne(1L, new User());

        assertNotNull(result);
        // assert not same -> output should be model, not entity
        assertNotSame(result, mock);
    }

    @Test
    @Override
    public void updateOneFail() {
        Object result = service.updateOne(null, new User());

        assertNull(result);
    }

    @Test
    @Override
    public void deleteOneTest() {
        boolean result = service.deleteOne(1L);

        assertTrue(result);
    }
}
