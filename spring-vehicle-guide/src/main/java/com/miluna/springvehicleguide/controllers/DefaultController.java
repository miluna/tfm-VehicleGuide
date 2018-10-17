package com.miluna.springvehicleguide.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DefaultController<T> {

    ResponseEntity<T> createOne(T t);

    ResponseEntity<T> getOne(Long id);

    ResponseEntity<T> updateOne(Long id, T t);

    ResponseEntity<T> deleteOne(Long id);

    ResponseEntity<List> getAll();

}
