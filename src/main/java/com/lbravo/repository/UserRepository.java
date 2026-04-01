package com.lbravo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lbravo.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  public Optional<User> findUserByEmail(String email);

}
