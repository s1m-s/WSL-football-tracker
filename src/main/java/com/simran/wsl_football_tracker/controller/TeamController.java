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
        Team team = new Team();
        team.setName("Arsenal Women");
        teamRepository.save(team);
        return "Team added successfully!";
    }
}
