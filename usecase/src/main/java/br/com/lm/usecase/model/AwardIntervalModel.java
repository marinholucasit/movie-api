package br.com.lm.usecase.model;

public class AwardIntervalModel {
    private final String producer;
    private final int interval;
    private final int previousWin;
    private final int followingWin;

    public AwardIntervalModel(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public int getInterval() {
        return interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }
}
