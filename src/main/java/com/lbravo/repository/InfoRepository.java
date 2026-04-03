package com.lbravo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lbravo.entity.Info;

@Repository
public interface InfoRepository extends MongoRepository<Info, String> {
  Page<Info> findAllByOrderByCreatedAtDesc(Pageable pageable);

  @Query("{ '$or': [ { 'name': { '$regex': ?0, '$options': 'i' } }, { 'lastName': { '$regex': ?0, '$options': 'i' } } ] }")
  Page<Info> findByFullNameContaining(String name, Pageable pageable);
}
