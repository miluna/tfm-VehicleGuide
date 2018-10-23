package com.miluna.springvehicleguide.models;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String password;

    private Role role;

    public User(UserEntity entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = null;
        this.role = new Role(entity.getRole());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
