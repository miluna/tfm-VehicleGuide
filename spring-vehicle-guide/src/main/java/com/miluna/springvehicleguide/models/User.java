package com.miluna.springvehicleguide.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;

    private String email;

    private String password;

    private String password2;

    private String role;

    public User(){}

    public User(UserEntity entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.role = entity.getRole();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
