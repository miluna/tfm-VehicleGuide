package com.miluna.springvehicleguide.entities;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.models.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "brands")
public class BrandEntity implements UpdateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "country")
    private String country;

    public BrandEntity(){}

    public BrandEntity(Brand brand){
        setId(brand.getId());
        setName(brand.getName());
        setYear(brand.getYear());
        setCountry(brand.getCountry());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public void updateProperties(Object newEntity) {
        BrandEntity target = (BrandEntity) newEntity;

        this.name = target.getName();
        this.year = target.getYear();
        this.country = target.getCountry();
    }
}
