package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "UserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    @Query(value = "SELECT a FROM users a WHERE a.id = ?0 and a.role = 'ADMIN'", nativeQuery = true)
    UserEntity findAdminById(Long id);

}
