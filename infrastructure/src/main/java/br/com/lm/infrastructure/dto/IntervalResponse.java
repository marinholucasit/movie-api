package br.com.lm.infrastructure.dto;

import java.util.List;

public record IntervalResponse(List<AwardInterval> min, List<AwardInterval> max){
}
