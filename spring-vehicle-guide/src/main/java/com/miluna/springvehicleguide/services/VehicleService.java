package com.miluna.springvehicleguide.services;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.VehicleEntity;
import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.models.Engine;
import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service(value = "VehicleService")
public class VehicleService implements CrudService<Vehicle> {

    private static Logger LOG = Logger.getLogger(VehicleService.class.getName());
    private final BrandService brandService;
    private final EngineService engineService;
    private final VehicleRepository repository;

    @Autowired
    private VehicleService(@Qualifier(value = "VehicleRepository") VehicleRepository repository,
                           @Qualifier(value = "BrandService") BrandService brandService,
                           @Qualifier(value = "EngineService") EngineService engineService){
        this.repository = repository;
        this.brandService = brandService;
        this.engineService = engineService;
    }

    @Override
    public Vehicle createOne(Vehicle v) {
        // retrieve all info from mapped object
        Brand brand = this.getBrandFromVehicleJson(v);
        List<Engine> engines = this.getEnginesFromVehicleJson(v);

        v.setBrand(brand);
        v.setEngines(engines);
        VehicleEntity entity = new VehicleEntity(v);
        VehicleEntity saved = repository.save(entity);
        v = new Vehicle(saved);
        return v;
    }

    @Override
    public Vehicle findOne(Long id) {
        if (id == null) return null;
        Optional<VehicleEntity> found = repository.findById(id);
        if (found.isPresent()) return new Vehicle(found.get());
        else return null;
    }

    @Override
    public Vehicle updateOne(Long id, Vehicle v) {
        if (id == null) return null;
        // retrieve all info from mapped object
        Brand brand = this.getBrandFromVehicleJson(v);
        List<Engine> engines = this.getEnginesFromVehicleJson(v);

        // set and convert to entity
        v.setBrand(brand);
        v.setEngines(engines);
        VehicleEntity vehicleEntity = new VehicleEntity(v);
        LOG.info("Saving: " + new Gson().toJson(vehicleEntity));

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
    public List<Vehicle> findAll() {
        List<Vehicle> result = repository.findAll()
                .stream()
                .map(e -> new Vehicle(e))
                .collect(Collectors.toList());

        return result;
    }

    public Brand getBrandFromVehicleJson(Vehicle v) {
        Brand brand = new Brand();

        if (v.getBrand() != null) {
            brand = brandService.findOne(v.getBrand().getId());
        }
        return brand;
    }

    public List<Engine> getEnginesFromVehicleJson(Vehicle v) {
        List<Engine> filledEngines = new ArrayList<>();
        List<Engine> engines = v.getEngines();

        if (engines != null ) {
            engines.forEach(e -> {
                Engine engine = engineService.findOne(e.getId());
                if (engine != null) filledEngines.add(engine);
            });
        }
        LOG.info("Engines found: " + new Gson().toJson(filledEngines));
        return filledEngines;
    }
}
