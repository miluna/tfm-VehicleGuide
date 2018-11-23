package com.miluna.springvehicleguide.controllers;

import com.miluna.springvehicleguide.services.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController(value = "SearchController")
public class SearchController {

    private static Logger LOG = Logger.getLogger(SearchController.class);

    private SearchService service;

    @Autowired
    private SearchController(@Qualifier("SearchService") SearchService searchService){
        this.service = searchService;
    }

    @GetMapping(value = "/search")
    public ResponseEntity doSearch(@PathParam("name") String vehicleName,
                                   @PathParam("type") String vehicleType,
                                   @PathParam("brand") String brand,
                                   @PathParam("minPrice") String minPrice,
                                   @PathParam("maxPrice") String maxPrice,
                                   @PathParam("orderValue") String orderValue,
                                   @PathParam("order") String order) {

        List<?> results = service.doSearch(vehicleName, vehicleType, brand, minPrice, maxPrice, orderValue, order);

        if (results.size() != 0) return new ResponseEntity<>(results, HttpStatus.OK);
        else return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
    }
}
