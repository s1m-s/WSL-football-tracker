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

//SOAP endpoint that recieves match data from the python command line client as stores it in postgresql 
@Endpoint

public class MatchEndpoint {

    //provides access to match dat stored in postgresql
    @Autowired
    private MatchRepository matchRepository;

    //recieves SOAP request containing match information
    @PayloadRoot(namespace = "http://wslfootballtracker.com", localPart = "MatchRequest")

    //processes a matc hthat has been subitted by the python client and calculated the winner ad saves the result 
    @ResponsePayload
    public MatchResponse saveMatch(
        @RequestPayload MatchRequest request) {
            
        //create match entity using the values recieved fro mthe SOAP request 
        Match match = new Match();

        //stores submitted home and away teams 
        match.setHomeTeam(request.getHomeTeam());
        match.setAwayTeam(request.getAwayTeam());
        //stores te submitted scores 
        match.setHomeScore(request.getHomeScore());
        match.setAwayScore(request.getAwayScore());

        //automatically calculate the winner, winner = highest score, equal scores result in draw
        String winner;
        if(request.getHomeScore() > request.getAwayScore()) {
            winner = request.getHomeTeam();
        } else if (request.getHomeScore() < request.getAwayScore()) {
            winner = request.getAwayTeam();
        } else {
            winner = "Draw";
        }

        //sve calculated winner
        match.setWinner(winner);

        //save the ompleted matc hrecord to postgresql
        matchRepository.save(match);

        //create SOAP response that is returned to hte python client 
        MatchResponse response = new MatchResponse();
        //inform client 
        response.setMessage("Match saved successfully!");
        //send SOAP response bac kto the python client 
        return response;
    }
    
}
