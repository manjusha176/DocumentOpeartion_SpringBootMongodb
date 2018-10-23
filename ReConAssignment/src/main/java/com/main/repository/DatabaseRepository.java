package com.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.main.model.CollectionStructure;

@Repository
public interface DatabaseRepository extends MongoRepository<CollectionStructure, String>{

}
