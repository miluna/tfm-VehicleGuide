package com.miluna.springvehicleguide.models;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.RoleEntity;
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

    public Role(RoleEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.canCreate = entity.getCanCreate();
        this.canUpdate = entity.getCanUpdate();
        this.canDelete = entity.getCanDelete();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
