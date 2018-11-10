package com.miluna.springvehicleguide.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController(value = "SearchController")
public class SearchController {

    private static Logger LOG = Logger.getLogger(SearchController.class);

    @GetMapping(value = "/search")
    public ResponseEntity doSearch(@PathParam("name") String vehicleName,
                                   @PathParam("type") String vehicleType,
                                   @PathParam("brand") String brand,
                                   @PathParam("minPrice") String minPrice,
                                   @PathParam("maxPrice") String maxPrice,
                                   @PathParam("orderValue") String orderValue,
                                   @PathParam("order") String order) {

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
