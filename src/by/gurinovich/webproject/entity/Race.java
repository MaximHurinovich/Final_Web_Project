package by.gurinovich.webproject.entity;

import java.util.ArrayList;

public class Race {
    private String card;
    private String date;
    private ArrayList<Horse> horses;

    public Race(String card, String date, ArrayList<Horse> horses) {
        this.card = card;
        this.date = date;
        this.horses = horses;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public void setHorses(ArrayList<Horse> horses) {
        this.horses = horses;
    }


}
