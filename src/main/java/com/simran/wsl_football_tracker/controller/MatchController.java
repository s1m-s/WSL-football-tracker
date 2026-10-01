package com.simran.wsl_football_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simran.wsl_football_tracker.entity.Match;
import com.simran.wsl_football_tracker.repository.MatchRepository;

@RestController
public class MatchController {

    @Autowired 
    private MatchRepository matchRepository;

    @GetMapping ("/addmatch")
    
    public String addMatch() {

        Match match = new Match();

        match.setHomeTeam("Arsenal Women");
        match.setAwayTeam("Chelsea Women");
        match.setMatchDate("2026-01-01");
        match.setHomeScore(2);
        match.setAwayScore(1);
        match.setWinner("Arsenal Women");

        matchRepository.save(match);

        return "Match added successfully!";
    }
}
