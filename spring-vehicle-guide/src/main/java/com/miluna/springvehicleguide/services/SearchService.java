package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.entities.VehicleEntity;
import com.miluna.springvehicleguide.models.Vehicle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "SearchService")
public class SearchService {

    private static Logger LOG = Logger.getLogger(SearchService.class);
    private DataSource dataSource;

    @Autowired
    private SearchService(@Qualifier(value = "DataSource") DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List doSearch(String vehicleName,
                         String vehicleType,
                         String brand,
                         String minPrice,
                         String maxPrice,
                         String orderValue,
                         String order){

        StringBuilder sql = new StringBuilder(
                "SELECT A.*, B.*, C.*, COUNT(C.id) AS engineCount " +
                "FROM vehicles A " +
                "INNER JOIN brands B " +
                "ON A.brandId=B.id " +
                "INNER JOIN engines C " +
                "ON A.engineId=C.id ");

        String clauses = getClauses(vehicleName, vehicleType, brand, minPrice, maxPrice, orderValue, order);
        sql.append(clauses);
        List<Vehicle> results = this.getResults(sql.toString());

        if (results.size() > 0) return results;
        else return new ArrayList();
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
                clauses.append(String.format("AND LOWER(C.type) = '%s' ", vehicleType.toLowerCase()));
            } else {
                clauses.append(String.format("WHERE LOWER(C.type) = '%s' ", vehicleType.toLowerCase()));
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
                clauses.append(String.format("AND A.basePrice BETWEEN %s AND %s ", minPrice, maxPrice));
            } else {
                clauses.append(String.format("WHERE A.basePrice BETWEEN %s AND %s ", minPrice, maxPrice));
            }
        }

        if (isValidParameter(orderValue) && isValidParameter(order)){
            clauses.append(String.format("ORDER BY %s %s", this.getOrderValue(orderValue), order));
        }

        return clauses.toString();
    }

    private boolean isValidParameter(String parameter){
        return parameter != null && !parameter.equals("");
    }

    private String getOrderValue(String orderValue){
        String value = null;
        if (orderValue.equals("price")) value = "A.basePrice";
        if (orderValue.equals("name")) value = "A.name";
        if (orderValue.equals("year")) value = "A.year";
        if (orderValue.equals("brand")) value = "B.name";
        if (orderValue.equals("displacement")) value = "C.displacement";

        return value;
    }

    private List<Vehicle> getResults(String sql){
        List<Vehicle> results = new ArrayList<>();

        try {
            Connection conn = this.dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                VehicleEntity entity = new VehicleEntity();
                results.add(new Vehicle(entity));
            }

        } catch (SQLException e) {
            LOG.error(e);
        }

        return results;
    }
}
