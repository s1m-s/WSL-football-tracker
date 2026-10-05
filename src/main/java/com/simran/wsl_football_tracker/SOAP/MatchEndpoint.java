package com.simran.wsl_football_tracker.SOAP;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.springframework.beans.factory.annotation.Autowired;
import com.simran.wsl_football_tracker.repository.MatchRepository;

import com.simran.wsl_football_tracker.entity.Match;
import com.simran.wsl_football_tracker.SOAP.dto.MatchResponse;
import com.simran.wsl_football_tracker.SOAP.dto.MatchRequest;

@Endpoint

public class MatchEndpoint {

    @Autowired
    private MatchRepository matchRepository;

    @PayloadRoot(namespace = "http://wslfootballtracker.com", localPart = "MatchRequest")
    @ResponsePayload
    public MatchResponse saveMatch(
        @RequestPayload MatchRequest request) {
        System.out.println("REQUEST CLASS = " + request.getClass());
        System.out.println("REQUEST = " + request);
        Match match = new Match();
        System.out.println("HOME TEAM = " + request.getHomeTeam());
        System.out.println("AWAY TEAM = " + request.getAwayTeam());
        System.out.println("HOME SCORE = " + request.getHomeScore());
        System.out.println("AWAY SCORE = " + request.getAwayScore());
        match.setHomeTeam(request.getHomeTeam());
        match.setAwayTeam(request.getAwayTeam());
        match.setHomeScore(request.getHomeScore());
        match.setAwayScore(request.getAwayScore());

        String winner;
        if(request.getHomeScore() > request.getAwayScore()) {
            winner = request.getHomeTeam();
        } else if (request.getHomeScore() < request.getAwayScore()) {
            winner = request.getAwayTeam();
        } else {
            winner = "Draw";
        }


        match.setWinner(winner);

        matchRepository.save(match);

        MatchResponse response = new MatchResponse();
        response.setMessage("Match saved successfully!");
        return response;
    }
    
}
