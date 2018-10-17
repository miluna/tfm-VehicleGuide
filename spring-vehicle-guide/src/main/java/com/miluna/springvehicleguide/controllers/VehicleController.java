package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "VehicleController")
public class VehicleController implements DefaultController {

    @Override
    public ResponseEntity createOne(Object o) {
        return new ResponseEntity<Vehicle>(new Vehicle(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getOne(Long id) {
        return new ResponseEntity<Vehicle>(new Vehicle(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateOne(Long id, Object o) {
        return new ResponseEntity<Vehicle>(new Vehicle(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteOne(Long id) {
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<List>(new ArrayList(), HttpStatus.OK);
    }
}
