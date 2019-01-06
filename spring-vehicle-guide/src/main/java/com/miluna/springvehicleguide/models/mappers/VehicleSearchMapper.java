package com.miluna.springvehicleguide.models.mappers;

import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.models.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleSearchMapper implements RowMapper<Vehicle> {
    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle v = new Vehicle();
        Brand b = new Brand();

        v.setId(            rs.getLong("id"));
        v.setName(          rs.getString("name"));
        v.setYear(          rs.getInt("year"));
        v.setDescription(   rs.getString("description"));
        v.setSegment(       rs.getString("segment").charAt(0));
        v.setBasePrice(     rs.getLong("base_price"));
        v.setMainImage(     rs.getString("main_image"));
        b.setName(          rs.getString("brand_name"));
        v.setBrand(b);

        return v;
    }
}
