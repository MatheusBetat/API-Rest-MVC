package com.sprint03.controller;

import com.sprint03.integration.model.mapper.request.MoviedbRequest;
import com.sprint03.integration.model.mapper.response.MoviedbResponse;
import com.sprint03.service.MoviedbService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/moviedb")
public class MoviedbController {

    private final MoviedbService moviedbService;

    @GetMapping()
    @ResponseStatus(OK)
    public MoviedbResponse getMovie(@RequestParam MoviedbRequest moviedbRequest) {
        return moviedbService.getMovie(moviedbRequest);

    }

    @GetMapping("/movies")
    @ResponseStatus(OK)
    public List<MoviedbResponse> getAllMovies() {
        return moviedbService.movies();

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMoviesByIDs(@RequestParam List<String> ids) {
        moviedbService.deleteMovies(ids);

    }
}