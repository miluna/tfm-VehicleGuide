package com.miluna.springvehicleguide.controllers;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.services.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Log
@RestController(value = "BrandController")
@Api(value = "Controller designed to add/retrieve/modify brands")
public class BrandController implements CrudController<Brand> {

    private final BrandService service;

    @Autowired
    private BrandController(@Qualifier(value = "BrandService") BrandService service){
        this.service = service;
    }

    @GetMapping(value = "/brands")
    @ApiOperation(value = "Get all brands", response = List.class)
    @Override
    public ResponseEntity<List<Brand>> getAll() {
        log.info("BrandController - GET ALL BRANDS");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/brands")
    @ApiOperation(value = "Create one brand", response = Brand.class)
    @Override
    public ResponseEntity<Brand> createOne(
        @ApiParam(value = "Brand object Model.", required = true) @RequestBody Brand o) {
        log.info("BrandController - CREATE ONE BRAND");
        return new ResponseEntity<>(service.createOne(o), HttpStatus.OK);
    }

    @GetMapping(value = "/brands/{id}")
    @ApiOperation(value = "Get one brand", response = Brand.class)
    @Override
    public ResponseEntity<Brand> getOne(
        @ApiParam(value = "Brand id", required = true) @PathVariable Long id) {
        log.info("BrandController - GET ONE BRAND");
        Brand b = (id != null) ? service.findOne(id) : null;
        if (b == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @PutMapping(value = "/brands/{id}")
    @ApiOperation(value = "Update one brand", response = Brand.class)
    @Override
    public ResponseEntity<Brand> updateOne(
        @ApiParam(value = "Brand id", required = true) @PathVariable Long id, 
        @ApiParam(value = "Brand object Model.", required = true) @RequestBody Brand o) {
        log.info("BrandController - UPDATE ONE BRAND");
        Brand b = (id != null) ? service.updateOne(id, o) : null;
        if (b == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @DeleteMapping(value = "/brands/{id}")
    @ApiOperation(value = "Delete one brand", response = HttpStatus.class)
    @Override
    public ResponseEntity<HttpStatus> deleteOne(
        @ApiParam(value = "Brand id", required = true) @PathVariable Long id) {
        log.info("BrandController - DELETE ONE BRAND");
        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/brands/{id}/vehicles")
    @ApiOperation(value = "Get all vehicles from a brand", response = List.class)
    public ResponseEntity<List<Vehicle>> getBrandVehicles(
        @ApiParam(value = "Brand id", required = true) @PathVariable Long id) {
        log.info("BrandController - GET ALL VEHICLES FROM A BRAND");
        List<Vehicle> results = service.getBrandVehicles(id);
        log.info("RESULTS: " + new Gson().toJson(results));
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
