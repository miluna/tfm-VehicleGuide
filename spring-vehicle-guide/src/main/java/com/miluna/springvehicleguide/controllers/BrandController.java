package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.services.BrandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController(value = "BrandController")
public class BrandController implements DefaultController {

    private static Logger LOG = Logger.getLogger(BrandController.class);

    private final BrandService service;

    @Autowired
    private BrandController(@Qualifier(value = "BrandService") BrandService service){
        this.service = service;
    }

    @GetMapping(value = "/brands")
    @Override
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/brands")
    @Override
    public ResponseEntity createOne(@RequestBody Object o) {
        return new ResponseEntity<>(service.createOne(o), HttpStatus.OK);
    }

    @GetMapping(value = "/brands/{id}")
    @Override
    public ResponseEntity getOne(@PathVariable Long id) {
        Brand b = service.findOne(id);
        if (b == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @PutMapping(value = "/brands/{id}")
    @Override
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody Object o) {
        return new ResponseEntity<>(service.updateOne(id, o), HttpStatus.OK);
    }

    @DeleteMapping(value = "/brands/{id}")
    @Override
    public ResponseEntity deleteOne(@PathVariable Long id) {
        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
