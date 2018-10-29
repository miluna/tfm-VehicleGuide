package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.entities.VehicleEntity;
import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.repositories.VehicleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "VehicleService")
public class VehicleService implements DefaultService {

    private static Logger LOG = Logger.getLogger(VehicleService.class);

    private final VehicleRepository repository;

    @Autowired
    private VehicleService(@Qualifier(value = "VehicleRepository") VehicleRepository repository){
        this.repository = repository;
    }

    @Override
    public Vehicle createOne(Object o) {
        Vehicle v = (Vehicle) o;
        VehicleEntity entity = new VehicleEntity(v);

        VehicleEntity saved = repository.save(entity);
        v = new Vehicle(saved);
        return v;
    }

    @Override
    public Vehicle findOne(Long id) {
        Optional<VehicleEntity> found = repository.findById(id);
        if (found.isPresent()) return new Vehicle(found.get());
        else return null;
    }

    @Override
    public Vehicle updateOne(Long id, Object o) {
        VehicleEntity v = new VehicleEntity((Vehicle) o);

        Optional<VehicleEntity> found = repository.findById(id);
        if (found.isPresent()){
            VehicleEntity entity = found.get();
            entity.updateProperties(v);
            VehicleEntity saved = repository.save(entity);
            return new Vehicle(saved);
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
        List<Vehicle> result = repository.findAll()
                .stream()
                .map(e -> new Vehicle(e))
                .collect(Collectors.toList());

        return result;
    }
}
