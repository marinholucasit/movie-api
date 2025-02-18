package br.com.lm.usecase.model;

import java.util.List;

public class ProducerAwardIntervalsModel {
    private final List<AwardIntervalModel> min;
    private final List<AwardIntervalModel> max;

    public ProducerAwardIntervalsModel(List<AwardIntervalModel> min, List<AwardIntervalModel> max) {
        this.min = min;
        this.max = max;
    }

    public List<AwardIntervalModel> getMin() {
        return min;
    }

    public List<AwardIntervalModel> getMax() {
        return max;
    }
}
