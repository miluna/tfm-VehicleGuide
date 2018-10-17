package com.miluna.springvehicleguide.services;

import java.util.List;

public interface DefaultService<T> {

    T createOne(T t);

    T findOne(Long id);

    T updateOne(Long id, T t);

    Boolean deleteOne(Long id);

    List findAll();

}
