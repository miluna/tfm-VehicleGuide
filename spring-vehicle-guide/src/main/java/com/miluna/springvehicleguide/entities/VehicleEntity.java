package com.miluna.springvehicleguide.entities;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Entity
@Table(name = "vehicles")
public class VehicleEntity implements UpdateableEntity<VehicleEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = BrandEntity.class)
    private BrandEntity brand;

    @ManyToMany(targetEntity = EngineEntity.class)
    private List<EngineEntity> engines;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private Integer year;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "doors")
    private Integer doors;

    @Column(name = "segment")
    private Character segment;

    @Column(name = "basePrice")
    private Long basePrice;

    public VehicleEntity(){}

    public VehicleEntity(Vehicle dto){
        setId(dto.getId());
        setBrand(new BrandEntity(dto.getBrand()));
        setEngines(dto.getEngines()
                .stream()
                .map(e -> new EngineEntity(e))
                .collect(Collectors.toList()));
        setName(dto.getName());
        setDescription(dto.getDescription());
        setYear(dto.getYear());
        setWeight(dto.getWeight());
        setDoors(dto.getDoors());
        setSegment(dto.getSegment());
        setBasePrice(dto.getBasePrice());
    }

    // VALIDATION

    public void setWeight(Integer weight){
        if (weight == null || weight.longValue() < 0) this.weight = 0;
        else this.weight = weight;
    }

    public void setDoors(Integer doors){
        if (doors == null || doors < 0) this.doors = 0;
        else this.doors = doors;
    }

    public void setBasePrice(Long basePrice){
        if (basePrice == null || basePrice < 0) this.basePrice = 0L;
        else this.basePrice = basePrice;
    }

    @Override
    public void updateProperties(VehicleEntity target) {

        this.name = target.getName();
        this.description = target.getDescription();
        this.brand = target.getBrand();
        this.segment = target.getSegment();
        this.setYear(target.getYear());
        this.setWeight(target.getWeight());
        this.setDoors(target.getDoors());
        this.setBasePrice(target.getBasePrice());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
