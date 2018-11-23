package com.miluna.springvehicleguide.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miluna.springvehicleguide.entities.UserEntity;
import com.miluna.springvehicleguide.models.User;
import com.miluna.springvehicleguide.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "UserService")
public class UserService implements CrudService {

    private static Logger LOG = Logger.getLogger(UserService.class);

    private final UserRepository repository;
    private static ObjectMapper mapper;

    @Autowired
    private UserService(@Qualifier(value = "UserRepository") UserRepository repository){
        this.repository = repository;
        mapper = new ObjectMapper();
    }

    @Override
    public Object createOne(Object o) {
        User u = mapper.convertValue(o, User.class);
        try {
            UserEntity entity = new UserEntity(u, true);
            UserEntity result = repository.save(entity);
            return new User(result);
        } catch (Exception e){
            LOG.error("Error creating UserEntity");
            LOG.error(e);
            return new Error(e.getMessage());
        }
    }

    @Override
    public User findOne(Long id) {
        User result = null;

        Optional<UserEntity> found = repository.findById(id);
        if (found.isPresent()){
            result = new User(found.get());
        }
        return result;
    }

    @Override
    public User updateOne(Long id, Object o) {
        User u = mapper.convertValue(o, User.class);

        Optional<UserEntity> found = repository.findById(id);
        if (found.isPresent()){
            UserEntity entity = found.get();
            entity.updateProperties(u);

            UserEntity saved = repository.save(entity);
            return new User(saved);
        }
        return null;
    }

    @Override
    public Boolean deleteOne(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List findAll() {
        List<User> result = repository.findAll()
                .stream()
                .map(e -> new User(e))
                .collect(Collectors.toList());
        return result;
    }

    public User login(User logUser){
        User result = null;

        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            UserEntity entity = new UserEntity(logUser, false);
            UserEntity found = repository.findByEmail(entity.getEmail());

            if (found != null) {
                boolean passwordMatches = encoder.matches(entity.getPassword(), found.getPassword());
                if (passwordMatches) result = new User(found);
            }
        } catch (Exception e) {
            LOG.error(e);
        }
        return result;
    }

    public boolean isUserAdmin(Long id) {
        UserEntity found = repository.findAdminById(id);

        if (found == null) return false;
        else return true;
    }
}
