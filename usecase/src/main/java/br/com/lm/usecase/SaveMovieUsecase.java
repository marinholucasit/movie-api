package br.com.lm.usecase;

import br.com.domain.entity.Movie;
import br.com.domain.exception.InternalServerErrorException;

import java.util.List;

public interface SaveMovieUsecase {
    void saveAll(List<Movie> movies) throws InternalServerErrorException;
}
