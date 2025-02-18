package br.com.lm.infrastructure.repository;

import br.com.lm.infrastructure.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {
    List<MovieEntity> findByWinnerTrue();
}
