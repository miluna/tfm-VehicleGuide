package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController(value = "VehicleController")
public class VehicleController implements DefaultController {

    private static Logger LOG = Logger.getLogger(VehicleController.class);

    private final VehicleService service;

    @Autowired
    private VehicleController(@Qualifier(value = "VehicleService") VehicleService service) {
        this.service = service;
    }

    @GetMapping(value = "/vehicles")
    @Override
    public ResponseEntity getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/vehicles")
    @Override
    public ResponseEntity createOne(@RequestBody Object o) {
        return new ResponseEntity<>(service.createOne(o), HttpStatus.OK);
    }

    @GetMapping(value = "/vehicles/{id}")
    @Override
    public ResponseEntity getOne(@PathVariable Long id) {
        Vehicle v = service.findOne(id);
        if (v == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(v, HttpStatus.OK);
    }

    @PutMapping(value = "/vehicles/{id}")
    @Override
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody Object o) {
        return new ResponseEntity<>(service.updateOne(id, o), HttpStatus.OK);
    }

    @DeleteMapping(value = "/vehicles/{id}")
    @Override
    public ResponseEntity deleteOne(@PathVariable Long id) {
        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/vehicles/{id}/engines")
    public ResponseEntity getVehicleEngines(@PathVariable Long id) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity getBrandVehicles(@PathParam("brandId") String brandId) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

}
