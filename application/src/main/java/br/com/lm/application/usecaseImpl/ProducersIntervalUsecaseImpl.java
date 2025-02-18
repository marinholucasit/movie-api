package br.com.lm.application.usecaseImpl;

import br.com.domain.entity.Movie;
import br.com.lm.application.gateway.ProducersIntervalGateway;
import br.com.lm.usecase.ProducersIntervalUseCase;
import br.com.lm.usecase.model.AwardIntervalModel;
import br.com.lm.usecase.model.ProducerAwardIntervalsModel;

import java.util.*;
import java.util.stream.Collectors;

public class ProducersIntervalUsecaseImpl implements ProducersIntervalUseCase {

    final ProducersIntervalGateway producersIntervalGateway;

    public ProducersIntervalUsecaseImpl(ProducersIntervalGateway producersIntervalGateway) {
        this.producersIntervalGateway = producersIntervalGateway;
    }

    @Override
    public ProducerAwardIntervalsModel execute() {
        List<Movie> winners = producersIntervalGateway.findWinningMovies();
        Map<String, List<Integer>> producerAwards = new HashMap<>();

        for (Movie movie : winners) {
            String[] producers = movie.getProducer().split(",| and ");
            for (String producer : producers) {
                producer = producer.trim();
                producerAwards.computeIfAbsent(producer, k -> new ArrayList<>()).add(movie.getYear());
            }
        }

        List<AwardIntervalModel> allIntervals = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : producerAwards.entrySet()) {
            String producer = entry.getKey();
            List<Integer> years = entry.getValue();
            Collections.sort(years);

            if (years.size() >= 2) {
                for (int i = 1; i < years.size(); i++) {
                    int interval = years.get(i) - years.get(i - 1);
                    int previousWin = years.get(i - 1);
                    int followingWin = years.get(i);
                    allIntervals.add(new AwardIntervalModel(producer, interval, previousWin, followingWin));
                }
            }
        }

        int minInterval = allIntervals.stream().mapToInt(AwardIntervalModel::getInterval).min().orElse(0);
        int maxInterval = allIntervals.stream().mapToInt(AwardIntervalModel::getInterval).max().orElse(0);

        List<AwardIntervalModel> minIntervals = allIntervals.stream()
                .filter(dto -> dto.getInterval() == minInterval)
                .collect(Collectors.toList());

        List<AwardIntervalModel> maxIntervals = allIntervals.stream()
                .filter(dto -> dto.getInterval() == maxInterval)
                .collect(Collectors.toList());

        return new ProducerAwardIntervalsModel(minIntervals, maxIntervals);
    }
}
