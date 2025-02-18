package br.com.lm.infrastructure.controller;

import br.com.lm.infrastructure.dto.IntervalResponse;
import br.com.lm.infrastructure.mapper.ProducerAwardIntervalsMapper;
import br.com.lm.infrastructure.service.ProducersIntervalGatewayImpl;
import br.com.lm.usecase.ProducersIntervalUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private final ProducersIntervalUseCase producersIntervalUseCase;

    @GetMapping("/awards")
    public IntervalResponse getProducersIntervals() {
        return ProducerAwardIntervalsMapper.toResponse(producersIntervalUseCase.execute());
    }

}
