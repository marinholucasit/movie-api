package br.com.lm.infrastructure.mapper;

import br.com.domain.entity.Movie;
import br.com.lm.infrastructure.Entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public static Movie toMovie(MovieEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Movie(
                entity.getYearMovie(),
                entity.getTitle(),
                entity.getStudio(),
                entity.getProducer(),
                entity.isWinner()
        );
    }

    public static MovieEntity toMovieEntity(Movie movie) {
        if (movie == null) {
            return null;
        }
        return new MovieEntity(
                movie.getId() != null ? movie.getId() : UUID.randomUUID(),
                movie.getYear(),
                movie.getTitle(),
                movie.getStudio(),
                movie.getProducer(),
                movie.isWinner(),
                LocalDateTime.now(),
                null
        );
    }

    public static List<Movie> toMovieList(List<MovieEntity> entities) {
        return entities.stream()
                .map(MovieMapper::toMovie)
                .collect(Collectors.toList());
    }

    public static List<MovieEntity> toMovieEntityList(List<Movie> movies) {
        return movies.stream()
                .map(MovieMapper::toMovieEntity)
                .collect(Collectors.toList());
    }
}
