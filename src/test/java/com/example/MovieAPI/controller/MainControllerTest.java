package com.example.MovieAPI.controller;

import com.example.MovieAPI.entity.Movie;
import com.example.MovieAPI.repo.MovieRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    @Mock
    private MovieRepo movieRepo;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private MainController mainController;

    @Test
    void addNewMovieFailedTest() throws Exception {
        Movie movie = new Movie();

        movie.setYearRelease(12345);

        String expected = "YEAR IS NOT CORRECT!";
        assertEquals(expected, mainController.addNewMovie(movie));
        verify(movieRepo, never()).save(movie);
    }

    @Test
    void addNewMovieTest() throws  Exception{
        Movie movie = new Movie();

        movie.setYearRelease(2023);
        String expectedJson = objectMapper.writeValueAsString(movie);

        when(movieRepo.save(movie)).thenReturn(movie);
        String result = mainController.addNewMovie(movie);

        assertEquals(expectedJson, result);
        verify(movieRepo).save(movie);
    }
}