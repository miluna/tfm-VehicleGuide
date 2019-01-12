package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.models.mappers.VehicleSearchMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service(value = "SearchService")
public class SearchService {

    private static Logger LOG = Logger.getLogger(SearchService.class);
    private JdbcTemplate jdbc;

    @Autowired
    private SearchService(@Qualifier(value = "JdbcTemplate") JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    public List<Vehicle> doSearch(String vehicleName,
                         String vehicleType,
                         String brand,
                         String minPrice,
                         String maxPrice,
                         String orderValue,
                         String order){

        LOG.debug("Making custom search");
        StringBuilder sql = new StringBuilder();

        // Prepare sql
        String select = "SELECT DISTINCT A.id, A.main_image, A.name, A.year, A.base_price, A.segment, A.description, B.name AS brand_name ";
        String tables =
                "FROM vehicles A " +
                "LEFT JOIN brands B " +
                "ON A.brand_id=B.id " +
                "INNER JOIN vehicles_engines C  " +
                "ON A.id=C.vehicle_entity_id " +
                "INNER JOIN engines D " +
                "ON D.id=C.engines_id ";
        String clauses = getClauses(vehicleName, vehicleType, brand, minPrice, maxPrice, orderValue, order);

        // Append to final string
        sql.append(select);
        sql.append(tables);
        sql.append(clauses);

        LOG.debug("QUERY: ");
        LOG.debug(sql.toString());
        List<Vehicle> results = jdbc.query(sql.toString(), new VehicleSearchMapper());
        // LOG.debug("RESULTS: ");
        // LOG.debug(new Gson().toJson(results));

        return results;
    }

    private String getClauses(String vehicleName,
                                  String vehicleType,
                                  String brand,
                                  String minPrice,
                                  String maxPrice,
                                  String orderValue,
                                  String order){
        StringBuilder clauses = new StringBuilder();
        boolean moreThanOneParameter = false;

        if (isValidParameter(vehicleName)) {
            clauses.append(String.format("WHERE LOWER(A.name) = '%s' ", vehicleName.toLowerCase()));
            moreThanOneParameter = true;
        }

        if (isValidParameter(vehicleType)) {
            if (moreThanOneParameter){
                clauses.append(String.format("AND LOWER(D.type) = '%s' ", vehicleType.toLowerCase()));
            } else {
                clauses.append(String.format("WHERE LOWER(D.type) = '%s' ", vehicleType.toLowerCase()));
                moreThanOneParameter = true;
            }
        }

        if (isValidParameter(brand)) {
            if (moreThanOneParameter) {
                clauses.append(String.format("AND LOWER(B.name) = '%s' ", brand.toLowerCase()));
            } else {
                clauses.append(String.format("WHERE LOWER(B.name) = '%s' ", brand.toLowerCase()));
                moreThanOneParameter = true;
            }
        }

        if (isValidParameter(minPrice) && isValidParameter(maxPrice)){
            if (moreThanOneParameter) {
                clauses.append(String.format("AND A.base_price BETWEEN %s AND %s ", minPrice, maxPrice));
            } else {
                clauses.append(String.format("WHERE A.base_price BETWEEN %s AND %s ", minPrice, maxPrice));
            }
        }

        if (isValidParameter(orderValue) && isValidParameter(order)){
            String realOrderValue = this.getOrderValue(orderValue);
            boolean contains = Arrays.asList("ASC", "DESC").contains(order);

            if (isValidParameter(realOrderValue) && contains) {
                clauses.append(String.format("ORDER BY %s %s", this.getOrderValue(orderValue), order));
            }
        }

        return clauses.toString();
    }

    private boolean isValidParameter(String parameter){
        return parameter != null && !parameter.equals("");
    }

    private String getOrderValue(String orderValue){
        String value = null;
        if (orderValue.equals("price")) value = "A.base_price";
        if (orderValue.equals("name")) value = "A.name";
        if (orderValue.equals("year")) value = "A.year";
        if (orderValue.equals("brand")) value = "B.name";
        if (orderValue.equals("displacement")) value = "D.displacement";

        return value;
    }
}
