package com.irb.plantas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.irb.plantas.model.Category;

public interface CategoryRepository extends MongoRepository<Category, UUID> {

    Optional<Category> findByName(String name);

    List<Category> findAll();

    void deleteById(UUID id);
}
