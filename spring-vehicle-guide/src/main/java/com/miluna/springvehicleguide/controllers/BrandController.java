package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.services.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "BrandController")
@Api(value = "Controller designed to add/retrieve/modify brands")
public class BrandController implements CrudController {

    private static Logger LOG = Logger.getLogger(BrandController.class);

    private final BrandService service;

    @Autowired
    private BrandController(@Qualifier(value = "BrandService") BrandService service){
        this.service = service;
    }

    @GetMapping(value = "/brands")
    @ApiOperation(value = "Get all brands", response = List.class)
    @Override
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/brands")
    @ApiOperation(value = "Create one brand", response = Brand.class)
    @Override
    public ResponseEntity createOne(@RequestBody Object o) {
        return new ResponseEntity<>(service.createOne(o), HttpStatus.OK);
    }

    @GetMapping(value = "/brands/{id}")
    @ApiOperation(value = "Get one brand", response = Brand.class)
    @Override
    public ResponseEntity getOne(@PathVariable Long id) {
        Brand b = (id != null) ? service.findOne(id) : null;
        if (b == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @PutMapping(value = "/brands/{id}")
    @ApiOperation(value = "Update one brand", response = Brand.class)
    @Override
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody Object o) {
        Brand b = (id != null) ? service.updateOne(id, o) : null;
        if (b == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @DeleteMapping(value = "/brands/{id}")
    @ApiOperation(value = "Delete one brand", response = HttpStatus.class)
    @Override
    public ResponseEntity deleteOne(@PathVariable Long id) {
        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/brands/{id}/vehicles")
    @ApiOperation(value = "Get all vehicles from a brand", response = List.class)
    public ResponseEntity getBrandVehicles(@PathVariable Long id) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

}
