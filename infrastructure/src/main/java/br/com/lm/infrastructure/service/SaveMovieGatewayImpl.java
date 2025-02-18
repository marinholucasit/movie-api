package br.com.lm.infrastructure.service;

import br.com.domain.entity.Movie;
import br.com.lm.application.gateway.SaveMovieGateway;
import br.com.lm.infrastructure.mapper.MovieMapper;
import br.com.lm.infrastructure.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.lm.infrastructure.utils.LogUtil.log;

@Service
@AllArgsConstructor
public class SaveMovieGatewayImpl implements SaveMovieGateway {

    private final MovieRepository movieRepository;

    @Override
    public Boolean saveAll(List<Movie> movies) {
        try {
            log.info("Start save list of movie::SaveMovieGatewayImpl");
            movieRepository.saveAll(MovieMapper.toMovieEntityList(movies));
            log.info("Movies saved with success::SaveMovieGatewayImpl");
            return true;
        } catch (Exception e) {
            log.info("Error for save movie::SaveMovieGatewayImpl");
            return  false;
        }
    }
}
