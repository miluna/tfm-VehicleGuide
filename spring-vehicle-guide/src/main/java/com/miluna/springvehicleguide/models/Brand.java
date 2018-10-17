package com.miluna.springvehicleguide.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    private String name;

    private Date year;

    private String country;
}
