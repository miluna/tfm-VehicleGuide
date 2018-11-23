package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.User;
import com.miluna.springvehicleguide.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "UserController")
public class UserController implements CrudController {

    private static Logger LOG = Logger.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    private UserController(@Qualifier(value = "UserService") UserService service){
        this.service = service;
    }

    @GetMapping(value = "/users")
    @Override
    public ResponseEntity getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    @Override
    public ResponseEntity createOne(@RequestBody Object o) {
        Object result = service.createOne(o);
        if (result instanceof Error) return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    @Override
    public ResponseEntity getOne(@PathVariable Long id) {
        User u = service.findOne(id);
        if (u == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PutMapping(value = "/users/{id}")
    @Override
    public ResponseEntity updateOne(@PathVariable Long id, @RequestBody Object o) {
        User result = service.updateOne(id, o);
        if (result == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    @Override
    public ResponseEntity deleteOne(@PathVariable Long id) {
        boolean result = service.deleteOne(id);
        if (result)return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // post mapping to /login is implemented by spring security
    // @PostMapping(value = "/login")

}
