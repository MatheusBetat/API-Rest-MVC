package com.sprint03.repository;

import com.sprint03.integration.model.entity.MoviedbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MoviedbRepository extends MongoRepository<MoviedbEntity, String>{

    List<MoviedbEntity> findByTitleContainingIgnoreCase(String title);

}
