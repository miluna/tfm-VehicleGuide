package com.miluna.springvehicleguide.entities;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "canCreate")
    private Boolean canCreate;

    @Column(name = "canUpdate")
    private Boolean canUpdate;

    @Column(name = "canDelete")
    private Boolean canDelete;

    public RoleEntity(Role role){
        setId(role.getId());
        setName(role.getName());
        setCanCreate(role.getCanCreate());
        setCanUpdate(role.getCanUpdate());
        setCanDelete(role.getCanDelete());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
