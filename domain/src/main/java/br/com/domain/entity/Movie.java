package br.com.domain.entity;

import java.util.Objects;
import java.util.UUID;

public class Movie {

    private UUID id;
    private int year;
    private String title;
    private String studio;
    private String producer;
    private boolean winner;

    public Movie(int year, String title, String studio, String producer, boolean winner) {
        this.id = UUID.randomUUID();
        this.year = year;
        this.title = title;
        this.studio = studio;
        this.producer = producer;
        this.winner = winner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;
        return year == movie.year && winner == movie.winner && id.equals(movie.id) && Objects.equals(title, movie.title) && Objects.equals(studio, movie.studio) && Objects.equals(producer, movie.producer);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + year;
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(studio);
        result = 31 * result + Objects.hashCode(producer);
        result = 31 * result + Boolean.hashCode(winner);
        return result;
    }
}
