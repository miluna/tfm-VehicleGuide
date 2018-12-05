package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "VehicleController")
@Api(value = "Controller designed to add/retrieve/modify Vehicles")
public class VehicleController implements CrudController<Vehicle> {

    private final VehicleService service;

    @Autowired
    private VehicleController(@Qualifier(value = "VehicleService") VehicleService service) {
        this.service = service;
    }

    @GetMapping(value = "/vehicles")
    @ApiOperation(value = "Get all vehicles", response = List.class)
    @Override
    public ResponseEntity<List<Vehicle>> getAll() {

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/vehicles")
    @ApiOperation(value = "Create one Vehicle", response = Vehicle.class)
    @Override
    public ResponseEntity<Vehicle> createOne(
        @ApiParam(value = "Vehicle object model", required = true) @RequestBody Vehicle o) {
        
        return new ResponseEntity<>(service.createOne(o), HttpStatus.OK);
    }

    @GetMapping(value = "/vehicles/{id}")
    @ApiOperation(value = "Get one Vehicle", response = Vehicle.class)
    @Override
    public ResponseEntity<Vehicle> getOne(
        @ApiParam(value = "Vehicle id", required = true) @PathVariable Long id) {
        
        Vehicle v = (id != null) ? service.findOne(id) : null;
        if (v == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(v, HttpStatus.OK);
    }

    @PutMapping(value = "/vehicles/{id}")
    @ApiOperation(value = "Update one Vehicle", response = Vehicle.class)
    @Override
    public ResponseEntity<Vehicle> updateOne(
        @ApiParam(value = "Vehicle id", required = true) @PathVariable Long id, 
        @ApiParam(value = "Vehicle object model", required = true) @RequestBody Vehicle o) {
        
        Vehicle v = (id != null) ? service.updateOne(id, o) : null;
        if (v == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(v, HttpStatus.OK);
    }

    @DeleteMapping(value = "/vehicles/{id}")
    @ApiOperation(value = "Delete one Vehicle", response = HttpStatus.class)
    @Override
    public ResponseEntity<HttpStatus> deleteOne(
        @ApiParam(value = "Vehicle id", required = true) @PathVariable Long id) {

        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/vehicles/{id}/engines")
    public ResponseEntity<List<Engine>> getVehicleEngines(
        @ApiParam(value = "Vehicle id", required = true) @PathVariable Long id) {

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

}
