package com.miluna.springvehicleguide.entities;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    @OneToOne(targetEntity = RoleEntity.class, mappedBy = "roles")
    private RoleEntity role;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
