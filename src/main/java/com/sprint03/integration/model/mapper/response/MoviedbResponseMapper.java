package com.sprint03.integration.model.mapper.response;

import com.sprint03.integration.model.entity.MoviedbEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MoviedbResponseMapper {

    public static MoviedbEntity mapToMoviedbEntity(MoviedbResponse moviedbResponse) {
        return MoviedbEntity.builder()
                .title(moviedbResponse.getTitle())
                .genre(moviedbResponse.getGenre())
                .description(moviedbResponse.getPlot())
                .year(moviedbResponse.getYear())
                .type(moviedbResponse.getType())
                .poster(moviedbResponse.getPoster())
                .build();

    }
    public static MoviedbResponse toResponse(MoviedbEntity moviedbEntity){
        return MoviedbResponse.builder()
                .id(moviedbEntity.getId())
                .title(moviedbEntity.getTitle())
                .genre(moviedbEntity.getGenre())
                .plot(moviedbEntity.getDescription())
                .year(moviedbEntity.getYear())
                .type(moviedbEntity.getType())
                .poster(moviedbEntity.getPoster())
                .build();
    }
}
