package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.services.EngineService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "EngineController")
public class EngineController implements DefaultController{

    private static Logger LOG = Logger.getLogger(BrandController.class);

    private final EngineService service;

    @Autowired
    private EngineController(@Qualifier(value = "EngineService") EngineService service){
        this.service = service;
    }

    @GetMapping(value = "/engines")
    @Override
    public ResponseEntity getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/engines")
    @Override
    public ResponseEntity createOne(@RequestBody Object o) {
        return new ResponseEntity<>(service.createOne(o), HttpStatus.OK);
    }

    @GetMapping(value = "/engines/{id}")
    @Override
    public ResponseEntity getOne(@PathVariable Long id) {
        Engine e = service.findOne(id);
        if (e == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PutMapping(value = "/engines/{id}")
    @Override
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody Object o) {
        return new ResponseEntity<>(service.updateOne(id, o), HttpStatus.OK);
    }

    @DeleteMapping(value = "/engines/{id}")
    @Override
    public ResponseEntity deleteOne(@PathVariable Long id) {
        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
