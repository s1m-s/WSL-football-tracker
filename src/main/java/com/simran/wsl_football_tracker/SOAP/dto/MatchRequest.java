package com.simran.wsl_football_tracker.SOAP.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "MatchRequest", namespace = "http://wslfootballtracker.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name =  "",
    propOrder = {
        "homeTeam",
        "awayTeam",
        "homeScore",
        "awayScore"
    }
)

public class MatchRequest {
    
    @XmlElement (name = "homeTeam",
    namespace = "http://wslfootballtracker.com",
    required = true)
    private String homeTeam;

    @XmlElement (name = "awayTeam",
    namespace = "http://wslfootballtracker.com",
    required = true)
    private String awayTeam;

    @XmlElement (name = "homeScore",
    namespace = "http://wslfootballtracker.com",
    required = true)
    private Integer homeScore;

    @XmlElement (name = "awayScore",
    namespace = "http://wslfootballtracker.com",
    required = true)
    private Integer awayScore;


    public MatchRequest() {
    }

    public String getHomeTeam(){
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam(){
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeScore(){
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore(){
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }
}
