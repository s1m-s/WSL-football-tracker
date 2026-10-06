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


@Controller
public class HomeController {
    @Autowired 
    private TeamRepository teamRepository;

    @Autowired 
    private MatchRepository matchRepository;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
            "teams",
            teamRepository.findAll());

        List<LeagueTableEntry> leagueTable = new ArrayList<>();

        for (Team team : teamRepository.findAll()) {

            LeagueTableEntry entry = new LeagueTableEntry();

            entry.setTeamName(team.getName());

            int played = 0;
            int won = 0; 
            int drawn = 0;
            int lost = 0;
            int points = 0;

            for (Match match : matchRepository.findAll()) {

                boolean involved = match.getHomeTeam().equals(team.getName()) || match.getAwayTeam().equals(team.getName());

                if (!involved) {
                    continue;
                }

                played++;

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

        //sorting league table by points 
        leagueTable.sort(
            (a,b) ->
                Integer.compare(
                    b.getPoints(),
                    a.getPoints()));

        model.addAttribute("leagueTable", leagueTable);

        return "Home";
    }
}
