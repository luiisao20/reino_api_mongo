package com.lbravo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lbravo.entity.Draw;

@Repository
public interface DrawRepository extends MongoRepository<Draw, String> {
  List<Draw> findAllByOrderByCreatedAtDesc();
}
