package by.gurinovich.webproject.entity;

public class Bookmaker extends Person {
    private int bookmakerID;
    public Bookmaker(String username, String password, String firstName, String secondName, String email, int bookmakerID) {
        super(username, password, firstName, secondName, email);
        this.bookmakerID = bookmakerID;
    }

    public int getBookmakerID() {
        return bookmakerID;
    }

    public void setBookmakerID(int bookmakerID) {
        this.bookmakerID = bookmakerID;
    }
}
