package br.com.lm.application.usecaseImpl;

import br.com.domain.entity.Movie;
import br.com.lm.application.gateway.ProducersIntervalGateway;
import br.com.lm.usecase.ProducersIntervalUseCase;
import br.com.lm.usecase.model.AwardIntervalModel;
import br.com.lm.usecase.model.ProducerAwardIntervalsModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProducersIntervalUsecaseImpl implements ProducersIntervalUseCase {

    final ProducersIntervalGateway producersIntervalGateway;

    public ProducersIntervalUsecaseImpl(ProducersIntervalGateway producersIntervalGateway) {
        this.producersIntervalGateway = producersIntervalGateway;
    }

    @Override
    public ProducerAwardIntervalsModel execute() {
        List<Movie> winners = producersIntervalGateway.findWinningMovies();

        Map<String, List<Integer>> producerAwards = groupAwardsData(winners);

        List<AwardIntervalModel> allIntervals = calculateIntervals(producerAwards);

        if (allIntervals.isEmpty()) {
            return new ProducerAwardIntervalsModel(Collections.emptyList(), Collections.emptyList());
        }

        int minInterval = allIntervals.stream().mapToInt(AwardIntervalModel::getInterval).min().orElseThrow();
        int maxInterval = allIntervals.stream().mapToInt(AwardIntervalModel::getInterval).max().orElseThrow();

        return new ProducerAwardIntervalsModel(
                filterIntervals(allIntervals, minInterval),
                filterIntervals(allIntervals, maxInterval)
        );
    }

    private Map<String, List<Integer>> groupAwardsData(List<Movie> winners) {
        return winners.stream()
                .flatMap(movie -> groupProducers(movie.getProducer()).stream()
                        .map(producer -> new AbstractMap.SimpleEntry<>(producer, movie.getYear())))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private List<String> groupProducers(String producers) {
        return Arrays.stream(producers.split(",| and "))
                .map(String::trim)
                .filter(producer -> !producer.isEmpty())
                .collect(Collectors.toList());
    }

    private List<AwardIntervalModel> calculateIntervals(Map<String, List<Integer>> producerAwards) {
        return producerAwards.entrySet().stream()
                .flatMap(entry -> {
                    List<Integer> years = entry.getValue().stream().sorted().toList();
                    return IntStream.range(1, years.size())
                            .mapToObj(i -> new AwardIntervalModel(entry.getKey(),
                                    years.get(i) - years.get(i - 1),
                                    years.get(i - 1),
                                    years.get(i)));
                })
                .collect(Collectors.toList());
    }

    private List<AwardIntervalModel> filterIntervals(List<AwardIntervalModel> intervals, int targetInterval) {
        return intervals.stream()
                .filter(interval -> interval.getInterval() == targetInterval)
                .toList();
    }
}
