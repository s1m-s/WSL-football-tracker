package com.simran.wsl_football_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simran.wsl_football_tracker.repository.TeamRepository;

@Controller
public class HomeController {
    @Autowired 
    private TeamRepository teamRepository;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
            "teams",
            teamRepository.findAll());

        return "Home";
    }
}
