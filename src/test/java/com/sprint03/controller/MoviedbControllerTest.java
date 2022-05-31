package com.sprint03.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sprint03.integration.model.mapper.request.MoviedbRequest;
import com.sprint03.integration.model.mapper.response.MoviedbResponse;
import com.sprint03.service.MoviedbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MoviedbController.class)
class MoviedbControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoviedbService moviedbService;

    @Test
    void shouldGetMovie() throws Exception {

        var movie = MoviedbResponse.builder()
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if " +
                        "they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build();

        var title = MoviedbRequest.builder()
                .title("The Avengers")
                .build();

        when(moviedbService.getMovie(title))
                .thenReturn(movie);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse= mapper.writeValueAsString(movie);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/v1/moviedb?moviedbRequest=The Avengers"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));

        //Assertions.assertEquals(title.getTitle(), movie.getTitle());

    }

    @Test
    void shouldGetAllMovies() throws Exception{

        var movies = List.of(MoviedbResponse.builder()
                .title("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .plot("Earth's mightiest heroes must come together and learn to fight as a team if " +
                        "they are going to stop the mischievous Loki and his alien army from enslaving humanity.")
                .type("movie")
                .build());

        when(moviedbService.movies())
                .thenReturn(movies);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse= mapper.writeValueAsString(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/v1/moviedb/movies"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));

    }

    @Test
    void shouldDeleteMoviesByIds() throws Exception{

        List<String> ids = Arrays.asList("11", "10");

        doNothing().when(this.moviedbService).deleteMovies(ids);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("http://localhost:8080/v1/moviedb/delete?ids=11,10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }
}