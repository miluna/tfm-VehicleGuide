package com.miluna.springvehicleguide.security;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {

    private String Authorization;

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
