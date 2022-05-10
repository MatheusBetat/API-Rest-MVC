package com.sprint03.integration.model.mapper.request;

import com.sprint03.integration.model.entity.MoviedbEntity;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class MoviedbRequestMapper {


    public static MoviedbEntity toMoviedbRequest(MoviedbRequest moviedbRequest) {
        return Optional.ofNullable(moviedbRequest)
                .map(moviedbService -> MoviedbEntity.builder()
                        .title(moviedbService.getTitle())
                        .build())
                .orElse(null);

    }
}
