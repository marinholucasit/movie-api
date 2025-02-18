package br.com.lm.infrastructure.mapper;

import br.com.lm.infrastructure.dto.AwardInterval;
import br.com.lm.infrastructure.dto.IntervalResponse;
import br.com.lm.usecase.model.AwardIntervalModel;
import br.com.lm.usecase.model.ProducerAwardIntervalsModel;

import java.util.List;
import java.util.stream.Collectors;

public class ProducerAwardIntervalsMapper {

    public static IntervalResponse toResponse(ProducerAwardIntervalsModel intervals) {
        return new IntervalResponse(
                mapToDto(intervals.getMin()),
                mapToDto(intervals.getMax())
        );
    }

    private static List<AwardInterval> mapToDto(List<AwardIntervalModel> models) {
        return models.stream()
                .map(model -> new AwardInterval(model.getProducer(), model.getInterval(), model.getPreviousWin(), model.getFollowingWin()))
                .collect(Collectors.toList());
    }
}
