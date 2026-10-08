package com.simran.wsl_football_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.simran.wsl_football_tracker.repository.TeamRepository;
import com.simran.wsl_football_tracker.repository.MatchRepository;
import com.simran.wsl_football_tracker.entity.Team;

//controls the team page and displays the team information and match results that they have been involved in
@Controller 
public class TeamPageController {

    //provides access to the team table in postgresql 
    @Autowired 
    private TeamRepository teamRepository;

    //provides access to saved match data 
    @Autowired 
    private MatchRepository matchRepository;

    //loads all the teams and displayes them as clickable links on the teams page (homepage)
    @GetMapping("/teams")
    public String teamsPage(Model model) {
        
        //retrieves every team from postgresql
        model.addAttribute( 
            "teams",
            teamRepository.findAll());
        
        //sends tea list to teams.html
        return "teams";
    }

    //loads a specific team page using the teams ID
    @GetMapping ("/team/{teamId}")
    public String teamPage(
        @PathVariable Long teamId, 
        Model model) {

            //retrieve the selected team from postgresql
            Team team = teamRepository.findById(teamId).orElse(null);

            //if the team cant be found then return the user too the homepage
            if (team==null) {
                return "redirect:/teams";
            }

        //sends tea mdetails to team.html
        model.addAttribute("team", team);

        model.addAttribute(
            "matches",
            //find all matches where this team has appeared as either home or away  team 
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
        
        //displayes the selected teams results page 
        return "team";
        }
}
