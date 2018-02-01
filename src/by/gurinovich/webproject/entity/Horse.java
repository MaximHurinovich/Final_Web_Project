package by.gurinovich.webproject.entity;

import java.util.ArrayList;

public class Horse {
    private int horseId;
    private int raceId;
    private String name;
    private Bet bets;
    private int place;

    public Horse(int horseId, int raceId, String name, Bet bets, int place) {
        this.horseId = horseId;
        this.raceId = raceId;
        this.name = name;
        this.bets = bets;
        this.place = place;
    }

    public Horse(int raceId, String name, Bet bets) {
        this.raceId = raceId;
        this.name = name;
        this.bets = bets;
    }

    public Horse(int raceId, String name, int place) {
        this.raceId = raceId;
        this.name = name;
        this.place = place;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bet getBets() {
        return bets;
    }

    public void setBets(Bet bets) {
        this.bets = bets;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }
}
