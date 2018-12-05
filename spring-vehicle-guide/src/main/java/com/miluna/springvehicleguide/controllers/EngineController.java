package com.miluna.springvehicleguide.controllers;

import java.util.List;
import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController(value = "EngineController")
@Api(value = "Controller designed to add/retrieve/modify engines")
public class EngineController implements CrudController<Engine> {

    private final EngineService service;

    @Autowired
    private EngineController(@Qualifier(value = "EngineService") EngineService service){
        this.service = service;
    }

    @GetMapping(value = "/engines")
    @ApiOperation(value = "Get all engines", response = List.class)
    @Override
    public ResponseEntity<List<Engine>> getAll() {
        
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/engines")
    @ApiOperation(value = "Create one engine", response = Engine.class)
    @Override
    public ResponseEntity<Engine> createOne(
        @ApiParam(value = "Engine object model", required = true) @RequestBody Engine o) {
        
        return new ResponseEntity<>(service.createOne(o), HttpStatus.OK);
    }

    @GetMapping(value = "/engines/{id}")
    @ApiOperation(value = "Get one engine", response = Engine.class)
    @Override
    public ResponseEntity<Engine> getOne(
        @ApiParam(value = "Engine id", required = true) @PathVariable Long id) {
        
        Engine e = (id != null) ? service.findOne(id) : null;
        if (e == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PutMapping(value = "/engines/{id}")
    @ApiOperation(value = "Update one engine", response = Engine.class)
    @Override
    public ResponseEntity<Engine> updateOne(
        @ApiParam(value = "Engine id", required = true) @PathVariable Long id, 
        @ApiParam(value = "Engine object model", required = true) @RequestBody Engine o) {
        
        Engine e = (id != null) ? service.updateOne(id, o) : null;
        if (e == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @DeleteMapping(value = "/engines/{id}")
    @ApiOperation(value = "Delete one engine", response = HttpStatus.class)
    @Override
    public ResponseEntity<HttpStatus> deleteOne(
        @ApiParam(value = "Engine id", required = true) @PathVariable Long id) {
        
        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
