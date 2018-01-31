package by.gurinovich.webproject.entity;

import by.gurinovich.webproject.dao.AuthenticationDAO;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String cardNumber;
    private String email;
    private double amount;



    public User(String username, String password, String firstName, String secondName, String cardNumber, String email, double amount) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.cardNumber = cardNumber;
        this.email = email;

        this.amount = amount;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public double getAmount() {
        return amount;
    }
    public String getPassword(){ return password;}
}
