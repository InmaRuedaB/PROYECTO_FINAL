package com.irb.plantas.repository;

import com.irb.plantas.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, UUID> {

  Optional<User> findByPhone(String phone);

  List<User> findAll();

  void deleteById(UUID id);

}
