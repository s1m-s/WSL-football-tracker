package com.simran.wsl_football_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.simran.wsl_football_tracker.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match>findByHomeTeamOrAwayTeam(
        String homeTeam,
        String awayTeam
    );

    
}
