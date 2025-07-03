package com.example.MovieAPI.controller;

import com.example.MovieAPI.entity.Movie;
import com.example.MovieAPI.repo.MovieRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final MovieRepo movieRepo;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @GetMapping("/all")
    public String getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        return objectMapper.writeValueAsString(movies);
    }

    @PostMapping("/add")
    public void addNewMovie(@RequestBody Movie movie) {
        log.info("New row: " + movieRepo.save(movie));
    }

    @SneakyThrows
    @GetMapping("/byTitle")
    public String findByTitle(@RequestParam String name) {
        return objectMapper.writeValueAsString(movieRepo.findByTitle(name));
    }

    @SneakyThrows
    @GetMapping("/byYear")
    public String findByYear(@RequestParam int year) {
        return objectMapper.writeValueAsString(movieRepo.findByYear(year));
    }

    @SneakyThrows
    @GetMapping("/byGenre")
    public String findByGenre(@RequestParam String genre) {
        return objectMapper.writeValueAsString(movieRepo.findByGenre(genre));
    }

    @SneakyThrows
    @GetMapping("/byCountry")
    public String findByCountry(@RequestParam String country) {
        return objectMapper.writeValueAsString(movieRepo.findByCountry(country));
    }

    @DeleteMapping("/delete")
    public void deleteMovie(@RequestParam String name, int year){
        Movie movie = movieRepo.findToDelete(name, year);
        log.info("Delete row: " + movie);
        movieRepo.delete(movie);
    }
}
