package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

}
