package br.com.lm.infrastructure.dto;

public record AwardInterval(String producer, int interval, int previousWin, int followingWin) {}
