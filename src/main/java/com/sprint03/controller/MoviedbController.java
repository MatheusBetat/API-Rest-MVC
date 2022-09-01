package com.sprint03.controller;

import com.sprint03.integration.model.mapper.request.MoviedbRequest;
import com.sprint03.integration.model.mapper.response.MoviedbResponse;
import com.sprint03.service.MoviedbService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/moviedb")
@CrossOrigin()
public class MoviedbController {

    private final MoviedbService moviedbService;

    @GetMapping()
    @ResponseStatus(OK)
    public MoviedbResponse getMovie(@RequestParam MoviedbRequest moviedbRequest) {
        return moviedbService.getMovie(moviedbRequest);

    }

    @GetMapping("/movies")
    @ResponseStatus(OK)
    public List<MoviedbResponse> getAllMovies(Pageable pageable) {
        return moviedbService.movies(pageable);

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMoviesByIDs(@RequestParam List<String> ids) {
        moviedbService.deleteMovies(ids);

    }
    @GetMapping("/{title}")
    public List<MoviedbResponse> getMovieByTitle(@PathVariable String title){
        return moviedbService.getMovieByTitle(title);
    }
}