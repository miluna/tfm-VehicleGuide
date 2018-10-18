package com.miluna.springvehicleguide.entities;

import com.miluna.springvehicleguide.models.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Date year;

    @Column(name = "country")
    private String country;

    public BrandEntity(Brand brand){
        setId(brand.getId());
        setName(brand.getName());
        setYear(brand.getYear());
        setCountry(brand.getCountry());
    }

}
