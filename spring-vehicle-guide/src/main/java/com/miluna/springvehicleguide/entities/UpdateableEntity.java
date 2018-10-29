package com.miluna.springvehicleguide.entities;

public interface UpdateableEntity<T> {

    void updateProperties(T newEntity);
}
