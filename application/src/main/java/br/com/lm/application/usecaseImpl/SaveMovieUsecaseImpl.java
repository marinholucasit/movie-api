package br.com.lm.application.usecaseImpl;

import br.com.domain.entity.Movie;
import br.com.domain.exception.InternalServerErrorException;
import br.com.domain.exception.enums.ErrorCodeEnum;
import br.com.lm.application.gateway.SaveMovieGateway;
import br.com.lm.usecase.SaveMovieUsecase;

import java.util.List;

public class SaveMovieUsecaseImpl implements SaveMovieUsecase {

    private final SaveMovieGateway saveMovieGateway;

    public SaveMovieUsecaseImpl(SaveMovieGateway saveMovieGateway) {
        this.saveMovieGateway = saveMovieGateway;
    }

    @Override
    public void saveAll(List<Movie> movies) throws InternalServerErrorException {
        if (!saveMovieGateway.saveAll(movies)) {
            throw new InternalServerErrorException(ErrorCodeEnum.F0001.getMessage(), ErrorCodeEnum.F0001.getCode());
        }
    }
}
