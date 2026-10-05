package com.simran.wsl_football_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simran.wsl_football_tracker.entity.Team;
import com.simran.wsl_football_tracker.repository.TeamRepository;

@RestController

public class TeamController {
    @Autowired 
    private TeamRepository teamRepository;

    @GetMapping ("/addteam")
    public String addTeam() {
        String[] teams = {
            "Arsenal",
            "Aston Villa",
            "Birmingham City",
            "Brighton & Hove Alblon",
            "Charlton Athletic",
            "Chelsea",
            "Crystal Palace",
            "Everton",
            "Liverpool",
            "London City Lionesses",
            "Manchester City",
            "Manchester United",
            "Tottenham Hotspur",
            "West Ham United"
        };

        for (String teamName : teams) {
            Team team = new Team();
            team.setName(teamName);
            teamRepository.save(team);

        }
  
        return "Team Saved!";
    }
}
