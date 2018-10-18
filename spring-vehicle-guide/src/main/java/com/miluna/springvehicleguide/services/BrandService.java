package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.models.Brand;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;

@Service(value = "BrandService")
public class BrandService implements DefaultService {

    private static Logger LOG = Logger.getLogger(BrandService.class);

    @Override
    public Brand createOne(Object o) {
        Brand brand = (Brand) o;
        return null;
    }

    @Override
    public Brand findOne(Long id) {
        return null;
    }

    @Override
    public Brand updateOne(Long id, Object o) {
        Brand brand = (Brand) o;
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
