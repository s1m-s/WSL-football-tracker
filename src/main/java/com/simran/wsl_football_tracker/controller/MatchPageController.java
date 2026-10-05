package com.simran.wsl_football_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simran.wsl_football_tracker.repository.MatchRepository;

@Controller

public class MatchPageController {

    @Autowired 
    private MatchRepository matchRepository;

    @GetMapping("/results")
    public String resultsPage(Model model) {
        model.addAttribute("matches", matchRepository.findAll());

        return "matches";
    }
    
}
