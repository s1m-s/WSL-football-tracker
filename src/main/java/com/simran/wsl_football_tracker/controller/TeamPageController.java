package com.simran.wsl_football_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.simran.wsl_football_tracker.repository.TeamRepository;
import com.simran.wsl_football_tracker.repository.MatchRepository;
import com.simran.wsl_football_tracker.entity.Team;

@Controller 
public class TeamPageController {

    @Autowired 
    private TeamRepository teamRepository;

    @Autowired 
    private MatchRepository matchRepository;

    @GetMapping("/teams")
    public String teamsPage(Model model) {
        
        model.addAttribute( 
            "teams",
            teamRepository.findAll());

        return "teams";
    }

    @GetMapping ("/team/{teamId}")
    public String teamPage(
        @PathVariable Long teamId, 
        Model model) {

            Team team = teamRepository.findById(teamId).orElse(null);
            if (team==null) {
                return "redirect:/teams";
            }

        model.addAttribute("team", team);

        model.addAttribute(
            "matches",
            matchRepository.findByHomeTeamOrAwayTeam(
                team.getName(),
                team.getName()
            )
        );

        System.out.println(team.getName());

        System.out.println(
            matchRepository.findByHomeTeamOrAwayTeam(
                team.getName(),
                team.getName()
            ).size()
        );
        
        return "team";
        }
}
