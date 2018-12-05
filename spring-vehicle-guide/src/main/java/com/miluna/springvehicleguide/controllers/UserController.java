package com.miluna.springvehicleguide.controllers;

import java.util.List;
import com.miluna.springvehicleguide.models.User;
import com.miluna.springvehicleguide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController(value = "UserController")
@Api(value = "Controller designed to add/retrieve/modify users")
public class UserController implements CrudController<User> {

    private final UserService service;

    @Autowired
    private UserController(@Qualifier(value = "UserService") UserService service){
        this.service = service;
    }

    @GetMapping(value = "/users")
    @ApiOperation(value = "Get all users", response = List.class)
    @Override
    public ResponseEntity<List<User>> getAll() {

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    @ApiOperation(value = "Create one user", response = User.class)
    @Override
    public ResponseEntity<User> createOne(
        @ApiParam(value = "User object model", required = true) @RequestBody User o) {

        if (o.getPassword().equals(o.getPassword2())) {
            User result = service.createOne(o);
            if (result != null) return new ResponseEntity<>(result, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        
    }

    @GetMapping(value = "/users/{id}")
    @ApiOperation(value = "Get one user", response = User.class)
    @Override
    public ResponseEntity<User> getOne(
        @ApiParam(value = "User id", required = true) @PathVariable Long id) {

        User u = (id != null) ? service.findOne(id) : null;
        if (u == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PutMapping(value = "/users/{id}")
    @ApiOperation(value = "Update one user", response = User.class)
    @Override
    public ResponseEntity<User> updateOne(
        @ApiParam(value = "User id", required = true) @PathVariable Long id, 
        @ApiParam(value = "User object model", required = true) @RequestBody User o) {

        User result = (id != null) ? service.updateOne(id, o) : null;
        if (result == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    @ApiOperation(value = "Delete one user", response = HttpStatus.class)
    @Override
    public ResponseEntity<HttpStatus> deleteOne(
        @ApiParam(value = "User id", required = true) @PathVariable Long id) {

        boolean result = service.deleteOne(id);
        if (result) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
