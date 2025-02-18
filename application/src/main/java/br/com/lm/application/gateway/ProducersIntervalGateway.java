package br.com.lm.application.gateway;

import br.com.domain.entity.Movie;
import br.com.lm.usecase.model.ProducerAwardIntervalsModel;

import java.util.List;

public interface ProducersIntervalGateway {
    List<Movie> findWinningMovies();
}
