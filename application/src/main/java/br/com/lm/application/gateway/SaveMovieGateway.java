package br.com.lm.application.gateway;

import br.com.domain.entity.Movie;

import java.util.List;

public interface SaveMovieGateway {
    Boolean saveAll(List<Movie> movies);
}
