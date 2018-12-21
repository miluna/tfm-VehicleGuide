package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController(value = "SearchController")
@Api(value = "Controller designed to search vehicles")
public class SearchController {

    private SearchService service;

    @Autowired
    private SearchController(@Qualifier("SearchService") SearchService searchService){
        this.service = searchService;
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Do search in the DB for vehicles matching parameters", response = Vehicle.class)
    public ResponseEntity<List<Vehicle>> doSearch(
        @ApiParam(value = "Name of the vehicle", required = false) @PathParam("name") String vehicleName,
        @ApiParam(value = "Type of the vehicle", required = false) @PathParam("type") String vehicleType,
        @ApiParam(value = "Brand of the vehicle", required = false) @PathParam("brand") String brand,
        @ApiParam(value = "Minimum price", required = false) @PathParam("minPrice") String minPrice,
        @ApiParam(value = "Maximum price", required = false) @PathParam("maxPrice") String maxPrice,
        @ApiParam(value = "Value to order by. Accepts 'price', 'name', 'year' and 'displacement'", required = false) @PathParam("orderValue") String orderValue,
        @ApiParam(value = "ASC or DESC", required = false) @PathParam("order") String order) {

        List<Vehicle> results = service.doSearch(vehicleName, vehicleType, brand, minPrice, maxPrice, orderValue, order);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
