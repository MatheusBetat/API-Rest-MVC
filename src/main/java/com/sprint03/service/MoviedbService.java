package com.sprint03.service;

import com.sprint03.integration.model.mapper.request.MoviedbRequest;
import com.sprint03.integration.model.mapper.response.MoviedbResponse;
import com.sprint03.integration.model.mapper.response.MoviedbResponseMapper;
import com.sprint03.integration.resttemplate.MoviedbClient;
import com.sprint03.repository.MoviedbRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sprint03.integration.model.mapper.response.MoviedbResponseMapper.mapToMoviedbEntity;
import static com.sprint03.integration.model.mapper.response.MoviedbResponseMapper.toResponse;

@AllArgsConstructor
@Service
public class MoviedbService {

    private final MoviedbRepository moviedbRepository;

    private final MoviedbClient moviedbClient;

    public MoviedbResponse getMovie(MoviedbRequest moviedbRequest) {
        return toResponse(moviedbRepository
                .save(mapToMoviedbEntity(moviedbClient
                        .call(moviedbRequest))));

    }
    public List<MoviedbResponse> movies(Pageable pageable){
        return moviedbRepository.findAll(Pageable.ofSize(pageable.getPageSize())).stream()
                .map(MoviedbResponseMapper::toResponse)
                .toList();
    }

    public List<MoviedbResponse> getMovieByTitle(String title){
        return moviedbRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(MoviedbResponseMapper::toResponse).toList();

    }

    public void deleteMovies(List<String> ids) {
         moviedbRepository.deleteAllById(ids);

    }
}
