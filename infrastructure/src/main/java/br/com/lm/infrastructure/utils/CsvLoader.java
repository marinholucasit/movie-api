package br.com.lm.infrastructure.utils;

import br.com.domain.entity.Movie;
import br.com.domain.exception.InternalServerErrorException;
import br.com.lm.infrastructure.mapper.MovieMapper;
import br.com.lm.infrastructure.repository.MovieRepository;
import br.com.lm.usecase.SaveMovieUsecase;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CsvLoader {

    private SaveMovieUsecase saveMovieUsecase;

    @PostConstruct
    public void loadMovies() throws InternalServerErrorException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("movielist.csv");

        InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));

        BufferedReader reader = new BufferedReader(inputStreamReader);

        final List<Movie> movies = reader.lines()
                .skip(1)
                .map(line -> {
                    String[] data = line.split(";");
                    if (data.length == 5) {
                        return new Movie(Integer.parseInt(data[0]), data[1], data[2], data[3], "yes".equals(data[4]));
                    } else {
                        return new Movie(Integer.parseInt(data[0]), data[1], data[2], data[3], false);
                    }
                })
                .toList();

        saveMovieUsecase.saveAll(movies);

    }

}
