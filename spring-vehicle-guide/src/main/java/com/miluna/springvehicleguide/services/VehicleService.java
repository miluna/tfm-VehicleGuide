package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.models.Vehicle;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;

@Service(value = "VehicleService")
public class VehicleService implements DefaultService {

    private static Logger LOG = Logger.getLogger(VehicleService.class);

    @Override
    public Vehicle createOne(Object o) {
        return null;
    }

    @Override
    public Vehicle findOne(Long id) {
        return null;
    }

    @Override
    public Vehicle updateOne(Long id, Object o) {
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
