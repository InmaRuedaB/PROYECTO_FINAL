package com.irb.plantas.repository;

import com.irb.plantas.model.Category;
import com.irb.plantas.model.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlantRepository extends MongoRepository<Plant, UUID> {

    Optional<Plant> findByName(String name);

    List<Plant> findAll();

    Optional<Plant> findById(UUID id);

    List<Plant> findByCategory(Category category);

    void deleteById(UUID id);


}
