package com.simran.wsl_football_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MatchPageController {

    @GetMapping("/results")
    public String resultsPage() {
        return "matches";
    }
    
}
