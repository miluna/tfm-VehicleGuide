package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "UserService")
public class UserService implements DefaultService {

    @Override
    public User createOne(Object o) {
        return null;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User updateOne(Long id, Object o) {
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
