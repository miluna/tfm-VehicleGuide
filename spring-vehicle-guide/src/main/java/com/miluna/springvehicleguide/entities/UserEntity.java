package com.miluna.springvehicleguide.entities;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UpdateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public UserEntity(){}

    public UserEntity(User u) throws Exception{
        if (!u.getPassword().equals(u.getPassword2())) {
            throw new Exception("Password fields are not equal");
        }else {
            this.email = u.getEmail();
            this.password = new BCryptPasswordEncoder().encode(u.getPassword());
            this.role = u.getRole();
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public void updateProperties(Object newEntity) {
        UserEntity target = (UserEntity) newEntity;

        this.email = target.getEmail();
        this.password = new BCryptPasswordEncoder().encode(target.getPassword());
        this.role = target.getRole();
    }
}
