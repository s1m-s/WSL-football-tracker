package com.simran.wsl_football_tracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simran.wsl_football_tracker.repository.MatchRepository;
import com.simran.wsl_football_tracker.repository.TeamRepository;

import com.simran.wsl_football_tracker.entity.Team;
import com.simran.wsl_football_tracker.entity.Match;
import com.simran.wsl_football_tracker.entity.LeagueTableEntry;

//controls the home page and league table
//loads the teams and matches from the database and calculates the league table based on the match results
@Controller
public class HomeController {

    //Provides access to the Team table in postgreSQL
    @Autowired 
    private TeamRepository teamRepository;

    //provides access to saved match results 
    @Autowired 
    private MatchRepository matchRepository;

    //Loads the home page and sends the League table and team list to the home.html
    @GetMapping("/")
    public String home(Model model) {
        
        //retrieve all wsl teams from postgresql so they can be displayed on the hopepage
        model.addAttribute(
            "teams",
            teamRepository.findAll());
        
        //create a collection that will hold the calculated league table entries for each team 
        List<LeagueTableEntry> leagueTable = new ArrayList<>();
        
        //calculates league statistics for each team 
        for (Team team : teamRepository.findAll()) {

            LeagueTableEntry entry = new LeagueTableEntry();

            entry.setTeamName(team.getName());
            
            //varialbes used to calcualte the teams season record
            int played = 0;
            int won = 0; 
            int drawn = 0;
            int lost = 0;
            int points = 0;

            //check every saved match to see if the team was involved and update the teams record accordingly
            for (Match match : matchRepository.findAll()) {

                //check if it was the home or away team in the match
                boolean involved = match.getHomeTeam().equals(team.getName()) || match.getAwayTeam().equals(team.getName());

                if (!involved) {
                    continue;
                }

                played++;

                //award points based on the match result:
                //3 points for a win, 1 point for a draw, 0 points for a loss
                if ("Draw".equals(match.getWinner())) {

                    drawn++;
                    points++;

                } else if (match.getWinner().equals(team.getName())) {

                    won++;
                    points += 3;

                } else {

                    lost++;

                }
            }

            entry.setPlayed(played);
            entry.setWon(won);
            entry.setDrawn(drawn);
            entry.setLost(lost);
            entry.setPoints(points);

            leagueTable.add(entry);
        }

        //sorting league table by points highest to lowest so the team with the most points is at the top of the table
        leagueTable.sort(
            (a,b) ->
                Integer.compare(
                    b.getPoints(),
                    a.getPoints()));

        //send the league table to the home.html page so it can be displayed
        model.addAttribute("leagueTable", leagueTable);

        //render the homepage
        return "Home";
    }
}
