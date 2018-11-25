package com.miluna.springvehicleguide.models;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.BrandEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Brand {

    private Long id;

    private String name;

    private Integer year;

    private String country;

    public Brand(){}

    public Brand(BrandEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.year = entity.getYear();
        this.country = entity.getCountry();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
