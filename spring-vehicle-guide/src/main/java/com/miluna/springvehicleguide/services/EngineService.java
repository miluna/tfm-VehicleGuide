package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.models.Engine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "EngineService")
public class EngineService implements DefaultService {

    @Override
    public Engine createOne(Object o) {
        return null;
    }

    @Override
    public Engine findOne(Long id) {
        return null;
    }

    @Override
    public Engine updateOne(Long id, Object o) {
        return null;
    }

    @Override
    public Boolean deleteOne(Long id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }
}
