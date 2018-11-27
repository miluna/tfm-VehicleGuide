package com.miluna.springvehicleguide.models.mappers;

import com.miluna.springvehicleguide.models.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleMapper implements RowMapper<Vehicle> {
    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle v = new Vehicle();

        v.setId(            rs.getLong("id"));
        v.setName(          rs.getString("name"));
        v.setDescription(   rs.getString("description"));
        v.setYear(          rs.getInt("year"));
        v.setWeight(        rs.getInt("weight"));
        v.setDoors(         rs.getInt("doors"));
        v.setSegment(       rs.getString("segment").charAt(0));
        v.setBasePrice(     rs.getLong("base_price"));

        return v;
    }
}
