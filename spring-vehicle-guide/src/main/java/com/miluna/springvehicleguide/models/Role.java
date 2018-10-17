package com.miluna.springvehicleguide.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long id;

    private String name;

    private Boolean canCreate;

    private Boolean canUpdate;

    private Boolean canDelete;

}
