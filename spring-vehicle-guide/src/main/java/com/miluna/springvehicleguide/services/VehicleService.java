package com.miluna.springvehicleguide.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miluna.springvehicleguide.entities.VehicleEntity;
import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.repositories.VehicleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "VehicleService")
public class VehicleService implements CrudService {

    private static Logger LOG = Logger.getLogger(VehicleService.class);

    private final BrandService brandService;
    private final EngineService engineService;
    private final VehicleRepository repository;
    private static ObjectMapper mapper;

    @Autowired
    private VehicleService(@Qualifier(value = "VehicleRepository") VehicleRepository repository,
                           @Qualifier(value = "BrandService") BrandService brandService,
                           @Qualifier(value = "EngineService") EngineService engineService){
        this.repository = repository;
        this.brandService = brandService;
        this.engineService = engineService;
        mapper = new ObjectMapper();
    }

    @Override
    public Vehicle createOne(Object o) {
        Vehicle v = mapper.convertValue(o, Vehicle.class);
        // retrieve all info from mapped object
        Brand brand = this.getBrandFromVehicleJson(v);
        List<Engine> engines = this.getEnginesFromVehicleJson(v);

        if (brand != null && engines.size() > 0) {
            v.setBrand(brand);
            v.setEngines(engines);
            VehicleEntity entity = new VehicleEntity(v);
            VehicleEntity saved = repository.save(entity);
            v = new Vehicle(saved);
            return v;
        } else {
            return null;
        }
    }

    @Override
    public Vehicle findOne(Long id) {
        Optional<VehicleEntity> found = repository.findById(id);
        if (found.isPresent()) return new Vehicle(found.get());
        else return null;
    }

    @Override
    public Vehicle updateOne(Long id, Object o) {
        Vehicle v = mapper.convertValue(o, Vehicle.class);
        // retrieve all info from mapped object
        Brand brand = this.getBrandFromVehicleJson(v);
        List<Engine> engines = this.getEnginesFromVehicleJson(v);

        // set and convert to entity
        v.setBrand(brand);
        v.setEngines(engines);
        VehicleEntity vehicleEntity = new VehicleEntity(v);

        Optional<VehicleEntity> found = repository.findById(id);
        if (found.isPresent()){
            VehicleEntity entity = found.get();
            entity.updateProperties(vehicleEntity);
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

    private Brand getBrandFromVehicleJson(Vehicle v) {
        Brand brand = null;

        if (v.getBrand() != null) {
            brand = brandService.findOne(v.getBrand().getId());
        }
        return brand;
    }

    private List<Engine> getEnginesFromVehicleJson(Vehicle v) {
        List<Engine> filledEngines = new ArrayList<>();
        List<Engine> engines = v.getEngines();

        if (engines != null ) {
            engines.forEach(e -> {
                Engine engine = engineService.findOne(e.getId());
                if (engine != null) filledEngines.add(engine);
            });
        }
        return filledEngines;
    }
}
