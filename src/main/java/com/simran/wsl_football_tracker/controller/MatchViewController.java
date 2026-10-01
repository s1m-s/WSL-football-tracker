package com.simran.wsl_football_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import com.simran.wsl_football_tracker.entity.Match;
import com.simran.wsl_football_tracker.repository.MatchRepository;

@RestController

public class MatchViewController {
    @Autowired 
    private MatchRepository matchRepository;

    @GetMapping ("/matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    } 
}
