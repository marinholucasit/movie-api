package br.com.lm.infrastructure.service;

import br.com.domain.entity.Movie;
import br.com.lm.application.gateway.ProducersIntervalGateway;
import br.com.lm.infrastructure.Entity.MovieEntity;
import br.com.lm.infrastructure.dto.IntervalResponse;
import br.com.lm.infrastructure.mapper.MovieMapper;
import br.com.lm.infrastructure.repository.MovieRepository;
import br.com.lm.usecase.model.AwardIntervalModel;
import br.com.lm.usecase.model.ProducerAwardIntervalsModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProducersIntervalGatewayImpl implements ProducersIntervalGateway {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findWinningMovies() {
        return MovieMapper.toMovieList(movieRepository.findByWinnerTrue());
    }

}
