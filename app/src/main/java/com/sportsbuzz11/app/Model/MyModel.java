package com.sportsbuzz11.app.Model;

public class MyModel {

    private String date;
    private String type;
    private String time;
    private String id;
    private String image1;
    private String image2;
    private String t1;
    private String t2;
    private String team1;
    private String team2;
    private String url;
    private String venue;
    private String target;
    private String ads;


    public MyModel() {

    }

    public MyModel(String date, String type, String time, String id, String image1, String image2, String t1, String t2, String team1, String team2, String url, String venue, String target, String ads) {
        this.date = date;
        this.type = type;
        this.time = time;
        this.id = id;
        this.image1 = image1;
        this.image2 = image2;
        this.t1 = t1;
        this.t2 = t2;
        this.team1 = team1;
        this.team2 = team2;
        this.url = url;
        this.venue = venue;
        this.target = target;
        this.ads = ads;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }
}
