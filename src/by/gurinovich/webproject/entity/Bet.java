package by.gurinovich.webproject.entity;

public class Bet {
    private double winner;
    private double top3;
    private double outsider;
    private int bookmakerID;
    private int horseID;

    public Bet(int bookmakerID, double winner, double top3, double outsider, int horseID) {
        this.bookmakerID = bookmakerID;
        this.winner = winner;
        this.top3 = top3;
        this.outsider = outsider;
        this.horseID = horseID;
    }

    public double getWinner() {
        return winner;
    }

    public void setWinner(double winner) {
        this.winner = winner;
    }

    public double getTop3() {
        return top3;
    }

    public void setTop3(double top3) {
        this.top3 = top3;
    }

    public double getOutsider() {
        return outsider;
    }

    public void setOutsider(double outsider) {
        this.outsider = outsider;
    }


    public int getBookmakerID() {
        return bookmakerID;
    }

    public void setBookmakerID(int bookmakerID) {
        this.bookmakerID = bookmakerID;
    }

    public int getHorseID() {
        return horseID;
    }

    public void setHorseID(int horseID) {
        this.horseID = horseID;
    }
}
