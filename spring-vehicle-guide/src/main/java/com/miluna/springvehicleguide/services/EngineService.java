package com.miluna.springvehicleguide.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miluna.springvehicleguide.entities.EngineEntity;
import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.repositories.EngineRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "EngineService")
public class EngineService implements DefaultService {

    private static Logger LOG = Logger.getLogger(EngineService.class);

    private final EngineRepository repository;
    private static ObjectMapper mapper;

    @Autowired
    private EngineService(@Qualifier(value = "EngineRepository") EngineRepository repository){
        this.repository = repository;
        mapper = new ObjectMapper();
    }

    @Override
    public Engine createOne(Object o) {
        Engine e = mapper.convertValue(o, Engine.class);
        EngineEntity entity = new EngineEntity(e);
        EngineEntity saved = repository.save(entity);
        return new Engine(saved);
    }

    @Override
    public Engine findOne(Long id) {
        Optional<EngineEntity> found = repository.findById(id);
        if (found.isPresent()){
            Engine e = new Engine(found.get());
            return e;
        }
        return null;
    }

    @Override
    public Engine updateOne(Long id, Object o) {
        Engine e = mapper.convertValue(o, Engine.class);
        EngineEntity target = new EngineEntity(e);

        Optional<EngineEntity> found = repository.findById(id);
        if (found.isPresent()){
            EngineEntity entity = found.get();
            entity.updateProperties(target);

            EngineEntity saved = repository.save(entity);
            return new Engine(saved);
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
        List<Engine> result = repository.findAll()
                .stream()
                .map(e -> new Engine(e))
                .collect(Collectors.toList());
        return result;
    }
}
