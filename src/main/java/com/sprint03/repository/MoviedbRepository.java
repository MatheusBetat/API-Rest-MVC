package com.sprint03.repository;

import com.sprint03.integration.model.entity.MoviedbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoviedbRepository extends MongoRepository<MoviedbEntity, String> {

}
