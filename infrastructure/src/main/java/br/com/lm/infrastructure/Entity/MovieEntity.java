package br.com.lm.infrastructure.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Column(name = "Id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "Year_movie", nullable = false)
    private int yearMovie;
    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "Studio", nullable = false)
    private String studio;
    @Column(name = "Producer", nullable = false)
    private String producer;
    @Column(name = "Winner")
    private boolean winner;
    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;
}
