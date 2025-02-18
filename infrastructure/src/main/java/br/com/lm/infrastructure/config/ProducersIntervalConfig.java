package br.com.lm.infrastructure.config;

import br.com.lm.application.gateway.ProducersIntervalGateway;
import br.com.lm.application.usecaseImpl.ProducersIntervalUsecaseImpl;
import br.com.lm.usecase.ProducersIntervalUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducersIntervalConfig {

    @Bean
    public ProducersIntervalUseCase producersIntervalUseCase(ProducersIntervalGateway producersIntervalGateway) {
        return new ProducersIntervalUsecaseImpl(producersIntervalGateway);
    }

}
