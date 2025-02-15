package br.com.lm.infrastructure.config;

import br.com.lm.application.gateway.SaveMovieGateway;
import br.com.lm.application.usecaseImpl.SaveMovieUsecaseImpl;
import br.com.lm.usecase.SaveMovieUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MovieConfig {

    @Bean
    public SaveMovieUsecase saveMovieUsecase(SaveMovieGateway saveMovieGateway) {
        return new SaveMovieUsecaseImpl(saveMovieGateway);
    }
}
