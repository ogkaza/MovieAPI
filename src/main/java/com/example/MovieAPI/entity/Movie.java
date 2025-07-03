package com.example.MovieAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int yearRelease;
    private String country;
    private String genre;

    public Movie() {
    }

    public Movie(String name, int yearRelease, String country, String genre) {
        this.name = name;
        this.yearRelease = yearRelease;
        this.country = country;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateRelease=" + yearRelease +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearRelease(int yearRelease) throws Exception {
        if (String.valueOf(yearRelease).length() != 4) throw new Exception("YEAR IS NOT CORRECT!");
        this.yearRelease = yearRelease;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
