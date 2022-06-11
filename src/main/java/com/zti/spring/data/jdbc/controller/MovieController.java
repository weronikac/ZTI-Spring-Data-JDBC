package com.zti.spring.data.jdbc.controller;

import com.zti.spring.data.jdbc.model.Movie;
import com.zti.spring.data.jdbc.model.Genre;
import com.zti.spring.data.jdbc.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static java.util.Arrays.asList;

@Log4j2
@Transactional 
@RestController
@RequiredArgsConstructor
class MovieController {

    final MovieRepository movies;

    @DeleteMapping("/movie/{id}")
    public CompletableFuture<String> deleteMovie(@PathVariable("id") Long id) {
        return CompletableFuture.supplyAsync(() -> {
            movies.findById(id)
                    .map(Movie -> {
                        log.info("going to remove a movie: {}", Movie);
                        return Movie;
                    })
                    .ifPresent(movies::delete);
            return "Accepted.";
        });
    }

    @GetMapping("/movie/{id}")
    public Movie getmovie(@PathVariable("id") Long id) {
        return movies.findById(id).orElse(null);
    }

    @GetMapping("/movies")
    public Iterable<Movie> getMovies() {
        return movies.findAll();
    }

    @PostMapping
    public CompletableFuture<Iterable<Movie>> post(@RequestBody Map<String, String> request) {
        return CompletableFuture.supplyAsync(() -> {
            String content = Objects.requireNonNull(request.get("content"), "content parameter is required.");
            Movie movie1 = movies.save(Movie.of(content));
            log.info("created movie without genre: {}", movie1);
            Movie movie2 = movies.save(Movie.of(content, Genre.UNDEFINED));
            log.info("created movie with genre: {}", movie2);
            return asList(movie1, movie2);
        });
    }

    @RequestMapping
    public ResponseEntity<List> apiFallback() {
        return ResponseEntity.ok(
                asList(
                        "   get movies:  http get    :8080/movies",
                        "   find movie:  http get    :8080/movie/{id}",
                        " delete movie:  http delete :8080/movie/{id}",
                        " create movie:  http post   :8080 content={content}"
                )
        );
    }
}