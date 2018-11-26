package com.miluna.springvehicleguide;

import com.miluna.springvehicleguide.entities.EngineEntity;
import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.repositories.EngineRepository;
import com.miluna.springvehicleguide.services.EngineService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EngineServiceTests implements CrudTests {

    private EngineEntity mock = mock(EngineEntity.class);
    private EngineRepository repository = mock(EngineRepository.class);

    @InjectMocks
    private EngineService service;

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
        result.forEach(e -> assertTrue(e instanceof Engine));
    }

    @Test
    @Override
    public void createOneTest() {
        Object result = service.createOne(new Engine());

        assertNotNull(result);
        // assert not same -> output should be model, not entity
        assertNotSame(result, mock);
    }

    @Test
    @Override
    public void updateOneTest() {
        Object result = service.updateOne(1L, new Engine());

        assertNotNull(result);
        // assert not same -> output should be model, not entity
        assertNotSame(result, mock);
    }

    @Test
    @Override
    public void updateOneFail() {
        Object result = service.updateOne(null, new Engine());

        assertNull(result);
    }

    @Test
    @Override
    public void deleteOneTest() {
        boolean result = service.deleteOne(1L);

        assertTrue(result);
    }
}
