package br.com.lm.infrastructure.utils;

import br.com.domain.entity.Movie;
import br.com.lm.infrastructure.Entity.MovieEntity;
import br.com.lm.infrastructure.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CsvLoader {

    private final MovieRepository movieRepository;

    @PostConstruct
    public void loadMovies() {


    }

}
