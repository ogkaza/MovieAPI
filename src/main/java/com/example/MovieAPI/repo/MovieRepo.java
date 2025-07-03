package com.example.MovieAPI.repo;

import com.example.MovieAPI.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT * FROM movies WHERE name = :name", nativeQuery = true)
    List<Movie> findByTitle(@Param("name") String name);

    @Query(value = "SELECT * FROM movies WHERE year_release = :yearRelease", nativeQuery = true)
    List<Movie> findByYear(@Param("yearRelease") int yearRelease);

    @Query(value = "SELECT * FROM movies WHERE genre = :genre", nativeQuery = true)
    List<Movie> findByGenre(@Param("genre") String genre);

    @Query(value = "SELECT * FROM movies WHERE country = :country", nativeQuery = true)
    List<Movie> findByCountry(@Param("country") String country);

    @Query(value = "SELECT * FROM movies WHERE name = :name AND year_release = :yearRelease", nativeQuery = true)
    Movie findToDelete(@Param("name") String name, @Param("yearRelease") int yearRelease);
}
