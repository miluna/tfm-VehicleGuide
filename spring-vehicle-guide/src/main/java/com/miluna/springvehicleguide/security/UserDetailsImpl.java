package com.miluna.springvehicleguide.security;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.UserEntity;
import com.miluna.springvehicleguide.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashSet;

@Service(value = "UserDetails")
public class UserDetailsImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    private UserDetailsImpl(@Qualifier(value = "UserRepository") UserRepository userRepository){
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity entity  = repository.findByEmail(email);

        if (entity == null) throw new UsernameNotFoundException(email);

        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add( new SimpleGrantedAuthority(entity.getRole()));

        return new User(entity.getEmail(), entity.getPassword(), authorities);
    }
}
