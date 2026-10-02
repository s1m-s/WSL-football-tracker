package com.simran.wsl_football_tracker.SOAP;

import org.springframework.beans.factory.annotation.Autowired;
import com.simran.wsl_football_tracker.repository.MatchRepository;

import com.simran.wsl_football_tracker.entity.Match;
import com.simran.wsl_football_tracker.SOAP.dto.MatchResponse;
import com.simran.wsl_football_tracker.SOAP.dto.MatchRequest;

public class MatchEndpoint {

    @Autowired
    private MatchRepository matchRepository;

    public MatchResponse saveMatch(MatchRequest request) {
        Match match = new Match();
        match.setHomeTeam(request.getHomeTeam());
        match.setAwayTeam(request.getAwayTeam());
        match.setHomeScore(request.getHomeScore());
        match.setAwayScore(request.getAwayScore());

        match.setWinner("to be calculated");
        match.setMatchDate("2026-01-01");

        matchRepository.save(match);

        MatchResponse response = new MatchResponse();
        response.setMessage("Match saved successfully!");
        return response;
    }
    
}
