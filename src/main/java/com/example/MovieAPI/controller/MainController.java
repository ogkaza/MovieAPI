package com.example.MovieAPI.controller;

import com.example.MovieAPI.entity.Movie;
import com.example.MovieAPI.repo.MovieRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final MovieRepo movieRepo;
    private final ObjectMapper objectMapper;

    @Operation(summary = "вывод всех фильмов")
    @SneakyThrows
    @GetMapping("/all")
    public String getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        return objectMapper.writeValueAsString(movies);
    }

    @Operation(summary = "добавление фильма")
    @PostMapping("/add")
    public void addNewMovie(@RequestBody Movie movie) {
        log.info("New row: " + movieRepo.save(movie));
    }

    @Operation(summary = "поиск фильмов по названию")
    @SneakyThrows
    @GetMapping("/byTitle")
    public String findByTitle(@RequestParam String name) {
        return objectMapper.writeValueAsString(movieRepo.findByTitle(name));
    }

    @Operation(summary = "поиск фильмов по году выпуска")
    @SneakyThrows
    @GetMapping("/byYear")
    public String findByYear(@RequestParam int year) {
        return objectMapper.writeValueAsString(movieRepo.findByYear(year));
    }

    @Operation(summary = "поиск фильмов по жанру")
    @SneakyThrows
    @GetMapping("/byGenre")
    public String findByGenre(@RequestParam String genre) {
        return objectMapper.writeValueAsString(movieRepo.findByGenre(genre));
    }

    @Operation(summary = "поиск фильмов по стране выпуска")
    @SneakyThrows
    @GetMapping("/byCountry")
    public String findByCountry(@RequestParam String country) {
        return objectMapper.writeValueAsString(movieRepo.findByCountry(country));
    }

    @Operation(summary = "удаление фильма", description = "Находит фильм по названию и году выпуска, после чего происходит удаление")
    @DeleteMapping("/delete")
    public void deleteMovie(@RequestParam String name, int year) {
        Movie movie = movieRepo.findToDelete(name, year);
        log.info("Delete row: " + movie);
        movieRepo.delete(movie);
    }
}
