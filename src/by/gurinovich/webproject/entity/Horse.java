package by.gurinovich.webproject.entity;

import java.util.ArrayList;

public class Horse {
    private int raceId;
    private String name;
    private Bet bets;

    public Horse(int raceId, String name, Bet bets) {
        this.raceId = raceId;
        this.name = name;
        this.bets = bets;
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
}
