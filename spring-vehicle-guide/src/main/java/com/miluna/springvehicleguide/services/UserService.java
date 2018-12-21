package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.entities.UserEntity;
import com.miluna.springvehicleguide.models.User;
import com.miluna.springvehicleguide.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "UserService")
public class UserService implements CrudService<User> {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    private final UserRepository repository;

    @Autowired
    private UserService(@Qualifier(value = "UserRepository") UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User createOne(User u) {
        try {
            UserEntity entity = new UserEntity(u, true);
            UserEntity result = repository.save(entity);
            u = new User(result);
        } catch (Exception e){
            LOG.error("Error creating UserEntity");
            LOG.error(e);
        }
        return u;
    }

    @Override
    public User findOne(Long id) {
        if (id == null) return null;
        User result = null;

        Optional<UserEntity> found = repository.findById(id);
        if (found.isPresent()){
            result = new User(found.get());
        }
        return result;
    }

    @Override
    public User updateOne(Long id, User u) {
        if (id == null) return null;

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
    public List<User> findAll() {
        List<User> result = repository.findAll()
                .stream()
                .map(e -> new User(e))
                .collect(Collectors.toList());
        return result;
    }

}
