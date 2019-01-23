package com.miluna.springvehicleguide.services;

import com.miluna.springvehicleguide.entities.BrandEntity;
import com.miluna.springvehicleguide.entities.VehicleEntity;
import com.miluna.springvehicleguide.models.Brand;
import com.miluna.springvehicleguide.models.Vehicle;
import com.miluna.springvehicleguide.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "BrandService")
public class BrandService implements CrudService<Brand> {

    private BrandRepository repository;
    private JdbcTemplate template;

    @Autowired
    private BrandService(@Qualifier(value = "BrandRepository") BrandRepository repository,
                         @Qualifier("JdbcTemplate") JdbcTemplate template){
        this.repository = repository;
        this.template = template;
    }

    @Override
    public Brand createOne(Brand b) {
        BrandEntity entity = new BrandEntity(b);

        BrandEntity saved = repository.save(entity);
        return new Brand(saved);
    }

    @Override
    public Brand findOne(Long id) {
        if (id == null) return null;

        Optional<BrandEntity> found = repository.findById(id);
        if (found.isPresent()){
            BrandEntity entity = found.get();
            return new Brand(entity);
        }

        return null;
    }

    @Override
    public Brand updateOne(Long id, Brand brand) {
        if (id == null) return null;
        BrandEntity target = new BrandEntity(brand);

        Optional<BrandEntity> found = repository.findById(id);
        if (found.isPresent()){
            BrandEntity entity = found.get();
            entity.updateProperties(target);

            BrandEntity saved = repository.save(entity);
            return new Brand(saved);
        }
        return null;
    }

    @Override
    public Boolean deleteOne(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> result = repository.findAll()
                .stream()
                .map(e -> new Brand(e))
                .collect(Collectors.toList());
        return result;
    }

    public List<Vehicle> getBrandVehicles(Long id) {
        String sql =    "SELECT A.id, A.name " +
                        "FROM vehicles A " +
                        "INNER JOIN brands B " +
                        "ON A.brand_id=B.id " +
                        "WHERE B.id=?";

        RowMapper<VehicleEntity> rowmapper = ((resultSet, i) -> {
            VehicleEntity v = new VehicleEntity();
            v.setId(resultSet.getLong("id"));
            v.setName(resultSet.getString("name"));
            return v;
        });

        return template.query(sql, new Object[]{id}, rowmapper)
                .stream()
                .map(e -> {
                    Vehicle v = new Vehicle();
                    v.setId(e.getId());
                    v.setName(e.getName());
                    return v;
                })
                .collect(Collectors.toList());
    }
}
