package com.simran.wsl_football_tracker.entity;

public class LeagueTableEntry {

    private String teamName;

    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int points;

    public String getTeamName() {
        return teamName;
    }
    
    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public int getPlayed() {
        return played;
    }

    public void setPalyed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }
    
    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
