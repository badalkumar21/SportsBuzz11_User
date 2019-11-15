package com.sportsbuzz11.app.Model;

public class ModelSquad {

    private String name;
    private String role;
    private String team;
    private float points;
    private boolean isPlaying;
    private String ads;


    public ModelSquad() {

    }


    public ModelSquad(String name, String role, String team, float points, boolean isPlaying) {
        this.name = name;
        this.role = role;
        this.team = team;
        this.points = points;
        this.isPlaying = isPlaying;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }
}
