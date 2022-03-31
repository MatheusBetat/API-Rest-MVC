package com.sprint03.repository;

import com.sprint03.model.entity.MusicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicRepository extends MongoRepository<MusicEntity, String> {
}
