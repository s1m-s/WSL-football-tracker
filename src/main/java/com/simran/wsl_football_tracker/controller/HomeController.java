package com.simran.wsl_football_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HomeController {
    @GetMapping ("/")
    @ResponseBody
    public String home() {
        return "Welcome to WSL Football Tracker!";
    }
}
