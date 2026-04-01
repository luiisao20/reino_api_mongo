package com.lbravo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lbravo.entity.Info;

@Repository
public interface InfoRepository extends MongoRepository<Info, String> {
  List<Info> findAllByOrderByCreatedAtDesc();

  @Query("{ '$or': [ { 'name': { '$regex': ?0, '$options': 'i' } }, { 'lastName': { '$regex': ?0, '$options': 'i' } } ] }")
  List<Info> findByFullNameContaining(String name);
}
