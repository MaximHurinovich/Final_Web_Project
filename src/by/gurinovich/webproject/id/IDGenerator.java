package by.gurinovich.webproject.id;

public class IDGenerator {
    private static int id = 1000;
    public static int generateID(){
        return id++;
    }
}
